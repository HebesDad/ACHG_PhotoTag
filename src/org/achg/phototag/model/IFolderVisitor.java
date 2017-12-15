package org.achg.phototag.model;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;

public interface IFolderVisitor {
void visitFolder(Folder folder);
}
