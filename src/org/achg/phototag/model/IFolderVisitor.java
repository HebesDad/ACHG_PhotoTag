package org.achg.phototag.model;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;

/**
 * Interface for classes which visit folders
 */
public interface IFolderVisitor
{
	/**
	 * Visit a folder
	 * 
	 * @param folder the folder
	 * @return {@code true} if sub-folders of the specified folder should also be visited
	 */
	boolean visitFolder(Folder folder);
}
