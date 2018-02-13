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
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;
import org.achg.phototag.model.DataChangeType;
import org.achg.phototag.model.DataChanger;
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
import org.eclipse.jface.viewers.StructuredSelection;
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
	private ImagesModelLabelProvider _imagesModelLabelProvider = new ImagesModelLabelProvider();
	private TagValue _selectedValue = null;

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
		_tableViewer.setLabelProvider(_imagesModelLabelProvider);
		Table table = (Table)_tableViewer.getControl();
		table.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				processTableSelection();
			}
		});

		Composite deleteComposite = new Composite(viewParent, SWT.NONE);
		GridLayout gl = new GridLayout(1, true);
		deleteComposite.setLayout(gl);

		Button deleteTagValue = new Button(deleteComposite, SWT.NONE);
		deleteTagValue.setText("Delete");
		deleteTagValue.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				TagValueCoordinate doomed = null;
				for(Object selected : _tableViewer.getStructuredSelection().toList())
				{
					if(selected instanceof TagValue)
					{
						DataChanger.getInstance().removeImageValue(_selectedImage, (TagValue)selected, false);

						for(TagValueCoordinate coord : _selectedImage.getTagValueCoordinatesList())
						{
							if(coord.getTagValue() == selected)
							{
								doomed = coord;
								break;
							}
						}

					}
				}
				if(doomed != null)
					DataChanger.getInstance().removeCoordinate(_selectedImage, doomed);

				ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.VALUE_USAGE));
			}
		});

		Button deleteValueCoordinates = new Button(deleteComposite, SWT.NONE);
		deleteValueCoordinates.setText("Delete Coords");
		deleteValueCoordinates.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{

				if(_selectedValue != null)
				{
					TagValueCoordinate victim = null;
					for(TagValueCoordinate coord : _selectedImage.getTagValueCoordinatesList())
					{
						if(coord.getTagValue() == _selectedValue)
						{
							victim = coord;
						}
					}
					DataChanger.getInstance().removeCoordinate(_selectedImage, victim);

					CoordinatesCoordinator.getInstance().clickedAt(0, 0, true);
					_tableViewer.refresh();
				}
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
		_selectedValue = (TagValue)((IStructuredSelection)_tableViewer.getSelection()).getFirstElement();
		TagCategory cat = (TagCategory)_selectedValue.getTag().eContainer();
		_catCombo.select(ModelManager.getInstance().getModel().getTagCategoriesList().indexOf(cat));

		populateTagCombo();

		_tagCombo.select(cat.getTagsList().indexOf(_selectedValue.getTag()));

		populateTagValueCombo();

		int i = 0;
		for(String contender : _valueCombo.getItems())
		{
			if(contender.equals(_selectedValue.getValue()))
			{
				_valueCombo.select(i);
				break;
			}
			i++;
		}

		populateSubCombo();

		// even if we have no sub tag, the combo may have been set previously with sub tag values, so we need to refresh

		_subCombo.select(_selectedValue.getTag().getSubTagList().indexOf(_selectedValue.getSubTag()));
		populateSubValueCombo();

		i = 0;
		for(String contender : _subValueCombo.getItems())
		{
			if(contender.equals(_selectedValue.getSubValue()))
			{
				_subValueCombo.select(i);
				break;
			}
			i++;
		}

		for(TagValueCoordinate coord : _selectedImage.getTagValueCoordinatesList())
		{
			if(coord.getTagValue() == _selectedValue)
			{
				CoordinatesCoordinator.getInstance().clickedAt(coord.getXPercentage(), coord.getYPercentage(), true);
				return;
			}
		}
		CoordinatesCoordinator.getInstance().clickedAt(0, 0, true);
	}

	private void addTag()
	{
		// reset coords so we don't inherit values from a previous TagVal;ue
		_x = _y = 0;
		CoordinatesCoordinator.getInstance().reset();

		List<DataChangeType> modTypes = new ArrayList<>();
		_catCombo.getText();
		TagCategory cat;
		int catSel = _catCombo.getSelectionIndex();
		if(catSel == -1)
		{
			cat = PhotoTagModelFactory.eINSTANCE.createTagCategory();
			cat.setName(_catCombo.getText());

			DataChanger.getInstance().addCategory(cat, false);
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
			mainTag.setName(_tagCombo.getText().trim());
			DataChanger.getInstance().addTag(cat, mainTag, false);

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
				subTag.setName(_subCombo.getText().trim());
				DataChanger.getInstance().addTag(mainTag, subTag, false);

				subSel = mainTag.getSubTagLength() - 1;
				modTypes.add(DataChangeType.ADD_SUBTAG);
			}
			else
			{
				subTag = mainTag.getSubTag(subSel);
			}
		}
		String mainValue = _valueCombo.getText().trim();
		String subValue = _subValueCombo.getText().trim();
		if(subValue.isEmpty())
		{
			subValue = null;
			subTag = null;
		}

		TagValue value = ModelManager.getInstance().findValue(mainTag, subTag, mainValue, subValue);
		if(value == null)
		{
			value = PhotoTagModelFactory.eINSTANCE.createTagValue();
			value.setTag(mainTag);
			value.setSubTag(subTag);
			value.setValue(mainValue);
			value.setSubValue(subValue);
			DataChanger.getInstance().addTagValue(value, false);

			modTypes.add(DataChangeType.ADD_VALUE);
		}

		DataChanger.getInstance().addImageValue(_selectedImage, value, false);

		modTypes.add(DataChangeType.VALUE_USAGE);

		// So if we already had the tag without a subtag we should remove it, so that in effect we have just added a subtag
		if(subTag != null)
		{
			TagValue oldvalue = ModelManager.getInstance().findValue(mainTag, null, mainValue, null);

			if(_selectedImage.getTagValuesList().contains(oldvalue))
			{
				DataChanger.getInstance().removeImageValue(_selectedImage, oldvalue, false);

			}

			TagValueCoordinate victim = null;
			for(TagValueCoordinate coord : _selectedImage.getTagValueCoordinatesList())
			{
				if(coord.getTagValue() == value)
				{
					victim = coord;
					break;
				}
			}
			if(victim != null)
			{
				DataChanger.getInstance().removeCoordinate(_selectedImage, victim);

			}
		}

		ModelManager.getInstance().notifyModelContentChangeListeners(modTypes);
		_tableViewer.setSelection(new StructuredSelection(value));
		_selectedValue = value;
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
			_imagesModelLabelProvider.setParentImage(_selectedImage);
			_tableViewer.refresh();

			IStructuredSelection sel = (IStructuredSelection)_tableViewer.getSelection();
			if(sel != null && sel.getFirstElement() != null)
			{
				for(TagValueCoordinate coord : _selectedImage.getTagValueCoordinatesList())
				{
					if(coord.getTagValue() == sel.getFirstElement())
					{
						CoordinatesCoordinator.getInstance().clickedAt(coord.getXPercentage(), coord.getYPercentage(), true);
						return;
					}
				}
			}
		}
	}

	@Override
	public void modelContentChanged(List<DataChangeType> types)
	{
		if(_sync == null)
			return;
		_sync.syncExec(new Runnable()
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
		if(!fromData)
		{
			if(_selectedValue == null)
			{
				CoordinatesCoordinator.getInstance().reset();
				return;
			}
			_x = CoordinatesCoordinator.getInstance().getX();
			_y = CoordinatesCoordinator.getInstance().getY();
			for(TagValueCoordinate coord : _selectedImage.getTagValueCoordinatesList())
			{
				if(coord.getTagValue() == _selectedValue)
				{
					coord.setXPercentage(_x);
					coord.setYPercentage(_y);
					CoordinatesCoordinator.getInstance().clickedAt(_x, _y, true);
					return;
				}
			}

			TagValueCoordinate coord = PhotoTagModelFactory.eINSTANCE.createTagValueCoordinate();
			coord.setTagValue(_selectedValue);
			coord.setXPercentage(_x);
			coord.setYPercentage(_y);

			DataChanger.getInstance().addCoordinates(_selectedImage, coord);

			CoordinatesCoordinator.getInstance().clickedAt(_x, _y, true);

			_tableViewer.refresh();
		}
	}
}
