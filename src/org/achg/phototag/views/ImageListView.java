package org.achg.phototag.views;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.DataChangeType;
import org.achg.phototag.model.IModelContentChangeListener;
import org.achg.phototag.model.IModelStatusChangeListener;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.search.ISearchResultListener;
import org.achg.phototag.search.SearchCriteriaContainer;
import org.achg.phototag.views.components.ImageFinderFolderVisitor;
import org.achg.phototag.views.components.ImageListContentProvider;
import org.achg.phototag.views.components.ImageListInput;
import org.achg.phototag.views.components.ImageListViewerComparator;
import org.achg.phototag.views.components.ModelLabelProvider;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.Preferences;

/**
 * View for listing images
 */
public class ImageListView implements IModelContentChangeListener, ISearchResultListener, IModelStatusChangeListener
{
	/** Part ID */
	public static String ID = "org.achg.phototag.part.imagelist";

	private TreeViewer _treeViewer;
	private UISynchronize _sync;
	private ESelectionService _selectionService;
	private EPartService _partService;

	/**
	 * Constructor
	 * 
	 * @param sync the UI synchronisation object
	 */
	@Inject
	public ImageListView(UISynchronize sync)
	{
		_sync = sync;
	}

	/**
	 * Create the UI components
	 * 
	 * @param viewParent the parent composite
	 * @param selectionService the Eclipse selection service
	 * @param shell the main shell
	 * @param partService the Eclipse part service
	 */
	@PostConstruct
	public void create(Composite viewParent, ESelectionService selectionService, Shell shell, EPartService partService)
	{
		_selectionService = selectionService;
		_partService = partService;
		GridLayout layout = new GridLayout(1, false);
		viewParent.setLayout(layout);

		_treeViewer = new TreeViewer(viewParent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FILL);
		_treeViewer.setContentProvider(new ImageListContentProvider());
		_treeViewer.setInput(ImageListInput.getInstance());
		_treeViewer.setLabelProvider(new ModelLabelProvider());
		_treeViewer.setAutoExpandLevel(10);
		_treeViewer.setComparator(new ImageListViewerComparator());
		_treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			@Override
			public void selectionChanged(SelectionChangedEvent event)
			{
				selectionService.setSelection(_treeViewer.getStructuredSelection().getFirstElement());
			}
		});
		_treeViewer.expandToLevel(3);

		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		_treeViewer.getTree().setLayoutData(gd);

		ModelManager.getInstance().addModelContentChangeListener(this);
		SearchCriteriaContainer.getInstance().addSearchResultListener(this);

		shell.setMaximized(true);

		selectLastImage();

		ModelManager.getInstance().addModelStatusChangeListener(this);
	}

	@Override
	public void modelContentChanged(List<DataChangeType> types)
	{
		_sync.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if(types.contains(DataChangeType.ADD_FOLDER) || types.contains(DataChangeType.ADD_IMAGE))
				{
					_treeViewer.refresh();
				}

				_treeViewer.expandToLevel(3);
			}
		});

	}

	private void selectLastImage()
	{
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.achg.phototag");

		Preferences rootPreferences = preferences.node("root");
		String lastImageName = rootPreferences.get("selectedImage", null);
		if(lastImageName != null)
		{
			ImageFinderFolderVisitor finder = new ImageFinderFolderVisitor(lastImageName);
			ModelManager.getInstance().visitFolders(finder);
			if(finder.getFoundImage() != null)
			{
				expandToShow((Folder)finder.getFoundImage().eContainer());
				_treeViewer.expandToLevel(finder.getFoundImage(), 0);
				_treeViewer.setSelection(new StructuredSelection(finder.getFoundImage()));
			}
		}
	}

	private void expandToShow(Folder eContainer)
	{
		if(eContainer.eContainer() instanceof Folder)
		{
			expandToShow((Folder)eContainer.eContainer());
		}

		_treeViewer.expandToLevel(eContainer, AbstractTreeViewer.ALL_LEVELS);
	}

	@Override
	public void notifyNewResult()
	{
		_sync.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				_treeViewer.refresh();
				_treeViewer.expandToLevel(3);
			}
		});
	}

	private void pushSelection(Image selection)
	{
		_treeViewer.setSelection(new StructuredSelection(selection));
		_selectionService.setSelection(selection);

		MPart imageDisplayPart = _partService.findPart(ImageDisplayView.ID);
		if(imageDisplayPart != null)
		{
			Object obj = imageDisplayPart.getObject();
			if(obj instanceof ImageDisplayView)
			{
				((ImageDisplayView)obj).receiveSelection(selection);
			}

		}

		MPart tagControlPart = _partService.findPart(ImagesTagControlView.ID);
		if(tagControlPart != null)
		{
			Object obj = tagControlPart.getObject();
			if(obj instanceof ImagesTagControlView)
			{
				((ImagesTagControlView)obj).receiveSelection(selection);
			}
		}
	}

	/**
	 * Select the previous image
	 */
	public void previousImage()
	{
		if(SearchCriteriaContainer.getInstance().getResults() != null)
		{
			Image selected = (Image)_treeViewer.getStructuredSelection().getFirstElement();
			List<Image> result = SearchCriteriaContainer.getInstance().getResults();
			int index = result.indexOf(selected);
			if(index > 0)
			{
				index--;
				selected = result.get(index);
				pushSelection(selected);
			}
		}
		else
		{
			Image selected = (Image)_treeViewer.getStructuredSelection().getFirstElement();
			Folder folder = (Folder)selected.eContainer();
			int index = folder.getImagesList().indexOf(selected);
			index--;
			if(index >= 0)
			{
				selected = folder.getImagesList().get(index);
				pushSelection(selected);
			}
		}

	}

	/**
	 * Select the next image
	 */
	public void nextImage()
	{
		if(SearchCriteriaContainer.getInstance().getResults() != null)
		{
			Image selected = (Image)_treeViewer.getStructuredSelection().getFirstElement();
			List<Image> result = SearchCriteriaContainer.getInstance().getResults();
			int index = result.indexOf(selected);
			index++;
			if(index < result.size())
			{
				selected = result.get(index);
				pushSelection(selected);
			}
		}
		else
		{
			Image selected = (Image)_treeViewer.getStructuredSelection().getFirstElement();
			Folder folder = (Folder)selected.eContainer();
			int index = folder.getImagesList().indexOf(selected);
			index++;
			if(index < folder.getImagesLength())
			{
				selected = folder.getImagesList().get(index);
				pushSelection(selected);
			}
		}
	}

	@Override
	public void modelStatusChanged()
	{
		_sync.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				selectLastImage();
			}
		});
	}

	@Override
	public void statusMessage(String message)
	{
		// Ignore status messages
	}
}
