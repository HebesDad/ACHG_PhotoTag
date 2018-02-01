/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Value Coordinate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getXPercentage <em>XPercentage</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getYPercentage <em>YPercentage</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getTagValue <em>Tag Value</em>}</li>
 * </ul>
 *
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValueCoordinate()
 * @model
 * @generated
 */
public interface TagValueCoordinate extends EObject
{
	/**
	 * Returns the value of the '<em><b>XPercentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XPercentage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XPercentage</em>' attribute.
	 * @see #setXPercentage(double)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValueCoordinate_XPercentage()
	 * @model
	 * @generated
	 */
	double getXPercentage();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getXPercentage <em>XPercentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XPercentage</em>' attribute.
	 * @see #getXPercentage()
	 * @generated
	 */
	void setXPercentage(double value);

	/**
	 * Returns the value of the '<em><b>YPercentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YPercentage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YPercentage</em>' attribute.
	 * @see #setYPercentage(double)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValueCoordinate_YPercentage()
	 * @model
	 * @generated
	 */
	double getYPercentage();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getYPercentage <em>YPercentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YPercentage</em>' attribute.
	 * @see #getYPercentage()
	 * @generated
	 */
	void setYPercentage(double value);

	/**
	 * Returns the value of the '<em><b>Tag Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Value</em>' reference.
	 * @see #setTagValue(TagValue)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValueCoordinate_TagValue()
	 * @model
	 * @generated
	 */
	TagValue getTagValue();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getTagValue <em>Tag Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Value</em>' reference.
	 * @see #getTagValue()
	 * @generated
	 */
	void setTagValue(TagValue value);

} // TagValueCoordinate
