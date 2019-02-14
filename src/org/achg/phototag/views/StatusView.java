package org.achg.phototag.views;

import javax.inject.Inject;

import org.achg.phototag.model.IModelStatusChangeListener;
import org.achg.phototag.model.ModelManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * View for displaying the model status
 */
public class StatusView implements IModelStatusChangeListener
{
	private Label _currentStatusLabel;
	private UISynchronize _sync;

	/**
	 * Create the UI components
	 * 
	 * @param viewParent the parent composite
	 * @param shell the main shell
	 * @param sync the UI synchronisation object
	 */
	@Inject
	public void create(Composite viewParent, Shell shell, UISynchronize sync)
	{
		_sync = sync;
		Composite compo = new Composite(viewParent, SWT.BORDER | SWT.FILL);
		GridLayout layout = new GridLayout(2, false);
		compo.setLayout(layout);

		new Label(compo, SWT.NONE).setText("Status:");

		_currentStatusLabel = new Label(compo, SWT.NONE);
		GridData gd = new GridData(SWT.LEFT, SWT.TOP, true, false);
		_currentStatusLabel.setLayoutData(gd);

		modelStatusChanged();

		ModelManager.getInstance().addModelStatusChangeListener(this);

		if(!ModelManager.getInstance().isLoaded())
		{
			MessageDialog.openInformation(shell, "Welcome",
					"It looks like you haven't used this tool before.\nClick the \"Open Folder\" menu item to connect to a folder.");
		}
	}

	@Override
	public void modelStatusChanged()
	{
		if(ModelManager.getInstance().isLoaded())
		{
			String message = String.format("Attached to: %s - %d images", ModelManager.getInstance().getImagesRoot().getAbsolutePath(),
					ModelManager.getInstance().getModel().getImageCount());
			_sync.asyncExec(new Runnable()
			{

				@Override
				public void run()
				{
					_currentStatusLabel.setText(message);
				}
			});

		}
		else
		{
			_currentStatusLabel.setText("Not attached to images");
		}
		_sync.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				_currentStatusLabel.requestLayout();
			}
		});
	}

	@Override
	public void statusMessage(String message)
	{
		_sync.asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				_currentStatusLabel.setText(message);
				_currentStatusLabel.requestLayout();
			}
		});

		Job.getJobManager().cancel(MessageResetJob.class);
		new MessageResetJob().schedule(30 * 1000); // 30 seconds
	}

	private class MessageResetJob extends Job
	{

		public MessageResetJob()
		{
			super("Message reset job");
		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			modelStatusChanged();
			return Status.OK_STATUS;
		}

		@Override
		public boolean belongsTo(Object obj)
		{
			return obj == MessageResetJob.class;
		}

	}
}
