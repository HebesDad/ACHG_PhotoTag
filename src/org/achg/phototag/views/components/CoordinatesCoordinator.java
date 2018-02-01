package org.achg.phototag.views.components;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesCoordinator
{
	private static CoordinatesCoordinator _instance;

	private double _xPercentage = 0;
	private double _yPercentage = 0;
	private List<ICoordinatesListener> _listeners = new ArrayList<>();

	private CoordinatesCoordinator()
	{

	}

	public static synchronized CoordinatesCoordinator getInstance()
	{
		if(_instance == null)
			_instance = new CoordinatesCoordinator();
		return _instance;
	}

	public void clickedAt(double x, double y, boolean fromData)
	{
		_xPercentage = x;
		_yPercentage = y;
		for(ICoordinatesListener listener : _listeners)
		{
			listener.notifyNewCoordinates(fromData);
		}
	}

	public void reset()
	{
		_xPercentage = _yPercentage = 0;
	}

	public void addListener(ICoordinatesListener listener)
	{
		_listeners.add(listener);
	}

	public double getX()
	{

		return _xPercentage;
	}

	public double getY()
	{

		return _yPercentage;
	}

}
