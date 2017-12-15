/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getTag <em>Tag</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getValue <em>Value</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubTag <em>Sub Tag</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubValue <em>Sub Value</em>}</li>
 * </ul>
 *
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValue()
 * @model
 * @generated
 */
public interface TagValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Tag</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' reference.
	 * @see #setTag(Tag)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValue_Tag()
	 * @model keys="Name" required="true"
	 * @generated
	 */
	Tag getTag();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getTag <em>Tag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' reference.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(Tag value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValue_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Sub Tag</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Tag</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Tag</em>' reference.
	 * @see #setSubTag(Tag)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValue_SubTag()
	 * @model
	 * @generated
	 */
	Tag getSubTag();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubTag <em>Sub Tag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Tag</em>' reference.
	 * @see #getSubTag()
	 * @generated
	 */
	void setSubTag(Tag value);

	/**
	 * Returns the value of the '<em><b>Sub Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Value</em>' attribute.
	 * @see #setSubValue(String)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTagValue_SubValue()
	 * @model
	 * @generated
	 */
	String getSubValue();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubValue <em>Sub Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Value</em>' attribute.
	 * @see #getSubValue()
	 * @generated
	 */
	void setSubValue(String value);

} // TagValue
