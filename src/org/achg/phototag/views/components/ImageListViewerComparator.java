package org.achg.phototag.views.components;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * Comparator for objects in the image list viewer
 */
public class ImageListViewerComparator extends ViewerComparator
{
	@Override
	public int compare(Viewer viewer, Object o1, Object o2)
	{
		if(o1 instanceof Folder && o2 instanceof Image)
		{
			return -1;
		}
		if(o2 instanceof Folder && o1 instanceof Image)
		{
			return 1;
		}
		if(o1 instanceof Folder && o2 instanceof Folder)
		{
			return ((Folder)o1).getName().compareTo((((Folder)o2).getName()));
		}
		if(o1 instanceof Image && o2 instanceof Image)
		{
			return ((Image)o1).getName().compareTo((((Image)o2).getName()));
		}
		// should not get here
		return 0;
	};
}
