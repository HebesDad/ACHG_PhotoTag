package org.achg.phototag.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Modify model data
 */
public class DataModifier
{

	/**
	 * Add a tag category
	 * 
	 * @param shell the main shell
	 * @param newCategory the new tag category
	 */
	public void addCategory(Shell shell, TagCategory newCategory)
	{
		ModelManager manager = ModelManager.getInstance();

		List<TagCategory> categories = new ArrayList<>();
		for(TagCategory oldCategory : manager.getModel().getTagCategories())
		{
			if(oldCategory.getName().equalsIgnoreCase(newCategory.getName()))
			{
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}
			categories.add(oldCategory);
		}
		categories.add(newCategory);
		manager.getModel().setTagCategories(categories.toArray(new TagCategory[categories.size()]));
		manager.notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_CATEGORY));
	}

	/**
	 * Add a tag
	 * 
	 * @param shell the main shell
	 * @param category the tag category
	 * @param newTag the new tag
	 */
	public void addTag(Shell shell, TagCategory category, Tag newTag)
	{
		List<Tag> tags = new ArrayList<>();
		for(Tag oldTag : category.getTags())
		{
			if(oldTag.getName().equalsIgnoreCase(newTag.getName()))
			{
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}
			tags.add(oldTag);
		}
		tags.add(newTag);

		category.setTags(tags.toArray(new Tag[tags.size()]));

		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_TAG));
	}

	/**
	 * Add a sub-tag
	 * 
	 * @param shell the main shell
	 * @param parentTag the parent tag
	 * @param newSubTag the new sub-tag
	 */
	public void addSubTag(Shell shell, Tag parentTag, Tag newSubTag)
	{
		List<Tag> subtags = new ArrayList<>();
		for(Tag oldSubTag : parentTag.getSubTag())
		{
			if(oldSubTag.getName().equalsIgnoreCase(newSubTag.getName()))
			{
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}
			subtags.add(oldSubTag);
		}
		subtags.add(newSubTag);

		parentTag.setSubTag(subtags.toArray(new Tag[subtags.size()]));

		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_SUBTAG));
	}

	/**
	 * Add a tag value
	 * 
	 * @param shell the main shell
	 * @param newValue the new value
	 */
	public void addValue(Shell shell, TagValue newValue)
	{
		ModelManager manager = ModelManager.getInstance();

		List<TagValue> values = new ArrayList<>();
		for(TagValue oldValue : manager.getModel().getValues())
		{
			if(oldValue.getTag() == newValue.getTag() && oldValue.getSubTag() == newValue.getSubTag())
			{
				if(oldValue.getValue().equalsIgnoreCase(newValue.getValue()))
				{
					MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
					return;
				}
			}
			values.add(oldValue);
		}
		values.add(newValue);

		manager.getModel().setValues(values.toArray(new TagValue[values.size()]));

		manager.notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_VALUE));
	}

}
