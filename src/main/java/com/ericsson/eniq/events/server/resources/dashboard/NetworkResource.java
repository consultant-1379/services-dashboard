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

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author echchik
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class NetworkResource extends AbstractResource {

    private static final String DASHBOARD_ENODEB_SERVICE = "LTECFASummaryEnodeBService";

    private static final String DASHBOARD_ECELL_SERVICE = "LTECFASummaryEcellService";

    private static final String DASHBOARD_LTE_HFA_ECELL_SERVICE = "LTEHFASummaryEcellService";

    @EJB(beanName = DASHBOARD_ENODEB_SERVICE)
    private Service lteCfaSummaryEnodeBService;

    @EJB(beanName = DASHBOARD_ECELL_SERVICE)
    private Service lteCfaSummaryEcellService;

    @EJB(beanName = DASHBOARD_LTE_HFA_ECELL_SERVICE)
    private Service lteHfaSummaryEcellService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    //---------------------------------------------------------------------------------
    //LTE NETWORK...
    //---------------------------------------------------------------------------------
    @Path(LTE_CFA_SUMMARY_ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCfaSummaryEnodeService() {
        return lteCfaSummaryEnodeBService.getData(mapResourceLayerParameters());
    }

    @Path(LTE_CFA_SUMMARY_ECELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCfaSummaryEcellService() {
        return lteCfaSummaryEcellService.getData(mapResourceLayerParameters());
    }

    @Path(LTE_HFA_SUMMARY_ECELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteHfaSummaryEcellService() {
        return lteHfaSummaryEcellService.getData(mapResourceLayerParameters());
    }
}
