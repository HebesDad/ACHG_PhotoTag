package org.achg.phototag.jobs;

import java.io.File;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
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

public class ScanFoldersJob extends Job {
	File _fileRoot;
	Root _modelRoot;
	DataModifier _modifier = new DataModifier();
	boolean _dataModified = false;
	String _rootPrefix;
	UISynchronize _sync;
	Shell _shell;

	public ScanFoldersJob(File fileroot, Root modelRoot, UISynchronize sync, Shell shell) {
		super("Scanning for folders");
		_fileRoot = fileroot;
		_rootPrefix = fileroot.getAbsoluteFile().getPath();
//		_rootPrefix = _rootPrefix.substring(0, _rootPrefix.length() - fileroot.getName().length());
		_modelRoot = modelRoot;
		_sync = sync;
		_shell = shell;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		Folder matchingFolder = null;
		if (_modelRoot.getFolders() != null)
			for (Folder folder : _modelRoot.getFolders()) {
				if (folder.getName().equals("")) {
					matchingFolder = folder;
					break;
				}
			}
		if (matchingFolder == null) {
			matchingFolder = PhotoTagModelFactory.eINSTANCE.createFolder();
			// matchingFolder.setName(_fileRoot.getName());
			_modelRoot.getFoldersList().add(0, matchingFolder);
			_dataModified = true;
		}

		final Folder finalFolder = matchingFolder;
		ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(_shell);
		final IProgressMonitor finalMonitor = monitorDialog.getProgressMonitor();
		_sync.syncExec(new Runnable() {

			@Override
			public void run() {
				monitorDialog.open();

				checkFolderContents(finalFolder, _fileRoot, finalMonitor);

				monitorDialog.close();
			}
		});

		if (_dataModified) {
			ModelManager.getInstance().notifyModelContentChangeListeners();
		}
		return Status.OK_STATUS;
	}

	private void checkFolderContents(Folder modelFolder, File javaioFile, IProgressMonitor monitor) {

		File[] children = javaioFile.listFiles();
		
		

		SubMonitor 	subMonitor =SubMonitor.convert(monitor, children.length);
				
		
		

		for (File child : children) {
			String childName = child.getName();
			if (childName.equals("[Originals]")) {
				continue;
			}
			if (child.isDirectory()) {
				Folder childFolder = findFolder(modelFolder, child);
				if (childFolder == null) {
					// "new" folder
					childFolder = PhotoTagModelFactory.eINSTANCE.createFolder();
					childFolder.setName(child.getAbsolutePath().substring(_rootPrefix.length() +1));
					modelFolder.getFoldersList().add(childFolder);
					_dataModified = true;
				}
				checkFolderContents(childFolder, child, subMonitor.split(1));
			}
			if (childName.endsWith(".jpg") || childName.endsWith(".jpeg") || childName.endsWith(".gif")
					|| childName.endsWith(".png")) {
				if (findImage(modelFolder, child) == null) {
					Image newImage = PhotoTagModelFactory.eINSTANCE.createImage();
					newImage.setName(child.getAbsolutePath().substring(_rootPrefix.length()+1));
					modelFolder.getImagesList().add(newImage);
					_dataModified = true;
				}
			}
		}
	}

	private Image findImage(Folder modelFolder, File child) {
		String targetName = child.getAbsolutePath().substring(_rootPrefix.length());
		for (Image putative : modelFolder.getImagesList()) {
			if (putative.getName().equals(targetName))
				return putative;
		}
		return null;
	}

	private Folder findFolder(Folder modelFolder, File child) {
		String targetName = child.getAbsolutePath().substring(_rootPrefix.length());
		for (Folder putative : modelFolder.getFoldersList()) {
			if (putative.getName().equals(targetName))
				return putative;
		}
		return null;
	}

}
