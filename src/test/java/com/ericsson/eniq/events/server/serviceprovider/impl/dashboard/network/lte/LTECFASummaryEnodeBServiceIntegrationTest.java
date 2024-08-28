/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.network.lte;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.lte.SummaryEnodeBTestDataProvider;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author echchik
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:dashboard-service-context.xml"})
public class LTECFASummaryEnodeBServiceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCfaSummaryEnodeBService")
    private LTECFASummaryEnodeBService lteCfaSummaryEnodeBService;

    @Test
    @Parameters(source = SummaryEnodeBTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCfaSummaryEnodeBService);
    }
}
