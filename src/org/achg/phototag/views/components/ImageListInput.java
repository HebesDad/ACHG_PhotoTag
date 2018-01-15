package org.achg.phototag.views.components;

import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.ModelManager;
import org.achg.phototag.search.SearchCriteriaContainer;

public class ImageListInput
{
	private static ImageListInput _instance = null;

	private ImageListInput()
	{

	}

	public static synchronized ImageListInput getInstance()
	{
		if(_instance == null)
		{
			_instance = new ImageListInput();
		}
		return _instance;
	}

	public Object[] getContent()
	{
		List<Image> searchResults = SearchCriteriaContainer.getInstance().getResults();
		if(searchResults != null)
		{
			return searchResults.toArray(new Image[searchResults.size()]);
		}
		if(ModelManager.getInstance().isLoaded())
		{
			return ModelManager.getInstance().getModel().getFolders();
		}
		return new Object[] {};
	}

}
