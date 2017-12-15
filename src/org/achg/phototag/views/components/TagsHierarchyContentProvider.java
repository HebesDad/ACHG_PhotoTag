package org.achg.phototag.views.components;


import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.model.ModelManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class TagsHierarchyContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Root)
			return ((Root) inputElement).getTagCategoriesList().toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Root)
			return ((Root) parentElement).getTagCategoriesList().toArray();
		if (parentElement instanceof TagCategory)
			return ((TagCategory) parentElement).getTags();
		if (parentElement instanceof Tag)
		{
			List<Object> result = new ArrayList<>();
			for (Tag subtag: ((Tag) parentElement).getSubTag())
			{
			result.add(subtag);
			}
			
			//probably a bit more to do here with subtags and their values
			for (TagValue value:ModelManager.getInstance().getModel().getValues())
			{
				if (value.getTag()==parentElement)
				{
					result.add(value);
				}
			}
			return result.toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// no need
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof TagValue)
			return false;
		return true;
	}

}
