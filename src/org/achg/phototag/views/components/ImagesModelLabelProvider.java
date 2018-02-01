package org.achg.phototag.views.components;

import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.ui.TaggerImages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for the images list
 */
public class ImagesModelLabelProvider extends LabelProvider
{
	@Override
	public Image getImage(Object element)
	{
		if(element instanceof TagValue)
		{
			if(((TagValue)element).getXPercentage() > 0)
				return TaggerImages.IMG_CROSSHAIR16;
		}
		return null;
	}

	@Override
	public String getText(Object element)
	{
		if(element instanceof TagValue)
		{
			TagValue value = (TagValue)element;
			StringBuilder sb = new StringBuilder();

			EObject obj = value.getTag().eContainer();
			if(obj instanceof TagCategory)
			{
				sb.append(((TagCategory)obj).getName());
				sb.append(" / ");
			}
			sb.append(value.getTag().getName());
			sb.append("=");
			sb.append(value.getValue());
			if(value.getSubTag() != null)
			{
				sb.append(" , ");
				sb.append(value.getSubTag().getName());
				sb.append("=");
				sb.append(value.getSubValue());
			}

			return sb.toString();
		}
		return null;
	}
}
