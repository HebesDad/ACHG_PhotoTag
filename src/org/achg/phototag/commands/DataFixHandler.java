package org.achg.phototag.commands;

import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.model.IFolderVisitor;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.views.ConsoleView;
import org.achg.phototag.views.components.ImagesModelLabelProvider;
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
		findUnusedTagValues();
	}

	private void findUnusedTagValues()
	{

		List<TagValue> values = innerFindUnusedTagValues();
		for(TagValue value : values)
		{
			ModelManager.getInstance().getModel().getValuesList().remove(value);
			String description = "Removing unused value: " + _tagValueLabelProvider.getText(value);
			_consoleView.addMessage(description);
		}

	}

	private List<TagValue> innerFindUnusedTagValues()
	{
		List<TagValue> result = new ArrayList<>();
		UsedTagValueVisitor visitor = new UsedTagValueVisitor();
		for(TagValue value : ModelManager.getInstance().getModel().getValuesList())
		{
			visitor.setTarget(value);
			ModelManager.getInstance().visitFolders(visitor);
			if(!visitor.getFound())
			{
				result.add(value);
			}
		}
		return result;
	}

	private class UsedTagValueVisitor implements IFolderVisitor
	{
		private boolean _found = false;
		private TagValue _target;

		public void setTarget(TagValue target)
		{
			_target = target;
			_found = false;
		}

		public boolean getFound()
		{
			return _found;
		}

		@Override
		public boolean visitFolder(Folder folder)
		{

			for(Image img : folder.getImagesList())
			{
				if(img.getTagValuesList().contains(_target))
				{
					_found = true;
					break;
				}
			}

			return !_found;
		}

	}
}
