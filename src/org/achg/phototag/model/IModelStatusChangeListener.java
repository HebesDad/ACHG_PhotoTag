package org.achg.phototag.model;

public interface IModelStatusChangeListener
{
	void modelStatusChanged();

	void statusMessage(String message);
}
