/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getFoldersList <em>Folders</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getImagesList <em>Images</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getFolder()
 * @model
 * @generated
 */
public interface Folder extends EObject
{
	/**
	 * <!-- begin-user-doc -->
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
	 * If the meaning of the '<em>Folders</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folders</em>' containment reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getFolder_Folders()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<Folder> getFoldersList();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Image[] getImages();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Image getImages(int index);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	int getImagesLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setImages(Image[] newImages);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	void setImages(int index, Image element);

	/**
	 * Returns the value of the '<em><b>Images</b></em>' containment reference list.
	 * The list contents are of type {@link org.achg.phototag.generated.model.PhotoTagModel.Image}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Images</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Images</em>' containment reference list.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getFolder_Images()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	List<Image> getImagesList();

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
	 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage#getFolder_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Folder
