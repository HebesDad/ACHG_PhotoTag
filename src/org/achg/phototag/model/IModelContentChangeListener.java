package org.achg.phototag.model;

import java.util.List;

/**
 * Interface for classes which listen for model content changes
 */
public interface IModelContentChangeListener
{
	/**
	 * Called when the model content has been changed
	 * 
	 * @param dataChanges a list of the types of changes which have occurred in the model
	 */
	void modelContentChanged(List<DataChangeType> dataChanges);
}
