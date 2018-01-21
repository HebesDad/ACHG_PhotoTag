package org.achg.phototag.views.components;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.ui.TaggerImages;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ModelLabelProvider extends LabelProvider
{

	@Override
	public Image getImage(Object element)
	{
		if(element instanceof TagCategory)
		{
			return TaggerImages.IMG_CATEGORY16;
		}
		if(element instanceof Tag)
		{
			return TaggerImages.IMG_TAG16;
		}
		if(element instanceof TagValue)
		{
			return TaggerImages.IMG_VALUE16;
		}
		if(element instanceof org.achg.phototag.generated.model.PhotoTagModel.Image)
		{
			return TaggerImages.IMG_IMAGE16;
		}
		if(element instanceof Folder)
		{
			return TaggerImages.IMG_FOLDER16;
		}

		return null;
	}

	@Override
	public String getText(Object element)
	{
		if(element instanceof TagCategory)
			return ((TagCategory)element).getName();
		if(element instanceof Tag)
			return ((Tag)element).getName();
		if(element instanceof TagValue)
		{
			if (((TagValue)element).getSubTag()!=null)
			{
				return ((TagValue)element).getValue() + ", " + ((TagValue)element).getSubValue();
			}
			return ((TagValue)element).getValue();
		}
		if(element instanceof org.achg.phototag.generated.model.PhotoTagModel.Image)
		{
			String name = ((org.achg.phototag.generated.model.PhotoTagModel.Image)element).getName();
			if(name.contains("\\"))
			{
				return name.substring(name.lastIndexOf('\\') + 1);
			}
			return name;
		}
		if(element instanceof Folder)
		{
			String name = ((Folder)element).getName();
			if(name == null)
				name = "<top>";
			if(name.contains("\\"))
			{
				return name.substring(name.lastIndexOf('\\') + 1);
			}
			return name;
		}
		return null;
	}
}
