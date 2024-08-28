package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.homerroamer;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.BasicDataProvider;

/**
 *
 * @author eemecoy
 * @since 2012
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:dashboard-service-context.xml" })
public class SummaryHomerRoamerServiceTest extends ServiceBaseTest {

    @Resource(name = "summaryHomerRoamerService")
    SummaryHomerRoamerService summaryHomerRoamerService;

    @Test
    @Parameters(source = BasicDataProvider.class)
    public void getData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, summaryHomerRoamerService);
    }

}
