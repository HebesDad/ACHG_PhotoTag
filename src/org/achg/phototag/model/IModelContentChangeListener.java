package org.achg.phototag.model;

import java.util.List;

public interface IModelContentChangeListener
{
	void modelContentChanged(List<DataChangeType> dataChanges);
}
