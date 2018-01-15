package org.achg.phototag.views.components;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.IFolderVisitor;

public class ImageFinderFolderVisitor implements IFolderVisitor
{

	private String _targetName;
	private Image _foundImage;

	public ImageFinderFolderVisitor(String targetName)
	{
		_targetName = targetName;
	}

	public Image getFoundImage()
	{
		return _foundImage;
	}

	@Override
	public boolean visitFolder(Folder folder)
	{
		for(Image contender : folder.getImages())
		{
			if(contender.getName().equals(_targetName))
			{
				_foundImage = contender;
				return false;
			}
		}
		return true;
	}

}
