/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.network.kpi.core;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.CoreNetworkKPIServiceDataProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author ericker
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:dashboard-service-context.xml"})
@Ignore
public class CoreNetworkKPIServiceTest extends ServiceBaseTest {

    @Resource
    private CoreNetworkKPIService coreNetworkKPIService;

    @Test
    @Parameters(source = CoreNetworkKPIServiceDataProvider.class)
    public void testCoreNetworkKPIService(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, coreNetworkKPIService);
    }
}
