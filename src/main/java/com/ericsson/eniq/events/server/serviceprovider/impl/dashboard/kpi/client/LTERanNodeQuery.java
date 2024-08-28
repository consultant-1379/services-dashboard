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
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkRanKPIDataType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

@Stateless
@Local
public class LTERanNodeQuery {

    @EJB
    ApplicationConfigManager applicationConfigManager;

    @EJB
    LTEKpiUtility lteKpiUtility;

    private static String serviceURL;

    private static String username;

    private static String password;

    private static final String SERVICE_RAN_PATH_NAME = "dswsbobje/qaawsservices/biws?WSDL=1&cuid=AZDb2pDkEGFMutVcNYOiFuk";

    private static final QName SELECTION_RAN_QUERY_QNAME = new QName("EniqEventsLTENetworkRanKpi", "EniqEventsLTENetworkRanKpi");

    /**
     * get the serviceURL from glassfish
     */
    @PostConstruct
    public void init() {
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_RAN_PATH_NAME;
        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
    }

    public LTENetworkRanKPIDataType getNetworkRanKPISoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows = getListOfRanKPIRowFromBISSoap3(date);
        return lteKpiUtility.calculateRanKpisSoap3(kpiRows);
    }

    public LTENetworkRanKPIDataType getNetworkRanKPISoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows = getListOfRanKPIRowFromBISSoap4(date);
        return lteKpiUtility.calculateRanKpisSoap4(kpiRows);
    }

    /**
     * @param date
     * @return
     * @throws MalformedURLException
     */

    private List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> getListOfRanKPIRowFromBISSoap3(final XMLGregorianCalendar date)
            throws MalformedURLException {
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.EniqEventsLTENetworkRanKpi nodeSelection = new com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.EniqEventsLTENetworkRanKpi(
                new URL(serviceURL), SELECTION_RAN_QUERY_QNAME);
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.QueryAsAServiceSoap soapClient = nodeSelection
                .getQueryAsAServiceSoap();
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAService runQueryService = new com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);
        runQueryService.setDate(date);
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAServiceResponse serviceResponse = soapClient
                .runQueryAsAService(runQueryService,
                        new com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }

    private List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> getListOfRanKPIRowFromBISSoap4(final XMLGregorianCalendar date)
            throws MalformedURLException {
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.EniqEventsLTENetworkRanKpi nodeSelection = new com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.EniqEventsLTENetworkRanKpi(
                new URL(serviceURL), SELECTION_RAN_QUERY_QNAME);
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.QueryAsAServiceSoap soapClient = nodeSelection
                .getQueryAsAServiceSoap();
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAService runQueryService = new com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);
        runQueryService.setDate(date);
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAServiceResponse serviceResponse = soapClient
                .runQueryAsAService(runQueryService,
                        new com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }
}
