/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.impl;

import java.util.Collection;
import java.util.List;

import org.achg.phototag.generated.model.PhotoTagModel.Image;
import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;

import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl#getTagValuesList <em>Tag Values</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl#getTagValueCoordinatesList <em>Tag Value Coordinates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImageImpl extends MinimalEObjectImpl.Container implements Image
{
	/**
	 * The cached value of the '{@link #getTagValuesList() <em>Tag Values</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagValuesList()
	 * @generated
	 * @ordered
	 */
	protected EList<TagValue> tagValues;

	/**
	 * The empty value for the '{@link #getTagValues() <em>Tag Values</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagValues()
	 * @generated
	 * @ordered
	 */
	protected static final TagValue[] TAG_VALUES_EEMPTY_ARRAY = new TagValue [0];

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
	 * The cached value of the '{@link #getTagValueCoordinatesList() <em>Tag Value Coordinates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagValueCoordinatesList()
	 * @generated
	 * @ordered
	 */
	protected EList<TagValueCoordinate> tagValueCoordinates;

	/**
	 * The empty value for the '{@link #getTagValueCoordinates() <em>Tag Value Coordinates</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagValueCoordinates()
	 * @generated
	 * @ordered
	 */
	protected static final TagValueCoordinate[] TAG_VALUE_COORDINATES_EEMPTY_ARRAY = new TagValueCoordinate [0];

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImageImpl()
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
		return PhotoTagModelPackage.Literals.IMAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue[] getTagValues()
	{
		if (tagValues == null || tagValues.isEmpty()) return TAG_VALUES_EEMPTY_ARRAY;
		BasicEList<TagValue> list = (BasicEList<TagValue>)tagValues;
		list.shrink();
		return (TagValue[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue getTagValues(int index)
	{
		return getTagValuesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTagValuesLength()
	{
		return tagValues == null ? 0 : tagValues.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagValues(TagValue[] newTagValues)
	{
		((BasicEList<TagValue>)getTagValuesList()).setData(newTagValues.length, newTagValues);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagValues(int index, TagValue element)
	{
		getTagValuesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TagValue> getTagValuesList()
	{
		if (tagValues == null)
		{
			tagValues = new EObjectResolvingEList<TagValue>(TagValue.class, this, PhotoTagModelPackage.IMAGE__TAG_VALUES);
		}
		return tagValues;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.IMAGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValueCoordinate[] getTagValueCoordinates()
	{
		if (tagValueCoordinates == null || tagValueCoordinates.isEmpty()) return TAG_VALUE_COORDINATES_EEMPTY_ARRAY;
		BasicEList<TagValueCoordinate> list = (BasicEList<TagValueCoordinate>)tagValueCoordinates;
		list.shrink();
		return (TagValueCoordinate[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValueCoordinate getTagValueCoordinates(int index)
	{
		return getTagValueCoordinatesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTagValueCoordinatesLength()
	{
		return tagValueCoordinates == null ? 0 : tagValueCoordinates.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagValueCoordinates(TagValueCoordinate[] newTagValueCoordinates)
	{
		((BasicEList<TagValueCoordinate>)getTagValueCoordinatesList()).setData(newTagValueCoordinates.length, newTagValueCoordinates);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagValueCoordinates(int index, TagValueCoordinate element)
	{
		getTagValueCoordinatesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TagValueCoordinate> getTagValueCoordinatesList()
	{
		if (tagValueCoordinates == null)
		{
			tagValueCoordinates = new EObjectContainmentEList.Resolving<TagValueCoordinate>(TagValueCoordinate.class, this, PhotoTagModelPackage.IMAGE__TAG_VALUE_COORDINATES);
		}
		return tagValueCoordinates;
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
			case PhotoTagModelPackage.IMAGE__TAG_VALUE_COORDINATES:
				return ((InternalEList<?>)getTagValueCoordinatesList()).basicRemove(otherEnd, msgs);
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
			case PhotoTagModelPackage.IMAGE__TAG_VALUES:
				return getTagValuesList();
			case PhotoTagModelPackage.IMAGE__NAME:
				return getName();
			case PhotoTagModelPackage.IMAGE__TAG_VALUE_COORDINATES:
				return getTagValueCoordinatesList();
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
			case PhotoTagModelPackage.IMAGE__TAG_VALUES:
				getTagValuesList().clear();
				getTagValuesList().addAll((Collection<? extends TagValue>)newValue);
				return;
			case PhotoTagModelPackage.IMAGE__NAME:
				setName((String)newValue);
				return;
			case PhotoTagModelPackage.IMAGE__TAG_VALUE_COORDINATES:
				getTagValueCoordinatesList().clear();
				getTagValueCoordinatesList().addAll((Collection<? extends TagValueCoordinate>)newValue);
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
			case PhotoTagModelPackage.IMAGE__TAG_VALUES:
				getTagValuesList().clear();
				return;
			case PhotoTagModelPackage.IMAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PhotoTagModelPackage.IMAGE__TAG_VALUE_COORDINATES:
				getTagValueCoordinatesList().clear();
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
			case PhotoTagModelPackage.IMAGE__TAG_VALUES:
				return tagValues != null && !tagValues.isEmpty();
			case PhotoTagModelPackage.IMAGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PhotoTagModelPackage.IMAGE__TAG_VALUE_COORDINATES:
				return tagValueCoordinates != null && !tagValueCoordinates.isEmpty();
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ImageImpl
