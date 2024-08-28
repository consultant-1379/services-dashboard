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
 * User: eeikbe
 * Date: 29/11/11
 * Time: 13:36
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class TerminalDrillDownResource extends AbstractResource {

    private static final String DASHBOARD_TERMINAL_DRILLDOWN_SERVICE = "SummaryTerminalDDService";

    private static final String DASHBOARD_TERMINAL_WCDMA_DD1_SERVICE = "SummaryTerminalWCDMACfaHfaDDService";

    private static final String LTE_CFA_DASHBOARD_TERMINAL_DRILLDOWN_SERVICE = "LTECFASummaryTerminalDrilldownService";

    @EJB(beanName = DASHBOARD_TERMINAL_DRILLDOWN_SERVICE)
    private Service summaryTerminalDDService;

    //Drilldown from WCDMA, will show CFA and HFA totals.
    @EJB(beanName = DASHBOARD_TERMINAL_WCDMA_DD1_SERVICE)
    private Service summaryTerminalWCDMACfaHfaDDService;

    //Drilldown from LTE CFA, will show CFA terminal summary window.
    @EJB(beanName = LTE_CFA_DASHBOARD_TERMINAL_DRILLDOWN_SERVICE)
    private Service lteCfaSummaryTerminalDrilldownService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    //---------------------------------------------------------------------------------
    //THE CORE NETWORK SIDE...
    //---------------------------------------------------------------------------------

    @Path(DASHBOARD_SUMMARY_CORE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSummaryTerminalDDService() {
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    @Path(APN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAPNTerminalService() {
        //Redirect to Core Network drilldown view for APN.
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    @Path(APN_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAPNGroupTerminalService() {
        //Redirect to Core Network drilldown view for APN Group.
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_SGSN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSGSNTerminalService() {
        //Redirect to Core Network drilldown view for SGSN.
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    @Path(SGSN_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSGSNGroupTerminalService() {
        //Redirect to Core Network drilldown view for SGSN group.
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    @Path(TYPE_MSC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMSCTerminalService() {
        //Redirect to Core Network drilldown view for MSC.
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    @Path(MSC_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMSCGroupTerminalService() {
        //Redirect to Core Network drilldown view for MSC.
        return summaryTerminalDDService.getData(mapResourceLayerParameters());
    }

    //---------------------------------------------------------------------------------
    //THE RADIO NETWORK SIDE...
    //---------------------------------------------------------------------------------
    @Path(DASHBOARD_SUMMARY_WCDMA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSummaryTerminalWCDMAService() {
        return summaryTerminalWCDMACfaHfaDDService.getData(mapResourceLayerParameters());
    }

    //Controller
    @Path(TYPE_BSC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBSCTerminalWCDMAService() {
        //Redirect to Radio Network drilldown view for BSC.
        return summaryTerminalWCDMACfaHfaDDService.getData(mapResourceLayerParameters());
    }

    //Controller Group
    @Path(BSC_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBSCGroupTerminalWCDMAService() {
        //Redirect to Radio Network drilldown view for BSC group.
        return summaryTerminalWCDMACfaHfaDDService.getData(mapResourceLayerParameters());
    }

    //Access Area
    @Path(TYPE_CELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCELLTerminalWCDMAService() {
        //Redirect to Radio Network drilldown view for CELL.
        return summaryTerminalWCDMACfaHfaDDService.getData(mapResourceLayerParameters());
    }

    //Access Area Group
    @Path(CELL_GROUP_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCELLGroupTerminalWCDMAService() {
        //Redirect to Radio Network drilldown view for CELL Group.
        return summaryTerminalWCDMACfaHfaDDService.getData(mapResourceLayerParameters());
    }

    //Dashboard LTE CFA Terminal Summary Drilldown
    @Path(SUMMARY_LTE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteSummaryTerminalService() {
        return lteCfaSummaryTerminalDrilldownService.getData(mapResourceLayerParameters());
    }

    //Dashboard LTE CFA Terminal Summary Drilldown csv export
    @Path(SUMMARY_LTE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteSummaryTerminalServiceAsCSV() {
        return lteCfaSummaryTerminalDrilldownService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
