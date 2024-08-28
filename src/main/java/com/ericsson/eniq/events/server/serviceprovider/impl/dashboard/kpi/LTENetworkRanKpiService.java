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
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkRanKPIDataType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

@Stateless
@Local(Service.class)
public class LTENetworkRanKpiService extends AbstractKPIService {

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
                    .getXMLLocalGregorianCalendarFromParams(serviceProviderParameters);
            final LTENetworkRanKPIDataType nodeQueryKPIDataType;
            if (version.trim().equals(BIS_SOAP_SERVICES_VERSION_SOAP4)) {
                nodeQueryKPIDataType = lteNodeQuery.getNetworkRanKPISoap4(xmlGregorianCalendar);

            } else {
                nodeQueryKPIDataType = lteNodeQuery.getNetworkRanKPISoap3(xmlGregorianCalendar);
            }
            final KPIDataType kpiDataType = lteKpiUtility.getLteNetworkRanKpiData(nodeQueryKPIDataType);
            return objectMapper.writeValueAsString(kpiDataType);
        } catch (final Exception e) {
            return handleKPIServiceException(e, LTENetworkRanKpiService.class.getName());
        }
    }
}
