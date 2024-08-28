/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.network.kpi.core;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.kpi.KPI;
import com.ericsson.eniq.events.server.kpi.KpiFactory;
import com.ericsson.eniq.events.server.query.IQueryGenerator;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.DateTimeRange;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.RequestParametersWrapper;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ericker
 */
@Stateless
@Local(Service.class)
public class CoreNetworkKPIService extends GenericService {
    @EJB(beanName = "KPIQueryGenerator")
    protected IQueryGenerator queryGenerator;

    private FormattedDateTimeRange dateTimeRange;

    @EJB
    private KpiFactory kpiFactory;

    @PostConstruct
    public void init() {
        setQueryGenerator(queryGenerator);
    }

    @Override
    public String getTemplatePath() {
        return null;
    }

    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange1,
            final TechPackList techPackList) {
        this.dateTimeRange = dateTimeRange1;
        final RequestParametersWrapper requestParametersWrapper = new RequestParametersWrapper(requestParameters);
        final Map<String, Object> templateParameters = new HashMap<String, Object>();
        templateParameters.put(USE_TAC_EXCLUSION_BOOLEAN, true);

        if (isGroup(requestParametersWrapper.getType())) {
            requestParameters.putSingle(GROUP_NAME_PARAM, requestParametersWrapper.getSearchParam());
            updateTemplateParametersWithGroupDefinition(templateParameters, requestParameters);
            templateParameters.put(GROUP_NAME_PARAM, requestParametersWrapper.getSearchParam());
        }

        templateParameters.put(
                START_TIME,
                "'"
                        + DateTimeRange.formattedDateTimeAgainstDayTable(dateTimeRange1.getStartDateTime(),
                                dateTimeRange1.getMinutesOfStartDateTime()) + "'");
        templateParameters.put(
                END_TIME,
                "'"
                        + DateTimeRange.formattedDateTimeAgainstDayTable(dateTimeRange1.getEndDateTime(),
                                dateTimeRange1.getMinutesOfEndDateTime()) + "'");
        updateRequestParameters(requestParameters);
        return templateParameters;
    }

    private boolean isGroup(final String type) {
        return type != null ? type.contains(GROUP) : false;
    }

    @Override
    public List<KPI> getKPIList() {
        return kpiFactory.getCoreNetworkKPIs();
    }

    private void updateRequestParameters(final MultivaluedMap<String, String> requestParameters) {
        final String node = requestParameters.getFirst(SEARCH_PARAM);
        if (StringUtils.isBlank(node)) {
            return;
        }
        final String type = requestParameters.getFirst(TYPE_PARAM);
        if (type.equals(TYPE_APN)) {
            requestParameters.putSingle(APN_PARAM, node);
        } else if (type.equals(TYPE_SGSN)) {
            requestParameters.putSingle(SGSN_PARAM, node);
        } else if (type.equals(TYPE_SGSN_GROUP)) {
            requestParameters.putSingle(TYPE_PARAM, TYPE_SGSN);
            requestParameters.putSingle(GROUP_NAME_PARAM, node);
        } else if (type.equals(TYPE_APN_GROUP)) {
            requestParameters.putSingle(TYPE_PARAM, TYPE_APN);
            requestParameters.putSingle(GROUP_NAME_PARAM, node);
        }
    }

    @Override
    public Map<String, Object> getServiceSpecificDataServiceParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> dataServiceParams = new HashMap<String, Object>();
        // TODO: Test what happens when this is not there, or hardcode it to 0
        dataServiceParams.put(TZ_OFFSET, requestParameters.getFirst(TZ_OFFSET));
        return dataServiceParams;
    }

    @Override
    public Map<String, QueryParameter> getServiceSpecificQueryParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, QueryParameter> queryParameterMap = new HashMap<String, QueryParameter>();
        // TODO: This is likely not required if correct changes are made in UI, return empty map
        final QueryParameter startDateTime = QueryParameter.createStringParameter(DateTimeRange
                .formattedDateTimeAgainstDayTable(dateTimeRange.getStartDateTime(),
                        dateTimeRange.getMinutesOfStartDateTime()));
        queryParameterMap.put(ApplicationConstants.DATE_FROM_QUERY_PARAM, startDateTime);
        final QueryParameter endDateTime = QueryParameter.createStringParameter(DateTimeRange
                .formattedDateTimeAgainstDayTable(dateTimeRange.getEndDateTime(),
                        dateTimeRange.getMinutesOfEndDateTime()));
        queryParameterMap.put(ApplicationConstants.DATE_TO_QUERY_PARAM, endDateTime);
        return queryParameterMap;
    }

    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParams = new ArrayList<String>();
        return requiredParams;
    }

    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        final MultivaluedMap<String, String> staticParameters = new MultivaluedMapImpl();
        return staticParameters;
    }

    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        return null;
    }

    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        if (type.equals(TYPE_APN) | type.equals(TYPE_APN_GROUP)) {
            return new AggregationTableInfo(APN_EVENTID, EventDataSourceType.AGGREGATED_DAY);
        } else if (type.equals(TYPE_SGSN) | type.equals(TYPE_SGSN_GROUP)) {
            return new AggregationTableInfo(EVNTSRC_EVENTID, EventDataSourceType.AGGREGATED_DAY);
        } else {
            return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
        }
    }

    @Override
    public EventDataSourceType forceAggregationType() {
        return EventDataSourceType.AGGREGATED_DAY;
    }

    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_SGEH_TPNAME);
        return techPacks;
    }

    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return true;
    }

    @Override
    public int getMaxAllowableSize() {
        return 0; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        return false; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTableSuffixKey() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getMeasurementTypes() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getRawTableKeys() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    /*
    * (non-Javadoc)
    * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTimeColumnIndices()
    */
    @Override
    public List<Integer> getTimeColumnIndices() {
        final List<Integer> timeColumnIndicies = new ArrayList<Integer>();
        timeColumnIndicies.add(1);
        return timeColumnIndicies;
    }
}
