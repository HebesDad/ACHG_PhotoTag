package org.achg.phototag;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Collections;

import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class FileUtil {

	public static void copyFile(Root modelRoot,String fromFile, String toFile) {
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// create a resource
		Resource resource = resSet.createResource(URI.createURI("file://" + fromFile.replace('\\', '/')));
		
		resource.getContents().add(modelRoot);

		try (OutputStream os = new FileOutputStream(new File(toFile))) {
			Files.copy(new File(fromFile).toPath(), os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
