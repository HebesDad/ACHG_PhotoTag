package org.achg.phototag.model;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.achg.phototag.FileUtil;
import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.jobs.AutoSaveJob;
import org.achg.phototag.jobs.ImageCounterJob;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * Manager for the model
 */
public class ModelManager
{
	private static final String TAGFILE = "phototags.xmi";

	private static ModelManager _instance = null;

	private boolean _modelLoaded = false;
	private File _imagesRootFolder;
	private List<IModelStatusChangeListener> _modelStatusChangeListeners = new ArrayList<>();
	private List<IModelContentChangeListener> _modelContentChangeListeners = new ArrayList<>();
	private Root _modelRoot = null;
	private String _pathToXmi;

	private ModelManager()
	{
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.achg.phototag");

		Preferences rootPreferences = preferences.node("root");
		String folder = rootPreferences.get("selectedFolder", null);
		if(folder != null)
		{
			setRootFolder(folder);
		}
	}

	/**
	 * Get the singleton instance of the model manager
	 * 
	 * @return the singleton instance
	 */
	public static synchronized ModelManager getInstance()
	{
		if(_instance == null)
		{
			_instance = new ModelManager();
		}

		return _instance;
	}

	/**
	 * Get whether the model has been loaded
	 * 
	 * @return {@code true} if loaded, otherwise {@code false}
	 */
	public boolean isLoaded()
	{
		return _modelLoaded;
	}

	/**
	 * Get the root folder which contains all the images
	 * 
	 * @return the root folder
	 */
	public File getImagesRoot()
	{
		return _imagesRootFolder;
	}

	/**
	 * Set the root folder which contains all the images
	 * 
	 * @param selectedFolder the root folder
	 */
	public void setRootFolder(String selectedFolder)
	{
		_imagesRootFolder = new File(selectedFolder);
		_pathToXmi = selectedFolder + '\\' + TAGFILE;
		File xmiFile = new File(_pathToXmi);
		if(!xmiFile.exists())
		{
			_modelRoot = PhotoTagModelFactory.eINSTANCE.createRoot();
			addDefaultsToModel(_modelRoot);
		}
		else
		{
			_modelRoot = loadModel();

		}
		_modelLoaded = true;

		notifyModelStatusChangeListeners();

		List<DataChangeType> types = new ArrayList<>();
		types.add(DataChangeType.ADD_FOLDER);
		types.add(DataChangeType.ADD_IMAGE);
		types.add(DataChangeType.ADD_TAG);
		types.add(DataChangeType.ADD_SUBTAG);
		types.add(DataChangeType.ADD_VALUE);

		notifyModelContentChangeListeners(types);

		storeSelectedFolder(selectedFolder);
	}

	private void storeSelectedFolder(String selectedFolder)
	{
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.achg.phototag");

		Preferences rootPreferences = preferences.node("root");

		rootPreferences.put("selectedFolder", selectedFolder);
		try
		{
			// forces the application to save the preferences
			preferences.flush();
		}
		catch(BackingStoreException e)
		{
			// nothing can be done
		}
	}

	private Root loadModel()
	{
		Registry registry = Registry.INSTANCE;
		Map<String, Object> map = registry.getExtensionToFactoryMap();
		map.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resourceSet = new ResourceSetImpl();

		// Get the resource
		Resource resource = resourceSet.getResource(URI.createURI("file://" + _pathToXmi.replace('\\', '/')), true);

		Root myWeb = (Root)resource.getContents().get(0);

		if(myWeb.getImageCount() == 0)
		{
			new ImageCounterJob().schedule();
		}

		return myWeb;
	}

	/**
	 * Notify all the registered model status change listeners
	 */
	public void notifyModelStatusChangeListeners()
	{
		for(IModelStatusChangeListener listener : _modelStatusChangeListeners)
		{
			listener.modelStatusChanged();
		}
	}

	/**
	 * Notify all the registered model status change listeners, with a message
	 * 
	 * @param message the message
	 */
	public void notifyModelStatusChangeListeners(String message)
	{
		for(IModelStatusChangeListener listener : _modelStatusChangeListeners)
		{
			listener.statusMessage(message);
		}
	}

	/**
	 * Notify all the registered model content change listeners
	 * 
	 * @param dataChanges a list containing the types of model changes which have occurred
	 */
	public void notifyModelContentChangeListeners(List<DataChangeType> dataChanges)
	{
		for(IModelContentChangeListener listener : _modelContentChangeListeners)
		{
			listener.modelContentChanged(dataChanges);
		}

		Job.getJobManager().cancel(AutoSaveJob.class);
		AutoSaveJob newJob = new AutoSaveJob();
		newJob.schedule(3 * 60 * 60 * 1000); // 3 minutes
	}

	/**
	 * Register a new model status change listener
	 * 
	 * @param listener the listener
	 */
	public void addModelStatusChangeListener(IModelStatusChangeListener listener)
	{
		_modelStatusChangeListeners.add(listener);

	}

	/**
	 * Unregister a model status change listener
	 * 
	 * @param listener the listener
	 */
	public void removeModelStatusChangeListener(IModelStatusChangeListener listener)
	{
		_modelStatusChangeListeners.remove(listener);
	}

	/**
	 * Register a new model content change listener
	 * 
	 * @param listener the listener
	 */
	public void addModelContentChangeListener(IModelContentChangeListener listener)
	{
		_modelContentChangeListeners.add(listener);

	}

	/**
	 * Unregister a model content change listener
	 * 
	 * @param listener the listener
	 */
	public void removeModelContentChangeListener(IModelContentChangeListener listener)
	{
		_modelContentChangeListeners.remove(listener);
	}

	/**
	 * Save the model
	 */
	public void save()
	{
		if(_modelLoaded)
		{
			Registry registry = Registry.INSTANCE;
			Map<String, Object> map = registry.getExtensionToFactoryMap();
			map.put("website", new XMIResourceFactoryImpl());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d_H.m.s");
			String datestr = format.format(Date.from(Instant.now()));

			FileUtil.copyFile(_modelRoot, _pathToXmi, _pathToXmi + datestr);
		}
	}

	private void addDefaultsToModel(Root root)
	{
		// consider later just storing a pre-canned xmi that we could then load rather
		// than doing it all via code
		TagCategory categoryMeta = PhotoTagModelFactory.eINSTANCE.createTagCategory();
		categoryMeta.setName("Metadata");

		TagCategory categoryLocation = PhotoTagModelFactory.eINSTANCE.createTagCategory();
		categoryLocation.setName("Location");

		TagCategory categoryPeople = PhotoTagModelFactory.eINSTANCE.createTagCategory();
		categoryPeople.setName("People");

		TagCategory categoryEvent = PhotoTagModelFactory.eINSTANCE.createTagCategory();
		categoryEvent.setName("Event");

		TagCategory[] categories = {categoryMeta, categoryLocation, categoryPeople, categoryEvent};

		root.setTagCategories(categories);
	}

	/**
	 * Get the model root
	 * 
	 * @return the model root
	 */
	public Root getModel()
	{
		return _modelRoot;
	}

	/**
	 * Get the category names
	 * 
	 * @return an array of category names
	 */
	public static String[] getCategories()
	{
		List<String> categoryNames = new ArrayList<>();

		Root model = _instance.getModel();

		if(model != null)
		{
			for(TagCategory cat : model.getTagCategoriesList())
			{
				categoryNames.add(cat.getName());
			}
		}

		return categoryNames.toArray(new String[categoryNames.size()]);
	}

	/**
	 * Get the tag names from a given category
	 * 
	 * @param selectedCategory the category to get tag names from
	 * @return an array of tag names
	 */
	public static String[] getTags(int selectedCategory)
	{
		List<String> tagNames = new ArrayList<>();

		if(selectedCategory < 0)
		{
			selectedCategory = 0;
		}

		Root model = _instance.getModel();

		if(model != null)
		{
			List<Tag> tags = model.getTagCategories()[selectedCategory].getTagsList();

			for(Tag tag : tags)
			{
				tagNames.add(tag.getName());
			}
		}

		return tagNames.toArray(new String[tagNames.size()]);
	}

	/**
	 * Get the tag values for a given category and tag
	 * 
	 * @param categoryIndex the category index
	 * @param tagIndex the tag index
	 * @param additionals any additional tag values to include
	 * @return an array of tag values
	 */
	public static String[] getTagValues(int categoryIndex, int tagIndex, String... additionals)
	{
		List<String> values = new ArrayList<>();
		for(String additional : additionals)
		{
			values.add(additional);
		}

		if(categoryIndex < 0)
		{
			categoryIndex = 0;
		}

		if(tagIndex < 0)
		{
			tagIndex = 0;
		}

		Root model = _instance.getModel();

		if(model != null)
		{
			List<Tag> tagList = model.getTagCategories()[categoryIndex].getTagsList();

			if(tagList.size() > tagIndex)
			{
				Tag tag = tagList.get(tagIndex);

				for(TagValue value : model.getValuesList())
				{
					if(value.getTag() == tag && !values.contains(value.getValue()))
					{
						values.add(value.getValue());
					}
				}
			}
		}

		Collections.sort(values);

		return values.toArray(new String[values.size()]);
	}

	/**
	 * Get the sub-tag names for a given category and tag
	 * 
	 * @param categoryIndex the category index
	 * @param tagIndex the tag index
	 * @return an array of sub-tag names
	 */
	public static String[] getSubsTags(int categoryIndex, int tagIndex)
	{
		List<String> subTagNames = new ArrayList<>();

		if(categoryIndex < 0)
		{
			categoryIndex = 0;
		}

		if(tagIndex < 0)
		{
			tagIndex = 0;
		}

		Root model = _instance.getModel();

		if(model != null)
		{
			List<Tag> tags = model.getTagCategories()[categoryIndex].getTagsList();
			List<Tag> subTags = tags.get(tagIndex).getSubTagList();

			for(Tag subTag : subTags)
			{
				subTagNames.add(subTag.getName());
			}
		}

		return subTagNames.toArray(new String[subTagNames.size()]);
	}

	/**
	 * Get the sub-tag names for a given category, tag and sub-tag
	 * 
	 * @param categoryIndex the category index
	 * @param tagIndex the tag index
	 * @param subTagIndex the sub-tag index
	 * @param tagValue the tag value
	 * @param additionals any additional sub-tag values to include
	 * @return an array of sub-tag names
	 */
	public static String[] getSubValues(int categoryIndex, int tagIndex, int subTagIndex, String tagValue, String... additionals)
	{
		List<String> values = new ArrayList<>();

		for(String additional : additionals)
		{
			values.add(additional);
		}

		if(categoryIndex < 0)
		{
			categoryIndex = 0;
		}

		if(tagIndex < 0)
		{
			tagIndex = 0;
		}

		if(subTagIndex < 0)
		{
			subTagIndex = 0;
		}

		Root model = _instance.getModel();

		if(model != null)
		{
			Tag tag = model.getTagCategories()[categoryIndex].getTagsList().get(tagIndex);
			List<Tag> subTagList = tag.getSubTagList();

			if(subTagList != null && !subTagList.isEmpty())
			{
				Tag subTag = subTagList.get(subTagIndex);

				for(TagValue value : model.getValuesList())
				{
					if(value.getTag() == tag && value.getSubTag() == subTag && value.getValue().equals(tagValue))
					{
						if(value.getSubValue() != null && !values.contains(value.getSubValue()))
						{
							values.add(value.getSubValue());
						}
					}
				}
			}
		}

		Collections.sort(values);

		return values.toArray(new String[values.size()]);
	}

	/**
	 * Visit the folder from the model root
	 * 
	 * @param visitor the folder visitor
	 */
	public void visitFolders(IFolderVisitor visitor)
	{
		innerVisitFolder(visitor, _modelRoot.getFolders());
	}

	private boolean innerVisitFolder(IFolderVisitor visitor, Folder[] folders)
	{
		for(Folder folder : folders)
		{
			if(!visitor.visitFolder(folder))
			{
				return false;
			}

			if(folder.getFolders() != null && !innerVisitFolder(visitor, folder.getFolders()))
			{
				return false;
			}
		}
		return true;
	}
}
