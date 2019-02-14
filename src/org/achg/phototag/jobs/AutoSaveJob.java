package org.achg.phototag.jobs;

import org.achg.phototag.model.ModelManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Job for performing auto-saves
 */
public class AutoSaveJob extends Job
{
	/**
	 * Constructor
	 */
	public AutoSaveJob()
	{
		super("Auto-save Job");
	}

	@Override
	public boolean belongsTo(Object obj)
	{
		return (obj == AutoSaveJob.class);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		ModelManager.getInstance().save();
		ModelManager.getInstance().notifyModelStatusChangeListeners("Auto-save complete");
		return Status.OK_STATUS;
	}

}
