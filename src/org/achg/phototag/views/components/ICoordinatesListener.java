package org.achg.phototag.views.components;

/**
 * Interface of a coordinates listener
 * 
 * @author cdr
 *
 */
public interface ICoordinatesListener
{

	/**
	 * tell the listener that there are new coordinates values
	 * 
	 * @param fromData whether the change came from data or if not a use click
	 */
	void notifyNewCoordinates(boolean fromData);

}
