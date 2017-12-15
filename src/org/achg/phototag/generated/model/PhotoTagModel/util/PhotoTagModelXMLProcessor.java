/**
 */
package org.achg.phototag.generated.model.PhotoTagModel.util;

import java.util.Map;

import org.achg.phototag.generated.model.PhotoTagModel.PhotoTagModelPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhotoTagModelXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhotoTagModelXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		PhotoTagModelPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the PhotoTagModelResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new PhotoTagModelResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new PhotoTagModelResourceFactoryImpl());
		}
		return registrations;
	}

} //PhotoTagModelXMLProcessor
