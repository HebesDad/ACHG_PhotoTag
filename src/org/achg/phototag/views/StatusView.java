package org.achg.phototag.views;

import javax.inject.Inject;

import org.achg.phototag.model.IModelStatusChangeListener;
import org.achg.phototag.model.ModelManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class StatusView implements IModelStatusChangeListener {

	private Label _currentStatusLabel;

	@Inject
	public void create(Composite parent, Shell shell) {
		Composite compo = new Composite(parent, SWT.BORDER | SWT.FILL);
		GridLayout layout = new GridLayout(2, false);
		compo.setLayout(layout);

		new Label(compo, SWT.NONE).setText("Status:");

		_currentStatusLabel = new Label(compo, SWT.NONE);
		GridData gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		_currentStatusLabel.setLayoutData(gd);

		modelStatusChanged();

		ModelManager.getInstance().addModelStatusChangeListener(this);
	}

	@Override
	public void modelStatusChanged() {
		if (ModelManager.getInstance().isLoaded()) {
			_currentStatusLabel.setText("Attached to: " + ModelManager.getInstance().getImagesRoot().getAbsolutePath());
		} else
			_currentStatusLabel.setText("Not attached to images");
		_currentStatusLabel.requestLayout();
	}
}
