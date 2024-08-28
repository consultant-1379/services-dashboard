/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.dashboard;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author echimma
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class AccessAreaResource extends AbstractResource {

    private static final String DASHBOARD_LTE_HFA_ECELL_SERVICE = "LTEHFASummaryEcellService";

    private static final String DASHBOARD_LTE_CFA_ECELL_SERVICE = "LTECFASummaryEcellService";

    private static final String DASHBOARD_LTE_CFA_ECELL_DRILLDOWN_SERVICE = "LTECFASummaryEcellDrilldownService";

    private static final String DASHBOARD_LTE_HFA_ECELL_DRILLDOWN_SERVICE = "LTEHFASummaryEcellDrilldownService";

    @EJB(beanName = DASHBOARD_LTE_CFA_ECELL_SERVICE)
    private Service lteCfaSummaryEcellService;

    @EJB(beanName = DASHBOARD_LTE_HFA_ECELL_SERVICE)
    private Service lteHfaSummaryEcellService;

    @EJB(beanName = DASHBOARD_LTE_CFA_ECELL_DRILLDOWN_SERVICE)
    private Service lteCfaSummaryEcellDrilldownService;

    @EJB(beanName = DASHBOARD_LTE_HFA_ECELL_DRILLDOWN_SERVICE)
    private Service lteHfaSummaryEcellDrilldownService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(LTE_CFA_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCfaSummaryEnodeService() {
        return lteCfaSummaryEcellService.getData(mapResourceLayerParameters());
    }

    @Path(LTE_HFA_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteHfaDrilldownEnodeService() {
        return lteHfaSummaryEcellService.getData(mapResourceLayerParameters());
    }

    @Path(LTE_CFA_DRILLDOWN + PATH_SEPARATOR + SUMMARY_LTE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCfaDrilldownEnodeService() {
        return lteCfaSummaryEcellDrilldownService.getData(mapResourceLayerParameters());
    }

    @Path(LTE_CFA_DRILLDOWN + PATH_SEPARATOR + SUMMARY_LTE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCfaDrilldownEnodeServiceAsCSV() {
        return lteCfaSummaryEcellDrilldownService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(LTE_HFA_DRILLDOWN + PATH_SEPARATOR + SUMMARY_LTE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteHfaSummaryEnodeService() {
        return lteHfaSummaryEcellDrilldownService.getData(mapResourceLayerParameters());
    }

    @Path(LTE_HFA_DRILLDOWN + PATH_SEPARATOR + SUMMARY_LTE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteHfaSummaryEnodeServiceAsCSV() {
        return lteHfaSummaryEcellDrilldownService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
