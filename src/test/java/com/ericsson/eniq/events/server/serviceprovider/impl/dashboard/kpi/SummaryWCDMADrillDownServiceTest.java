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
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.kpi.SummaryWCDMADrillDownServiceTestDataProvider;

/**
 * Desrible SummaryWCDMADrillDownServiceTest
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:dashboard-service-context.xml" })
public class SummaryWCDMADrillDownServiceTest extends ServiceBaseTest {
    @Resource(name = "summaryWCDMADrillDownService")
    SummaryWCDMADrillDownService service;

    @Test
    @Parameters(source = SummaryWCDMADrillDownServiceTestDataProvider.class)
    public void testGetDataSoap4(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, service);
    }

    @Test
    @Parameters(source = SummaryWCDMADrillDownServiceTestDataProvider.class)
    public void testGetDataSoap3(final MultivaluedMap<String, String> requestParameters) {
        ReflectionTestUtils.setField(service, "version", "3.1");
        runQuery(requestParameters, service);
    }

}
