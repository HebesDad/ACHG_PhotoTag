package org.achg.phototag.views;

import java.awt.FileDialog;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.achg.phototag.FileUtil;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.ui.TaggerImages;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ImageDisplayView {
	Label _imageNameLabel;
	Label _imageLabel;
	Button _saveCopyButton;
	
//	@PreDestroy
//	public void goodbye()
//	{
//		//try to ensure we release any handle
//		org.eclipse.swt.graphics.Image img = _imageLabel.getImage();
//		if (img != null)
//		{
//			_imageLabel.setImage(null);
//			img.dispose();
//		}
//	}

	@PostConstruct
	public void create(Composite viewParent, EPartService partService, Shell shell) {
		GridLayout layout = new GridLayout(2, false);
		viewParent.setLayout(layout);
		
		 _saveCopyButton = new Button(viewParent,SWT.NONE);
		_saveCopyButton.setImage(TaggerImages.IMG_SAVECOPY16);
		_saveCopyButton.setToolTipText("Save a copy");
		GridData gd = new GridData(SWT.LEFT		, SWT.TOP, false, false);
		_saveCopyButton.setLayoutData(gd);
		_saveCopyButton.setEnabled(false);
		_saveCopyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.FileDialog dialog = new org.eclipse.swt.widgets.FileDialog(shell);
				dialog.setFileName(_imageNameLabel.getText().substring(_imageNameLabel.getText().lastIndexOf('\\')+1));
				String result =dialog.open();
				if (result != null)
				{
					FileUtil.copyFile(ModelManager.getInstance().getModel(), ModelManager.getInstance().getImagesRoot().getAbsolutePath() + "\\" + _imageNameLabel.getText(), result);
				}
			}
		});
		

		_imageNameLabel = new Label(viewParent, SWT.NONE);
		 gd = new GridData(SWT.CENTER, SWT.TOP, true, false);
		_imageNameLabel.setLayoutData(gd);

		Composite imageComposite = new Composite(viewParent, SWT.FILL);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan=2;
		imageComposite.setLayoutData(gd);
		 layout = new GridLayout(1, false);
			imageComposite.setLayout(layout);

		_imageLabel = new Label(imageComposite, SWT.FILL);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		_imageLabel.setLayoutData(gd);

	
	}

	@Inject
	public void receiveSelection(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		if (selection != null && selection instanceof Image) {
			_imageNameLabel.setText(((Image) selection).getName());
			_imageNameLabel.requestLayout();
			_saveCopyButton.setEnabled(true);
			String rootPath = ModelManager.getInstance().getImagesRoot().getAbsolutePath();

			paintImage(rootPath + "\\" + ((Image) selection).getName());
			
		}
	}

	private void paintImage(String imageFileName) {
		try {
			org.eclipse.swt.graphics.Image oldImage = _imageLabel.getImage();
			if (oldImage != null) {
				_imageLabel.setImage(null);
				oldImage.dispose();
			}

			ImageDescriptor desc = ImageDescriptor.createFromURL(new URL("file", null, imageFileName));
			org.eclipse.swt.graphics.Image swtImage = desc.createImage();

			float scaleFactorH = ((float) (_imageLabel.getParent().getSize().x - 20)) / swtImage.getBounds().width;
			float scaleFactorV = ((float) (_imageLabel.getParent().getSize().y - 20)) / swtImage.getBounds().height;

			float scaleFactor = scaleFactorH < scaleFactorV ? scaleFactorH : scaleFactorV;

			org.eclipse.swt.graphics.Image scaled = new org.eclipse.swt.graphics.Image(_imageLabel.getDisplay(),
					swtImage.getImageData().scaledTo((int) (swtImage.getBounds().width * scaleFactor),
							(int) (swtImage.getBounds().height * scaleFactor)));

			_imageLabel.setImage(scaled);

			_imageLabel.requestLayout();
			swtImage.dispose();
			_imageNameLabel.getParent().requestLayout();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
