/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.*;
import javax.ws.rs.Path;

import com.ericsson.eniq.events.server.resources.dashboard.DashboardReportsResource;

/**
 * The Class DashboardServiceResource. Sub-root resource of RESTful service.
 * 
 * @author eeikbe
 * @since 2011
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class DashboardServiceResource {

    //    @EJB
    //    protected HomerRoamerResource homerRoamerResource;
    //
    //    @EJB
    //    protected TerminalResource terminalResource;

    @EJB
    protected DashboardReportsResource dashboardReportsResource;

    //    @EJB
    //    protected DashboardKPIResource dashboardKPIResource;
    //
    //    @EJB
    //    protected NetworkResource networkResource;
    //
    //    @EJB
    //    protected AccessAreaResource accessAreaResource;
    //
    //    @Path(HOMER_ROAMER_SERVICES)
    //    public HomerRoamerResource getHomerRoamerResource() {
    //        return this.homerRoamerResource;
    //    }
    //
    //    @Path(TERMINAL_SERVICES)
    //    public TerminalResource getTerminalResource() {
    //        return this.terminalResource;
    //    }

    @Path(REPORTS_SERVICES)
    public DashboardReportsResource getDashboardReportsResource() {
        return this.dashboardReportsResource;

    }

    //    @Path(KPI)
    //    public DashboardKPIResource getDashboardKPIResource() {
    //        return this.dashboardKPIResource;
    //    }
    //
    //    @Path(NETWORK_SERVICES)
    //    public NetworkResource getNetworkResource() {
    //        return this.networkResource;
    //    }
    //
    //    @Path(CELL)
    //    public AccessAreaResource getAccessAreaResource() {
    //        return this.accessAreaResource;
    //    }
}
