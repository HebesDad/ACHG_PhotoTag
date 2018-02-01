/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.impl;

import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage;
import org.achg.phototag.generated.model.PhotoTagModel.Tag;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl#getSubTag <em>Sub Tag</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl#getSubValue <em>Sub Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TagValueImpl extends MinimalEObjectImpl.Container implements TagValue
{
	/**
	 * The cached value of the '{@link #getTag() <em>Tag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected Tag tag;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSubTag() <em>Sub Tag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubTag()
	 * @generated
	 * @ordered
	 */
	protected Tag subTag;

	/**
	 * The default value of the '{@link #getSubValue() <em>Sub Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubValue()
	 * @generated
	 * @ordered
	 */
	protected static final String SUB_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubValue() <em>Sub Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubValue()
	 * @generated
	 * @ordered
	 */
	protected String subValue = SUB_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagValueImpl()
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
		return PhotoTagModelPackage.Literals.TAG_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag getTag()
	{
		if (tag != null && tag.eIsProxy())
		{
			InternalEObject oldTag = (InternalEObject)tag;
			tag = (Tag)eResolveProxy(oldTag);
			if (tag != oldTag)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PhotoTagModelPackage.TAG_VALUE__TAG, oldTag, tag));
			}
		}
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag basicGetTag()
	{
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTag(Tag newTag)
	{
		Tag oldTag = tag;
		tag = newTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE__TAG, oldTag, tag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue)
	{
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag getSubTag()
	{
		if (subTag != null && subTag.eIsProxy())
		{
			InternalEObject oldSubTag = (InternalEObject)subTag;
			subTag = (Tag)eResolveProxy(oldSubTag);
			if (subTag != oldSubTag)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PhotoTagModelPackage.TAG_VALUE__SUB_TAG, oldSubTag, subTag));
			}
		}
		return subTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag basicGetSubTag()
	{
		return subTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubTag(Tag newSubTag)
	{
		Tag oldSubTag = subTag;
		subTag = newSubTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE__SUB_TAG, oldSubTag, subTag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubValue()
	{
		return subValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubValue(String newSubValue)
	{
		String oldSubValue = subValue;
		subValue = newSubValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE__SUB_VALUE, oldSubValue, subValue));
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
			case PhotoTagModelPackage.TAG_VALUE__TAG:
				if (resolve) return getTag();
				return basicGetTag();
			case PhotoTagModelPackage.TAG_VALUE__VALUE:
				return getValue();
			case PhotoTagModelPackage.TAG_VALUE__SUB_TAG:
				if (resolve) return getSubTag();
				return basicGetSubTag();
			case PhotoTagModelPackage.TAG_VALUE__SUB_VALUE:
				return getSubValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case PhotoTagModelPackage.TAG_VALUE__TAG:
				setTag((Tag)newValue);
				return;
			case PhotoTagModelPackage.TAG_VALUE__VALUE:
				setValue((String)newValue);
				return;
			case PhotoTagModelPackage.TAG_VALUE__SUB_TAG:
				setSubTag((Tag)newValue);
				return;
			case PhotoTagModelPackage.TAG_VALUE__SUB_VALUE:
				setSubValue((String)newValue);
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
			case PhotoTagModelPackage.TAG_VALUE__TAG:
				setTag((Tag)null);
				return;
			case PhotoTagModelPackage.TAG_VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case PhotoTagModelPackage.TAG_VALUE__SUB_TAG:
				setSubTag((Tag)null);
				return;
			case PhotoTagModelPackage.TAG_VALUE__SUB_VALUE:
				setSubValue(SUB_VALUE_EDEFAULT);
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
			case PhotoTagModelPackage.TAG_VALUE__TAG:
				return tag != null;
			case PhotoTagModelPackage.TAG_VALUE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case PhotoTagModelPackage.TAG_VALUE__SUB_TAG:
				return subTag != null;
			case PhotoTagModelPackage.TAG_VALUE__SUB_VALUE:
				return SUB_VALUE_EDEFAULT == null ? subValue != null : !SUB_VALUE_EDEFAULT.equals(subValue);
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
		result.append(" (Value: ");
		result.append(value);
		result.append(", SubValue: ");
		result.append(subValue);
		result.append(')');
		return result.toString();
	}

} //TagValueImpl
