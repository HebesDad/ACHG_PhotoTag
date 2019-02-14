package org.achg.phototag.model;

import java.util.Collections;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;
import org.eclipse.emf.ecore.EObject;

/**
 * Class to do all the data modification - making it easier to implement model delta tracking
 * 
 * @author cdr
 *
 */
public class DataChanger
{
	private static DataChanger _instance = null;
	private DeltaLogger _logger = new DeltaLogger();

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

	private DataChanger()
	{

	}

	/**
	 * Add a new category
	 * 
	 * @param category the new one
	 * @param sendNotification whether to send a notification
	 */
	public void addCategory(TagCategory category, boolean sendNotification)
	{
		ModelManager.getInstance().getModel().getTagCategoriesList().add(category);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_CATEGORY));
		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_CATEGORY, category);
	}

	/**
	 * Add a coordinates object to an image
	 * 
	 * @param image the image
	 * @param coord
	 */
	public void addCoordinates(Image image, TagValueCoordinate coord)
	{
		image.getTagValueCoordinatesList().add(coord);

		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_COORDINATE, image, coord);

	}

	/**
	 * Add a folder to a Root or Folder
	 * 
	 * @param parent the parent
	 * @param child the folder to add
	 * @param sendNotification whether to send a notification
	 */
	public void addFolder(EObject parent, Folder child, boolean sendNotification)
	{
		if(parent instanceof Root)
		{
			((Root)parent).getFoldersList().add(child);
		}
		else if(parent instanceof Folder)
		{
			((Folder)parent).getFoldersList().add(child);
		}
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_FOLDER));

		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_FOLDER, parent, child);
	}

	/**
	 * Add an Image to a Folder
	 * 
	 * @param parent the folder
	 * @param child the image
	 * @param sendNotification whether to send a notification
	 */
	public void addImage(Folder parent, Image child, boolean sendNotification)
	{
		parent.getImagesList().add(child);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_IMAGE));
		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_IMAGE, parent, child);
	}

	/**
	 * Add a value to an image
	 * 
	 * @param img the image
	 * @param value the new value
	 * @param sendNotification
	 */
	public void addImageValue(Image img, TagValue value, boolean sendNotification)
	{
		img.getTagValuesList().add(value);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));
		_logger.log(DeltaLogger.COMMAND_ASSOCIATE, DeltaLogger.OBJECT_VALUE, img, value);
	}

	/**
	 * Add a (sub)tag to a tag
	 * 
	 * @param owner
	 * @param tag
	 * @param sendNotification
	 */
	public void addTag(Tag owner, Tag tag, boolean sendNotification)
	{
		owner.getSubTagList().add(tag);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_TAG));

		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_TAG, owner.eContainer(), owner, tag);
	}

	/**
	 * Add a tag to a category
	 * 
	 * @param category
	 * @param tag
	 * @param sendNotification
	 */
	public void addTag(TagCategory category, Tag tag, boolean sendNotification)
	{
		category.getTagsList().add(tag);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_TAG));
		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_TAG, category, tag);
	}

	/**
	 * Add a TagValue to the model
	 * 
	 * @param subject the one to add
	 * @param sendNotification
	 */
	public void addTagValue(TagValue subject, boolean sendNotification)
	{
		ModelManager.getInstance().getModel().getValuesList().add(subject);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.ADD_VALUE));
		_logger.log(DeltaLogger.COMMAND_ADD, DeltaLogger.OBJECT_VALUE, subject);
	}

	/**
	 * Remove a coordinate from an image
	 * 
	 * @param parent parent
	 * @param coord coordinate
	 */
	public void removeCoordinate(Image parent, TagValueCoordinate coord)
	{
		parent.getTagValueCoordinatesList().remove(coord);
		_logger.log(DeltaLogger.COMMAND_DELETE, DeltaLogger.OBJECT_COORDINATE, parent, coord);
	}

	/**
	 * Remove a value from an image
	 * 
	 * @param img the image
	 * @param value the victim
	 * @param sendNotification
	 */
	public void removeImageValue(Image img, TagValue value, boolean sendNotification)
	{
		img.getTagValuesList().remove(value);
		if(sendNotification)
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));

		_logger.log(DeltaLogger.COMMAND_REMOVE, DeltaLogger.OBJECT_VALUE, img, value);
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

		_logger.log(DeltaLogger.COMMAND_DELETE, DeltaLogger.OBJECT_VALUE, ModelManager.getInstance().getModel(), victim);
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

		_logger.log(DeltaLogger.COMMAND_MODIFY, DeltaLogger.OBJECT_CATEGORY, category, name);
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

		_logger.log(DeltaLogger.COMMAND_MODIFY, DeltaLogger.OBJECT_COORDINATE, coord, value);
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

		_logger.log(DeltaLogger.COMMAND_MODIFY, DeltaLogger.OBJECT_TAG, subject.eContainer(), subject, name);
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

		if(subTag != null)
			_logger.log(DeltaLogger.COMMAND_MODIFY, DeltaLogger.OBJECT_VALUE, subject, mainTag, subTag);
		else
			_logger.log(DeltaLogger.COMMAND_MODIFY, DeltaLogger.OBJECT_VALUE, subject, mainTag, DeltaLogger.NULL);
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

		// TODO consider ambiguity and how to know whether things are string or first part of objects, objects might need an object prefix, and strings a String
		// prefix
		_logger.log(DeltaLogger.COMMAND_MODIFY, DeltaLogger.OBJECT_VALUE, subject, mainTag, subTag != null ? subTag : DeltaLogger.NULL);
	}

	/**
	 * @return the delta logger
	 */
	public DeltaLogger getLogger()
	{
		return _logger;
	}

}
