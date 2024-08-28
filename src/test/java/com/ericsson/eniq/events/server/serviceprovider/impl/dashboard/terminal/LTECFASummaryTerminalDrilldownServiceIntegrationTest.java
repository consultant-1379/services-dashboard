/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.lte.LTECFASummaryTerminalDrilldownTestDataProvider;

/**
 * @author echimma
 * @since 2012
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = { "classpath:dashboard-service-context.xml" })
public class LTECFASummaryTerminalDrilldownServiceIntegrationTest extends ServiceBaseTest {
    @Resource(name = "lteCfaSummaryTerminalDrilldownService")
    private LTECFASummaryTerminalDrilldownService lteCfaSummaryTerminalDrilldownService;

    @Test
    @Parameters(source = LTECFASummaryTerminalDrilldownTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCfaSummaryTerminalDrilldownService);
    }
}
