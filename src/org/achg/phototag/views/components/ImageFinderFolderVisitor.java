package org.achg.phototag.views.components;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.model.IFolderVisitor;

/**
 * Folder visitor which finds images
 */
public class ImageFinderFolderVisitor implements IFolderVisitor
{
	private String _targetName;
	private Image _foundImage;

	/**
	 * Constructor
	 * 
	 * @param targetName the target image name
	 */
	public ImageFinderFolderVisitor(String targetName)
	{
		_targetName = targetName;
	}

	/**
	 * Get the found image (or null if not found)
	 * 
	 * @return the found image (or null if not found)
	 */
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
