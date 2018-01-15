package org.achg.phototag.jobs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.model.DataChangeType;
import org.achg.phototag.model.DataModifier;
import org.achg.phototag.model.ModelManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;

public class ScanFoldersJob extends Job
{
	File _fileRoot;
	Root _modelRoot;
	DataModifier _modifier = new DataModifier();
	boolean _dataModified = false;
	String _rootPrefix;
	UISynchronize _sync;
	Shell _shell;
	private int _extraFolderCount = 0;
	private int _extraImageCount = 0;

	public ScanFoldersJob(File fileroot, Root modelRoot, UISynchronize sync, Shell shell)
	{
		super("Scanning for folders");
		_fileRoot = fileroot;
		_rootPrefix = fileroot.getAbsoluteFile().getPath();
		_modelRoot = modelRoot;
		_sync = sync;
		_shell = shell;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		List<DataChangeType> modTypes = new ArrayList<>();
		Folder matchingFolder = null;
		if(_modelRoot.getFolders() != null)
			for(Folder folder : _modelRoot.getFolders())
			{
				if(folder.getName() == null || folder.getName().equals(""))
				{
					matchingFolder = folder;
					break;
				}
			}
		if(matchingFolder == null)
		{
			matchingFolder = PhotoTagModelFactory.eINSTANCE.createFolder();

			_modelRoot.getFoldersList().add(0, matchingFolder);
			_dataModified = true;
			_extraFolderCount++;

			modTypes.add(DataChangeType.ADD_FOLDER);
		}

		final Folder finalFolder = matchingFolder;
		ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(_shell);
		final IProgressMonitor finalMonitor = monitorDialog.getProgressMonitor();
		_sync.syncExec(new Runnable()
		{

			@Override
			public void run()
			{
				monitorDialog.open();

				checkFolderContents(finalFolder, _fileRoot, finalMonitor, modTypes);

				monitorDialog.close();
			}
		});

		if(_dataModified)
		{
			String message = String.format("Scan complete - %d extra folders found and %d extra images found", _extraFolderCount, _extraImageCount);
			ModelManager.getInstance().notifyModelStatusChangeListeners(message);
			ModelManager.getInstance().notifyModelContentChangeListeners(modTypes);
		}
		else
			ModelManager.getInstance().notifyModelStatusChangeListeners("Scan complete - no additions found");
		return Status.OK_STATUS;
	}

	private void checkFolderContents(Folder modelFolder, File javaioFile, IProgressMonitor monitor, List<DataChangeType> modTypes)
	{

		File[] children = javaioFile.listFiles();

		SubMonitor subMonitor = SubMonitor.convert(monitor, children.length);

		for(File child : children)
		{
			String childName = child.getName();
			if(childName.equals("[Originals]"))
			{
				continue;
			}
			if(child.isDirectory())
			{
				Folder childFolder = findFolder(modelFolder, child);
				if(childFolder == null)
				{
					// "new" folder
					childFolder = PhotoTagModelFactory.eINSTANCE.createFolder();
					childFolder.setName(child.getAbsolutePath().substring(_rootPrefix.length() + 1));
					modelFolder.getFoldersList().add(childFolder);
					_extraFolderCount++;
					_dataModified = true;
					modTypes.add(DataChangeType.ADD_FOLDER);
				}
				checkFolderContents(childFolder, child, subMonitor.split(1), modTypes);
			}
			if(childName.endsWith(".jpg") || childName.endsWith(".jpeg") || childName.endsWith(".gif") || childName.endsWith(".png"))
			{
				if(findImage(modelFolder, child) == null)
				{
					Image newImage = PhotoTagModelFactory.eINSTANCE.createImage();
					newImage.setName(child.getAbsolutePath().substring(_rootPrefix.length() + 1));
					modelFolder.getImagesList().add(newImage);
					_extraImageCount++;
					_dataModified = true;
					modTypes.add(DataChangeType.ADD_IMAGE);
				}
			}
		}
	}

	private Image findImage(Folder modelFolder, File child)
	{
		String targetName = child.getAbsolutePath().substring(_rootPrefix.length() + 1);
		for(Image putative : modelFolder.getImagesList())
		{
			if(putative.getName().equals(targetName))
				return putative;
		}
		return null;
	}

	private Folder findFolder(Folder modelFolder, File child)
	{
		String targetName = child.getAbsolutePath().substring(_rootPrefix.length() + 1);
		for(Folder putative : modelFolder.getFoldersList())
		{
			if(putative.getName().equals(targetName))
				return putative;
		}
		return null;
	}

}
