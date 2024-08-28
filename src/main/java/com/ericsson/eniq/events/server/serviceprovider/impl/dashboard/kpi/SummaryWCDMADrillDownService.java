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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDrillDownDataType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

/**
 * Desrible SummaryWCDMADrillDownService
 */

@Stateless
@Local(Service.class)
public class SummaryWCDMADrillDownService extends AbstractKPIService {

    @EJB
    private ApplicationConfigManager applicationConfigManager;
    private String version;

    @PostConstruct
    public void postConstruct() {
        version = applicationConfigManager.getBISVersion();
    }

    @Override
    public Response getDataAsCSV(final MultivaluedMap<String, String> serviceProviderParameters, final HttpServletResponse response) {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getData(final MultivaluedMap<String, String> serviceProviderParameters) {
        try {
            final XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility
                    .getXMLGregorianCalendarFromParams(serviceProviderParameters);
            final List<NodeQueryKPIDrillDownDataType> nodeQueryKPIDataTypes;
            if (version.trim().equals(BIS_SOAP_SERVICES_VERSION_SOAP4)) {
                nodeQueryKPIDataTypes = nodeQuery.getNetworkKPIDrillDownSoap4(xmlGregorianCalendar);
            } else {
                nodeQueryKPIDataTypes = nodeQuery.getNetworkKPIDrillDownSoap3(xmlGregorianCalendar);
            }
            final KPIDataType kpiDataType = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(nodeQueryKPIDataTypes);
            return objectMapper.writeValueAsString(kpiDataType);
        } catch (Exception e) {
            return handleKPIServiceException(e, SummaryWCDMADrillDownService.class.getName());
        }

    }
}
