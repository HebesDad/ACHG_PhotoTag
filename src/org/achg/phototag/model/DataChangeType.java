package org.achg.phototag.model;

/**
 * Enum representing the different types of data changes which can occur in the application
 */
public enum DataChangeType
{
	/** Add a category */
	ADD_CATEGORY,

	/** Add a folder */
	ADD_FOLDER,

	/** Add an image */
	ADD_IMAGE,

	/** Add a tag */
	ADD_TAG,

	/** Add a sub-tag */
	ADD_SUBTAG,

	/** Add a value */
	ADD_VALUE,

	/** Modify a tag */
	MODIFY_TAG,

	/** Modify a sub-tag */
	MODIFY_SUBTAG,

	/** Modify a value */
	MODIFY_VALUE,

	/** Remove a value */
	REMOVE_VALUE,

	/** Use a value */
	VALUE_USAGE,

	/** Modify a category */
	MODIFY_CATEGORY;
}
