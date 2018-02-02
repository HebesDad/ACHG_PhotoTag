package org.achg.phototag.views;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.achg.phototag.FileUtil;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.ui.TaggerImages;
import org.achg.phototag.views.components.CoordinatesCoordinator;
import org.achg.phototag.views.components.ICoordinatesListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * View for displaying images
 */
public class ImageDisplayView implements ICoordinatesListener, PaintListener
{
	/** Part ID */
	public static final String ID = "org.achg.phototag.part.imageDisplayPart";

	private Label _imageNameLabel;
	private Canvas _imageCanvas;
	private Button _saveCopyButton;
	private org.eclipse.swt.graphics.Image _scaledImage;

	/**
	 * Create the UI components
	 * 
	 * @param viewParent the parent composite
	 * @param partService the Eclipse part service
	 * @param shell the main shell
	 */
	@PostConstruct
	public void create(Composite viewParent, EPartService partService, Shell shell)
	{
		GridLayout layout = new GridLayout(2, false);
		viewParent.setLayout(layout);

		_saveCopyButton = new Button(viewParent, SWT.NONE);
		_saveCopyButton.setImage(TaggerImages.IMG_SAVECOPY16);
		_saveCopyButton.setToolTipText("Save a copy");
		GridData gd = new GridData(SWT.LEFT, SWT.TOP, false, false);
		_saveCopyButton.setLayoutData(gd);
		_saveCopyButton.setEnabled(false);
		_saveCopyButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				FileDialog dialog = new FileDialog(shell);
				dialog.setFileName(_imageNameLabel.getText().substring(_imageNameLabel.getText().lastIndexOf('\\') + 1));

				ModelManager manager = ModelManager.getInstance();

				String result = dialog.open();
				if(result != null)
				{
					FileUtil.copyFile(manager.getModel(), manager.getImagesRoot().getAbsolutePath() + "\\" + _imageNameLabel.getText(), result);
				}
			}
		});

		_imageNameLabel = new Label(viewParent, SWT.NONE);
		gd = new GridData(SWT.CENTER, SWT.TOP, true, false);
		_imageNameLabel.setLayoutData(gd);

		Composite imageComposite = new Composite(viewParent, SWT.FILL);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 2;
		imageComposite.setLayoutData(gd);
		layout = new GridLayout(1, false);
		imageComposite.setLayout(layout);

		_imageCanvas = new Canvas(imageComposite, SWT.FILL);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		_imageCanvas.setLayoutData(gd);
		_imageCanvas.addPaintListener(this);
		_imageCanvas.addListener(SWT.MouseDown, new Listener()
		{

			@Override
			public void handleEvent(Event event)
			{
				double x = (((double)event.x) / _scaledImage.getBounds().width) * 100;
				double y = (((double)event.y) / _scaledImage.getBounds().height) * 100;
				CoordinatesCoordinator.getInstance().clickedAt(x, y, false);

			}
		});

		CoordinatesCoordinator.getInstance().addListener(this);

	}

	@Override
	public void paintControl(PaintEvent e)
	{
		if(_scaledImage != null)
		{
			e.gc.drawImage(_scaledImage, 0, 0);
			if(CoordinatesCoordinator.getInstance().getX() > 0)
			{

				ImageData crosshairImageData = TaggerImages.DESC_GREEN_CROSSHAIR.getImageData(100);
				ImageData displayedImageData = _scaledImage.getImageData();

				int destX = (int)(((CoordinatesCoordinator.getInstance().getX() / 100) * (displayedImageData.width)) - (crosshairImageData.width / 2));
				int destY = (int)(((CoordinatesCoordinator.getInstance().getY() / 100) * (displayedImageData.height)) - (crosshairImageData.height / 2));

				e.gc.drawImage(TaggerImages.IMG_GREEN_CROSSHAIR, /* src x */0, /* src y */0, /* src width */crosshairImageData.width,
						/* src height */ crosshairImageData.height, destX, destY, crosshairImageData.width, crosshairImageData.height);
			}

		}
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
			_imageNameLabel.setText(((Image)selection).getName());
			_imageNameLabel.requestLayout();
			_saveCopyButton.setEnabled(true);

			String rootPath = ModelManager.getInstance().getImagesRoot().getAbsolutePath();

			paintImage(rootPath + "\\" + ((Image)selection).getName());

			IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("org.achg.phototag");

			Preferences rootPreferences = preferences.node("root");
			rootPreferences.put("selectedImage", ((Image)selection).getName());

			try
			{
				preferences.flush();
			}
			catch(BackingStoreException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void paintImage(String imageFileName)
	{
		try
		{

			if(_scaledImage != null)
			{
				_scaledImage.dispose();
			}
			CoordinatesCoordinator.getInstance().reset();

			ImageDescriptor desc = ImageDescriptor.createFromURL(new URL("file", null, imageFileName));
			org.eclipse.swt.graphics.Image swtImage = desc.createImage();

			float scaleFactorH = ((float)(_imageCanvas.getParent().getSize().x - 20)) / swtImage.getBounds().width;
			float scaleFactorV = ((float)(_imageCanvas.getParent().getSize().y - 20)) / swtImage.getBounds().height;

			float scaleFactor = scaleFactorH < scaleFactorV ? scaleFactorH : scaleFactorV;

			_scaledImage = new org.eclipse.swt.graphics.Image(_imageCanvas.getDisplay(),
					swtImage.getImageData().scaledTo((int)(swtImage.getBounds().width * scaleFactor), (int)(swtImage.getBounds().height * scaleFactor)));

			_imageCanvas.redraw();
			swtImage.dispose();
			_imageNameLabel.getParent().requestLayout();
			desc.destroyResource(swtImage);

		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void notifyNewCoordinates(boolean fromData)
	{
		if(fromData)
		{
			_imageCanvas.redraw();
		}

	}
}
