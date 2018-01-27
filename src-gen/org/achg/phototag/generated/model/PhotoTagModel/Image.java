/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Image#getTagValuesList <em>Tag Values</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Image#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getImage()
 * @model
 * @generated
 */
public interface Image extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagValue[] getTagValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagValue getTagValues(int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	int getTagValuesLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setTagValues(TagValue[] newTagValues);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setTagValues(int index, TagValue element);

	/**
	 * Returns the value of the '<em><b>Tag Values</b></em>' reference list.
	 * The list contents are of type {@link org.achg.phototag.generated.model.PhotoTagModel.TagValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Values</em>' reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getImage_TagValues()
	 * @model
	 * @generated
	 */
	List<TagValue> getTagValuesList();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getImage_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.Image#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Image
