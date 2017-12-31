package org.achg.phototag.model;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;

public interface IFolderVisitor {
boolean visitFolder(Folder folder);
}
