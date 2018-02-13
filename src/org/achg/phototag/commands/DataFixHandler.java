package org.achg.phototag.commands;

import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;
import org.achg.phototag.model.DataChanger;
import org.achg.phototag.model.IFolderVisitor;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.views.ConsoleView;
import org.achg.phototag.views.components.ImagesModelLabelProvider;
import org.achg.phototag.views.components.ModelLabelProvider;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Data Fix handler
 * 
 * @author cdr
 *
 */
public class DataFixHandler
{
	private ImagesModelLabelProvider _tagValueLabelProvider = new ImagesModelLabelProvider();
	private ConsoleView _consoleView = null;
	private ModelLabelProvider _modelLabelProvider = new ModelLabelProvider();

	/**
	 * Handle data fix request
	 * 
	 * @param partService the part service
	 * 
	 */
	@Execute
	public void handle(EPartService partService)
	{
		MPart mpart = partService.findPart("org.achg.phototag.part.consoleView");
		if(mpart != null)
		{
			_consoleView = (ConsoleView)mpart.getObject();
		}
		new DataFixJob().schedule();
	}

	// private void findUnusedTagValues()
	// {
	// _consoleView.addMessage("Looking for unreferenced values");
	// List<TagValue> values = innerFindUnusedTagValues();
	// for(TagValue value : values)
	// {
	// ModelManager.getInstance().getModel().getValuesList().remove(value);
	// String description = "Removing unused value: " + _tagValueLabelProvider.getText(value);
	// _consoleView.addMessage(description);
	// }
	// _consoleView.addMessage("Looking for unreferenced values - FINISHED");
	// }

	private void fixTrimmableValues()
	{
		if(_consoleView != null)
			_consoleView.addMessage("Looking for untrimmed values");
		List<TagValue> candidates = new ArrayList<>(ModelManager.getInstance().getModel().getValuesList());
		for(TagValue value : candidates)
		{
			boolean isTrimmable = false;

			String trimmedValue = value.getValue().trim();
			isTrimmable |= !trimmedValue.equals(value.getValue());
			String trimmedSubValue = null;
			if(value.getSubTag() != null && value.getSubValue() != null)
			{
				trimmedSubValue = value.getSubValue().trim();
				isTrimmable |= !trimmedSubValue.equals(value.getSubValue());
			}

			if(isTrimmable)
			{
				if(_consoleView != null)
					_consoleView.addMessage("Trimming: " + _tagValueLabelProvider.getText(value));
				TagValue trimmed = ModelManager.getInstance().findValue(value.getTag(),
						trimmedSubValue == null || trimmedSubValue.isEmpty() ? null : value.getSubTag(), trimmedValue,
						trimmedSubValue == null || trimmedSubValue.isEmpty() ? null : trimmedSubValue);
				TagValue replacement = PhotoTagModelFactory.eINSTANCE.createTagValue();
				if(trimmedSubValue != null && !trimmedSubValue.isEmpty())
				{
					DataChanger.getInstance().setTagValue(replacement, value.getTag(), value.getSubTag());
				}
				DataChanger.getInstance().setTagValueValues(replacement, trimmedValue, trimmedSubValue);

				if(trimmed != null)
				{
					// need to go off and replace TagValue objects
					ModelManager.getInstance().visitFolders(new TagValueSwitchingVisitor(value, trimmed));
				}
				else
				{
					ModelManager.getInstance().visitFolders(new TagValueSwitchingVisitor(value, replacement));
					DataChanger.getInstance().addTagValue(replacement, true);

				}
				DataChanger.getInstance().removeTagValue(value);

			}
		}
		if(_consoleView != null)
			_consoleView.addMessage("Looking for untrimmed values - FINISHED");
	}

	private void fixDuplicateTagValues()
	{
		if(_consoleView != null)
			_consoleView.addMessage("Looking for duplicate values");

		List<TagValue> values = new ArrayList<>(ModelManager.getInstance().getModel().getValuesList());

		for(TagValue value1 : values)
		{
			if(ModelManager.getInstance().getModel().getValuesList().contains(value1))
			{
				List<TagValue> allMatching = ModelManager.getInstance().findValues(value1.getTag(), value1.getSubTag(), value1.getValue(),
						value1.getSubValue());
				if(allMatching.size() > 1)
				{
					allMatching.remove(value1);

					for(TagValue doomed : allMatching)
					{
						if(_consoleView != null)
							_consoleView.addMessage("trashing duplicate: " + _tagValueLabelProvider.getText(doomed));
						ModelManager.getInstance().visitFolders(new TagValueSwitchingVisitor(doomed, value1));
						DataChanger.getInstance().removeTagValue(doomed);

					}
				}
			}
		}

		if(_consoleView != null)
			_consoleView.addMessage("Looking for duplicate values - FINISHED");
	}

	private void fixSpaceyTags()
	{
		if(_consoleView != null)
			_consoleView.addMessage("Looking for spacey tags");
		for(TagCategory tagCat : ModelManager.getInstance().getModel().getTagCategoriesList())
		{
			DataChanger.getInstance().setCategoryName(tagCat, tagCat.getName().trim());

			for(Tag tag : tagCat.getTagsList())
			{
				if(!tag.getName().equals(tag.getName().trim()))
				{
					if(_consoleView != null)
						_consoleView.addMessage("Fixing spacey tag: " + _modelLabelProvider.getText(tag));
					DataChanger.getInstance().setTagName(tag, tag.getName().trim());

				}
				for(Tag subtag : tag.getSubTagList())
				{
					if(!subtag.getName().equals(subtag.getName().trim()))
					{
						if(_consoleView != null)
							_consoleView.addMessage("Fixing spacey tag: " + _modelLabelProvider.getText(subtag));
						DataChanger.getInstance().setTagName(subtag, subtag.getName().trim());
					}
				}
			}
		}
		if(_consoleView != null)
			_consoleView.addMessage("Looking for spacey tags - FINISHED");
	}

	// private List<TagValue> innerFindUnusedTagValues()
	// {
	// List<TagValue> result = new ArrayList<>();
	// UsedTagValueVisitor visitor = new UsedTagValueVisitor();
	// for(TagValue value : ModelManager.getInstance().getModel().getValuesList())
	// {
	//
	// visitor.setTarget(value);
	// ModelManager.getInstance().visitFolders(visitor);
	// if(!visitor.getFound())
	// {
	// result.add(value);
	// }
	// }
	// return result;
	// }

	private class DataFixJob extends Job
	{

		public DataFixJob()
		{
			super("Data Fix Job");

		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			// findUnusedTagValues();
			fixTrimmableValues();
			// TODO look for duplicates
			fixSpaceyTags();
			fixDuplicateTagValues();
			return Status.OK_STATUS;
		}

	}

	private class TagValueSwitchingVisitor implements IFolderVisitor
	{
		private TagValue _original;
		private TagValue _replacement;

		public TagValueSwitchingVisitor(TagValue original, TagValue replacement)
		{
			_original = original;
			_replacement = replacement;
		}

		@Override
		public boolean visitFolder(Folder folder)
		{
			for(Image img : folder.getImagesList())
			{
				considerImage(img);
			}
			return true;
		}

		private void considerImage(Image img)
		{
			if(img.getTagValuesList().contains(_original))
			{
				DataChanger.getInstance().addImageValue(img, _replacement, true);
				DataChanger.getInstance().removeImageValue(img, _original, true);

				for(TagValueCoordinate coord : img.getTagValueCoordinatesList())
				{
					if(coord.getTagValue() == _original)
						DataChanger.getInstance().setCoordinateValue(coord, _replacement);

				}
			}
		}
	}

	// private class UsedTagValueVisitor implements IFolderVisitor
	// {
	// private boolean _found = false;
	// private TagValue _target;
	//
	// public void setTarget(TagValue target)
	// {
	// _target = target;
	// _found = false;
	// }
	//
	// public boolean getFound()
	// {
	// return _found;
	// }
	//
	// @Override
	// public boolean visitFolder(Folder folder)
	// {
	//
	// for(Image img : folder.getImagesList())
	// {
	// if(img.getTagValuesList().contains(_target))
	// {
	// _found = true;
	// break;
	// }
	// }
	//
	// return !_found;
	// }
	//
	// }
}
