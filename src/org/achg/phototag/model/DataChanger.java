package org.achg.phototag.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;

/**
 * Class to do all the data modification - making it easier to implement model delta tracking
 * 
 * @author cdr
 *
 */
public class DataChanger
{
	private static DataChanger _instance = null;

	private DataChanger()
	{

	}

	/**
	 * 
	 * @return the singleton
	 */
	public synchronized static DataChanger getInstance()
	{
		if(_instance == null)
			_instance = new DataChanger();
		return _instance;
	}

	/**
	 * Set the tags within a TagValue object
	 * 
	 * @param subject the TagValue to alter
	 * @param mainTag the new tag
	 * @param subTag the new sub tag
	 */
	public void setTagValue(TagValue subject, Tag mainTag, Tag subTag)
	{
		subject.setTag(mainTag);
		subject.setSubTag(subTag);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_VALUE));

	}

	/**
	 * Set the values within a TagValue object
	 * 
	 * @param subject the TagValue
	 * @param mainTag main tag value
	 * @param subTag sub-tag value
	 */
	public void setTagValueValues(TagValue subject, String mainTag, String subTag)
	{
		subject.setValue(mainTag);
		subject.setSubValue(subTag);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_VALUE));

	}

	/**
	 * Remove a TagValue from the model
	 * 
	 * @param victim the one to remove
	 */
	public void removeTagValue(TagValue victim)
	{
		ModelManager.getInstance().getModel().getValuesList().remove(victim);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.REMOVE_VALUE));

	}

	/**
	 * Add a TagValue to the model
	 * 
	 * @param subject the one to add
	 */
	public void addTagValue(TagValue subject)
	{
		ModelManager.getInstance().getModel().getValuesList().add(subject);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_VALUE));

	}

	/**
	 * Set the name on a category
	 * 
	 * @param category the object to modify
	 * @param name the new name
	 */
	public void setCategoryName(TagCategory category, String name)
	{
		category.setName(name);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_CATEGORY));

	}

	/**
	 * Set the name on a tag object
	 * 
	 * @param subject the tag to alter
	 * @param name the new name
	 */
	public void setTagName(Tag subject, String name)
	{
		subject.setName(name);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_TAG));

	}

	/**
	 * Add a value to an image
	 * 
	 * @param img the image
	 * @param value the new value
	 */
	public void addImageValue(Image img, TagValue value)
	{
		img.getTagValuesList().add(value);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));

	}

	/**
	 * Remove a value from an image
	 * 
	 * @param img the image
	 * @param value the victim
	 */
	public void removeImageValue(Image img, TagValue value)
	{
		img.getTagValuesList().remove(value);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));

	}

	/**
	 * Set the TagValue within a coordinates object
	 * 
	 * @param coord the coordinates object
	 * @param value the new value
	 */
	public void setCoordinateValue(TagValueCoordinate coord, TagValue value)
	{
		coord.setTagValue(value);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));

	}

	/**
	 * Add a new category
	 * 
	 * @param category the new one
	 */
	public void addCategory(TagCategory category)
	{
		List<TagCategory> categories = new ArrayList<>();
		for(TagCategory oldCategory : ModelManager.getInstance().getModel().getTagCategories())
		{
			categories.add(oldCategory);
		}
		categories.add(category);
		ModelManager.getInstance().getModel().setTagCategories(categories.toArray(new TagCategory[categories.size()]));
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_CATEGORY));
	}

	/**
	 * Add a tag to a category
	 * 
	 * @param category
	 * @param tag
	 */
	public void addTag(TagCategory category, Tag tag)
	{
		category.getTagsList().add(tag);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_TAG));

	}

	/**
	 * Add a (sub)tag to a tag
	 * 
	 * @param owner
	 * @param tag
	 */
	public void addTag(Tag owner, Tag tag)
	{
		owner.getSubTagList().add(tag);
		ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_TAG));

	}

}
