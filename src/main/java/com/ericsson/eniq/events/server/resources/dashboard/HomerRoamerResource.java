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
public class HomerRoamerResource extends AbstractResource {

    private static final String DASHBOARD_HOMER_ROAMER_SERVICE = "SummaryHomerRoamerService";

    private static final String DASHBOARD_HOMER_ROAMER_WCDMA_SERVICE = "SummaryHomerRoamerWCDMAService";

    @EJB(beanName = DASHBOARD_HOMER_ROAMER_SERVICE)
    private Service homerRoamerSummaryService;

    @EJB(beanName = DASHBOARD_HOMER_ROAMER_WCDMA_SERVICE)
    private Service homerRoamerSummaryWCDMAService;

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
    public String getHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(APN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAPNHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(APN_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAPNGroupHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_SGSN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSGSNHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(SGSN_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSGSNGroupGroupHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_MSC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMSCHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(MSC_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMSCGroupGroupHomerRoamerSummaryService() {
        return homerRoamerSummaryService.getData(mapResourceLayerParameters());
    }

    //---------------------------------------------------------------------------------
    //THE RADIO SIDE...
    //---------------------------------------------------------------------------------

    @Path(DASHBOARD_SUMMARY_WCDMA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHomerHomerRadioSummaryService() {
        return homerRoamerSummaryWCDMAService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_BSC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBSCHomerHomerRadioSummaryService() {
        return homerRoamerSummaryWCDMAService.getData(mapResourceLayerParameters());
    }

    @Path(BSC_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBSCGroupHomerHomerRadioSummaryService() {
        return homerRoamerSummaryWCDMAService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_CELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCELLHomerHomerRadioSummaryService() {
        return homerRoamerSummaryWCDMAService.getData(mapResourceLayerParameters());
    }

    @Path(CELL_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCELLGroupHomerHomerRadioSummaryService() {
        return homerRoamerSummaryWCDMAService.getData(mapResourceLayerParameters());
    }

}