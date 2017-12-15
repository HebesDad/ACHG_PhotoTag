package org.achg.phototag.commands;

import org.achg.phototag.views.ImageListView;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class NextImageHandle {
	@Execute
	public void handle(EPartService partService) {
	MPart mpart =partService.findPart(ImageListView.ID);
	
	ImageListView view = (ImageListView) mpart.getObject();
	
	view.nextImage();
	}
}
