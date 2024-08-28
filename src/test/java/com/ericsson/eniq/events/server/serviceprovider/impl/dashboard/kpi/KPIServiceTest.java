package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.kpi.RNCKPIServiceTestDataProvider;

/**
 * Desrible RNCKPIServiceTest
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:dashboard-service-context.xml" })
public class KPIServiceTest extends ServiceBaseTest {

    @Resource(name = "rncKPIService")
    RNCKPIService rncKPIService;

    @Resource(name = "cellKPIService")
    CellKPIService cellKPIService;

    @Resource(name = "cellGroupKPIService")
    CellGroupKPIService cellGroupKPIService;

    @Resource(name = "cellKPIDrillDownService")
    CellKPIDrillDownService cellKPIDrillDownService;

    @Resource(name = "cellGroupKPIDrillDownService")
    CellGroupKPIDrillDownService cellGroupKPIDrillDownService;

    @Resource(name = "rnckpiDrillDownService")
    RNCKPIDrillDownService rnckpiDrillDownService;

    @Resource(name = "rncGroupKPIDrillDownService")
    RNCGroupKPIDrillDownService rncGroupKPIDrillDownService;

    @Resource(name = "summaryWCDMAService")
    SummaryWCDMAService summaryWCDMAService;

    @Resource(name = "rncGroupKPIService")
    RNCGroupKPIService rncGroupKPIService;

    @Resource(name = "summaryWCDMADrillDownService")
    SummaryWCDMADrillDownService summaryWCDMADrillDownService;

    @Resource(name = "lteNetworkRanKpiService")
    LTENetworkRanKpiService lteNetworkRanKpiService;

    @Resource(name = "lteNetworkCoreKpiService")
    LTENetworkCoreKpiService lteNetworkCoreKpiService;

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCKPIServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncKPIService);
    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCKPIServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(rncKPIService, "version", "3.1");
        runQuery(requestParameters, rncKPIService);
    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellKPIServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellKPIServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(cellKPIService, "version", "3.1");
        runQuery(requestParameters, cellKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellGroupKPIServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellGroupKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellGroupKPIServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(cellGroupKPIService, "version", "3.1");
        runQuery(requestParameters, cellGroupKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellKPIDrillDownServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellKPIDrillDownServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(cellKPIDrillDownService, "version", "3.1");
        runQuery(requestParameters, cellKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellGroupKPIDrillDownServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, cellGroupKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataCellGroupKPIDrillDownServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(cellGroupKPIDrillDownService, "version", "3.1");
        runQuery(requestParameters, cellGroupKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCKPIDrillDownServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rnckpiDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCKPIDrillDownServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(rnckpiDrillDownService, "version", "3.1");
        runQuery(requestParameters, rnckpiDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCGroupKPIDrillDownServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncGroupKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCGroupKPIDrillDownServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(rncGroupKPIDrillDownService, "version", "3.1");
        runQuery(requestParameters, rncGroupKPIDrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataSummaryWCDMAServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, summaryWCDMAService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataSummaryWCDMAServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(summaryWCDMAService, "version", "3.1");
        runQuery(requestParameters, summaryWCDMAService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCGroupKPIServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, rncGroupKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataRNCGroupKPIServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(rncGroupKPIService, "version", "3.1");
        runQuery(requestParameters, rncGroupKPIService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataSummaryWCDMADrillDownServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, summaryWCDMADrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataSummaryWCDMADrillDownServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(summaryWCDMADrillDownService, "version", "3.1");
        runQuery(requestParameters, summaryWCDMADrillDownService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataLTENetworkRanKpiServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteNetworkRanKpiService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataLTENetworkRanKpiServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(lteNetworkRanKpiService, "version", "3.1");
        runQuery(requestParameters, lteNetworkRanKpiService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataLTENetworkCoreKpiServiceSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteNetworkCoreKpiService);

    }

    @Test
    @Parameters(source = RNCKPIServiceTestDataProvider.class)
    public void testGetDataLTENetworkCoreKpiServiceSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(lteNetworkCoreKpiService, "version", "3.1");
        runQuery(requestParameters, lteNetworkCoreKpiService);
    }
}
