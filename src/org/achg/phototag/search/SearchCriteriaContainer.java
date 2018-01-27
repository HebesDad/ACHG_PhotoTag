package org.achg.phototag.search;

import java.util.ArrayList;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;

/**
 * Container for search criteria
 */
public class SearchCriteriaContainer
{
	private static SearchCriteriaContainer _instance = null;

	private List<TagValue> _criteria;
	private List<Image> _result = null;
	private List<ISearchResultListener> _listeners = new ArrayList<>();

	private SearchCriteriaContainer()
	{
		_criteria = new ArrayList<>();
	}

	/**
	 * Get the singleton instance of the search criteria container
	 * 
	 * @return the singleton instance
	 */
	public static synchronized SearchCriteriaContainer getInstance()
	{
		if(_instance == null)
		{
			_instance = new SearchCriteriaContainer();
		}

		return _instance;
	}

	/**
	 * Register a new search result listener
	 * 
	 * @param listener the listener
	 */
	public void addSearchResultListener(ISearchResultListener listener)
	{
		_listeners.add(listener);
	}

	/**
	 * Unregister a search result listener
	 * 
	 * @param listener the listener
	 */
	public void removeSearchResultListener(ISearchResultListener listener)
	{
		_listeners.remove(listener);
	}

	/**
	 * Add a tag value as a search criterion
	 * 
	 * @param value the tag value
	 */
	public void addCriteria(TagValue value)
	{
		_criteria.add(value);
	}

	/**
	 * Remove a tag value as a search criterion
	 * 
	 * @param value the tag value
	 */
	public void removeCriteria(TagValue value)
	{
		_criteria.remove(value);
	}

	/**
	 * Get the array of tag values to user as search criteria
	 * 
	 * @return value the tag values
	 */
	public TagValue[] getCriteria()
	{
		return _criteria.toArray(new TagValue[_criteria.size()]);
	}

	/**
	 * Set the list of result images from a search
	 * 
	 * @param result the list of images
	 */
	public void setResults(List<Image> result)
	{
		_result = result;

		for(ISearchResultListener listener : _listeners)
		{
			listener.notifyNewResult();
		}
	}

	/**
	 * Get the list of result images from a search
	 * 
	 * @return the list of images
	 */
	public List<Image> getResults()
	{
		return _result;
	}
}
