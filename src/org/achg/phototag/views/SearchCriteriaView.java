package org.achg.phototag.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.jobs.SearchJob;
import org.achg.phototag.model.DataChangeType;
import org.achg.phototag.model.IModelContentChangeListener;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.search.SearchCriteriaContainer;
import org.achg.phototag.views.components.ImagesModelLabelProvider;
import org.achg.phototag.views.components.TagValueComparator;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class SearchCriteriaView implements IModelContentChangeListener
{
	Combo _catCombo;
	Combo _tagCombo;
	Combo _tagValueCombo;
	Combo _subTagCombo;
	Combo _subTagValueCombo;
	TableViewer _selectedTableViewer;
	UISynchronize _sync;

	@PostConstruct
	public void create(Composite viewParent, EPartService partService, UISynchronize sync)
	{
		_sync = sync;
		GridLayout layout = new GridLayout(2, false);
		viewParent.setLayout(layout);

		GridData gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		gd.horizontalSpan = 2;

		Label label = new Label(viewParent, SWT.NONE);
		label.setText("Search Criteria");
		label.setLayoutData(gd);

		gd = new GridData(SWT.LEFT, SWT.TOP, true, false);

		label = new Label(viewParent, SWT.NONE);
		label.setText("Category:");
		label.setLayoutData(gd);

		_catCombo = new Combo(viewParent, SWT.NONE);
		_catCombo.setLayoutData(gd);
		_catCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				populateCombos(false, true, true, true, true);
			}
		});

		_tagCombo = new Combo(viewParent, SWT.NONE);
		gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		_tagCombo.setLayoutData(gd);
		_tagCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				populateCombos(false, false, true, true, true);
			}
		});

		_tagValueCombo = new Combo(viewParent, SWT.NONE);
		gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		_tagValueCombo.setLayoutData(gd);

		_tagValueCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				populateCombos(false, false, true, false, true);
			}
		});

		_subTagCombo = new Combo(viewParent, SWT.NONE);
		gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		_subTagCombo.setLayoutData(gd);
		_subTagCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				populateCombos(false, false, false, true, true);
			}
		});

		_subTagValueCombo = new Combo(viewParent, SWT.NONE);
		gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		_subTagValueCombo.setLayoutData(gd);

		populateCombos(true, true, true, true, true);

		Button addButton = new Button(viewParent, SWT.NONE);
		addButton.setText("Add");
		addButton.setToolTipText("Add selected search criteria from above");
		addButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				// todo
				TagValue criteria = PhotoTagModelFactory.eINSTANCE.createTagValue();

				criteria.setTag(ModelManager.getInstance().getModel().getTagCategories()[_catCombo.getSelectionIndex()].getTags(_tagCombo.getSelectionIndex()));
				criteria.setValue(_tagValueCombo.getText());

				if(!_subTagValueCombo.getText().equals("<any>"))
				{
					criteria.setSubTag(criteria.getTag().getSubTag(_subTagCombo.getSelectionIndex()));
					criteria.setSubValue(_subTagValueCombo.getText());
				}

				SearchCriteriaContainer.getInstance().addCriteria(criteria);

				_selectedTableViewer.setInput(SearchCriteriaContainer.getInstance().getCriteria());
				_selectedTableViewer.refresh();
				Job.getJobManager().cancel(SearchJob.class);
				new SearchJob().schedule();
			}
		});

		Button deleteButton = new Button(viewParent, SWT.NONE);
		deleteButton.setText("Delete");
		deleteButton.setToolTipText("Delete selected criteria from below");
		deleteButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				for(Object obj : _selectedTableViewer.getStructuredSelection().toList())
				{
					if(obj instanceof TagValue)
					{
						SearchCriteriaContainer.getInstance().removeCriteria((TagValue)obj);

					}
				}
				_selectedTableViewer.setInput(SearchCriteriaContainer.getInstance().getCriteria());
				_selectedTableViewer.refresh();
				Job.getJobManager().cancel(SearchJob.class);
				new SearchJob().schedule();
			}
		});

		_selectedTableViewer = new TableViewer(viewParent, SWT.FILL | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 2;

		_selectedTableViewer.getControl().setLayoutData(gd);
		_selectedTableViewer.setComparator(new TagValueComparator());
		_selectedTableViewer.setContentProvider(new ArrayContentProvider());
		_selectedTableViewer.setLabelProvider(new ImagesModelLabelProvider());

		_selectedTableViewer.setInput(SearchCriteriaContainer.getInstance().getCriteria());

		ModelManager.getInstance().addModelContentChangeListener(this);
	}

	private void populateCombos(boolean cats, boolean tags, boolean subtags, boolean tagvalues, boolean subtagvalues)
	{
		if(ModelManager.getInstance().isLoaded())
		{
			if(cats)
			{
				_catCombo.setItems(ModelManager.getCategories());
				_catCombo.select(0);
			}

			if(tags)
			{
				_tagCombo.setItems(ModelManager.getTags(_catCombo.getSelectionIndex()));
				_tagCombo.select(0);
				_tagCombo.requestLayout();

			}
			if(tagvalues)
			{
				_tagValueCombo.setItems(ModelManager.getTagValues(_catCombo.getSelectionIndex(), _tagCombo.getSelectionIndex(), "<unset>"));
				_tagValueCombo.select(0);
				_tagValueCombo.requestLayout();
			}

			if(subtags)
			{
				_subTagCombo.setItems(ModelManager.getSubsTags(_catCombo.getSelectionIndex(), _tagCombo.getSelectionIndex()));
				_subTagCombo.select(0);
				_subTagCombo.requestLayout();
			}

			if(subtagvalues)
			{
				_subTagValueCombo.setItems(ModelManager.getSubValues(_catCombo.getSelectionIndex(), _tagCombo.getSelectionIndex(),
						_subTagCombo.getSelectionIndex(), _tagValueCombo.getText().trim(), "<any>", "<unset>"));
				_subTagValueCombo.select(0);
				_subTagValueCombo.requestLayout();
			}

		}
	}

	@Override
	public void modelContentChanged(List<DataChangeType> modTypes)
	{
		_sync.asyncExec(new Runnable()
		{

			@Override
			public void run()
			{
				populateCombos(modTypes.contains(DataChangeType.ADD_CATEGORY) || modTypes.contains(DataChangeType.MODIFY_CATEGORY),
						modTypes.contains(DataChangeType.ADD_TAG) || modTypes.contains(DataChangeType.MODIFY_TAG),
						modTypes.contains(DataChangeType.ADD_SUBTAG) || modTypes.contains(DataChangeType.MODIFY_TAG),
						modTypes.contains(DataChangeType.ADD_VALUE) || modTypes.contains(DataChangeType.MODIFY_VALUE),
						modTypes.contains(DataChangeType.ADD_VALUE) || modTypes.contains(DataChangeType.MODIFY_VALUE));
			}
		});
	}
}
