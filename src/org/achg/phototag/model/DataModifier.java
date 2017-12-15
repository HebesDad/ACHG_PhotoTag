package org.achg.phototag.model;

import java.util.ArrayList;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class DataModifier {

	public void addCategory(Shell shell,TagCategory cat)
	{
		ArrayList<TagCategory> cats = new ArrayList<>();
		for (TagCategory old : ModelManager.getInstance().getModel().getTagCategories()) {

			if (old.getName().toLowerCase().equals(cat.getName().toLowerCase())) {
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}
			cats.add(old);
		}
		cats.add(cat);
		ModelManager.getInstance().getModel().setTagCategories(cats.toArray(new TagCategory[cats.size()]));
		ModelManager.getInstance().notifyModelContentChangeListeners();
	}
	
	public void addTag(Shell shell,TagCategory cat,Tag newtag)
	{
		ArrayList<Tag> tags = new ArrayList<>();
		for (Tag old : cat.getTags()) {
			if (old.getName().toLowerCase().equals(newtag.getName().toLowerCase())) {
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}
			tags.add(old);
		}
		tags.add(newtag);

		cat.setTags(tags.toArray(new Tag[tags.size()]));

		
		ModelManager.getInstance().notifyModelContentChangeListeners();
	}
	
	public void addSubTag(Shell shell,Tag parent, Tag newtag)
	{
		ArrayList<Tag> subtags = new ArrayList<>();
		for (Tag old : parent.getSubTag()) {
			if (old.getName().toLowerCase().equals(newtag.getName().toLowerCase())) {
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}
			subtags.add(old);
		}
		subtags.add(newtag);

		parent.setSubTag(subtags.toArray(new Tag[subtags.size()]));

		
		ModelManager.getInstance().notifyModelContentChangeListeners();
	}
	
	public void addValue(Shell shell, TagValue value)
	{
		ArrayList<TagValue> values = new ArrayList<>();
		for (TagValue old : ModelManager.getInstance().getModel().getValues()) {
			if (old.getTag() == value.getTag() && old.getSubTag() == value.getSubTag()) {
				if (old.getValue().toLowerCase().equals(value.getValue().toLowerCase())) {
					MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
					return;
				}
			}
			values.add(old);
		}
		values.add(value);

		ModelManager.getInstance().getModel().setValues(values.toArray(new TagValue[values.size()]));

		
		ModelManager.getInstance().notifyModelContentChangeListeners();
	}

	
}
