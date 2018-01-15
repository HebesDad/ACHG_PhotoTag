package org.achg.phototag.commands;

import org.achg.phototag.jobs.ScanFoldersJob;
import org.achg.phototag.model.ModelManager;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.swt.widgets.Shell;

public class ScanFoldersHandler
{
	@Execute
	public void handle(UISynchronize sync, Shell shell)
	{
		ScanFoldersJob job = new ScanFoldersJob(ModelManager.getInstance().getImagesRoot(), ModelManager.getInstance().getModel(), sync, shell);
		job.schedule();
	}
}
