/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
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

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author eeikbe
 * @since 2011
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class TerminalResource extends AbstractResource {

    private static final String DASHBOARD_TERMINAL_SERVICE = "SummaryTerminalService";

    private static final String DASHBOARD_TERMINAL_WCDMA_SERVICE = "SummaryTerminalWCDMAService";

    private static final String DASHBOARD_LTE_CFA_TERMINAL_SERVICE = "LTECFASummaryTerminalService";

    @EJB
    protected TerminalDrillDownResource drillDownResource;

    @EJB(beanName = DASHBOARD_TERMINAL_SERVICE)
    private Service summaryTerminalService;

    @EJB(beanName = DASHBOARD_TERMINAL_WCDMA_SERVICE)
    private Service summaryTerminalWCDMAService;

    @EJB(beanName = DASHBOARD_LTE_CFA_TERMINAL_SERVICE)
    private Service lteCfaSummaryTerminalService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    //---------------------------------------------------------------------------------
    //THE CORE SIDE...
    //---------------------------------------------------------------------------------

    @Path(DASHBOARD_SUMMARY_CORE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSummaryTerminalService() {
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(APN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAPNTerminalService() {
        //Redirect to Summary Network view for APN.
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(APN_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAPNGroupTerminalService() {
        //Redirect to Summary Network view for APN.
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_SGSN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSGSNTerminalService() {
        //Redirect to Summary Network view for SGSN.
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(SGSN_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSGSNGroupTerminalService() {
        //Redirect to Summary Network view for SGSN.
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_MSC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMSCTerminalService() {
        //Redirect to Summary Network view for MSC.
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(MSC_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMSCGroupTerminalService() {
        //Redirect to Summary Network view for MSC.
        return summaryTerminalService.getData(mapResourceLayerParameters());
    }

    @Path(DRILLDOWN)
    public TerminalDrillDownResource getDrillDownResource() {
        return this.drillDownResource;
    }

    //---------------------------------------------------------------------------------
    //THE RADIO SIDE...
    //---------------------------------------------------------------------------------
    @Path(DASHBOARD_SUMMARY_WCDMA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSummaryTerminalWCDMAService() {
        return summaryTerminalWCDMAService.getData(mapResourceLayerParameters());
    }

    //Controller
    @Path(TYPE_BSC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBSCTerminalWCDMAService() {
        return summaryTerminalWCDMAService.getData(mapResourceLayerParameters());
    }

    //Controller Group
    @Path(BSC_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBSCGroupTerminalWCDMAService() {
        return summaryTerminalWCDMAService.getData(mapResourceLayerParameters());
    }

    //Access Area
    @Path(TYPE_CELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCELLTerminalWCDMAService() {
        return summaryTerminalWCDMAService.getData(mapResourceLayerParameters());
    }

    //Access Area Group
    @Path(CELL_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCELLGroupTerminalWCDMAService() {
        return summaryTerminalWCDMAService.getData(mapResourceLayerParameters());
    }

    //---------------------------------------------------------------------------------
    //THE LTE SIDE...
    //---------------------------------------------------------------------------------

    @Path(SUMMARY_LTE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCfaSummaryTerminalService() {
        return lteCfaSummaryTerminalService.getData(mapResourceLayerParameters());
    }
}
