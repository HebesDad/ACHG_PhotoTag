/**
 */
package org.achg.phototag.generated.model.PhotoTagModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelFactory
 * @model kind="package"
 * @generated
 */
public interface PhotoTagModelPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "PhotoTagModel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org/achg/phototag";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.achg.phototag";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PhotoTagModelPackage eINSTANCE = org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 0;

	/**
	 * The feature id for the '<em><b>Xml Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__XML_HOME = 0;

	/**
	 * The feature id for the '<em><b>Folders</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__FOLDERS = 1;

	/**
	 * The feature id for the '<em><b>Tag Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__TAG_CATEGORIES = 2;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__VALUES = 3;

	/**
	 * The feature id for the '<em><b>Image Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__IMAGE_COUNT = 4;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl <em>Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getFolder()
	 * @generated
	 */
	int FOLDER = 1;

	/**
	 * The feature id for the '<em><b>Folders</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLDER__FOLDERS = 0;

	/**
	 * The feature id for the '<em><b>Images</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLDER__IMAGES = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLDER__NAME = 2;

	/**
	 * The number of structural features of the '<em>Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLDER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLDER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 2;

	/**
	 * The feature id for the '<em><b>Tag Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__TAG_VALUES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Tag Value Coordinates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__TAG_VALUE_COORDINATES = 2;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagCategoryImpl <em>Tag Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagCategoryImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTagCategory()
	 * @generated
	 */
	int TAG_CATEGORY = 3;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CATEGORY__TAGS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CATEGORY__NAME = 1;

	/**
	 * The number of structural features of the '<em>Tag Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CATEGORY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tag Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CATEGORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 4;

	/**
	 * The feature id for the '<em><b>Sub Tag</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__SUB_TAG = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = 1;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl <em>Tag Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTagValue()
	 * @generated
	 */
	int TAG_VALUE = 5;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE__TAG = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Sub Tag</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE__SUB_TAG = 2;

	/**
	 * The feature id for the '<em><b>Sub Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE__SUB_VALUE = 3;

	/**
	 * The number of structural features of the '<em>Tag Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Tag Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl <em>Tag Value Coordinate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl
	 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTagValueCoordinate()
	 * @generated
	 */
	int TAG_VALUE_COORDINATE = 6;

	/**
	 * The feature id for the '<em><b>XPercentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_COORDINATE__XPERCENTAGE = 0;

	/**
	 * The feature id for the '<em><b>YPercentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_COORDINATE__YPERCENTAGE = 1;

	/**
	 * The feature id for the '<em><b>Tag Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_COORDINATE__TAG_VALUE = 2;

	/**
	 * The number of structural features of the '<em>Tag Value Coordinate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_COORDINATE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Tag Value Coordinate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_VALUE_COORDINATE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getXmlHome <em>Xml Home</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xml Home</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Root#getXmlHome()
	 * @see #getRoot()
	 * @generated
	 */
	EAttribute getRoot_XmlHome();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getFoldersList <em>Folders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Folders</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Root#getFoldersList()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Folders();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getTagCategoriesList <em>Tag Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Categories</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Root#getTagCategoriesList()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_TagCategories();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getValuesList <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Root#getValuesList()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Values();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.Root#getImageCount <em>Image Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image Count</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Root#getImageCount()
	 * @see #getRoot()
	 * @generated
	 */
	EAttribute getRoot_ImageCount();

	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.Folder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Folder</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Folder
	 * @generated
	 */
	EClass getFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getFoldersList <em>Folders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Folders</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Folder#getFoldersList()
	 * @see #getFolder()
	 * @generated
	 */
	EReference getFolder_Folders();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getImagesList <em>Images</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Images</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Folder#getImagesList()
	 * @see #getFolder()
	 * @generated
	 */
	EReference getFolder_Images();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.Folder#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Folder#getName()
	 * @see #getFolder()
	 * @generated
	 */
	EAttribute getFolder_Name();

	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Image#getTagValuesList <em>Tag Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tag Values</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Image#getTagValuesList()
	 * @see #getImage()
	 * @generated
	 */
	EReference getImage_TagValues();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.Image#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Image#getName()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Image#getTagValueCoordinatesList <em>Tag Value Coordinates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Value Coordinates</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Image#getTagValueCoordinatesList()
	 * @see #getImage()
	 * @generated
	 */
	EReference getImage_TagValueCoordinates();

	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.TagCategory <em>Tag Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Category</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagCategory
	 * @generated
	 */
	EClass getTagCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.TagCategory#getTagsList <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagCategory#getTagsList()
	 * @see #getTagCategory()
	 * @generated
	 */
	EReference getTagCategory_Tags();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.TagCategory#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagCategory#getName()
	 * @see #getTagCategory()
	 * @generated
	 */
	EAttribute getTagCategory_Name();

	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the containment reference list '{@link org.achg.phototag.generated.model.PhotoTagModel.Tag#getSubTagList <em>Sub Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Tag</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Tag#getSubTagList()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_SubTag();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.Tag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.Tag#getName()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Name();

	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue <em>Tag Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Value</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValue
	 * @generated
	 */
	EClass getTagValue();

	/**
	 * Returns the meta object for the reference '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tag</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValue#getTag()
	 * @see #getTagValue()
	 * @generated
	 */
	EReference getTagValue_Tag();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValue#getValue()
	 * @see #getTagValue()
	 * @generated
	 */
	EAttribute getTagValue_Value();

	/**
	 * Returns the meta object for the reference '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubTag <em>Sub Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sub Tag</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubTag()
	 * @see #getTagValue()
	 * @generated
	 */
	EReference getTagValue_SubTag();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubValue <em>Sub Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sub Value</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValue#getSubValue()
	 * @see #getTagValue()
	 * @generated
	 */
	EAttribute getTagValue_SubValue();

	/**
	 * Returns the meta object for class '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate <em>Tag Value Coordinate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Value Coordinate</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate
	 * @generated
	 */
	EClass getTagValueCoordinate();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getXPercentage <em>XPercentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XPercentage</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getXPercentage()
	 * @see #getTagValueCoordinate()
	 * @generated
	 */
	EAttribute getTagValueCoordinate_XPercentage();

	/**
	 * Returns the meta object for the attribute '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getYPercentage <em>YPercentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>YPercentage</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getYPercentage()
	 * @see #getTagValueCoordinate()
	 * @generated
	 */
	EAttribute getTagValueCoordinate_YPercentage();

	/**
	 * Returns the meta object for the reference '{@link org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getTagValue <em>Tag Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tag Value</em>'.
	 * @see org.achg.phototag.generated.model.PhotoTagModel.TagValueCoordinate#getTagValue()
	 * @see #getTagValueCoordinate()
	 * @generated
	 */
	EReference getTagValueCoordinate_TagValue();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PhotoTagModelFactory getPhotoTagModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.RootImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();

		/**
		 * The meta object literal for the '<em><b>Xml Home</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROOT__XML_HOME = eINSTANCE.getRoot_XmlHome();

		/**
		 * The meta object literal for the '<em><b>Folders</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__FOLDERS = eINSTANCE.getRoot_Folders();

		/**
		 * The meta object literal for the '<em><b>Tag Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__TAG_CATEGORIES = eINSTANCE.getRoot_TagCategories();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__VALUES = eINSTANCE.getRoot_Values();

		/**
		 * The meta object literal for the '<em><b>Image Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROOT__IMAGE_COUNT = eINSTANCE.getRoot_ImageCount();

		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl <em>Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.FolderImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getFolder()
		 * @generated
		 */
		EClass FOLDER = eINSTANCE.getFolder();

		/**
		 * The meta object literal for the '<em><b>Folders</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOLDER__FOLDERS = eINSTANCE.getFolder_Folders();

		/**
		 * The meta object literal for the '<em><b>Images</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOLDER__IMAGES = eINSTANCE.getFolder_Images();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOLDER__NAME = eINSTANCE.getFolder_Name();

		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.ImageImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Tag Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMAGE__TAG_VALUES = eINSTANCE.getImage_TagValues();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__NAME = eINSTANCE.getImage_Name();

		/**
		 * The meta object literal for the '<em><b>Tag Value Coordinates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMAGE__TAG_VALUE_COORDINATES = eINSTANCE.getImage_TagValueCoordinates();

		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagCategoryImpl <em>Tag Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagCategoryImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTagCategory()
		 * @generated
		 */
		EClass TAG_CATEGORY = eINSTANCE.getTagCategory();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_CATEGORY__TAGS = eINSTANCE.getTagCategory_Tags();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_CATEGORY__NAME = eINSTANCE.getTagCategory_Name();

		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Sub Tag</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__SUB_TAG = eINSTANCE.getTag_SubTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__NAME = eINSTANCE.getTag_Name();

		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl <em>Tag Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTagValue()
		 * @generated
		 */
		EClass TAG_VALUE = eINSTANCE.getTagValue();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_VALUE__TAG = eINSTANCE.getTagValue_Tag();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_VALUE__VALUE = eINSTANCE.getTagValue_Value();

		/**
		 * The meta object literal for the '<em><b>Sub Tag</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_VALUE__SUB_TAG = eINSTANCE.getTagValue_SubTag();

		/**
		 * The meta object literal for the '<em><b>Sub Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_VALUE__SUB_VALUE = eINSTANCE.getTagValue_SubValue();

		/**
		 * The meta object literal for the '{@link org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl <em>Tag Value Coordinate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.TagValueCoordinateImpl
		 * @see org.achg.phototag.generated.model.PhotoTagModel.impl.PhotoTagModelPackageImpl#getTagValueCoordinate()
		 * @generated
		 */
		EClass TAG_VALUE_COORDINATE = eINSTANCE.getTagValueCoordinate();

		/**
		 * The meta object literal for the '<em><b>XPercentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_VALUE_COORDINATE__XPERCENTAGE = eINSTANCE.getTagValueCoordinate_XPercentage();

		/**
		 * The meta object literal for the '<em><b>YPercentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_VALUE_COORDINATE__YPERCENTAGE = eINSTANCE.getTagValueCoordinate_YPercentage();

		/**
		 * The meta object literal for the '<em><b>Tag Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_VALUE_COORDINATE__TAG_VALUE = eINSTANCE.getTagValueCoordinate_TagValue();

	}

} //PhotoTagModelPackage
