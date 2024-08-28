/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.namespace.QName;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.MismatchBISVersion;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BIObjectTreeDataType.ObjectType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
 * This class encapsulates the logic of the soap client. The soap client initializes a session to BIS and get the information of all the folders and
 * reports. No concurrent access in all methods so no need to synchronize attributes BIReports tree is updated every 7 seconds.
 *
 */
@Singleton
public class BICatalogObjectSoapClient {

    @EJB
    private ApplicationConfigManager applicationConfigManager;

    private static final List<String> EMPTY_STRING_LIST = new ArrayList<String>();

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private static final String FOLDER_OBJECT_TYPE = "Folder";

    private static final String BICATALOG = "BICatalog";

    private static final QName BICATALOG_SERVICE_QNAME = new QName("http://bicatalog.dsws.businessobjects.com/2007/06/01", "BICatalog");

    private static final QName SESSION_SERVICE_QNAME = new QName("http://session.dsws.businessobjects.com/2007/06/01", "Session");

    private static final String SESSION = "Session";

    private static final String FOLDER_EMPTY_INFO = "the folder is empty";

    private static final String SERVICE_PATH_NAME = "dswsbobje/services/";

    private static final String ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG = "Root folder does not exist.";

    private static final String SESSION_NOT_INIT_ERROR_MSG = "please initialise the connection before this operation";

    private static final String UNSUPPORTED_BIS_VERSION = "BIS Version is not supported. Connecting to default BIS version 4.1";

    private static String username;

    private static String password;

    private String serviceURL = null;

    private String opendocURLWithFormatterSoap3 = null;

    private String opendocURLWithFormatterSoap4 = null;

    private String bisReportsRootObjectName = null;

    private com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo sessionInfoSoap4 = null;

    private com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo sessionInfoSoap3 = null;

    private com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalogSoap biCatalogSoapSoap4 = null;

    private com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalogSoap biCatalogSoapSoap3 = null;

    private long resultIsInvalidBeyondThisTime = 0;

    private static final long RESULT_TTL_MILLIS = 7000;

    private BIObjectTreeDataType currentResult = null;

    private Exception currentException = null;

    private String version;

    private static final String WRONG_URL_FORMAT_MSG = "Wrong URL Format";

    private static final String INVALID_CREDENTIALS_MSG = "Invalid credentials";

    private static final String UNKNOWN_HOST_MSG = "Unknown Host.";

    private static final String UNABLE_TO_CONNECT = "Connection Error";

    /**
     * get the parameter from the applicationConfigManager
     */
    @PostConstruct
    public void postConstruct() {

        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_PATH_NAME;
        opendocURLWithFormatterSoap3 = applicationConfigManager.getBISServiceOpenDocCompleteURLWithFormatterSoap3();
        opendocURLWithFormatterSoap4 = applicationConfigManager.getBISServiceOpenDocCompleteURLWithFormatterSoap4();
        bisReportsRootObjectName = applicationConfigManager.getBISReportsRootObjectName();
        version = applicationConfigManager.getBISVersion();
    }

    /**
     * avoid full package name
     *
     * @throws MalformedURLException
     *             if the given url is in a wrong format , this exception will be thrown
     * @throws com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception
     *             all of web service exception
     */

    public void connectSoap4() {
        try {
            sessionInfoSoap4 = getSessionInfoSoap4(); // get session info

            final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalog biCatalogSoap4 = new com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalog(
                    new URL(serviceURL + BICATALOG), BICATALOG_SERVICE_QNAME); // init the biCatalog connection object

            biCatalogSoapSoap4 = biCatalogSoap4.getPort(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalogSoap.class); // init the connection object ,could be wrong
        } catch (final MalformedURLException ex) {
            ServicesLogger.warn(getClass().getName(), "connectSoap4", WRONG_URL_FORMAT_MSG);
        } catch (final com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception ex) {
            ServicesLogger.warn(getClass().getName(), "connectSoap4", INVALID_CREDENTIALS_MSG);
        } catch (final UnknownHostException ex) {
            ServicesLogger.warn(getClass().getName(), "connectSoap4", UNKNOWN_HOST_MSG);
        }

    }

    public void connectSoap3() {
        try {
            sessionInfoSoap3 = getSessionInfoSoap3(); // get session info

            final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalog biCatalogSoap3 = new com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalog(
                    new URL(serviceURL + BICATALOG), BICATALOG_SERVICE_QNAME); // init the biCatalog connection object

            biCatalogSoapSoap3 = biCatalogSoap3.getPort(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalogSoap.class); // init the connection object ,could be wrong
        } catch (final MalformedURLException ex) {
            ServicesLogger.warn(getClass().getName(), "connectSoap3", WRONG_URL_FORMAT_MSG);
        } catch (final com.ericsson.eniq.events.server.services.soap3.reports.session.DSWSException_Exception ex) {
            ServicesLogger.warn(getClass().getName(), "connectSoap3", INVALID_CREDENTIALS_MSG);
        } catch (final UnknownHostException ex) {
            ServicesLogger.warn(getClass().getName(), "connectSoap3", UNKNOWN_HOST_MSG);
        }
    }

    /**
     * @return a {@link BIObjectTreeDataType} object which represents the tree structure of the folder.
     * @throws MalformedURLException
     *             if the url is wrong . in the searchRootFolder(.. ) method
     * @throws com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception
     *             when trying to find the the document folder list
     */
    public BIObjectTreeDataType getDocumentFolderList() throws Exception {//NOPMD
        long currentMillis = System.currentTimeMillis();
        if (resultIsInvalidBeyondThisTime > currentMillis) {
            if (currentException != null) {
                throw currentException;
            }
            return currentResult;
        } else {

            try {
                if (version.trim().equals(BIS_SOAP_SERVICES_VERSION_SOAP3)) {
                    connectSoap3();
                    if (null == sessionInfoSoap3) {
                        ServicesLogger.warn(getClass().getName(), "getDocumentFolderList", SESSION_NOT_INIT_ERROR_MSG);
                        throw new com.ericsson.eniq.events.server.services.soap3.reports.session.DSWSException_Exception(SESSION_NOT_INIT_ERROR_MSG,
                                null);
                    }
                    final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder rootFolder = searchRootFolderSoap3(sessionInfoSoap3);
                    currentResult = getDocumentFolderListRecursiveSoap3(sessionInfoSoap3, rootFolder);
                    currentException = null;
                } else {
                    if (!(version.trim().equals(BIS_SOAP_SERVICES_VERSION_SOAP4))) {
                        ServicesLogger.warn(getClass().getName(), "getDocumentFolderList", UNSUPPORTED_BIS_VERSION);
                    }
                    connectSoap4();
                    if (null == sessionInfoSoap4) {
                        ServicesLogger.warn(getClass().getName(), "getDocumentFolderList", SESSION_NOT_INIT_ERROR_MSG);
                        throw new com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception(SESSION_NOT_INIT_ERROR_MSG,
                                null);
                    }
                    final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder rootFolder = searchRootFolderSoap4(sessionInfoSoap4);
                    currentResult = getDocumentFolderListRecursiveSoap4(sessionInfoSoap4, rootFolder);
                    currentException = null;
                }
                return currentResult;
            } catch (final Exception ex) {
                currentResult = null;
                currentException = ex;
                resultIsInvalidBeyondThisTime = 0;
                ServicesLogger.warn(getClass().getName(), "getDocumentFolderList", UNABLE_TO_CONNECT);
                throw new MismatchBISVersion();
            } finally {
                currentMillis = System.currentTimeMillis();
                resultIsInvalidBeyondThisTime = currentMillis + RESULT_TTL_MILLIS;
            }

        }

    }

    /**
     * @param sessionInfo1
     *            a valid session info object
     * @return the root folder object
     * @throws MalformedURLException
     *             if the URL to the BICatalog is malformed
     * @throws DSWSException_Exception
     *             if the web service call goes wrong
     */

    protected com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder searchRootFolderSoap4(final com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo sessionInfo1Soap4)
            throws MalformedURLException, com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.DSWSException_Exception {

        final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SimpleSearch searchSoap4 = new com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SimpleSearch();
        searchSoap4.setInName(bisReportsRootObjectName);
        searchSoap4.setObjectType(FOLDER_OBJECT_TYPE);
        final List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType> sortTypesSoap4 = new ArrayList<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType>();
        sortTypesSoap4.add(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType.NONE);

        final List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalogObject> objects = biCatalogSoapSoap4.search(
                sessionInfo1Soap4.getSessionID(), searchSoap4, sortTypesSoap4, EMPTY_STRING_LIST, EMPTY_STRING_LIST,
                com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.InstanceRetrievalType.ALL);

        if ((null == objects) || (objects.size() < ONE)
                || !(objects.get(ZERO) instanceof com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder)) { // check something is found and valid
            ServicesLogger.warn(getClass().getName(), "searchRootFolderSoap4", ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG);
            throw new com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.DSWSException_Exception(ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG,
                    null);
        }

        return (com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder) objects.get(ZERO);
    }

    protected com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder searchRootFolderSoap3(final com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo sessionInfo1Soap3)
            throws MalformedURLException, com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.DSWSException_Exception {

        final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SimpleSearch searchSoap3 = new com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SimpleSearch();
        searchSoap3.setInName(bisReportsRootObjectName);
        searchSoap3.setObjectType(FOLDER_OBJECT_TYPE);
        final List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType> sortTypesSoap3 = new ArrayList<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType>();
        sortTypesSoap3.add(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType.NONE);

        final List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalogObject> objects = biCatalogSoapSoap3.search(
                sessionInfo1Soap3.getSessionID(), searchSoap3, sortTypesSoap3, EMPTY_STRING_LIST, EMPTY_STRING_LIST,
                com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.InstanceRetrievalType.ALL);

        if ((null == objects) || (objects.size() < ONE)
                || !(objects.get(ZERO) instanceof com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder)) { // check something is found and valid
            ServicesLogger.warn(getClass().getName(), "searchRootFolderSoap3", ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG);
            throw new com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.DSWSException_Exception(ROOT_FOLDER_DOES_NOT_EXIST_ERROR_MSG,
                    null);
        }

        return (com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder) objects.get(ZERO);
    }

    /**
     * @param sessionInfo1
     *            a good sessionInfo object
     * @param rootFolder
     *            the root folder of all elements.
     * @return a BIObjectTreeDataType represent to whole tree.
     * @throws MalformedURLException
     *             if the url format is wrong
     * @throws DSWSException_Exception
     *             if something goes wrong when trying to get the document and folder object.
     */
    protected BIObjectTreeDataType getDocumentFolderListRecursiveSoap4(final com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo sessionInfo1Soap4,
                                                                       final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder rootFolder)
            throws MalformedURLException, com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.DSWSException_Exception {

        final List<BIObjectTreeDataType> bioTrees = new ArrayList<BIObjectTreeDataType>(); // a list of current item including folder and document

        final List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType> sortTypes = new ArrayList<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType>();
        sortTypes.add(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType.NONE);

        final List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder> folders = biCatalogSoapSoap4.getFolderList(
                sessionInfo1Soap4.getSessionID(), rootFolder.getUID(), ONE, sortTypes, EMPTY_STRING_LIST, EMPTY_STRING_LIST);
        List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document> documents = new ArrayList<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document>();
        try {
            documents = biCatalogSoapSoap4.getDocumentList(sessionInfo1Soap4.getSessionID(), rootFolder.getUID(), ZERO, sortTypes, EMPTY_STRING_LIST,
                    EMPTY_STRING_LIST, null);
        } catch (final Exception ex) {
            ServicesLogger.warn(getClass().getName(), "getDocumentFolderListRecursiveSoap4", FOLDER_EMPTY_INFO);
        }
        bioTrees.addAll(convertDocumentListToBIObjectTreeDataTypeListSoap4(documents));

        for (final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder folder : folders) {
            final BIObjectTreeDataType biObjectSoap4 = getDocumentFolderListRecursiveSoap4(sessionInfo1Soap4, folder);
            biObjectSoap4.setType(ObjectType.FOLDER);
            biObjectSoap4.setDisplayName(folder.getName());
            bioTrees.add(biObjectSoap4);

        }

        final BIObjectTreeDataType bioTreeRootSoap4 = new BIObjectTreeDataType();// construct the tree from the list ;
        bioTreeRootSoap4.setChildren(bioTrees); // the elements found into the root object
        bioTreeRootSoap4.setType(ObjectType.FOLDER);

        return bioTreeRootSoap4;

    }

    protected BIObjectTreeDataType getDocumentFolderListRecursiveSoap3(final com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo sessionInfo1Soap3,
                                                                       final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder rootFolder)
            throws MalformedURLException, com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.DSWSException_Exception {

        final List<BIObjectTreeDataType> bioTrees = new ArrayList<BIObjectTreeDataType>(); // a list of current item including folder and document

        final List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType> sortTypes = new ArrayList<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType>();
        sortTypes.add(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType.NONE);

        final List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder> folders = biCatalogSoapSoap3.getFolderList(
                sessionInfo1Soap3.getSessionID(), rootFolder.getUID(), ONE, sortTypes, EMPTY_STRING_LIST, EMPTY_STRING_LIST);
        List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document> documents = new ArrayList<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document>();
        try {
            documents = biCatalogSoapSoap3.getDocumentList(sessionInfo1Soap3.getSessionID(), rootFolder.getUID(), ZERO, sortTypes, EMPTY_STRING_LIST,
                    EMPTY_STRING_LIST, null);
        } catch (final Exception ex) {
            ServicesLogger.warn(getClass().getName(), "getDocumentFolderListRecursiveSoap3", FOLDER_EMPTY_INFO);
        }
        bioTrees.addAll(convertDocumentListToBIObjectTreeDataTypeListSoap3(documents));

        for (final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder folder : folders) {
            final BIObjectTreeDataType biObjectSoap3 = getDocumentFolderListRecursiveSoap3(sessionInfo1Soap3, folder);
            biObjectSoap3.setType(ObjectType.FOLDER);
            biObjectSoap3.setDisplayName(folder.getName());
            bioTrees.add(biObjectSoap3);

        }

        final BIObjectTreeDataType bioTreeRootSoap3 = new BIObjectTreeDataType();// construct the tree from the list ;
        bioTreeRootSoap3.setChildren(bioTrees); // the elements found into the root object
        bioTreeRootSoap3.setType(ObjectType.FOLDER);

        return bioTreeRootSoap3;

    }

    /**
     * converts a list of documents to a list of BIObjectTreeDataType objects
     *
     * @param docs
     *            a list of document objects
     * @return a list of BIObjectTreeDataType the converted document list
     */

    protected List<BIObjectTreeDataType> convertDocumentListToBIObjectTreeDataTypeListSoap4(final List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document> docs) {
        final List<BIObjectTreeDataType> biObjectTrees = new ArrayList<BIObjectTreeDataType>();
        if (null != docs) {
            for (final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document doc : docs) {
                final BIObjectTreeDataType bioTree = getBiObjectTreeSoap4(doc);
                biObjectTrees.add(bioTree);
            }
        }
        return biObjectTrees;
    }

    protected List<BIObjectTreeDataType> convertDocumentListToBIObjectTreeDataTypeListSoap3(final List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document> docs) {
        final List<BIObjectTreeDataType> biObjectTrees = new ArrayList<BIObjectTreeDataType>();
        if (null != docs) {
            for (final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document doc : docs) {
                final BIObjectTreeDataType bioTree = getBiObjectTreeSoap3(doc);
                biObjectTrees.add(bioTree);
            }
        }
        return biObjectTrees;
    }

    /**
     * @param doc
     * @return
     */
    private BIObjectTreeDataType getBiObjectTreeSoap4(final com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document doc) {
        final BIObjectTreeDataType bioTree = new BIObjectTreeDataType();
        bioTree.setType(ObjectType.FILE);
        final String url = String.format(opendocURLWithFormatterSoap4, doc.getUID(), sessionInfoSoap4.getDefaultToken());
        bioTree.setFileURL(url);
        bioTree.setDisplayName(doc.getName());
        return bioTree;
    }

    private BIObjectTreeDataType getBiObjectTreeSoap3(final com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document doc) {
        final BIObjectTreeDataType bioTree = new BIObjectTreeDataType();
        bioTree.setType(ObjectType.FILE);
        final String url = String.format(opendocURLWithFormatterSoap3, doc.getUID(), sessionInfoSoap3.getDefaultToken());
        bioTree.setFileURL(url);
        bioTree.setDisplayName(doc.getName());
        return bioTree;
    }

    /**
     * @return the sessionInfo object is everything is fine.
     * @throws MalformedURLException
     *             if the url is malformed ...
     * @throws com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception
     *             Credential error
     */
    protected com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo getSessionInfoSoap4() throws MalformedURLException,
            com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception, UnknownHostException {
        final com.ericsson.eniq.events.server.services.soap4.reports.session.Session sessionSoap4 = new com.ericsson.eniq.events.server.services.soap4.reports.session.Session(
                new URL(serviceURL + SESSION), SESSION_SERVICE_QNAME);
        final com.ericsson.eniq.events.server.services.soap4.reports.session.SessionPort sessionPortSoap4 = sessionSoap4.getSession();

        final com.ericsson.eniq.events.server.services.soap4.reports.session.EnterpriseCredential credentialSoap4 = new com.ericsson.eniq.events.server.services.soap4.reports.session.EnterpriseCredential();

        credentialSoap4.setLogin(username);
        credentialSoap4.setPassword(password);
        return sessionPortSoap4.login(credentialSoap4, null);

    }

    public com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo getSessionInfoSoap3() throws MalformedURLException,
            com.ericsson.eniq.events.server.services.soap3.reports.session.DSWSException_Exception, UnknownHostException {
        final com.ericsson.eniq.events.server.services.soap3.reports.session.Session sessionSoap3 = new com.ericsson.eniq.events.server.services.soap3.reports.session.Session(
                new URL(serviceURL + SESSION), SESSION_SERVICE_QNAME);
        final com.ericsson.eniq.events.server.services.soap3.reports.session.SessionPort sessionPortSoap3 = sessionSoap3.getSession();

        final com.ericsson.eniq.events.server.services.soap3.reports.session.EnterpriseCredential credentialSoap3 = new com.ericsson.eniq.events.server.services.soap3.reports.session.EnterpriseCredential();

        credentialSoap3.setLogin(username);
        credentialSoap3.setPassword(password);
        return sessionPortSoap3.login(credentialSoap3, null);

    }
}
