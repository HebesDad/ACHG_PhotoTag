package org.achg.phototag.model;

/**
 * Interface for classes which listen for model status changes
 */
public interface IModelStatusChangeListener
{
	/**
	 * Called when the model status has changed
	 */
	void modelStatusChanged();

	/**
	 * Called when a status message is provided
	 * 
	 * @param message the message
	 */
	void statusMessage(String message);
}
