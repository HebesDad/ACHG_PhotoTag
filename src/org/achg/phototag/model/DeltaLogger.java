package org.achg.phototag.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;
import org.eclipse.emf.ecore.EObject;

/**
 * Model Delta Logger
 * 
 * @author cdr
 *
 */
public class DeltaLogger
{

	/**
	 * Command to add an object
	 */
	public static final String COMMAND_ADD = "ADD";
	/**
	 * Command to modify an object
	 */
	public static final String COMMAND_MODIFY = "MODIFY";
	/**
	 * Command to remove an object from it's usage
	 */
	public static final String COMMAND_REMOVE = "REMOVE";
	/**
	 * Command to delete and object
	 */
	public static final String COMMAND_DELETE = "DELETE";

	/**
	 * marker to introduce object of type TagCategory
	 */
	public static final String OBJECT_CATEGORY = "CATEGORY";
	/**
	 * marker to introduce object of type Tag
	 */
	public static final String OBJECT_TAG = "TAG";
	/**
	 * marker to introduce object of type (sub) Tag
	 */
	public static final String OBJECT_SUBTAG = "SUBTAG";
	/**
	 * marker to introduce object of type TagValue
	 */
	public static final String OBJECT_VALUE = "VALUE";
	/**
	 * marker to introduce object of type TagValueCoordinate
	 */
	public static final String OBJECT_COORDINATE = "COORDINATE";
	/**
	 * marker to introduce object of type Folder
	 */
	public static final String OBJECT_FOLDER = "FOLDER";
	/**
	 * marker to introduce object of type Image
	 */
	public static final String OBJECT_IMAGE = "IMAGE";

	public static final String NULL = "<NULL>";
	public static final String ROOT = "<ROOT>";
	public static final String OBJECT_START = "<OBJECT>";
	public static final String OBJECT_END = "</OBJECT>";
	public static final String SPLIT = "<SPLIT>";

	private List<List<String>> _log = new ArrayList<>();

	public void emit(FileOutputStream os)
	{
		for(List<String> entry : _log)
		{
			StringBuilder sb = new StringBuilder();
			for(String str : entry)
			{
				sb.append(str);
				sb.append(SPLIT);
			}
			sb.append("\r\n");
			try
			{
				os.write(sb.toString().getBytes());
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void log(String command, String dataType, EObject... objects)
	{
		List<String> entry = new ArrayList<>();

		entry.add(command);
		entry.add(dataType);

		for(EObject object : objects)
		{
			entry.add(OBJECT_START);
			logObject(entry, object);
			entry.add(OBJECT_END);

		}

		_log.add(entry);
	}

	private void logObject(List<String> log, EObject object)
	{
		if(object instanceof TagCategory)
		{

			log.add(((TagCategory)object).getName());
		}
		else if(object instanceof Image)
		{
			log.add(((Image)object).getName());
		}
		else if(object instanceof TagValueCoordinate)
		{
			logValue(log, ((TagValueCoordinate)object).getTagValue());
			log.add(Double.valueOf(((TagValueCoordinate)object).getXPercentage()).toString());
			log.add(Double.valueOf(((TagValueCoordinate)object).getYPercentage()).toString());
		}
		else if(object instanceof Root)
		{
			log.add(ROOT);
		}
		else if(object instanceof Folder)
		{
			log.add(((Folder)object).getName());
		}
		else if(object instanceof TagValue)
		{
			logValue(log, (TagValue)object);
		}
		else if(object instanceof Tag)
		{
			log.add(((Folder)object).getName());
		}
	}

	private void logValue(List<String> log, TagValue value)
	{
		log.add(((TagCategory)value.getTag().eContainer()).getName());
		log.add(value.getTag().getName());
		log.add(value.getValue());
		if(value.getSubTag() != null)
		{
			log.add(value.getSubTag().getName());
			log.add(value.getSubValue());
		}
	}

	public void log(String command, String dataType, EObject object, String newValue)
	{
		List<String> entry = new ArrayList<>();

		entry.add(command);
		entry.add(dataType);

		entry.add(OBJECT_START);
		logObject(entry, object);
		entry.add(OBJECT_END);

		entry.add(newValue);

		_log.add(entry);
	}

	public void log(String command, String dataType, EObject object1, EObject object2, String newValue)
	{
		List<String> entry = new ArrayList<>();

		entry.add(command);
		entry.add(dataType);
		entry.add(OBJECT_START);
		logObject(entry, object1);
		entry.add(OBJECT_END);
		entry.add(OBJECT_START);
		logObject(entry, object2);
		entry.add(OBJECT_END);

		entry.add(newValue);

		_log.add(entry);
	}

	public void log(String command, String dataType, EObject object1, String newValue1, String newValue2)
	{
		List<String> entry = new ArrayList<>();

		entry.add(command);
		entry.add(dataType);
		entry.add(OBJECT_START);
		logObject(entry, object1);
		entry.add(OBJECT_END);

		entry.add(newValue1);
		entry.add(newValue2);

		_log.add(entry);
	}
}
