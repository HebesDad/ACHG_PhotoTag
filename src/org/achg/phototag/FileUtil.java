package org.achg.phototag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.achg.phototag.generated.model.PhotoTagModel.Root;

/**
 * File manipulation utilities
 */
public class FileUtil
{

	/**
	 * Copy a file from place to place
	 * 
	 * @param modelRoot the model root
	 * @param fromStr full path of where to copy from
	 * @param toStr full path of where to copy to
	 */
	public static void copyFile(Root modelRoot, String fromStr, String toStr)
	{
		Path fromPath = Paths.get(fromStr);
		Path toPath = Paths.get(toStr);

		try
		{
			Files.copy(fromPath, toPath);
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
