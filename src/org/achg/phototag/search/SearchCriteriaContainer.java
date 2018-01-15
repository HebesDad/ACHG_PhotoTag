package org.achg.phototag.search;

import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;

public class SearchCriteriaContainer
{
	private List<TagValue> _criteria;
	private List<Image> _result = null;
	private List<ISearchResultListener> _listeners = new ArrayList<>();

	private SearchCriteriaContainer()
	{
		_criteria = new ArrayList<>();
	}

	public void addSearchResultListener(ISearchResultListener listener)
	{
		_listeners.add(listener);
	}

	private static SearchCriteriaContainer _instance = null;

	public static synchronized SearchCriteriaContainer getInstance()
	{
		if(_instance == null)
			_instance = new SearchCriteriaContainer();

		return _instance;
	}

	public void removeCriteria(TagValue value)
	{
		_criteria.remove(value);
	}

	public void addCriteria(TagValue value)
	{
		_criteria.add(value);
	}

	public TagValue[] getCriteria()
	{
		return _criteria.toArray(new TagValue[_criteria.size()]);
	}

	public void setResults(List<Image> result)
	{
		_result = result;
		for(ISearchResultListener listener : _listeners)
			listener.notifyNewResult();
	}

	public List<Image> getResults()
	{
		return _result;
	}
}
