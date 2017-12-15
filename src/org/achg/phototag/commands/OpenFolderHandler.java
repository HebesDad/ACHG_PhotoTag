package org.achg.phototag.commands;

import org.achg.phototag.model.ModelManager;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenFolderHandler {
	@Execute
	public void handle(Shell shell) {

		DirectoryDialog directoryDialog = new DirectoryDialog(shell);
		String selectedFolder = directoryDialog.open();
		if (selectedFolder != null) {
			ModelManager.getInstance().setRootFolder(selectedFolder);
		}

	}
}
