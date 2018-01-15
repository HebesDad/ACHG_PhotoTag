package org.achg.phototag.jobs;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.model.IFolderVisitor;
import org.achg.phototag.model.ModelManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class ImageCounterJob extends Job
{

	public ImageCounterJob()
	{
		super("Image counter job");

	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{

		FolderVisitor visitor = new FolderVisitor();
		ModelManager.getInstance().visitFolders(visitor);

		ModelManager.getInstance().getModel().setImageCount(visitor.getCount());
		ModelManager.getInstance().notifyModelStatusChangeListeners();
		return Status.OK_STATUS;
	}

	private class FolderVisitor implements IFolderVisitor
	{
		private int _count = 0;

		public int getCount()
		{
			return _count;
		}

		@Override
		public boolean visitFolder(Folder folder)
		{
			_count += folder.getImagesLength();
			return true;
		}

	}

}
