package org.achg.phototag.model;

import java.util.Collections;

import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
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
	 * @param categoryName the new name
	 */
	public void addCategory(Shell shell, String categoryName)
	{
		ModelManager manager = ModelManager.getInstance();

		for(TagCategory oldCategory : manager.getModel().getTagCategories())
		{
			if(oldCategory.getName().equalsIgnoreCase(categoryName))
			{
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}

		}
		TagCategory category = PhotoTagModelFactory.eINSTANCE.createTagCategory();
		category.setName(categoryName);

		DataChanger.getInstance().addCategory(category, true);

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

		for(Tag oldTag : category.getTags())
		{
			if(oldTag.getName().equalsIgnoreCase(newTag.getName()))
			{
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}

		}
		DataChanger.getInstance().addTag(category, newTag, true);

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

		for(Tag oldSubTag : parentTag.getSubTag())
		{
			if(oldSubTag.getName().equalsIgnoreCase(newSubTag.getName()))
			{
				MessageDialog.openInformation(shell, "Duplicate", "Creating this item would create a duplicate");
				return;
			}

		}

		DataChanger.getInstance().addTag(parentTag, newSubTag, true);

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

		}

		DataChanger.getInstance().addTagValue(newValue, true);
	}
}
