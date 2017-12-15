package org.achg.phototag.views;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.IModelContentChangeListener;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.search.ISearchResultListener;
import org.achg.phototag.search.SearchCriteriaContainer;
import org.achg.phototag.views.components.ImageListContentProvider;
import org.achg.phototag.views.components.ImageListInput;
import org.achg.phototag.views.components.ImageListViewerComparator;
import org.achg.phototag.views.components.ModelLabelProvider;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class ImageListView implements IModelContentChangeListener, ISearchResultListener {
	public static String ID = "org.achg.phototag.part.imagelist";
	TreeViewer _treeViewer;
	UISynchronize _sync;

	@Inject
	public ImageListView(UISynchronize sync) {
		_sync = sync;
	}

	@PostConstruct
	public void create(Composite viewParent, ESelectionService selectionService, Shell shell) {
		GridLayout layout = new GridLayout(1, false);
		viewParent.setLayout(layout);

		_treeViewer = new TreeViewer(viewParent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FILL);
		_treeViewer.setContentProvider(new ImageListContentProvider());
		_treeViewer.setInput(ImageListInput.getInstance());
		_treeViewer.setLabelProvider(new ModelLabelProvider());
		_treeViewer.setAutoExpandLevel(10);
		_treeViewer.setComparator(new ImageListViewerComparator());
		_treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionService.setSelection(_treeViewer.getStructuredSelection().getFirstElement());

			}
		});
		_treeViewer.expandToLevel(3);

		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		_treeViewer.getTree().setLayoutData(gd);

		ModelManager.getInstance().addModelContentChangeListener(this);
		SearchCriteriaContainer.getInstance().addSearchResultListener(this);

		shell.setMaximized(true);
	}

	@Override
	public void modelContentChanged() {
		_sync.asyncExec(new Runnable() {
			@Override
			public void run() {
				_treeViewer.refresh();
				_treeViewer.expandToLevel(3);
			}
		});

	}

	@Override
	public void notifyNewResult() {
		_sync.asyncExec(new Runnable() {
			@Override
			public void run() {
				_treeViewer.refresh();
				_treeViewer.expandToLevel(3);
			}
		});

	}

	public void previousImage() {
		if (SearchCriteriaContainer.getInstance().getResults() != null) {

			Image selected = (Image) _treeViewer.getStructuredSelection().getFirstElement();
			List<Image> result = SearchCriteriaContainer.getInstance().getResults();
			int index = result.indexOf(selected);
			if (index > 0) {
				index--;
				selected = result.get(index);
				_treeViewer.setSelection(new StructuredSelection(selected));
			}
		}

	}

	public void nextImage() {
		if (SearchCriteriaContainer.getInstance().getResults() != null) {

			Image selected = (Image) _treeViewer.getStructuredSelection().getFirstElement();
			List<Image> result = SearchCriteriaContainer.getInstance().getResults();
			int index = result.indexOf(selected);
			index++;
			if (index <result.size()) {
				selected = result.get(index);
				_treeViewer.setSelection(new StructuredSelection(selected));
			}
		}

	}
}
