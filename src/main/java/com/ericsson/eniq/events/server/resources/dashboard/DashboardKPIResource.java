package com.ericsson.eniq.events.server.resources.dashboard;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * Desrible DashboardKPIResource
 *
 * @author ezhelao
 * @since 11/2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class DashboardKPIResource extends AbstractResource {

    @EJB
    protected CellKPIResource cellKPIResource;

    @EJB
    protected RNCKPIResource rnckpiResource;

    @EJB
    protected SummaryWCDMAResource summaryWCDMAResource;

    @EJB
    protected KPIDrillDownResource kpiDrillDownResource;

    @EJB
    protected CoreNetworkKPIResource coreNetworkKPIResource;

    @EJB
    protected CellGroupKPIResource cellGroupKPIResource;

    @EJB
    protected RNCGroupKPIResource rncGroupKPIResource;

    @EJB
    protected LTENetworkRanKpiResource lteNetworkRanKpiResource;

    @EJB
    protected LTENetworkCoreKpiResource lteNetworkCoreKpiResource;

    @Override
    protected Service getService() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getData() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

    @Path(RAN_CELL_PATH)
    public CellKPIResource getCellKPIResource() {
        return this.cellKPIResource;
    }

    @Path(RAN_CELL_GROUP_PATH)
    public CellGroupKPIResource getCellKPIGroupResource() {
        return this.cellGroupKPIResource;
    }

    @Path(RAN_BSC_PATH)
    public RNCKPIResource getRnckpiResource() {
        return this.rnckpiResource;
    }

    @Path(RAN_BSC_GROUP_PATH)
    public RNCGroupKPIResource getRNCGroupKPIResource() {
        return this.rncGroupKPIResource;
    }

    @Path(RAN_SUMMARY_WCDMA)
    public SummaryWCDMAResource getSummaryWCDMAResource() {
        return this.summaryWCDMAResource;
    }

    @Path(DRILL_DOWN_PATH)
    public KPIDrillDownResource getKpiDrillDownResource() {
        return this.kpiDrillDownResource;
    }

    @Path(CORE_PATH)
    public CoreNetworkKPIResource cgetCoreNetworkKPIResource() {
        return this.coreNetworkKPIResource;
    }

    @Path(RAN_SUMMARY_LTE)
    public LTENetworkRanKpiResource getLteNetworkranKpiResource() {
        return this.lteNetworkRanKpiResource;
    }

    @Path(CORE_SUMMARY_LTE)
    public LTENetworkCoreKpiResource getLteNetworkCoreKpiResource() {
        return this.lteNetworkCoreKpiResource;
    }
}
