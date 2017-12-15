package org.achg.phototag.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class ModelManager {
	private static ModelManager _instance = null;
	private boolean _modelLoaded = false;
	private File _imagesRootFolder;
	private List<IModelStatusChangeListener> _modelStatusChangeListeners = new ArrayList<>();
	private List<IModelContentChangeListener> _modelContentChangeListeners = new ArrayList<>();
	private static final String TAGFILE = "phototags.xmi";
	private Root _modelRoot = null;
	private String _pathToXmi;

	private ModelManager() {
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.achg.phototag");

		Preferences sub1 = preferences.node("root");
		String folder = sub1.get("selectedFolder", null);
		if (folder != null) {
			setRootFolder(folder);
		}
	}

	public static synchronized ModelManager getInstance() {
		if (_instance == null) {
			_instance = new ModelManager();
		}

		return _instance;
	}

	public boolean isLoaded() {
		return _modelLoaded;
	}

	public File getImagesRoot() {
		return _imagesRootFolder;
	}

	public void setRootFolder(String selectedFolder) {
		_imagesRootFolder = new File(selectedFolder);
		_pathToXmi = selectedFolder + "\\" + TAGFILE;
		File xmiFile = new File(_pathToXmi);
		if (!xmiFile.exists()) {
			_modelRoot = PhotoTagModelFactory.eINSTANCE.createRoot();
			addDefaultsToModel(_modelRoot);
		} else {
			_modelRoot = loadModel();

		}
		_modelLoaded = true;
		notifyModelStatusChangeListeners();
		notifyModelContentChangeListeners();

		storeSelectedFolder(selectedFolder);
	}

	private void storeSelectedFolder(String selectedFolder) {
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.achg.phototag");

		Preferences sub1 = preferences.node("root");

		sub1.put("selectedFolder", selectedFolder);
		try {
			// forces the application to save the preferences
			preferences.flush();
		} catch (BackingStoreException e) {
			// nothing can be done
		}
	}

	private Root loadModel() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Get the resource
		Resource resource = resSet.getResource(URI.createURI("file://" + _pathToXmi.replace('\\', '/')), true);

		Root myWeb = (Root) resource.getContents().get(0);

		return myWeb;
	}

	private void notifyModelStatusChangeListeners() {
		for (IModelStatusChangeListener listener : _modelStatusChangeListeners) {
			listener.modelStatusChanged();
		}
	}

	public void notifyModelContentChangeListeners() {
		for (IModelContentChangeListener listener : _modelContentChangeListeners) {
			listener.modelContentChanged();
		}
	}

	public void addModelStatusChangeListener(IModelStatusChangeListener listener) {
		_modelStatusChangeListeners.add(listener);

	}

	public void removeModelStatusChangeListener(IModelStatusChangeListener listener) {
		_modelStatusChangeListeners.remove(listener);
	}

	public void addModelContentChangeListener(IModelContentChangeListener listener) {
		_modelContentChangeListeners.add(listener);

	}

	public void removeModelContentChangeListener(IModelContentChangeListener listener) {
		_modelContentChangeListeners.remove(listener);
	}

	public void save() {
		if (_modelLoaded) {
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = reg.getExtensionToFactoryMap();
			m.put("website", new XMIResourceFactoryImpl());

			// Obtain a new resource set
			ResourceSet resSet = new ResourceSetImpl();

			// create a resource
			Resource resource = resSet.createResource(URI.createURI("file://" + _pathToXmi.replace('\\', '/')));
			// Get the first model element and cast it to the right type, in my
			// example everything is hierarchical included in this first node
			resource.getContents().add(_modelRoot);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd-H-m-s");
			String datestr = format.format(Date.from(Instant.now()));
			try (OutputStream os = new FileOutputStream(new File(_pathToXmi + datestr))) {
				Files.copy(new File(_pathToXmi).toPath(), os);
				// Files.copy(new Path(_pathToXmi), new Path(_pathToXmi +
				// Date.from(Instant.now()).toString()),StandardCopyOption.ATOMIC_MOVE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// now save the content.
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addDefaultsToModel(Root root) {

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

		TagCategory[] categories = { categoryMeta, categoryLocation, categoryPeople, categoryEvent };

		root.setTagCategories(categories);

	}

	public Root getModel() {
		return _modelRoot;
	}

	public static String[] getCategories() {
		List<String> categories = new ArrayList<>();

		for (TagCategory cat : _instance.getModel().getTagCategoriesList()) {
			categories.add(cat.getName());
		}
		return categories.toArray(new String[categories.size()]);
	}

	public static String[] getTags(int selectedCategory) {
		List<String> tagnames = new ArrayList<>();

		List<Tag> tags = _instance.getModel().getTagCategories()[selectedCategory].getTagsList();

		for (Tag tag : tags) {
			tagnames.add(tag.getName());
		}
		return tagnames.toArray(new String[tagnames.size()]);
	}

	public static String[] getTagValues(int catIndex, int tagIndex, String... additionals) {
		List<String> values = new ArrayList<>();
		for (String addit : additionals) {
			values.add(addit);
		}

		Tag tag = _instance.getModel().getTagCategories()[catIndex].getTagsList().get(tagIndex);

		for (TagValue value : ModelManager.getInstance().getModel().getValuesList()) {
			if (value.getTag() == tag && !values.contains(value.getValue())) {
				values.add(value.getValue());
			}
		}

		Collections.sort(values);
		return values.toArray(new String[values.size()]);
	}

	public static String[] getSubsTags(int catIndex, int tagIndex) {
		List<String> tagnames = new ArrayList<>();

		List<Tag> tags = _instance.getModel().getTagCategories()[catIndex].getTagsList();
		tags = tags.get(tagIndex).getSubTagList();

		for (Tag tag : tags) {
			tagnames.add(tag.getName());
		}
		return tagnames.toArray(new String[tagnames.size()]);
	}

	public static String[] getSubValues(int catIndex, int tagIndex, int subIndex, String... additionals) {
		List<String> values = new ArrayList<>();

		for (String addit : additionals) {
			values.add(addit);
		}

		Tag tag = _instance.getModel().getTagCategories()[catIndex].getTagsList().get(tagIndex);
		List<Tag> stlist = tag.getSubTagList();

		if (stlist != null && !stlist.isEmpty()) {
			Tag subtag = stlist.get(subIndex);

			for (TagValue value : _instance.getModel().getValuesList()) {
				if (value.getTag() == tag && value.getSubTag() == subtag && value.getSubValue() != null) {
					if (!values.contains(value.getSubValue()))
					values.add(value.getSubValue());
				}
			}
		}
		
		Collections.sort(values);
		return values.toArray(new String[values.size()]);
	}

	public void visitFolders(IFolderVisitor visitor) {
		innerVisitFolder(visitor, _modelRoot.getFolders());
	}

	private void innerVisitFolder(IFolderVisitor visitor, Folder[] folders) {
		for (Folder folder : folders) {
			visitor.visitFolder(folder);
			if (folder.getFolders() != null)
				innerVisitFolder(visitor, folder.getFolders());
		}
	}
}
