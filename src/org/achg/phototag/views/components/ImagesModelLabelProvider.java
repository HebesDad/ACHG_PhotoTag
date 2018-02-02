package org.achg.phototag.views.components;

import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;
import org.achg.phototag.ui.TaggerImages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for the images list
 */
public class ImagesModelLabelProvider extends LabelProvider
{
	private org.achg.phototag.generated.model.PhotoTagModel.Image _parentImage = null;

	/**
	 * Tell the provider which image is being displayed (so it can know about tag value coordinates)
	 * 
	 * @param img the image being displayed
	 */
	public void setParentImage(org.achg.phototag.generated.model.PhotoTagModel.Image img)
	{
		_parentImage = img;
	}

	@Override
	public Image getImage(Object element)
	{
		if(element instanceof TagValue && _parentImage != null)
		{
			for(TagValueCoordinate coord : _parentImage.getTagValueCoordinatesList())
			{
				if(coord.getTagValue() == element)
				{
					return TaggerImages.IMG_CROSSHAIR16;
				}
			}

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
