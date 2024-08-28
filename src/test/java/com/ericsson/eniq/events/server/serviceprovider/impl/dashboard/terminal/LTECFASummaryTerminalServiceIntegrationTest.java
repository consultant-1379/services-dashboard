/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.TopFiveTerminalTestDataProvider;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal.LTECFASummaryTerminalService;

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
public class LTECFASummaryTerminalServiceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "lteCfaSummaryTerminalService")
    private LTECFASummaryTerminalService lteCfaSummaryTerminalService;

    @Test
    @Parameters(source = TopFiveTerminalTestDataProvider.class)
    public void testGetData(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, lteCfaSummaryTerminalService);
    }
}
