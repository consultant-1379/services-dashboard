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
 * Desrible KPIDrillDownResource
 *
 * @author ezhelao
 * @since 11/2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class KPIDrillDownResource extends AbstractResource {

    private static final String SUMMARY_WCDMA_DRILLDOWN_SERVICE_NAME = "SummaryWCDMADrillDownService";

    private static final String RNC_KPI_DRILL_DOWN_SERVICE_NAME = "RNCKPIDrillDownService";

    private static final String RNC_GROUP_KPI_DRILL_DOWN_SERVICE_NAME = "RNCGroupKPIDrillDownService";

    private static final String CELL_KPI_DRILL_DOWN_SERVICE_NAME = "CellKPIDrillDownService";

    private static final String CELL_GROUP_KPI_DRILL_DOWN_SERVICE_NAME = "CellGroupKPIDrillDownService";

    @EJB(beanName = SUMMARY_WCDMA_DRILLDOWN_SERVICE_NAME)
    Service summaryWCDMADrillDownService;

    @EJB(beanName = RNC_KPI_DRILL_DOWN_SERVICE_NAME)
    Service rnckpiDrillDownService;

    @EJB(beanName = CELL_KPI_DRILL_DOWN_SERVICE_NAME)
    Service cellKPIDrillDownService;

    @EJB(beanName = CELL_GROUP_KPI_DRILL_DOWN_SERVICE_NAME)
    Service cellGroupKPIDrillDownService;

    @EJB(beanName = RNC_GROUP_KPI_DRILL_DOWN_SERVICE_NAME)
    Service rncGroupKPIDrillDownService;

    @Override
    protected Service getService() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getData() {
        return null;
    }

    @GET
    @Path(SUMMARY_WCDMA)
    @Produces(MediaType.APPLICATION_JSON)
    public String getSummaryWCDMAData() {
        return summaryWCDMADrillDownService.getData(mapResourceLayerParameters());
    }

    @GET
    @Path(BSC)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRNCKPIDrillDownData() {
        return rnckpiDrillDownService.getData(mapResourceLayerParameters());
    }

    @GET
    @Path(BSC_GROUP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRNCGroupKPIDrillDownData() {
        return rncGroupKPIDrillDownService.getData(mapResourceLayerParameters());
    }

    @GET
    @Path(CELL)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCellKPIDrillDownData() {
        return cellKPIDrillDownService.getData(mapResourceLayerParameters());
    }

    @GET
    @Path(CELL_GROUP_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCellGroupKPIDrillDownData() {
        return cellGroupKPIDrillDownService.getData(mapResourceLayerParameters());
    }

}
