package org.achg.phototag.views.components;

import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * Image list tree content provider
 */
public class ImageListContentProvider implements ITreeContentProvider
{

	@Override
	public Object[] getElements(Object inputElement)
	{
		if(inputElement instanceof ImageListInput)
		{
			return ((ImageListInput)inputElement).getContent();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement)
	{
		List<EObject> result = new ArrayList<>();

		if(parentElement instanceof Folder)
		{
			for(Folder folder : ((Folder)parentElement).getFolders())
			{
				result.add(folder);
			}

			for(Image image : ((Folder)parentElement).getImages())
			{
				result.add(image);
			}
		}

		return result.toArray(new Object[result.size()]);
	}

	@Override
	public Object getParent(Object element)
	{
		// not needed
		return null;
	}

	@Override
	public boolean hasChildren(Object element)
	{
		return element instanceof Folder;
	}

}
