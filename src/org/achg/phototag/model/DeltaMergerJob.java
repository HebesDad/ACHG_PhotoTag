package org.achg.phototag.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.achg.phototag.views.ConsoleView;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Job to perform the merging of deltas
 * 
 * @author cdr
 *
 */
public class DeltaMergerJob extends Job
{
	private UISynchronize _sync;
	private Shell _shell;
	private File _deltaFile;
	private ConsoleView _consoleView;

	/**
	 * Constructor
	 * 
	 * @param sync ui synchronizer
	 * @param shell the parent shell
	 * @param consoleView console view - so messages can be logged
	 * @param deltaFile file containing the deltas
	 */
	public DeltaMergerJob(UISynchronize sync, Shell shell, ConsoleView consoleView, File deltaFile)
	{
		super("Delta Merger");
		_sync = sync;
		_shell = shell;
		_consoleView = consoleView;
		_deltaFile = deltaFile;
	}

	private void logMessage(String message)
	{
		if(_consoleView != null)
			_consoleView.addMessage(message);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		logMessage("Starting merge...");

		try (BufferedReader br = new BufferedReader(new FileReader(_deltaFile)))
		{
			String line;
			while((line = br.readLine()) != null)
			{
				if(line.contains(DeltaLogger.SPLIT))
				{
					processDeltaLine(line);
				}
				else
				{
					final String finalLine = line;
					_sync.asyncExec(new Runnable()
					{

						@Override
						public void run()
						{
							MessageDialog.openInformation(_shell, "Format Error", "Badly formed input: " + finalLine);

						}
					});
					break;
				}
			}
		}
		catch(FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logMessage("Merge processing complete");
		return Status.OK_STATUS;
	}

	private void processDeltaLine(String line)
	{
		String[] parts = line.split(DeltaLogger.SPLIT);
		Queue<String> partsQueue = new LinkedList<>();
		for(String part : parts)
			partsQueue.add(part);

		if(parts.length < 3) // somehow invalid input
			return;
		String command = partsQueue.remove();
		String dataType = partsQueue.remove();

		if(command.equals(DeltaLogger.COMMAND_ADD))
		{
			if(dataType.equals(DeltaLogger.OBJECT_CATEGORY))
			{
				processDeltaAddCategory(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_COORDINATE))
			{
				processDeltaAddCoordinate(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_FOLDER))
			{
				processDeltaAddFolder(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_IMAGE))
			{
				processDeltaAddImage(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_TAG))
			{
				processDeltaAddTag(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_VALUE))
			{
				processDeltaAddValue(partsQueue);
			}

		}
		else if(command.equals(DeltaLogger.COMMAND_ASSOCIATE))
		{
			if(dataType.equals(DeltaLogger.OBJECT_VALUE))
			{
				processAssociateImageValue(partsQueue);
			}
		}
		else if(command.equals(DeltaLogger.COMMAND_DELETE))
		{
			if(dataType.equals(DeltaLogger.OBJECT_COORDINATE))
			{
				processDeltaDeleteCoordinate(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_VALUE))
			{
				processDeltaDeleteValue(partsQueue);
			}
		}
		else if(command.equals(DeltaLogger.COMMAND_REMOVE))
		{
			if(dataType.equals(DeltaLogger.OBJECT_VALUE))
			{
				processDeltaRemoveImageValue(partsQueue);
			}
		}
		else if(command.equals(DeltaLogger.COMMAND_MODIFY))
		{
			if(dataType.equals(DeltaLogger.OBJECT_CATEGORY))
			{
				processDeltaModifyCategory(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_COORDINATE))
			{
				processDeltaModifyCoordinate(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_TAG))
			{
				processDeltaModifyTag(partsQueue);
			}
			else if(dataType.equals(DeltaLogger.OBJECT_VALUE))
			{
				processDeltaModifyValue(partsQueue);
			}
		}
	}

	private void processDeltaModifyValue(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaModifyTag(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaModifyCoordinate(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaModifyCategory(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaRemoveImageValue(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaDeleteValue(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaDeleteCoordinate(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processAssociateImageValue(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaAddValue(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaAddTag(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaAddImage(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaAddFolder(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaAddCoordinate(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

	private void processDeltaAddCategory(Queue<String> partsQueue)
	{
		// TODO Auto-generated method stub

	}

}
