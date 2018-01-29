package org.achg.phototag.search;

/**
 * Interface for classes which listen for search results
 */
public interface ISearchResultListener
{
	/**
	 * Called when new search results are found
	 */
	void notifyNewResult();
}
