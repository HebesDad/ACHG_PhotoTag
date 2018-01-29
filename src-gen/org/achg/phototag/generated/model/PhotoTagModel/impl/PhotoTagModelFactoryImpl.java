/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.impl;

import org.achg.phototag.generated.model.PhotoTagModel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PhotoTagModelFactoryImpl extends EFactoryImpl implements PhotoTagModelFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PhotoTagModelFactory init()
	{
		try
		{
			PhotoTagModelFactory thePhotoTagModelFactory = (PhotoTagModelFactory)EPackage.Registry.INSTANCE.getEFactory(PhotoTagModelPackage.eNS_URI);
			if (thePhotoTagModelFactory != null)
			{
				return thePhotoTagModelFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PhotoTagModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhotoTagModelFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case PhotoTagModelPackage.ROOT: return createRoot();
			case PhotoTagModelPackage.FOLDER: return createFolder();
			case PhotoTagModelPackage.IMAGE: return createImage();
			case PhotoTagModelPackage.TAG_CATEGORY: return createTagCategory();
			case PhotoTagModelPackage.TAG: return createTag();
			case PhotoTagModelPackage.TAG_VALUE: return createTagValue();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root createRoot()
	{
		RootImpl root = new RootImpl();
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Folder createFolder()
	{
		FolderImpl folder = new FolderImpl();
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Image createImage()
	{
		ImageImpl image = new ImageImpl();
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagCategory createTagCategory()
	{
		TagCategoryImpl tagCategory = new TagCategoryImpl();
		return tagCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag createTag()
	{
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagValue createTagValue()
	{
		TagValueImpl tagValue = new TagValueImpl();
		return tagValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhotoTagModelPackage getPhotoTagModelPackage()
	{
		return (PhotoTagModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PhotoTagModelPackage getPackage()
	{
		return PhotoTagModelPackage.eINSTANCE;
	}

} //PhotoTagModelFactoryImpl
