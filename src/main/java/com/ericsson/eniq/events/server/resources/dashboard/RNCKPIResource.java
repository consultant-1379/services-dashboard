package com.ericsson.eniq.events.server.resources.dashboard;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * Desrible RNCKPIResource
 *
 * @author ezhelao
 * @since 11/2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class RNCKPIResource extends AbstractResource {

    private static final String SERVICE_NAME = "RNCKPIService";

    @EJB(beanName = SERVICE_NAME)
    private Service service;

    @Override
    protected Service getService() {
        return this.service;
    }
}
