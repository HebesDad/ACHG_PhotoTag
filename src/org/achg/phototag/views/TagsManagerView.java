package org.achg.phototag.views;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.model.DataChangeType;
import org.achg.phototag.model.DataModifier;
import org.achg.phototag.model.IModelContentChangeListener;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.views.components.ModelLabelProvider;
import org.achg.phototag.views.components.TagsHierarchyContentProvider;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TagsManagerView implements IModelContentChangeListener {
	TreeViewer _treeViewer;
	Text _editText;
	Button _saveEditButton;
	Text _addCategoryText;
	Button _saveAddCategoryButton;
	Text _addTagText;
	Button _saveAddTagButton;
	Text _addSubtagText;
	Button _saveAddSubtagButton;
	Text _addValueText;
	Text _addSubValueText;
	Button _saveAddValueButton;
	Shell _shell;

	private UISynchronize _sync;

	DataModifier _modifier = new DataModifier();

	@Inject
	public TagsManagerView(UISynchronize sync) {
		_sync = sync;
	}

	@PostConstruct
	public void create(Shell shell, Composite viewParent) {
		_shell = shell;
		GridLayout layout = new GridLayout(2, false);
		viewParent.setLayout(layout);

		Group treeGroup = new Group(viewParent, SWT.FILL);
		treeGroup.setText("Category/Tag(/SubTag)/Value");
		layout = new GridLayout(1, false);
		treeGroup.setLayout(layout);

		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		treeGroup.setLayoutData(gd);

		_treeViewer = new TreeViewer(treeGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FILL);
		_treeViewer.setContentProvider(new TagsHierarchyContentProvider());
		if (ModelManager.getInstance().getModel() != null)
			_treeViewer.setInput(ModelManager.getInstance().getModel());
		_treeViewer.setLabelProvider(new ModelLabelProvider());
		_treeViewer.setAutoExpandLevel(10);
		_treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				processTreeSelection((EObject) event.getStructuredSelection().getFirstElement());
			}
		});

		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		_treeViewer.getTree().setLayoutData(gd);

		Composite addEditCompo = new Composite(viewParent, SWT.FILL);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		addEditCompo.setLayoutData(gd);
		layout = new GridLayout(1, false);
		addEditCompo.setLayout(layout);

		Group editGroup = new Group(addEditCompo, SWT.FILL);
		editGroup.setText("Edit");
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		editGroup.setLayoutData(gd);

		layout = new GridLayout(1, false);
		editGroup.setLayout(layout);

		_editText = new Text(editGroup, SWT.BORDER);
		_editText.setMessage("select an item to edit it's value here");
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_editText.setLayoutData(gd);

		_saveEditButton = new Button(editGroup, SWT.NONE);
		_saveEditButton.setText("Save");
		gd = new GridData(SWT.RIGHT, SWT.TOP, false, false);
		_saveEditButton.setLayoutData(gd);
		_saveEditButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveEdit();
			}
		});

		Group addGroup = new Group(addEditCompo, SWT.FILL);
		addGroup.setText("Add");
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		addGroup.setLayoutData(gd);

		layout = new GridLayout(2, false);
		addGroup.setLayout(layout);

		new Label(addGroup, SWT.NONE).setText("Category:");

		_addCategoryText = new Text(addGroup, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_addCategoryText.setLayoutData(gd);

		_saveAddCategoryButton = new Button(addGroup, SWT.NONE);
		_saveAddCategoryButton.setText("Save");
		gd = new GridData(SWT.RIGHT, SWT.TOP, false, false);
		gd.horizontalSpan = 2;
		_saveAddCategoryButton.setLayoutData(gd);
		_saveAddCategoryButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addCategory();
			}

		});

		new Label(addGroup, SWT.NONE).setText("Tag:");

		_addTagText = new Text(addGroup, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_addTagText.setLayoutData(gd);

		_saveAddTagButton = new Button(addGroup, SWT.NONE);
		_saveAddTagButton.setText("Save");
		gd = new GridData(SWT.RIGHT, SWT.TOP, false, false);
		gd.horizontalSpan = 2;
		_saveAddTagButton.setLayoutData(gd);
		_saveAddTagButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addTag();
			}

		});

		new Label(addGroup, SWT.NONE).setText("Subtag:");

		_addSubtagText = new Text(addGroup, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_addSubtagText.setLayoutData(gd);

		_saveAddSubtagButton = new Button(addGroup, SWT.NONE);
		_saveAddSubtagButton.setText("Save");
		gd = new GridData(SWT.RIGHT, SWT.TOP, false, false);
		gd.horizontalSpan = 2;
		_saveAddSubtagButton.setLayoutData(gd);
		_saveAddSubtagButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addSubtag();
			}

		});

		new Label(addGroup, SWT.NONE).setText("Value:");

		_addValueText = new Text(addGroup, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_addValueText.setLayoutData(gd);
		new Label(addGroup, SWT.NONE).setText("Subtag Value:");

		_addSubValueText = new Text(addGroup, SWT.BORDER);
		gd = new GridData(SWT.FILL, SWT.TOP, true, false);
		_addSubValueText.setLayoutData(gd);

		_saveAddValueButton = new Button(addGroup, SWT.NONE);
		_saveAddValueButton.setText("Save");
		gd = new GridData(SWT.RIGHT, SWT.TOP, false, false);
		gd.horizontalSpan = 2;
		_saveAddValueButton.setLayoutData(gd);
		_saveAddValueButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addValue();
			}

		});

		ModelManager.getInstance().addModelContentChangeListener(this);
		// viewParent.requestLayout();
		processTreeSelection(null);
	}

	private void addCategory() {
		String value = _addCategoryText.getText().trim();
		if (value.isEmpty()) {
			MessageDialog.openInformation(_shell, "Empty Value!", "Items cannot be created with a name");
			return;
		}
		TagCategory cat = PhotoTagModelFactory.eINSTANCE.createTagCategory();
		cat.setName(value);

		_modifier.addCategory(_shell, cat);

		_addCategoryText.setText("");

	}

	private void addTag() {
		String value = _addTagText.getText().trim();
		if (value.isEmpty()) {
			MessageDialog.openInformation(_shell, "Empty Value!", "Items cannot be created with a name");
			return;
		}

		Tag newtag = PhotoTagModelFactory.eINSTANCE.createTag();

		newtag.setName(value);
		TagCategory cat = (TagCategory) _addTagText.getData();

		_modifier.addTag(_shell, cat, newtag);

		_addTagText.setText("");

	}

	private void addSubtag() {
		String value = _addSubtagText.getText().trim();
		if (value.isEmpty()) {
			MessageDialog.openInformation(_shell, "Empty Value!", "Items cannot be created with a name");
			return;
		}
		Tag newtag = PhotoTagModelFactory.eINSTANCE.createTag();

		newtag.setName(value);
		Tag cat = (Tag) _addSubtagText.getData();

		_modifier.addSubTag(_shell, cat, newtag);

		_addSubtagText.setText("");

	}

	private void addValue() {
		String value = _addValueText.getText().trim();
		if (value.isEmpty()) {
			MessageDialog.openInformation(_shell, "Empty Value!", "Items cannot be created with a value");
			return;
		}
		TagValue tagvalue = PhotoTagModelFactory.eINSTANCE.createTagValue();

		tagvalue.setValue(value);
		tagvalue.setTag((Tag) _addValueText.getData());

		if (_addSubValueText.isEnabled()) {
			String subvalue = _addSubValueText.getText().trim();
			if (subvalue.isEmpty()) {
				MessageDialog.openInformation(_shell, "Empty Value!", "Items cannot be created with a value");
				return;
			}
			tagvalue.setSubValue(_addSubValueText.getText().trim());
			tagvalue.setSubTag((Tag) _addSubValueText.getData());
		}

		_modifier.addValue(_shell, tagvalue);

		_addValueText.setText("");
		_addSubValueText.setText("");
	}

	private void saveEdit() {
		EObject selection = (EObject) _editText.getData();
		if (selection instanceof TagCategory) {
			((TagCategory) selection).setName(_editText.getText());
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_CATEGORY));
		} else if (selection instanceof Tag) {
			((Tag) selection).setName(_editText.getText());
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_TAG));
		} else if (selection instanceof TagValue) {
			((TagValue) selection).setValue(_editText.getText());
			ModelManager.getInstance().notifyModelContentChangeListeners(Collections.singletonList(DataChangeType.MODIFY_VALUE));
		}
		
	}

	private void processTreeSelection(EObject selection) {
		if (selection instanceof TagCategory || selection instanceof Tag || selection instanceof TagValue) {
			if (selection instanceof TagCategory) {
				_editText.setText(((TagCategory) selection).getName());

				_saveAddSubtagButton.setEnabled(false);
				_saveAddTagButton.setEnabled(true);
				_saveAddValueButton.setEnabled(false);
				_addSubtagText.setEnabled(false);
				_addTagText.setEnabled(true);
				_addTagText.setData(selection);
				_addValueText.setEnabled(false);
				_addSubValueText.setEnabled(false);

			} else if (selection instanceof Tag) {
				_editText.setText(((Tag) selection).getName());

				_saveAddSubtagButton.setEnabled(true);
				_saveAddTagButton.setEnabled(false);
				_saveAddValueButton.setEnabled(true);
				_addSubtagText.setEnabled(true);
				_addSubtagText.setData(selection);
				_addTagText.setEnabled(false);
				_addValueText.setEnabled(true);
				if (selection.eContainer() instanceof Tag) {
					_addSubValueText.setEnabled(selection.eContainer() instanceof Tag);
					_addSubValueText.setData(selection);
					_addValueText.setData(selection.eContainer());
				} else {
					_addSubValueText.setEnabled(false);
					_addValueText.setData(selection);
					_addSubValueText.setData(null);
				}

			} else if (selection instanceof TagValue) {
				_editText.setText(((TagValue) selection).getValue());

				_saveAddSubtagButton.setEnabled(false);
				_saveAddTagButton.setEnabled(false);
				_saveAddValueButton.setEnabled(false);
				_addSubtagText.setEnabled(false);
				_addTagText.setEnabled(false);
				_addValueText.setEnabled(false);
				_addSubValueText.setEnabled(false);
			}
			_editText.setEnabled(true);
			_editText.setData(selection);
			_saveEditButton.setEnabled(true);
		} else {
			_editText.setText("");
			_editText.setEnabled(false);
			_saveEditButton.setEnabled(false);

			_saveAddSubtagButton.setEnabled(false);
			_saveAddTagButton.setEnabled(false);
			_saveAddValueButton.setEnabled(false);
			_addSubtagText.setEnabled(false);
			_addTagText.setEnabled(false);
			_addValueText.setEnabled(false);
			_addSubValueText.setEnabled(false);
		}
	}

	@Override
	public void modelContentChanged(List<DataChangeType> modTypes) {

		_sync.asyncExec(new Runnable() {

			@Override
			public void run() {
				_treeViewer.setInput(ModelManager.getInstance().getModel());
				_treeViewer.refresh();
			}
		});

	}
}
