package org.achg.phototag.ui;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Plugin images
 */
public class TaggerImages
{
	private static ImageRegistry _imageRegistry = JFaceResources.getImageRegistry(); // ideally replace with e4 ResourcePool usage
	private final static String ICONS_PATH = "$nl$/icons/"; //$NON-NLS-1$

	private static final String TAG16 = "tag16.png"; //$NON-NLS-1$
	private static final String CATEGORY16 = "category.png"; //$NON-NLS-1$
	private static final String VALUE16 = "value.png"; //$NON-NLS-1$
	private static final String FOLDER16 = "folder.png"; //$NON-NLS-1$
	private static final String IMAGE16 = "image.png"; //$NON-NLS-1$
	private static final String SAVECOPY16 = "savecopy.png"; //$NON-NLS-1$
	private static final String CROSSHAIR16 = "crosshair16.png"; //$NON-NLS-1$

	/** Tag image */
	public static final Image IMG_TAG16 = getImage(TAG16);
	/** Category image */
	public static final Image IMG_CATEGORY16 = getImage(CATEGORY16);
	/** Value image */
	public static final Image IMG_VALUE16 = getImage(VALUE16);
	/** Folder image */
	public static final Image IMG_FOLDER16 = getImage(FOLDER16);
	/** Image image */
	public static final Image IMG_IMAGE16 = getImage(IMAGE16);
	/** Save image */
	public static final Image IMG_SAVECOPY16 = getImage(SAVECOPY16);
	/** crosshair image */
	public static final Image IMG_CROSSHAIR16 = getImage(CROSSHAIR16);

	/** Tag image descriptor */
	public static final ImageDescriptor DESC_TAG16 = getImageDescriptor(TAG16);
	/** Category image descriptor */
	public static final ImageDescriptor DESC_CATEGORY16 = getImageDescriptor(CATEGORY16);
	/** Value image descriptor */
	public static final ImageDescriptor DESC_VALUE16 = getImageDescriptor(VALUE16);
	/** Folder image descriptor */
	public static final ImageDescriptor DESC_FOLDER16 = getImageDescriptor(FOLDER16);
	/** Image image descriptor */
	public static final ImageDescriptor DESC_IMAGE16 = getImageDescriptor(IMAGE16);
	/** Save image descriptor */
	public static final ImageDescriptor DESC_SAVECOPY16 = getImageDescriptor(SAVECOPY16);
	/** Crosshair image descriptor */
	public static final ImageDescriptor DESC_CROSSHAIR16 = getImageDescriptor(CROSSHAIR16);

	private static boolean _initialized = false;

	/**
	 * Initialise the image registry
	 */
	public static void initialize()
	{
		ImageRegistry reg = JFaceResources.getImageRegistry();
		initDescription(reg, ICONS_PATH, TAG16);
		initDescription(reg, ICONS_PATH, CATEGORY16);
		initDescription(reg, ICONS_PATH, VALUE16);
		initDescription(reg, ICONS_PATH, FOLDER16);
		initDescription(reg, ICONS_PATH, IMAGE16);
		initDescription(reg, ICONS_PATH, SAVECOPY16);
		initDescription(reg, ICONS_PATH, CROSSHAIR16);
		_initialized = true;
	}

	private static void initDescription(ImageRegistry reg, String location, String name)
	{
		ImageDescriptor desc = getDescriptor(location + name);
		reg.put(name, desc);
	}

	private static ImageDescriptor getDescriptor(String name)
	{
		Bundle bundle = FrameworkUtil.getBundle(TaggerImages.class);
		// use the org.eclipse.core.runtime.Path as import
		URL url = FileLocator.find(bundle, new Path(name), null);
		return ImageDescriptor.createFromURL(url);
	}

	/**
	 * Get the image
	 *
	 * @param symbolicName
	 *            Name of image to return
	 * @return The image
	 */
	private static Image getImage(String symbolicName)
	{
		if(!_initialized)
		{
			initialize();
		}

		return _imageRegistry.get(symbolicName);
	}

	/**
	 * Get the image descriptor
	 *
	 * @param symbolicName
	 *            Name of image descriptor to return
	 * @return The image descriptor
	 */
	private static ImageDescriptor getImageDescriptor(String symbolicName)
	{
		return _imageRegistry.getDescriptor(symbolicName);
	}

}
