package org.achg.phototag.commands;

import org.achg.phototag.model.ModelManager;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

public class ExitHandler {
	@Execute
	public void handle(IWorkbench workbench) {
		ModelManager.getInstance().save();
		workbench.close();
	}
}
