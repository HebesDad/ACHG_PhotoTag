package org.achg.phototag.jobs;

import org.achg.phototag.model.ModelManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class AutoSaveJob extends Job
{

	public AutoSaveJob()
	{
		super("Auto-save Job");

	}

	public boolean belongsTo(Object obj)
	{
		if(obj == AutoSaveJob.class)
		{
			return true;
		}
		return false;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		ModelManager.getInstance().save();
		ModelManager.getInstance().notifyModelStatusChangeListeners("Auto-save complete");
		return Status.OK_STATUS;
	}

}
