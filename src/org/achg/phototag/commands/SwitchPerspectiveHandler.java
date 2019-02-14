package org.achg.phototag.commands;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Handler for switching perspectives
 */
public class SwitchPerspectiveHandler
{
	/**
	 * Handle switching perspective
	 * 
	 * @param partService the Eclipse part service
	 * @param modelService the Eclipse model service
	 * @param window the main window
	 */
	@Execute
	public void handle(EPartService partService, EModelService modelService, MWindow window)
	{

		if(modelService.getActivePerspective(window).getElementId().equals("org.achg.phototag.perspective.view"))
		{
			partService.switchPerspective("org.achg.phototag.perspective.tags");
		}
		else
		{
			partService.switchPerspective("org.achg.phototag.perspective.view");
		}

	}
}
