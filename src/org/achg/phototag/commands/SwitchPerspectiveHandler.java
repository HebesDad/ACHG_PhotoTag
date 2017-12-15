package org.achg.phototag.commands;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SwitchPerspectiveHandler {
	@Execute
	public void handle( EPartService partService,EModelService modelService,MWindow window) {
		
		if (modelService.getActivePerspective(window).getElementId().equals("org.achg.phototag.perspective.view"))
		{
			partService.switchPerspective("org.achg.phototag.perspective.tags");
		}
		else
		{
			partService.switchPerspective("org.achg.phototag.perspective.view");
		}
		
	}
}
