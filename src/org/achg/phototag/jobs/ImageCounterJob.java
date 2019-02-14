package org.achg.phototag.jobs;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.model.IFolderVisitor;
import org.achg.phototag.model.ModelManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Job for counting the number of images under the root folder
 */
public class ImageCounterJob extends Job
{
	/**
	 * Constructor
	 */
	public ImageCounterJob()
	{
		super("Image counter job");
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		FolderVisitor visitor = new FolderVisitor();

		ModelManager manager = ModelManager.getInstance();

		manager.visitFolders(visitor);

		manager.getModel().setImageCount(visitor.getCount());
		manager.notifyModelStatusChangeListeners();

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
