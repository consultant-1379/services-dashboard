package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client;

import java.io.IOException;
import java.util.*;

import javax.xml.datatype.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.jmock.Expectations;
import org.junit.*;
import org.springframework.test.util.ReflectionTestUtils;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.RNCCellKPIServiceParamUtil;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

public class NodeQueryTest extends BaseJMockUnitTest {

    NodeQuery nodeQuery;
    ApplicationConfigManager mockedApplicationConfigManager;
    RNCCellKPIServiceParamUtil rncCellKPIServiceParamUtil;
    ObjectMapper objectMapper;

    private static final String BIS_VERSION_3 = "3.1";

    private static final String BIS_VERSION_4 = "4.1";

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        nodeQuery = new NodeQuery();
        mockedApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);
        allowOtherCallsForBISConfigurationSettings();

        rncCellKPIServiceParamUtil = new RNCCellKPIServiceParamUtil();
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

    private void mockBISServiceURL(final String url, final String bisVersion) {
        mockery.checking(new Expectations() {

            {
                one(mockedApplicationConfigManager).getBISVersion();
                will(returnValue(bisVersion));
                one(mockedApplicationConfigManager).getBISServiceURL();
                will(returnValue(url));
                one(mockedApplicationConfigManager).getBISUsername();
                will(returnValue("administrator"));
                one(mockedApplicationConfigManager).getBISPassword();
                will(returnValue(""));
            }

        });
        ReflectionTestUtils.setField(nodeQuery, "applicationConfigManager", mockedApplicationConfigManager);
        nodeQuery.init();

    }

    @Test
    @Ignore
    public void testGetNodeInformationRNCSuccessSoap3() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/", BIS_VERSION_3);
        final List<String> RNCs = new ArrayList<String>();
        RNCs.add("RNC1");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2011, 10, 14, 10, 10);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getRNCKPISoap3(RNCs, xmlGregorianCalendar);

    }

    @Test
    @Ignore
    public void testGetNodeInformationRNCSuccessSoap4() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/", BIS_VERSION_4);
        final List<String> RNCs = new ArrayList<String>();
        RNCs.add("RNC1");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2011, 10, 14, 10, 10);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getRNCKPISoap4(RNCs, xmlGregorianCalendar);

    }

    @Test
    @Ignore
    public void testGetNodeInformationCellSuccessSoap3() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/", BIS_VERSION_3);
        final List<String> cells = new ArrayList<String>();
        cells.add("RNC1-1-1");
        cells.add("RNC2-1-1");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2011, 10, 14, 14, 12, 37);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getCellKPISoap3(cells, xmlGregorianCalendar);

    }

    @Test
    @Ignore
    public void testGetNodeInformationCellSuccessSoap4() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/", BIS_VERSION_4);
        final List<String> cells = new ArrayList<String>();
        cells.add("RNC1-1-1");
        cells.add("RNC2-1-1");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2011, 10, 14, 14, 12, 37);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getCellKPISoap4(cells, xmlGregorianCalendar);

    }

    @Test
    @Ignore
    public void testGetNodeInformationNetworkSuccessSoap3() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/", BIS_VERSION_3);
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2011, 10, 14, 15, 00, 00);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getNetworkKPISoap3(xmlGregorianCalendar);

    }

    @Test
    @Ignore
    public void testGetNodeInformationNetworkSuccessSoap4() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/", BIS_VERSION_4);
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2011, 10, 14, 15, 00, 00);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getNetworkKPISoap4(xmlGregorianCalendar);

    }

    @Test
    public void objectMapperTest() throws IOException {
        final KPIDataType kpiDataType = new KPIDataType();

    }

}
