package org.achg.phototag.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.model.DataChangeType;
import org.achg.phototag.model.IModelContentChangeListener;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.views.components.CoordinatesCoordinator;
import org.achg.phototag.views.components.ICoordinatesListener;
import org.achg.phototag.views.components.ImagesModelLabelProvider;
import org.achg.phototag.views.components.TagValueComparator;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

/**
 * View for controlling image tags
 */
public class ImagesTagControlView implements IModelContentChangeListener, ICoordinatesListener
{
	/** Part ID */
	public static final String ID = "org.achg.phototag.part.imageTagControlView";

	private TableViewer _tableViewer;
	private Combo _catCombo;
	private Combo _tagCombo;
	private Combo _subCombo;
	private Combo _valueCombo;
	private Combo _subValueCombo;
	private Image _selectedImage = null;
	private UISynchronize _sync;
	private double _x;
	private double _y;

	/**
	 * Create the UI components
	 * 
	 * @param viewParent the parent composite
	 * @param partService the Eclipse part service
	 * @param sync the UI synchronisation object
	 */
	@PostConstruct
	public void create(Composite viewParent, EPartService partService, UISynchronize sync)
	{
		_sync = sync;
		GridLayout layout = new GridLayout(6, false);
		viewParent.setLayout(layout);

		_catCombo = new Combo(viewParent, SWT.FILL);
		_catCombo.setItems(ModelManager.getCategories());
		_catCombo.select(0);
		GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_catCombo.setLayoutData(gd);
		_catCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				_tagCombo.select(0);
				_subCombo.select(0);
				populateTagCombo();
				populateTagValueCombo();
				populateSubCombo();
				populateSubValueCombo();
			}
		});

		_tagCombo = new Combo(viewParent, SWT.FILL);
		_tagCombo.setLayoutData(gd);
		_tagCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				_subCombo.select(0);
				populateTagValueCombo();
				populateSubCombo();
				populateSubValueCombo();
			}
		});

		_valueCombo = new Combo(viewParent, SWT.FILL);
		_valueCombo.setLayoutData(gd);
		_valueCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				_subCombo.select(0);
				populateSubValueCombo();
			}
		});

		_subCombo = new Combo(viewParent, SWT.FILL);
		_subCombo.setLayoutData(gd);
		_subCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				_subCombo.select(0);
				populateSubValueCombo();
			}
		});

		_subValueCombo = new Combo(viewParent, SWT.FILL);
		_subValueCombo.setLayoutData(gd);

		Button addButton = new Button(viewParent, SWT.NONE);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				addTag();
			}
		});

		_tableViewer = new TableViewer(viewParent, SWT.FILL | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 5;
		_tableViewer.getControl().setLayoutData(gd);
		_tableViewer.setComparator(new TagValueComparator());
		_tableViewer.setContentProvider(new ArrayContentProvider());
		_tableViewer.setLabelProvider(new ImagesModelLabelProvider());
		Table table = (Table)_tableViewer.getControl();
		table.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				processTableSelection();
			}
		});

		Button deleteTagValue = new Button(viewParent, SWT.NONE);
		deleteTagValue.setText("Delete");
		deleteTagValue.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				for(Object selected : _tableViewer.getStructuredSelection().toList())
				{
					if(selected instanceof TagValue)
					{
						_selectedImage.getTagValuesList().remove(selected);
					}
				}
				ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));
			}
		});

		populateCategoryCombo();
		populateTagCombo();
		populateTagValueCombo();
		populateSubCombo();
		populateSubValueCombo();

		ModelManager.getInstance().addModelContentChangeListener(this);
		CoordinatesCoordinator.getInstance().addListener(this);
	}

	private void processTableSelection()
	{
		TagValue selectedValue = (TagValue)((IStructuredSelection)_tableViewer.getSelection()).getFirstElement();
		TagCategory cat = (TagCategory)selectedValue.getTag().eContainer();
		_catCombo.select(ModelManager.getInstance().getModel().getTagCategoriesList().indexOf(cat));

		populateTagCombo();

		_tagCombo.select(cat.getTagsList().indexOf(selectedValue.getTag()));

		populateTagValueCombo();

		int i = 0;
		for(String contender : _valueCombo.getItems())
		{
			if(contender.equals(selectedValue.getValue()))
			{
				_valueCombo.select(i);
				break;
			}
			i++;
		}

		populateSubCombo();

		// even if we have no sub tag, the combo may have been set previously with sub tag values, so we need to refresh

		_subCombo.select(selectedValue.getTag().getSubTagList().indexOf(selectedValue.getSubTag()));
		populateSubValueCombo();

		i = 0;
		for(String contender : _subValueCombo.getItems())
		{
			if(contender.equals(selectedValue.getSubValue()))
			{
				_subValueCombo.select(i);
				break;
			}
			i++;
		}
	}

	private void addTag()
	{
		List<DataChangeType> modTypes = new ArrayList<>();
		_catCombo.getText();
		TagCategory cat;
		int catSel = _catCombo.getSelectionIndex();
		if(catSel == -1)
		{
			cat = PhotoTagModelFactory.eINSTANCE.createTagCategory();
			cat.setName(_catCombo.getText());
			ModelManager.getInstance().getModel().getTagCategoriesList().add(cat);
			catSel = ModelManager.getInstance().getModel().getTagCategoriesList().size() - 1;
			modTypes.add(DataChangeType.ADD_CATEGORY);
		}
		else
		{
			cat = ModelManager.getInstance().getModel().getTagCategories()[_catCombo.getSelectionIndex()];
		}

		int tagSel = _tagCombo.getSelectionIndex();
		Tag mainTag;
		if(tagSel == -1)
		{
			mainTag = PhotoTagModelFactory.eINSTANCE.createTag();
			mainTag.setName(_tagCombo.getText());
			cat.getTagsList().add(mainTag);
			tagSel = cat.getTagsLength() - 1;
			modTypes.add(DataChangeType.ADD_TAG);
		}
		else
		{
			mainTag = cat.getTags(tagSel);
		}

		int subSel = _subCombo.getSelectionIndex();
		Tag subTag = null;
		if(!_subCombo.getText().isEmpty())
		{
			if(subSel == -1)
			{
				subTag = PhotoTagModelFactory.eINSTANCE.createTag();
				subTag.setName(_subCombo.getText());
				mainTag.getSubTagList().add(subTag);
				subSel = mainTag.getSubTagLength() - 1;
				modTypes.add(DataChangeType.ADD_SUBTAG);
			}
			else
			{
				subTag = mainTag.getSubTag(subSel);
			}
		}
		String mainValue = _valueCombo.getText();
		String subValue = _subValueCombo.getText().trim();
		if(subValue.isEmpty())
		{
			subValue = null;
			subTag = null;
		}

		TagValue value = findValue(mainTag, subTag, mainValue, subValue);
		if(value == null)
		{
			value = PhotoTagModelFactory.eINSTANCE.createTagValue();
			value.setTag(mainTag);
			value.setSubTag(subTag);
			value.setValue(mainValue);
			value.setSubValue(subValue);
			ModelManager.getInstance().getModel().getValuesList().add(value);
			modTypes.add(DataChangeType.ADD_VALUE);
		}

		value.setXPercentage(_x);
		value.setYPercentage(_y);
		CoordinatesCoordinator.getInstance().reset();
		_x = _y = 0;

		_selectedImage.getTagValuesList().add(value);
		modTypes.add(DataChangeType.VALUE_USAGE);

		// So if we already had the tag without a subtag we should remove it, so that in effect we have just added a subtag
		if(subTag != null)
		{
			value = findValue(mainTag, null, mainValue, null);

			if(_selectedImage.getTagValuesList().contains(value))
			{
				_selectedImage.getTagValuesList().remove(value);
			}
		}

		ModelManager.getInstance().notifyModelContentChangeListeners(modTypes);
	}

	private TagValue findValue(Tag mainTag, Tag subTag, String mainValue, String subValue)
	{
		for(TagValue value : ModelManager.getInstance().getModel().getValuesList())
		{
			if(value.getTag() == mainTag && value.getSubTag() == subTag && value.getValue().equals(mainValue))
			{
				if((subTag == null && value.getSubTag() == null)
						|| (value.getSubTag() == subTag && value.getSubValue() != null && value.getSubValue().equals(subValue)))
				{
					return value;
				}
			}
		}
		return null;
	}

	private void populateCategoryCombo()
	{
		int catSelected = _catCombo.getSelectionIndex();

		_catCombo.setItems(ModelManager.getCategories());
		if(catSelected >= 0)
		{
			_catCombo.select(catSelected);
		}

	}

	private void populateTagCombo()
	{
		int catSelected = _catCombo.getSelectionIndex();
		int tagSelected = _tagCombo.getSelectionIndex();

		_tagCombo.setItems(ModelManager.getTags(catSelected));
		if(tagSelected < 0)
		{
			tagSelected = 0;
		}
		_tagCombo.select(tagSelected);

	}

	private void populateTagValueCombo()
	{
		int catSelected = _catCombo.getSelectionIndex();

		int tagSelected = _tagCombo.getSelectionIndex();

		String[] vals = ModelManager.getTagValues(catSelected, tagSelected);
		if(vals.length > 0)
		{
			_valueCombo.setItems(vals);
		}
		else
		{
			_valueCombo.removeAll();
		}
		_valueCombo.select(0);

	}

	private void populateSubCombo()
	{
		int catSelected = _catCombo.getSelectionIndex();

		int tagSelected = _tagCombo.getSelectionIndex();

		int subSelected = _subCombo.getSelectionIndex();

		_subCombo.setItems(ModelManager.getSubsTags(catSelected, tagSelected));
		if(subSelected < 0)
		{
			subSelected = 0;
		}
		_subCombo.select(subSelected);

	}

	private void populateSubValueCombo()
	{
		int catSelected = _catCombo.getSelectionIndex();

		int tagSelected = _tagCombo.getSelectionIndex();

		int subSelected = _subCombo.getSelectionIndex();

		String[] subs = ModelManager.getSubValues(catSelected, tagSelected, subSelected, _valueCombo.getText().trim(), " ");
		if(subs.length > 0)
		{
			_subValueCombo.setItems(subs);
		}
		else
		{
			_subValueCombo.removeAll();
		}
		_subValueCombo.select(0);

	}

	/**
	 * Handle a UI selection
	 * 
	 * @param selection the selection
	 */
	@Inject
	public void receiveSelection(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Object selection)
	{
		if(selection != null && selection instanceof Image)
		{
			_selectedImage = (Image)selection;
			_tableViewer.setInput(((Image)selection).getTagValues());
			_tableViewer.refresh();
		}
	}

	@Override
	public void modelContentChanged(List<DataChangeType> types)
	{
		if(_sync == null)
			return;
		_sync.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				if(types.contains(DataChangeType.ADD_CATEGORY) || types.contains(DataChangeType.MODIFY_CATEGORY))
					populateCategoryCombo();
				if(types.contains(DataChangeType.ADD_TAG) || types.contains(DataChangeType.MODIFY_TAG))
					populateTagCombo();
				if(types.contains(DataChangeType.ADD_VALUE) || types.contains(DataChangeType.MODIFY_VALUE))
					populateTagValueCombo();
				if(types.contains(DataChangeType.ADD_SUBTAG) || types.contains(DataChangeType.MODIFY_TAG))
					populateSubCombo();
				if(types.contains(DataChangeType.ADD_VALUE) || types.contains(DataChangeType.MODIFY_VALUE))
					populateSubValueCombo();
				if(_selectedImage != null)
					_tableViewer.setInput(_selectedImage.getTagValues());
				_tableViewer.refresh();
			}
		});
	}

	@Override
	public void notifyNewCoordinates(boolean fromData)
	{
		_x = CoordinatesCoordinator.getInstance().getX();
		_y = CoordinatesCoordinator.getInstance().getY();
	}
}
