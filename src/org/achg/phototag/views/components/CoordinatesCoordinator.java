package org.achg.phototag.views.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton to coordinate x,y values for a TagValue
 * 
 * @author cdr
 *
 */
public class CoordinatesCoordinator
{
	private static CoordinatesCoordinator _instance;

	private double _xPercentage = 0;
	private double _yPercentage = 0;
	private List<ICoordinatesListener> _listeners = new ArrayList<>();

	private CoordinatesCoordinator()
	{

	}

	/**
	 * get the single instance of this class
	 * 
	 * @return the object
	 */
	public static synchronized CoordinatesCoordinator getInstance()
	{
		if(_instance == null)
			_instance = new CoordinatesCoordinator();
		return _instance;
	}

	/**
	 * Record the x,y positions, and notify listener of the values
	 * 
	 * @param x x percentage (from top left)
	 * @param y y percentage (from top left)
	 * @param fromData whether the position results from a data value or from the user clicking at the position
	 */
	public void clickedAt(double x, double y, boolean fromData)
	{
		_xPercentage = x;
		_yPercentage = y;
		for(ICoordinatesListener listener : _listeners)
		{
			listener.notifyNewCoordinates(fromData);
		}
	}

	/**
	 * wipe the known position
	 */
	public void reset()
	{
		_xPercentage = _yPercentage = 0;
	}

	/**
	 * Add a listener for new coordinates values
	 * 
	 * @param listener the listener
	 */
	public void addListener(ICoordinatesListener listener)
	{
		_listeners.add(listener);
	}

	/**
	 * Get the X percentage value
	 * 
	 * @return the value, 0 indicates no position
	 */
	public double getX()
	{

		return _xPercentage;
	}

	/**
	 * Get the Y percentage value
	 * 
	 * @return the value
	 */
	public double getY()
	{

		return _yPercentage;
	}

}
