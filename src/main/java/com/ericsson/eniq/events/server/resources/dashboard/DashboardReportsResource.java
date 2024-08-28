/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2011
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.eniq.events.server.resources.dashboard;

import javax.ejb.*;

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * Describe DashboardReportsResource here //TODO add comment
 *
 * @author ezhelao
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class DashboardReportsResource extends AbstractResource {

    private static final String DASHBOARD_REPORTS_LIST_SERVICE = "DashboardReportsListService";

    @EJB(beanName = DASHBOARD_REPORTS_LIST_SERVICE)
    protected Service service;

    @Override
    protected Service getService() {
        return service;
    }
}
