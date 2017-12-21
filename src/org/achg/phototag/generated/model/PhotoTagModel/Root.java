/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import java.util.List;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getXmlHome <em>Xml Home</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getFoldersList <em>Folders</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getTagCategoriesList <em>Tag Categories</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getValuesList <em>Values</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getImageCount <em>Image Count</em>}</li>
 * </ul>
 *
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject {
	/**
	 * Returns the value of the '<em><b>Xml Home</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xml Home</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Home</em>' attribute.
	 * @see #setXmlHome(String)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getRoot_XmlHome()
	 * @model default="" required="true"
	 * @generated
	 */
	String getXmlHome();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getXmlHome <em>Xml Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Home</em>' attribute.
	 * @see #getXmlHome()
	 * @generated
	 */
	void setXmlHome(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folders</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Folder[] getFolders();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Folder getFolders(int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	int getFoldersLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setFolders(Folder[] newFolders);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setFolders(int index, Folder element);

	/**
	 * Returns the value of the '<em><b>Folders</b></em>' containment reference list.
	 * The list contents are of type {@link org.achg.phototag.generated.model.PhotoTagModel.Folder}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folders</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folders</em>' containment reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getRoot_Folders()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<Folder> getFoldersList();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Categories</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagCategory[] getTagCategories();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagCategory getTagCategories(int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	int getTagCategoriesLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setTagCategories(TagCategory[] newTagCategories);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setTagCategories(int index, TagCategory element);

	/**
	 * Returns the value of the '<em><b>Tag Categories</b></em>' containment reference list.
	 * The list contents are of type {@link org.achg.phototag.generated.model.PhotoTagModel.TagCategory}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Categories</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Categories</em>' containment reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getRoot_TagCategories()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<TagCategory> getTagCategoriesList();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagValue[] getValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TagValue getValues(int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	int getValuesLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setValues(TagValue[] newValues);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setValues(int index, TagValue element);

	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.achg.phototag.generated.model.PhotoTagModel.TagValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getRoot_Values()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<TagValue> getValuesList();

	/**
	 * Returns the value of the '<em><b>Image Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Image Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image Count</em>' attribute.
	 * @see #setImageCount(int)
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getRoot_ImageCount()
	 * @model required="true"
	 * @generated
	 */
	int getImageCount();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getImageCount <em>Image Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image Count</em>' attribute.
	 * @see #getImageCount()
	 * @generated
	 */
	void setImageCount(int value);

} // Root
