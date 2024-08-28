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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDrillDownDataType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/*
 * Given a Business Intelligence Server host name, and a date, this program generates the Total number of connection requests and the Connection
 * Success rate for all cells and rnc's in a network, upon user input
 */

@Stateless
@Local
public class NodeQuery {

    @EJB
    ApplicationConfigManager applicationConfigManager;

    private static String serviceURL;

    private static String username;

    private static String password;

    private static final String SERVICE_PATH_NAME = "dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVXcrr2OpENFgxXW_5e3S7w";

    private static final QName SELECTION_QUERTY_QNAME = new QName("EniqEventsServicesNetworkRNCCellQuery", "EniqEventsServicesNetworkRNCCellQuery");

    private static final NodeQueryKPIDataType DEFAULT_RETURNED_OBJECT = new NodeQueryKPIDataType(0.0, 0);

    private static final List<String> EMPTY_LIST_STRING = new ArrayList<String>();

    private static final int KPI_MULT = 100;

    /**
     * get the serviceURL from glassfish
     */
    @PostConstruct
    public void init() {
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_PATH_NAME;
        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
    }

    public NodeQueryKPIDataType getNetworkKPISoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        return getKPISoap3(EMPTY_LIST_STRING, EMPTY_LIST_STRING, date);
    }

    public NodeQueryKPIDataType getNetworkKPISoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        return getKPISoap4(EMPTY_LIST_STRING, EMPTY_LIST_STRING, date);
    }

    public List<NodeQueryKPIDrillDownDataType> getNetworkKPIDrillDownSoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        return getDrillDownDataTypeSoap3(EMPTY_LIST_STRING, EMPTY_LIST_STRING, date);
    }

    public List<NodeQueryKPIDrillDownDataType> getNetworkKPIDrillDownSoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        return getDrillDownDataTypeSoap4(EMPTY_LIST_STRING, EMPTY_LIST_STRING, date);
    }

    public List<NodeQueryKPIDrillDownDataType> getRNCKPIDrillDownSoap3(final List<String> rncIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getDrillDownDataTypeSoap3(rncIDs, EMPTY_LIST_STRING, date);

    }

    public List<NodeQueryKPIDrillDownDataType> getRNCKPIDrillDownSoap4(final List<String> rncIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getDrillDownDataTypeSoap4(rncIDs, EMPTY_LIST_STRING, date);

    }

    public List<NodeQueryKPIDrillDownDataType> getCellKPIDrillDownSoap3(final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getDrillDownDataTypeSoap3(EMPTY_LIST_STRING, cellIDs, date);
    }

    public List<NodeQueryKPIDrillDownDataType> getCellKPIDrillDownSoap4(final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getDrillDownDataTypeSoap4(EMPTY_LIST_STRING, cellIDs, date);
    }

    private List<NodeQueryKPIDrillDownDataType> getDrillDownDataTypeSoap3(final List<String> rncIDs, final List<String> cellIDs,
                                                                          final XMLGregorianCalendar date) throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> rows = getListOfKPIRowFromBISSoap3(
                rncIDs, cellIDs, date);
        return convertRowsToNodeQueryKPIDrillDownDataTypeSoap3(rows);

    }

    private List<NodeQueryKPIDrillDownDataType> getDrillDownDataTypeSoap4(final List<String> rncIDs, final List<String> cellIDs,
                                                                          final XMLGregorianCalendar date) throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> rows = getListOfKPIRowFromBISSoap4(
                rncIDs, cellIDs, date);
        return convertRowsToNodeQueryKPIDrillDownDataTypeSoap4(rows);

    }

    private List<NodeQueryKPIDrillDownDataType> convertRowsToNodeQueryKPIDrillDownDataTypeSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> rows) {
        final List<NodeQueryKPIDrillDownDataType> nodeQueryKPIDrillDownDataTypeList = new ArrayList<NodeQueryKPIDrillDownDataType>();
        Double succRate = 0.0;
        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row r : rows) {
            succRate = 0.0;
            if ((int) r.getReq().doubleValue() == 0) {
                succRate = 0.0;
            } else {
                succRate = (100 * r.getReqSuccess()) / r.getReq();
            }

            final NodeQueryKPIDrillDownDataType nodeQueryKPIDrillDownDataType = getKpiDrillDownTypeSoap3(succRate, r);
            nodeQueryKPIDrillDownDataTypeList.add(nodeQueryKPIDrillDownDataType);
        }
        return nodeQueryKPIDrillDownDataTypeList;
    }

    private List<NodeQueryKPIDrillDownDataType> convertRowsToNodeQueryKPIDrillDownDataTypeSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> rows) {
        final List<NodeQueryKPIDrillDownDataType> nodeQueryKPIDrillDownDataTypeList = new ArrayList<NodeQueryKPIDrillDownDataType>();
        Double succRate = 0.0;
        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row r : rows) {
            succRate = 0.0;
            if ((int) r.getReq().doubleValue() == 0) {
                succRate = 0.0;
            } else {
                succRate = (100 * r.getReqSuccess()) / r.getReq();
            }

            final NodeQueryKPIDrillDownDataType nodeQueryKPIDrillDownDataType = getKpiDrillDownTypeSoap4(succRate, r);
            nodeQueryKPIDrillDownDataTypeList.add(nodeQueryKPIDrillDownDataType);
        }
        return nodeQueryKPIDrillDownDataTypeList;
    }

    /**
     * @param succRate
     * @param r
     * @return
     */
    private NodeQueryKPIDrillDownDataType getKpiDrillDownTypeSoap3(final Double succRate,
                                                                   final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row r) {
        final NodeQueryKPIDrillDownDataType nodeQueryKPIDrillDownDataType = new NodeQueryKPIDrillDownDataType(r.getUCellName(), r.getRNCName(),
                (int) r.getReqSuccess().doubleValue(), succRate, (int) r.getReq().doubleValue());
        return nodeQueryKPIDrillDownDataType;
    }

    private NodeQueryKPIDrillDownDataType getKpiDrillDownTypeSoap4(final Double succRate,
                                                                   final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row r) {
        final NodeQueryKPIDrillDownDataType nodeQueryKPIDrillDownDataType = new NodeQueryKPIDrillDownDataType(r.getUCellName(), r.getRNCName(),
                (int) r.getReqSuccess().doubleValue(), succRate, (int) r.getReq().doubleValue());
        return nodeQueryKPIDrillDownDataType;
    }

    public NodeQueryKPIDataType getRNCKPISoap3(final List<String> rncIDs, final XMLGregorianCalendar date) throws MalformedURLException {
        if ((null != rncIDs) && !(rncIDs.isEmpty())) {
            return getKPISoap3(rncIDs, EMPTY_LIST_STRING, date);
        }
        return DEFAULT_RETURNED_OBJECT;

    }

    public NodeQueryKPIDataType getRNCKPISoap4(final List<String> rncIDs, final XMLGregorianCalendar date) throws MalformedURLException {
        if ((null != rncIDs) && !(rncIDs.isEmpty())) {
            return getKPISoap3(rncIDs, EMPTY_LIST_STRING, date);
        }
        return DEFAULT_RETURNED_OBJECT;

    }

    public NodeQueryKPIDataType getCellKPISoap3(final List<String> cellIDs, final XMLGregorianCalendar date) throws MalformedURLException {
        if ((null != cellIDs) && !(cellIDs.isEmpty())) // a good param
        {
            return getKPISoap3(EMPTY_LIST_STRING, cellIDs, date);
        }
        return DEFAULT_RETURNED_OBJECT;
    }

    public NodeQueryKPIDataType getCellKPISoap4(final List<String> cellIDs, final XMLGregorianCalendar date) throws MalformedURLException {
        if ((null != cellIDs) && !(cellIDs.isEmpty())) // a good param
        {
            return getKPISoap3(EMPTY_LIST_STRING, cellIDs, date);
        }
        return DEFAULT_RETURNED_OBJECT;
    }

    private NodeQueryKPIDataType getKPISoap3(final List<String> rncIDs, final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> kpiRows = getListOfKPIRowFromBISSoap3(
                rncIDs, cellIDs, date);
        return getRrcConnSuccessRateSoap3(kpiRows);
    }

    private NodeQueryKPIDataType getKPISoap4(final List<String> rncIDs, final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> kpiRows = getListOfKPIRowFromBISSoap4(
                rncIDs, cellIDs, date);
        return getRrcConnSuccessRateSoap4(kpiRows);
    }

    /**
     * @param rncIDs
     * @param cellIDs
     * @param date
     * @return
     * @throws MalformedURLException
     */

    private List<com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> getListOfKPIRowFromBISSoap3(final List<String> rncIDs,
                                                                                                                                                   final List<String> cellIDs,
                                                                                                                                                   final XMLGregorianCalendar date)
            throws MalformedURLException {
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.EniqEventsServicesNetworkRNCCellQuery nodeSelection = new com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.EniqEventsServicesNetworkRNCCellQuery(
                new URL(serviceURL), SELECTION_QUERTY_QNAME);
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.QueryAsAServiceSoap soapClient = nodeSelection
                .getQueryAsAServiceSoap();

        final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAService runQueryService = new com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);

        runQueryService.setDate(date);
        runQueryService.getEnterValueSForRNCId().addAll(rncIDs);
        runQueryService.getEnterValueSForUCellId().addAll(cellIDs);
        final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAServiceResponse serviceResponse = soapClient
                .runQueryAsAService(runQueryService,
                        new com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }

    private List<com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> getListOfKPIRowFromBISSoap4(final List<String> rncIDs,
                                                                                                                                                   final List<String> cellIDs,
                                                                                                                                                   final XMLGregorianCalendar date)
            throws MalformedURLException {
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.EniqEventsServicesNetworkRNCCellQuery nodeSelection = new com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.EniqEventsServicesNetworkRNCCellQuery(
                new URL(serviceURL), SELECTION_QUERTY_QNAME);
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.QueryAsAServiceSoap soapClient = nodeSelection
                .getQueryAsAServiceSoap();

        final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAService runQueryService = new com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);

        runQueryService.setDate(date);
        runQueryService.getEnterValueSForRNCId().addAll(rncIDs);
        runQueryService.getEnterValueSForUCellId().addAll(cellIDs);
        final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAServiceResponse serviceResponse = soapClient
                .runQueryAsAService(runQueryService,
                        new com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }

    /**
     * Calculate the KPI, RrcConnectionSuccessRate
     *
     * @param rows
     * @return
     */
    private NodeQueryKPIDataType getRrcConnSuccessRateSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> rows) {
        Double tempReq = 0.0;
        Double tempReqSucc = 0.0;
        Double rate = 0.0;
        /**
         * if the rows size is zero
         */

        if (rows.isEmpty()) {
            return new NodeQueryKPIDataType(Double.NaN, (int) tempReq.doubleValue());
        }

        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row row : rows) {
            tempReq += row.getReq();
            tempReqSucc += row.getReqSuccess();
        }
        rate = (KPI_MULT * (tempReqSucc / tempReq));
        final NodeQueryKPIDataType nodeQueryKPIDataType = new NodeQueryKPIDataType(rate, (int) tempReq.doubleValue());
        return nodeQueryKPIDataType;

    }

    private NodeQueryKPIDataType getRrcConnSuccessRateSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row> rows) {
        Double tempReq = 0.0;
        Double tempReqSucc = 0.0;
        Double rate = 0.0;
        /**
         * if the rows size is zero
         */

        if (rows.isEmpty()) {
            return new NodeQueryKPIDataType(Double.NaN, (int) tempReq.doubleValue());
        }

        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row row : rows) {
            tempReq += row.getReq();
            tempReqSucc += row.getReqSuccess();
        }
        rate = (KPI_MULT * (tempReqSucc / tempReq));
        final NodeQueryKPIDataType nodeQueryKPIDataType = new NodeQueryKPIDataType(rate, (int) tempReq.doubleValue());
        return nodeQueryKPIDataType;

    }
}