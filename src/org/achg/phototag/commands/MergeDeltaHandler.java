package org.achg.phototag.commands;

import java.io.File;

import org.achg.phototag.model.DeltaMergerJob;
import org.achg.phototag.views.ConsoleView;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Handler for the merge delta command
 * 
 * @author cdr
 *
 */
public class MergeDeltaHandler
{
	/**
	 * Main handle method
	 * 
	 * @param shell parent shell
	 * @param sync ui synchronizer
	 * @param partService part service
	 */
	@Execute
	public void handle(Shell shell, UISynchronize sync, EPartService partService)
	{
		FileDialog dialog = new FileDialog(shell);
		String file = dialog.open();
		MPart mpart = partService.findPart("org.achg.phototag.part.consoleView");
		ConsoleView consoleView = null;
		if(mpart != null)
		{
			consoleView = (ConsoleView)mpart.getObject();
		}
		if(file != null)
		{
			new DeltaMergerJob(sync, shell, consoleView, new File(file)).schedule();
		}
	}
}
