/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.impl;

import java.util.Collection;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl#getFoldersList <em>Folders</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl#getImagesList <em>Images</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FolderImpl extends MinimalEObjectImpl.Container implements Folder
{
	/**
	 * The cached value of the '{@link #getFoldersList() <em>Folders</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFoldersList()
	 * @generated
	 * @ordered
	 */
	protected EList<Folder> folders;

	/**
	 * The empty value for the '{@link #getFolders() <em>Folders</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolders()
	 * @generated
	 * @ordered
	 */
	protected static final Folder[] FOLDERS_EEMPTY_ARRAY = new Folder [0];

	/**
	 * The cached value of the '{@link #getImagesList() <em>Images</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImagesList()
	 * @generated
	 * @ordered
	 */
	protected EList<Image> images;

	/**
	 * The empty value for the '{@link #getImages() <em>Images</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImages()
	 * @generated
	 * @ordered
	 */
	protected static final Image[] IMAGES_EEMPTY_ARRAY = new Image [0];

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FolderImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PhotoTagModelPackage.Literals.FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Folder[] getFolders()
	{
		if (folders == null || folders.isEmpty()) return FOLDERS_EEMPTY_ARRAY;
		BasicEList<Folder> list = (BasicEList<Folder>)folders;
		list.shrink();
		return (Folder[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Folder getFolders(int index)
	{
		return getFoldersList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFoldersLength()
	{
		return folders == null ? 0 : folders.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolders(Folder[] newFolders)
	{
		((BasicEList<Folder>)getFoldersList()).setData(newFolders.length, newFolders);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolders(int index, Folder element)
	{
		getFoldersList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Folder> getFoldersList()
	{
		if (folders == null)
		{
			folders = new EObjectContainmentEList.Resolving<Folder>(Folder.class, this, PhotoTagModelPackage.FOLDER__FOLDERS);
		}
		return folders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image[] getImages()
	{
		if (images == null || images.isEmpty()) return IMAGES_EEMPTY_ARRAY;
		BasicEList<Image> list = (BasicEList<Image>)images;
		list.shrink();
		return (Image[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image getImages(int index)
	{
		return getImagesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getImagesLength()
	{
		return images == null ? 0 : images.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImages(Image[] newImages)
	{
		((BasicEList<Image>)getImagesList()).setData(newImages.length, newImages);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImages(int index, Image element)
	{
		getImagesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Image> getImagesList()
	{
		if (images == null)
		{
			images = new EObjectContainmentEList.Resolving<Image>(Image.class, this, PhotoTagModelPackage.FOLDER__IMAGES);
		}
		return images;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.FOLDER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PhotoTagModelPackage.FOLDER__FOLDERS:
				return ((InternalEList<?>)getFoldersList()).basicRemove(otherEnd, msgs);
			case PhotoTagModelPackage.FOLDER__IMAGES:
				return ((InternalEList<?>)getImagesList()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case PhotoTagModelPackage.FOLDER__FOLDERS:
				return getFoldersList();
			case PhotoTagModelPackage.FOLDER__IMAGES:
				return getImagesList();
			case PhotoTagModelPackage.FOLDER__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case PhotoTagModelPackage.FOLDER__FOLDERS:
				getFoldersList().clear();
				getFoldersList().addAll((Collection<? extends Folder>)newValue);
				return;
			case PhotoTagModelPackage.FOLDER__IMAGES:
				getImagesList().clear();
				getImagesList().addAll((Collection<? extends Image>)newValue);
				return;
			case PhotoTagModelPackage.FOLDER__NAME:
				setName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case PhotoTagModelPackage.FOLDER__FOLDERS:
				getFoldersList().clear();
				return;
			case PhotoTagModelPackage.FOLDER__IMAGES:
				getImagesList().clear();
				return;
			case PhotoTagModelPackage.FOLDER__NAME:
				setName(NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case PhotoTagModelPackage.FOLDER__FOLDERS:
				return folders != null && !folders.isEmpty();
			case PhotoTagModelPackage.FOLDER__IMAGES:
				return images != null && !images.isEmpty();
			case PhotoTagModelPackage.FOLDER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (Name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //FolderImpl
