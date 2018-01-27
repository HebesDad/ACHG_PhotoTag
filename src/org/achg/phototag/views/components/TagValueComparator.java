package org.achg.phototag.views.components;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * Comparator for tag values
 */
public class TagValueComparator extends ViewerComparator
{
	private ImagesModelLabelProvider _labelProvider = new ImagesModelLabelProvider();

	@Override
	public int compare(Viewer viewer, Object o1, Object o2)
	{
		String s1 = _labelProvider.getText(o1);
		String s2 = _labelProvider.getText(o2);
		return s1.compareTo(s2);
	}
}
