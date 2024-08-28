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
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
 * Desrible SummaryWCDMAService
 */
@Stateless
@Local(Service.class)
public class SummaryWCDMAService extends AbstractKPIService {

    @EJB
    private ApplicationConfigManager applicationConfigManager;
    private String version;

    @PostConstruct
    public void postConstruct() {
        version = applicationConfigManager.getBISVersion();
    }

    @Override
    public Response getDataAsCSV(final MultivaluedMap<String, String> serviceProviderParameters, final HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getData(final MultivaluedMap<String, String> serviceProviderParameters) {
        try {
            final XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility
                    .getXMLGregorianCalendarFromParams(serviceProviderParameters);
            final NodeQueryKPIDataType nodeQueryKPIDataType;

            final XMLGregorianCalendar xmlGregorianCalendarPreviousDay = dashboardKpiCalenderUtility
                    .getPreviousDayXMLGregorianCalendarFromParams(serviceProviderParameters);

            final NodeQueryKPIDataType nodeQueryKPIDataTypePreviousDay;
            if (version.trim().equals(BIS_SOAP_SERVICES_VERSION_SOAP4)) {
                nodeQueryKPIDataType = nodeQuery.getNetworkKPISoap4(xmlGregorianCalendar);
                nodeQueryKPIDataTypePreviousDay = nodeQuery.getNetworkKPISoap4(xmlGregorianCalendarPreviousDay);
            } else {
                nodeQueryKPIDataType = nodeQuery.getNetworkKPISoap3(xmlGregorianCalendar);
                nodeQueryKPIDataTypePreviousDay = nodeQuery.getNetworkKPISoap3(xmlGregorianCalendarPreviousDay);
            }
            final KPIDataType kpiDataType = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(nodeQueryKPIDataType,
                    nodeQueryKPIDataTypePreviousDay);
            return objectMapper.writeValueAsString(kpiDataType);
        } catch (Exception e) {
            return handleKPIServiceException(e, SummaryWCDMAService.class.getName());
        }
    }
}
