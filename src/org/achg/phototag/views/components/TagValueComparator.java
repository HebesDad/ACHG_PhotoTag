package org.achg.phototag.views.components;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class TagValueComparator extends ViewerComparator
{
	ImagesModelLabelProvider labelProvider = new ImagesModelLabelProvider();

	public int compare(Viewer viewer, Object o1, Object o2)
	{
		String s1 = labelProvider.getText(o1);
		String s2 = labelProvider.getText(o2);
		return s1.compareTo(s2);
	}
}
