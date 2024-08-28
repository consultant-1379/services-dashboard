package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.BasicDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BICatalogObjectSoapClient;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Desrible DashboardReportsListServiceTest
 *
 * @author ezhelao
 * @since 11/2011
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:dashboard-service-context.xml"})
public class DashboardReportsListServiceTest extends ServiceBaseTest {

    @Resource(name = "biCatalogObjectSoapClient")
    BICatalogObjectSoapClient biCatalogObjectSoapClient;

    @Resource(name = "dashboardReportsListService")
    DashboardReportsListService service;

    @Test
    @Parameters(source = BasicDataProvider.class)
    public void getData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, service);
    }


}
