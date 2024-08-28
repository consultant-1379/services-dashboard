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

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.ericsson.eniq.events.server.services.soap3.reports.session.DSWSException_Exception;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
 * Describe BICatalogObjectSoapClientTest here...
 *
 */
public class BICatalogObjectSoapClientTest extends BaseJMockUnitTest {

    private static final String VALID_URL = "http://atrcx886vm2.athtem.eei.ericsson.se:8080/";

    private static final String INVALID_FORMAT_URL = "this is an invalid format url";

    private static final String INVALID_URL = "http://anyvalidurl.com:8080/";

    private static final String APPLICATION_CONFIGURE_CLASS_NAME = "applicationConfigManager";

    private static final String VALID_USER_NAME = "Administrator";

    private static final String VALID_PASSWORD = "";

    private static final String BIS_VERSION_3 = "3.1";

    private static final String BIS_VERSION_4 = "4.1";

    ApplicationConfigManager mockedApplicationConfigManager;

    List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document> mockDocsSoap3;

    com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document mockDocSoap3;

    com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo mockSessionInfoSoap3;

    com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalogSoap mockbiCatalogSoapSoap3;

    com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder mockRootFolderSoap3;

    List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder> mockRootFoldersSoap3;

    List<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType> mockSortTypesSoap3;

    List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document> mockDocsSoap4;

    com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document mockDocSoap4;

    com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo mockSessionInfoSoap4;

    com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder mockRootFolderSoap4;

    com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalogSoap mockbiCatalogSoapSoap4;

    List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder> mockRootFoldersSoap4;

    List<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType> mockSortTypesSoap4;

    com.ericsson.eniq.events.server.services.soap4.reports.session.Session mockSessionSoap4;

    @Before
    public void setup() {
        mockedApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);

        allowOtherCallsForBISConfigurationSettings();
    }

    private void mockBicatalogDocSoap3() {
        mockery.checking(new Expectations() {
            {
                atLeast(1).of(mockDocSoap3).getUID();
                will(returnValue("2345"));
                one(mockDocSoap3).getName();
                will(returnValue("myDoc"));
            }
        });
        mockDocsSoap3 = new ArrayList();
        mockDocsSoap3.add(mockDocSoap3);
    }

    private void mockSessionInfoForTokenSoap3() {
        mockery.checking(new Expectations() {
            {
                one(mockSessionInfoSoap3).getDefaultToken();
                will(returnValue("1111"));
            }
        });
    }

    private void mockSessionInfoForSIDSoap3() {
        mockery.checking(new Expectations() {
            {
                atLeast(1).of(mockSessionInfoSoap3).getSessionID();
                will(returnValue("1111"));
            }
        });
    }

    private void mockSessionInfoForSIDSoap4() {
        mockery.checking(new Expectations() {
            {
                atLeast(1).of(mockSessionInfoSoap4).getSessionID();
                will(returnValue("1111"));
            }
        });
    }

    private void mockBicatalogDocSoap4() {
        mockery.checking(new Expectations() {
            {
                one(mockDocSoap4).getUID();
                will(returnValue("2345"));
                one(mockDocSoap4).getName();
                will(returnValue("myDoc"));
            }
        });
        mockDocsSoap4 = new ArrayList();
        mockDocsSoap4.add(mockDocSoap4);
    }

    private void mockSessionInfoForTokenSoap4() {
        mockery.checking(new Expectations() {
            {
                one(mockSessionInfoSoap4).getDefaultToken();
                will(returnValue("1111"));
            }
        });
    }

    private void mockRootFolderSoap3() {
        mockery.checking(new Expectations() {
            {
                atLeast(1).of(mockRootFolderSoap3).getUID();
                will(returnValue("3333"));
                atLeast(1).of(mockRootFolderSoap3).getName();
                will(returnValue("AA"));
            }
        });
        mockRootFoldersSoap3 = new ArrayList();
        mockRootFoldersSoap3.add(mockRootFolderSoap3);
    }

    private void mockRootFolderSoap4() {
        mockery.checking(new Expectations() {
            {
                atLeast(1).of(mockRootFolderSoap4).getUID();
                will(returnValue("3333"));
                atLeast(1).of(mockRootFolderSoap4).getName();
                will(returnValue("AA"));
            }
        });
        mockRootFoldersSoap4 = new ArrayList();
        mockRootFoldersSoap4.add(mockRootFolderSoap4);
    }

    private void allowOtherCallsForBISConfigurationSettings() {
        mockery.checking(new Expectations() {
            {
                atMost(1).of(mockedApplicationConfigManager).getBISServiceOpenDocCompleteURLWithFormatterSoap3();
                atMost(1).of(mockedApplicationConfigManager).getBISServiceOpenDocCompleteURLWithFormatterSoap4();
                allowing(mockedApplicationConfigManager).getBISReportsRootObjectName();
            }
        });
    }

    /**
     * The log for this class.
     *
     * @throws DSWSException_Exception
     * @throws MalformedURLException
     * @throws UnknownHostException
     */

    @Test(expected = MalformedURLException.class)
    public void testInitConnectionURLMalformedExceptionSoap3() throws MalformedURLException,
            com.ericsson.eniq.events.server.services.soap3.reports.session.DSWSException_Exception, UnknownHostException {
        expectCallForGetBISServiceURL(INVALID_FORMAT_URL, BIS_VERSION_3);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getSessionInfoSoap3();
    }

    @Test(expected = MalformedURLException.class)
    public void testInitConnectionURLMalformedExceptionSoap4() throws MalformedURLException,
            com.ericsson.eniq.events.server.services.soap4.reports.session.DSWSException_Exception, UnknownHostException {
        expectCallForGetBISServiceURL(INVALID_FORMAT_URL, BIS_VERSION_4);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getSessionInfoSoap4();
    }

    private void expectCallForGetBISServiceURL(final String bisServiceURL, final String bisVersion) {
        mockery.checking(new Expectations() {
            {
                one(mockedApplicationConfigManager).getBISVersion();
                will(returnValue(bisVersion));
                one(mockedApplicationConfigManager).getBISUsername();
                will(returnValue(VALID_USER_NAME));
                one(mockedApplicationConfigManager).getBISPassword();
                will(returnValue(VALID_PASSWORD));
                one(mockedApplicationConfigManager).getBISServiceURL();
                will(returnValue(bisServiceURL));
            }
        });
    }

    private void mockSortTypesSoap3() {
        mockSortTypesSoap3 = new ArrayList<com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType>();
        mockSortTypesSoap3.add(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SortType.NONE);
    }

    private void mockSortTypesSoap4() {
        mockSortTypesSoap4 = new ArrayList<com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType>();
        mockSortTypesSoap4.add(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SortType.NONE);

    }

    private void mockBiCatalogSoapSoap3() throws Exception {
        mockery.checking(new Expectations() {
            {

                one(mockbiCatalogSoapSoap3).getDocumentList("1111", "3333", 0, mockSortTypesSoap3, new ArrayList<String>(), new ArrayList<String>(),
                        null);
                one(mockbiCatalogSoapSoap3).getDocumentList("1111", "3333", 0, mockSortTypesSoap3, new ArrayList<String>(), new ArrayList<String>(),
                        null);
                one(mockbiCatalogSoapSoap3).getFolderList("1111", "3333", 1, mockSortTypesSoap3, new ArrayList<String>(), new ArrayList<String>());
                will(returnValue(mockRootFoldersSoap3));
                one(mockbiCatalogSoapSoap3).getFolderList("1111", "3333", 1, mockSortTypesSoap3, new ArrayList<String>(), new ArrayList<String>());
            }
        });
    }

    private void mockBiCatalogSoapSoap4() throws Exception {
        mockery.checking(new Expectations() {
            {

                one(mockbiCatalogSoapSoap4).getDocumentList("1111", "3333", 0, mockSortTypesSoap4, new ArrayList<String>(), new ArrayList<String>(),
                        null);
                one(mockbiCatalogSoapSoap4).getDocumentList("1111", "3333", 0, mockSortTypesSoap4, new ArrayList<String>(), new ArrayList<String>(),
                        null);
                one(mockbiCatalogSoapSoap4).getFolderList("1111", "3333", 1, mockSortTypesSoap4, new ArrayList<String>(), new ArrayList<String>());
                will(returnValue(mockRootFoldersSoap4));
                one(mockbiCatalogSoapSoap4).getFolderList("1111", "3333", 1, mockSortTypesSoap4, new ArrayList<String>(), new ArrayList<String>());
            }
        });
    }

    private void mockBiCatalogSearchSoap4() throws Exception {
        mockery.checking(new Expectations() {
            {

                one(mockbiCatalogSoapSoap4).search(with(any(String.class)),
                        with(any(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.SimpleSearch.class)), with(any(List.class)),
                        with(any(List.class)), with(any(List.class)),
                        with(any(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.InstanceRetrievalType.class)));
                will(returnValue(mockRootFoldersSoap4));
            }
        });
    }

    private void mockBiCatalogSearchSoap3() throws Exception {
        mockery.checking(new Expectations() {
            {

                one(mockbiCatalogSoapSoap3).search(with(any(String.class)),
                        with(any(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.SimpleSearch.class)), with(any(List.class)),
                        with(any(List.class)), with(any(List.class)),
                        with(any(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.InstanceRetrievalType.class)));
                will(returnValue(mockRootFoldersSoap3));
            }
        });
    }

    @Test(expected = WebServiceException.class)
    public void testInitConnectionWebServiceExceptionSoap3() throws MalformedURLException, DSWSException_Exception, UnknownHostException {
        expectCallForGetBISServiceURL(INVALID_URL, BIS_VERSION_3);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.connectSoap3();
    }

    @Test(expected = WebServiceException.class)
    public void testInitConnectionWebServiceExceptionSoap4() throws MalformedURLException, DSWSException_Exception, UnknownHostException {
        expectCallForGetBISServiceURL(INVALID_URL, BIS_VERSION_4);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.connectSoap4();
    }

    @Test(expected = com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.MismatchBISVersion.class)
    public void getDocumentListDSWSExceptionExceptionSoap3() throws Exception {
        expectCallForGetBISServiceURL(null, BIS_VERSION_3);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getDocumentFolderList();

    }

    @Test(expected = com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.MismatchBISVersion.class)
    public void getDocumentListDSWSExceptionExceptionSoap4() throws Exception {
        expectCallForGetBISServiceURL(null, BIS_VERSION_4);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getDocumentFolderList();
    }

    @Test
    public void convertDocumentListToBIObjectTreeDataTypeListSoap3() {
        mockDocSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Document.class);
        mockSessionInfoSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo.class);
        mockBicatalogDocSoap3();
        mockSessionInfoForTokenSoap3();
        expectCallForGetBISServiceURL(VALID_URL, BIS_VERSION_3);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        ReflectionTestUtils.setField(soapClient, "sessionInfoSoap3", mockSessionInfoSoap3);
        soapClient.postConstruct();
        soapClient.convertDocumentListToBIObjectTreeDataTypeListSoap3(mockDocsSoap3);
    }

    @Test
    public void convertDocumentListToBIObjectTreeDataTypeListSoap4() {
        mockDocSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Document.class);
        mockSessionInfoSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo.class);
        mockBicatalogDocSoap4();
        mockSessionInfoForTokenSoap4();
        expectCallForGetBISServiceURL(VALID_URL, BIS_VERSION_4);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        ReflectionTestUtils.setField(soapClient, "sessionInfoSoap4", mockSessionInfoSoap4);
        soapClient.postConstruct();
        soapClient.convertDocumentListToBIObjectTreeDataTypeListSoap4(mockDocsSoap4);
    }

    @Test
    public void getDocumentFolderListRecursiveSoap3() throws Exception {
        mockRootFolderSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.Folder.class);
        mockSessionInfoSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo.class);
        mockbiCatalogSoapSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalogSoap.class);

        mockRootFolderSoap3();
        mockSessionInfoForSIDSoap3();
        mockSortTypesSoap3();
        mockBiCatalogSoapSoap3();

        expectCallForGetBISServiceURL(VALID_URL, BIS_VERSION_3);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        ReflectionTestUtils.setField(soapClient, "biCatalogSoapSoap3", mockbiCatalogSoapSoap3);

        ReflectionTestUtils.setField(soapClient, "sessionInfoSoap3", mockSessionInfoSoap3);
        soapClient.postConstruct();

        soapClient.getDocumentFolderListRecursiveSoap3(mockSessionInfoSoap3, mockRootFolderSoap3);
    }

    @Test
    public void getDocumentFolderListRecursiveSoap4() throws Exception {
        mockRootFolderSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.Folder.class);
        mockSessionInfoSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo.class);
        mockbiCatalogSoapSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalogSoap.class);

        mockRootFolderSoap4();
        mockSessionInfoForSIDSoap4();
        mockSortTypesSoap4();
        mockBiCatalogSoapSoap4();

        expectCallForGetBISServiceURL(VALID_URL, BIS_VERSION_4);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        ReflectionTestUtils.setField(soapClient, "biCatalogSoapSoap4", mockbiCatalogSoapSoap4);

        ReflectionTestUtils.setField(soapClient, "sessionInfoSoap4", mockSessionInfoSoap4);
        soapClient.postConstruct();

        soapClient.getDocumentFolderListRecursiveSoap4(mockSessionInfoSoap4, mockRootFolderSoap4);
    }

    @Test(expected = com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.DSWSException_Exception.class)
    public void testsearchRootFolderSoap4() throws Exception {
        mockSessionInfoSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.session.SessionInfo.class);
        mockbiCatalogSoapSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.reports.bicatalog.BICatalogSoap.class);

        mockSessionInfoForSIDSoap4();
        mockSortTypesSoap4();
        mockBiCatalogSearchSoap4();

        expectCallForGetBISServiceURL(VALID_URL, BIS_VERSION_4);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        ReflectionTestUtils.setField(soapClient, "biCatalogSoapSoap4", mockbiCatalogSoapSoap4);

        ReflectionTestUtils.setField(soapClient, "sessionInfoSoap4", mockSessionInfoSoap4);
        soapClient.postConstruct();

        soapClient.searchRootFolderSoap4(mockSessionInfoSoap4);
    }

    @Test(expected = com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.DSWSException_Exception.class)
    public void testsearchRootFolderSoap3() throws Exception {
        mockSessionInfoSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.session.SessionInfo.class);
        mockbiCatalogSoapSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.reports.bicatalog.BICatalogSoap.class);

        mockSessionInfoForSIDSoap3();
        mockSortTypesSoap3();
        mockBiCatalogSearchSoap3();

        expectCallForGetBISServiceURL(VALID_URL, BIS_VERSION_3);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        ReflectionTestUtils.setField(soapClient, "biCatalogSoapSoap3", mockbiCatalogSoapSoap3);

        ReflectionTestUtils.setField(soapClient, "sessionInfoSoap3", mockSessionInfoSoap3);
        soapClient.postConstruct();

        soapClient.searchRootFolderSoap3(mockSessionInfoSoap3);
    }
}
