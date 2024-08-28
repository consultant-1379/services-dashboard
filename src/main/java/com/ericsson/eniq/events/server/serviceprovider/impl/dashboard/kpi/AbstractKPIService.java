/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import static com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.RNCCellKPIServiceParamUtil.*;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.ejb.EJB;
import javax.xml.datatype.DatatypeConfigurationException;

import org.codehaus.jackson.map.ObjectMapper;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.LTECoreNodeQuery;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.LTERanNodeQuery;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.NodeQuery;
import com.ericsson.eniq.events.server.utils.json.JSONUtils;

/**
 * Desrible AbstractKPIService
 *
 * @author ezhelao
 * @since 11/2011
 */
public abstract class AbstractKPIService implements Service {

    @EJB
    protected NodeQuery nodeQuery;

    @EJB
    protected LTERanNodeQuery lteNodeQuery;

    @EJB
    protected LTECoreNodeQuery lteCoreNodeQuery;

    @EJB
    DashboardKpiCalenderUtility dashboardKpiCalenderUtility;

    @EJB
    protected RNCCellKPIServiceParamUtil rncCellKPIServiceParamUtil;

    @EJB
    protected LTEKpiUtility lteKpiUtility;

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    public static final String METHOD_NAME = "getData";

    protected static String handleKPIServiceException(final Exception ex, final String className) {
        try {
            throw ex;
        } catch (final DatatypeConfigurationException e) {
            ServicesLogger.error(className, METHOD_NAME, DATETIME_CONVERSION_ERROR_MSG);
            return JSONUtils.createJSONErrorResult(DATETIME_CONVERSION_ERROR_MSG);
        } catch (final MalformedURLException e) {
            ServicesLogger.error(className, METHOD_NAME, WRONG_URL_FORMAT_ERROR_MSG);
            return JSONUtils.createJSONErrorResult(WRONG_URL_FORMAT_ERROR_MSG);

        } catch (final IOException e) {
            ServicesLogger.error(className, METHOD_NAME, OBJECT_MAPPER_ERROR_MSG);
            return JSONUtils.createJSONErrorResult(OBJECT_MAPPER_ERROR_MSG);

        } catch (final RuntimeException e) {
            ServicesLogger.error(className, METHOD_NAME, ex.getStackTrace());
        } catch (final Exception e) {
            e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        }
        return JSONUtils.createJSONErrorResult(UNKOWN_ERROR_MSG);
    }
}
