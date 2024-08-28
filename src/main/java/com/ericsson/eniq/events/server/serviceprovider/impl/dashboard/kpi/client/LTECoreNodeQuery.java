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
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.LTEKpiUtility;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkCoreKPIDataType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

@Stateless
@Local
public class LTECoreNodeQuery {

    @EJB
    ApplicationConfigManager applicationConfigManager;

    @EJB
    LTEKpiUtility lteKpiUtility;

    private static String serviceURL;

    private static String username;

    private static String password;

    private static final String SERVICE_PATH_NAME = "dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVoNMCPoSC9Cr.Tck0K.xgs";

    private static final QName SELECTION_QUERY_QNAME = new QName("EniqEventsLTENetworkCoreKPI", "EniqEventsLTENetworkCoreKPI");

    /**
     * get the serviceURL from glassfish
     */
    @PostConstruct
    public void init() {
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_PATH_NAME;
        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
    }

    public LTENetworkCoreKPIDataType getNetworkCoreKPISoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows = getListOfCoreKPIRowFromBISSoap3(date);
        return lteKpiUtility.calculateCoreKpisSoap3(kpiRows);
    }

    public LTENetworkCoreKPIDataType getNetworkCoreKPISoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows = getListOfCoreKPIRowFromBISSoap4(date);
        return lteKpiUtility.calculateCoreKpisSoap4(kpiRows);
    }

    /**
     * @param date
     * @return
     * @throws MalformedURLException
     */

    private List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> getListOfCoreKPIRowFromBISSoap3(final XMLGregorianCalendar date)
            throws MalformedURLException {
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI nodeSelection = new com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI(
                new URL(serviceURL), SELECTION_QUERY_QNAME);
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.QueryAsAServiceSoap soapClient = nodeSelection
                .getQueryAsAServiceSoap();
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAService runQueryService = new com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);
        runQueryService.setDate(date);
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAServiceResponse serviceResponse = soapClient
                .runQueryAsAService(runQueryService,
                        new com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }

    private List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> getListOfCoreKPIRowFromBISSoap4(final XMLGregorianCalendar date)
            throws MalformedURLException {
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI nodeSelection = new com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI(
                new URL(serviceURL), SELECTION_QUERY_QNAME);
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.QueryAsAServiceSoap soapClient = nodeSelection
                .getQueryAsAServiceSoap();
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAService runQueryService = new com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);
        runQueryService.setDate(date);
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAServiceResponse serviceResponse = soapClient
                .runQueryAsAService(runQueryService,
                        new com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }
}
