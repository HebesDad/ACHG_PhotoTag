package org.achg.phototag.commands;

import org.achg.phototag.jobs.ScanFoldersJob;
import org.achg.phototag.model.ModelManager;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.swt.widgets.Shell;

/**
 * Handler for scanning folders for images
 */
public class ScanFoldersHandler
{
	/**
	 * Handle scanning folders for images
	 * 
	 * @param sync the UI synchronisation object
	 * @param shell the main shell
	 */
	@Execute
	public void handle(UISynchronize sync, Shell shell)
	{
		ModelManager manager = ModelManager.getInstance();
		ScanFoldersJob job = new ScanFoldersJob(manager.getImagesRoot(), manager.getModel(), sync, shell);
		job.schedule();
	}
}
