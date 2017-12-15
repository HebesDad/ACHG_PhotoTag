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

public class TaggerImages {
	private static ImageRegistry _imageRegistry = JFaceResources.getImageRegistry(); // ideally replace with e4
																						// ResourcePool usage
	private final static String ICONS_PATH = "$nl$/icons/"; //$NON-NLS-1$

	private static final String TAG16 = "tag16.png"; //$NON-NLS-1$
	private static final String CATEGORY16 = "category.png"; //$NON-NLS-1$
	private static final String VALUE16 = "value.png"; //$NON-NLS-1$
	private static final String FOLDER16 = "folder.png"; //$NON-NLS-1$
	private static final String IMAGE16 = "image.png"; //$NON-NLS-1$
	private static final String SAVECOPY16 = "savecopy.png"; //$NON-NLS-1$

	public static final Image IMG_TAG16 = getImage(TAG16);
	public static final Image IMG_CATEGORY16 = getImage(CATEGORY16);
	public static final Image IMG_VALUE16 = getImage(VALUE16);
	public static final Image IMG_FOLDER16 = getImage(FOLDER16);
	public static final Image IMG_IMAGE16 = getImage(IMAGE16);
	public static final Image IMG_SAVECOPY16 = getImage(SAVECOPY16);

	public static final ImageDescriptor DESC_TAG16 = getImageDescriptor(TAG16);
	public static final ImageDescriptor DESC_CATEGORY16 = getImageDescriptor(CATEGORY16);
	public static final ImageDescriptor DESC_VALUE16 = getImageDescriptor(VALUE16);
	public static final ImageDescriptor DESC_FOLDER16 = getImageDescriptor(FOLDER16);
	public static final ImageDescriptor DESC_IMAGE16 = getImageDescriptor(IMAGE16);
	public static final ImageDescriptor DESC_SAVECOPY16 = getImageDescriptor(SAVECOPY16);
	
	private static boolean _initialized=false;

	public static void initialize() {
		ImageRegistry reg =JFaceResources.getImageRegistry() ;
		initDescription(reg, ICONS_PATH, TAG16);
		initDescription(reg, ICONS_PATH, CATEGORY16);
		initDescription(reg, ICONS_PATH, VALUE16);
		initDescription(reg, ICONS_PATH, FOLDER16);
		initDescription(reg, ICONS_PATH, IMAGE16);
		initDescription(reg, ICONS_PATH, SAVECOPY16);
		_initialized=true;
	}

	private static void initDescription(ImageRegistry reg, String location, String name) {
		ImageDescriptor desc = getDescriptor(location + name);
		reg.put(name, desc);
	}

	private static ImageDescriptor getDescriptor(String name) {
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
	private static Image getImage(String symbolicName) {
		if (!_initialized)
			initialize();
		return _imageRegistry.get(symbolicName);
	}

	/**
	 * Get the image descriptor
	 *
	 * @param symbolicName
	 *            Name of image descriptor to return
	 * @return The image descriptor
	 */
	private static ImageDescriptor getImageDescriptor(String symbolicName) {
		return _imageRegistry.getDescriptor(symbolicName);
	}

}
