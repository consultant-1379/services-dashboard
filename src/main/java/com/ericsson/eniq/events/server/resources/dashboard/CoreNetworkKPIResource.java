/*
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author ericker
 * @since 2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CoreNetworkKPIResource extends AbstractResource {

    //private static final String DASHBOARD_HOMER_ROAMER_SERVICE = "SummaryHomerRoamerService";

    @EJB(beanName = "CoreNetworkKPIService")
    private Service coreNetworkKPIService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
        //return coreNetworkKPIService;
    }

    @Path("/{TYPE}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoreNetworkKPI(@PathParam("TYPE")
    final String type) {
        final MultivaluedMap<String, String> map = mapResourceLayerParameters();
        map.putSingle(TYPE_PARAM, type);
        return coreNetworkKPIService.getData(map);
    }
}