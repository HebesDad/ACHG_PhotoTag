/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Tag#getSubTagList <em>Sub Tag</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Tag#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTag()
 * @model
 * @generated
 */
public interface Tag extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Tag[] getSubTag();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Tag getSubTag(int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	int getSubTagLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setSubTag(Tag[] newSubTag);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setSubTag(int index, Tag element);

	/**
	 * Returns the value of the '<em><b>Sub Tag</b></em>' containment reference list.
	 * The list contents are of type {@link org.achg.phototag.generated.model.PhotoTagModel.Tag}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Tag</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Tag</em>' containment reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTag_SubTag()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<Tag> getSubTagList();

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
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getTag_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.Tag#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Tag
