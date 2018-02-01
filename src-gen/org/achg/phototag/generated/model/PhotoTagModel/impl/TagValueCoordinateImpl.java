/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.impl;

import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage;
import org.achg.phototag.generated.model.PhotoTagModel.TagValue;
import org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Value Coordinate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl#getXPercentage <em>XPercentage</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl#getYPercentage <em>YPercentage</em>}</li>
 *   <li>{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl#getTagValue <em>Tag Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TagValueCoordinateImpl extends MinimalEObjectImpl.Container implements TagValueCoordinate
{
	/**
	 * The default value of the '{@link #getXPercentage() <em>XPercentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPercentage()
	 * @generated
	 * @ordered
	 */
	protected static final double XPERCENTAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXPercentage() <em>XPercentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPercentage()
	 * @generated
	 * @ordered
	 */
	protected double xPercentage = XPERCENTAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getYPercentage() <em>YPercentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYPercentage()
	 * @generated
	 * @ordered
	 */
	protected static final double YPERCENTAGE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYPercentage() <em>YPercentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYPercentage()
	 * @generated
	 * @ordered
	 */
	protected double yPercentage = YPERCENTAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTagValue() <em>Tag Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagValue()
	 * @generated
	 * @ordered
	 */
	protected TagValue tagValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagValueCoordinateImpl()
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
		return PhotoTagModelPackage.Literals.TAG_VALUE_COORDINATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXPercentage()
	{
		return xPercentage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXPercentage(double newXPercentage)
	{
		double oldXPercentage = xPercentage;
		xPercentage = newXPercentage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE_COORDINATE__XPERCENTAGE, oldXPercentage, xPercentage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYPercentage()
	{
		return yPercentage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYPercentage(double newYPercentage)
	{
		double oldYPercentage = yPercentage;
		yPercentage = newYPercentage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE_COORDINATE__YPERCENTAGE, oldYPercentage, yPercentage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue getTagValue()
	{
		if (tagValue != null && tagValue.eIsProxy())
		{
			InternalEObject oldTagValue = (InternalEObject)tagValue;
			tagValue = (TagValue)eResolveProxy(oldTagValue);
			if (tagValue != oldTagValue)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PhotoTagModelPackage.TAG_VALUE_COORDINATE__TAG_VALUE, oldTagValue, tagValue));
			}
		}
		return tagValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue basicGetTagValue()
	{
		return tagValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagValue(TagValue newTagValue)
	{
		TagValue oldTagValue = tagValue;
		tagValue = newTagValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PhotoTagModelPackage.TAG_VALUE_COORDINATE__TAG_VALUE, oldTagValue, tagValue));
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
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__XPERCENTAGE:
				return getXPercentage();
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__YPERCENTAGE:
				return getYPercentage();
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__TAG_VALUE:
				if (resolve) return getTagValue();
				return basicGetTagValue();
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
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__XPERCENTAGE:
				setXPercentage((Double)newValue);
				return;
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__YPERCENTAGE:
				setYPercentage((Double)newValue);
				return;
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__TAG_VALUE:
				setTagValue((TagValue)newValue);
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
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__XPERCENTAGE:
				setXPercentage(XPERCENTAGE_EDEFAULT);
				return;
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__YPERCENTAGE:
				setYPercentage(YPERCENTAGE_EDEFAULT);
				return;
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__TAG_VALUE:
				setTagValue((TagValue)null);
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
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__XPERCENTAGE:
				return xPercentage != XPERCENTAGE_EDEFAULT;
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__YPERCENTAGE:
				return yPercentage != YPERCENTAGE_EDEFAULT;
			case PhotoTagModelPackage.TAG_VALUE_COORDINATE__TAG_VALUE:
				return tagValue != null;
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
		result.append(" (XPercentage: ");
		result.append(xPercentage);
		result.append(", YPercentage: ");
		result.append(yPercentage);
		result.append(')');
		return result.toString();
	}

} //TagValueCoordinateImpl
