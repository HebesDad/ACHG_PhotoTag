/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.impl;

import java.util.Collection;
import java.util.List;
import org.achg.phototag.generated.model.PhotoTagModel.Folder;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage;
import org.achg.phototag.generated.model.PhotoTagModel.Root;
import org.achg.phototag.generated.model.PhotoTagModel.TagCategory;

import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
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
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl#getXmlHome <em>Xml Home</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl#getFoldersList <em>Folders</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl#getTagCategoriesList <em>Tag Categories</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl#getValuesList <em>Values</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl#getImageCount <em>Image Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RootImpl extends MinimalEObjectImpl.Container implements Root {
	/**
	 * The default value of the '{@link #getXmlHome() <em>Xml Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlHome()
	 * @generated
	 * @ordered
	 */
	protected static final String XML_HOME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getXmlHome() <em>Xml Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlHome()
	 * @generated
	 * @ordered
	 */
	protected String xmlHome = XML_HOME_EDEFAULT;

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
	 * The cached value of the '{@link #getTagCategoriesList() <em>Tag Categories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagCategoriesList()
	 * @generated
	 * @ordered
	 */
	protected EList<TagCategory> tagCategories;

	/**
	 * The empty value for the '{@link #getTagCategories() <em>Tag Categories</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagCategories()
	 * @generated
	 * @ordered
	 */
	protected static final TagCategory[] TAG_CATEGORIES_EEMPTY_ARRAY = new TagCategory [0];

	/**
	 * The cached value of the '{@link #getValuesList() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValuesList()
	 * @generated
	 * @ordered
	 */
	protected EList<TagValue> values;

	/**
	 * The empty value for the '{@link #getValues() <em>Values</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected static final TagValue[] VALUES_EEMPTY_ARRAY = new TagValue [0];

	/**
	 * The default value of the '{@link #getImageCount() <em>Image Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageCount()
	 * @generated
	 * @ordered
	 */
	protected static final int IMAGE_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getImageCount() <em>Image Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageCount()
	 * @generated
	 * @ordered
	 */
	protected int imageCount = IMAGE_COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PhotoTagModelPackage.Literals.ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXmlHome() {
		return xmlHome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXmlHome(String newXmlHome) {
		String oldXmlHome = xmlHome;
		xmlHome = newXmlHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.ROOT__XML_HOME, oldXmlHome, xmlHome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Folder[] getFolders() {
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
	public Folder getFolders(int index) {
		return getFoldersList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFoldersLength() {
		return folders == null ? 0 : folders.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolders(Folder[] newFolders) {
		((BasicEList<Folder>)getFoldersList()).setData(newFolders.length, newFolders);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolders(int index, Folder element) {
		getFoldersList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Folder> getFoldersList() {
		if (folders == null) {
			folders = new EObjectContainmentEList.Resolving<Folder>(Folder.class, this, PhotoTagModelPackage.ROOT__FOLDERS);
		}
		return folders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagCategory[] getTagCategories() {
		if (tagCategories == null || tagCategories.isEmpty()) return TAG_CATEGORIES_EEMPTY_ARRAY;
		BasicEList<TagCategory> list = (BasicEList<TagCategory>)tagCategories;
		list.shrink();
		return (TagCategory[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagCategory getTagCategories(int index) {
		return getTagCategoriesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTagCategoriesLength() {
		return tagCategories == null ? 0 : tagCategories.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagCategories(TagCategory[] newTagCategories) {
		((BasicEList<TagCategory>)getTagCategoriesList()).setData(newTagCategories.length, newTagCategories);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagCategories(int index, TagCategory element) {
		getTagCategoriesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TagCategory> getTagCategoriesList() {
		if (tagCategories == null) {
			tagCategories = new EObjectContainmentEList.Resolving<TagCategory>(TagCategory.class, this, PhotoTagModelPackage.ROOT__TAG_CATEGORIES);
		}
		return tagCategories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue[] getValues() {
		if (values == null || values.isEmpty()) return VALUES_EEMPTY_ARRAY;
		BasicEList<TagValue> list = (BasicEList<TagValue>)values;
		list.shrink();
		return (TagValue[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue getValues(int index) {
		return getValuesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValuesLength() {
		return values == null ? 0 : values.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValues(TagValue[] newValues) {
		((BasicEList<TagValue>)getValuesList()).setData(newValues.length, newValues);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValues(int index, TagValue element) {
		getValuesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TagValue> getValuesList() {
		if (values == null) {
			values = new EObjectContainmentEList.Resolving<TagValue>(TagValue.class, this, PhotoTagModelPackage.ROOT__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getImageCount() {
		return imageCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImageCount(int newImageCount) {
		int oldImageCount = imageCount;
		imageCount = newImageCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.ROOT__IMAGE_COUNT, oldImageCount, imageCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PhotoTagModelPackage.ROOT__FOLDERS:
				return ((InternalEList<?>)getFoldersList()).basicRemove(otherEnd, msgs);
			case PhotoTagModelPackage.ROOT__TAG_CATEGORIES:
				return ((InternalEList<?>)getTagCategoriesList()).basicRemove(otherEnd, msgs);
			case PhotoTagModelPackage.ROOT__VALUES:
				return ((InternalEList<?>)getValuesList()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PhotoTagModelPackage.ROOT__XML_HOME:
				return getXmlHome();
			case PhotoTagModelPackage.ROOT__FOLDERS:
				return getFoldersList();
			case PhotoTagModelPackage.ROOT__TAG_CATEGORIES:
				return getTagCategoriesList();
			case PhotoTagModelPackage.ROOT__VALUES:
				return getValuesList();
			case PhotoTagModelPackage.ROOT__IMAGE_COUNT:
				return getImageCount();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PhotoTagModelPackage.ROOT__XML_HOME:
				setXmlHome((String)newValue);
				return;
			case PhotoTagModelPackage.ROOT__FOLDERS:
				getFoldersList().clear();
				getFoldersList().addAll((Collection<? extends Folder>)newValue);
				return;
			case PhotoTagModelPackage.ROOT__TAG_CATEGORIES:
				getTagCategoriesList().clear();
				getTagCategoriesList().addAll((Collection<? extends TagCategory>)newValue);
				return;
			case PhotoTagModelPackage.ROOT__VALUES:
				getValuesList().clear();
				getValuesList().addAll((Collection<? extends TagValue>)newValue);
				return;
			case PhotoTagModelPackage.ROOT__IMAGE_COUNT:
				setImageCount((Integer)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case PhotoTagModelPackage.ROOT__XML_HOME:
				setXmlHome(XML_HOME_EDEFAULT);
				return;
			case PhotoTagModelPackage.ROOT__FOLDERS:
				getFoldersList().clear();
				return;
			case PhotoTagModelPackage.ROOT__TAG_CATEGORIES:
				getTagCategoriesList().clear();
				return;
			case PhotoTagModelPackage.ROOT__VALUES:
				getValuesList().clear();
				return;
			case PhotoTagModelPackage.ROOT__IMAGE_COUNT:
				setImageCount(IMAGE_COUNT_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PhotoTagModelPackage.ROOT__XML_HOME:
				return XML_HOME_EDEFAULT == null ? xmlHome != null : !XML_HOME_EDEFAULT.equals(xmlHome);
			case PhotoTagModelPackage.ROOT__FOLDERS:
				return folders != null && !folders.isEmpty();
			case PhotoTagModelPackage.ROOT__TAG_CATEGORIES:
				return tagCategories != null && !tagCategories.isEmpty();
			case PhotoTagModelPackage.ROOT__VALUES:
				return values != null && !values.isEmpty();
			case PhotoTagModelPackage.ROOT__IMAGE_COUNT:
				return imageCount != IMAGE_COUNT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (XmlHome: ");
		result.append(xmlHome);
		result.append(", ImageCount: ");
		result.append(imageCount);
		result.append(')');
		return result.toString();
	}

} //RootImpl
