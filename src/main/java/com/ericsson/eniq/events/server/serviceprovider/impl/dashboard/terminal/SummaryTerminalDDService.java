package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal;

import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConfigConstants.MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.EVENT_E_SGEH;

/**
 * User: eeikbe
 * Date: 22/11/11
 * Time: 16:44
 */
@Stateless
@Local(Service.class)
public class SummaryTerminalDDService extends GenericService {
    /**
     * Fetch the template path for this service
     * This is used when looking up the templateMap.csv
     *
     * @return key for the templateMap.csv file eg EVENT_ANALYSIS or SUBBI/SUBSCRIBER_DETAILS
     */
    @Override
    public String getTemplatePath() {
        return DASHBOARD_TERMINAL_SUMMARY_DD;
    }

    /**
     * Put together the template parameters that are specific to this query
     * These template parameters will be added to the common template parameters, all of which are pumped into
     * the template at the query generation stage
     *
     * @param requestParameters request parameters provided by user
     * @param dateTimeRange     date time range for query
     * @param techPackList      the list of tech pack tables and views that should be used for the query
     *                          Included as a parameter to this method so that each sub Service can
     *                          access the techPackList object to get the specific tables and views that
     *                          it needs for its query
     * @return map of the service specific template parameters
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange, final TechPackList techPackList) {
        return new HashMap<String, Object>();
    }

    /**
     * Put together the template parameters that are specific to this query
     * These template parameters will be added to the common template parameters, all of which are pumped into
     * the template at the query generation stage
     *
     * @param requestParameters request parameters provided by user
     * @param dateTimeRange     date time range for query
     * @param techPackList      the list of tech pack tables and views that should be used for the query
     *                          Included as a parameter to this method so that each sub Service can
     *                          access the techPackList object to get the specific tables and views that
     *                          it needs for its query
     * @return map of the service specific template parameters
     */
//    @Override
//    public Map<String, Object> getServiceSpecificTemplateParameters(final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange, final TechPackList techPackList) {
//        return new HashMap<String, Object>();
//    }

    /**
     * Return the parameters to the call on the data service layer that are specific to this service or query
     * Examples are timeColumn - some services require this to be passed down to the data service layer.
     * The map of parameters returned is used in the runQuery() method above
     *
     * @param requestParameters the request parameters provided by the user
     * @return map of parameters for the data service layer
     */
    @Override
    public Map<String, Object> getServiceSpecificDataServiceParameters(final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> dataServiceParameters = new HashMap<String, Object>();
        dataServiceParameters.put(TZ_OFFSET, requestParameters.getFirst(TZ_OFFSET));
        return dataServiceParameters;
    }

    /**
     * Implementations that require additional query parameters should provide these parameters by implementing this
     * method to return these additional parameters for the SQL query
     * <p/>
     * Ideally, this method should return only a list of request parameters that are injected directly into the SQL
     * query (the framework could then take care of the injection), but due to legacy code, some queries inject
     * some more complicated parameters into the SQL, see MultipleRankingResource for an example
     *
     * @param requestParameters request parameters provided by user
     * @return the additional query parameters that should be added on query execution
     */
    @Override
    public Map<String, QueryParameter> getServiceSpecificQueryParameters(final MultivaluedMap<String, String> requestParameters) {
        final Map<String, QueryParameter> queryParameters = new HashMap<String, QueryParameter>();
        queryParameters.put(MARKETING_NAME_PARAM_UPPERCASE, QueryParameter.createStringParameter(requestParameters.getFirst(MARKETING_NAME_PARAM)));
        return queryParameters;
    }

    /**
     * Return the list of parameters that must be present in the URL for this service
     *
     * @return the parameters required for this query
     */
    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        requiredParameters.add(TZ_OFFSET);
        return requiredParameters;
    }

    /**
     * Return the display parameters that are valid for this service/query eg GRID, CHART
     *
     * @return the display parameters which are valid for this service/query
     */
    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        final MultivaluedMap<String, String> staticParameters = new MultivaluedMapImpl();
        staticParameters.add(DISPLAY_PARAM, GRID_PARAM);
        return staticParameters;
    }

    /**
     * Fetch the drill down type for the service/query
     *
     * @param requestParameters request parameters provided by user
     * @return the drill down type for service/query
     */
    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        return null;
    }

    /**
     * Fetch the aggregation view applicable for this view/query (and type, if applicable)
     * <p/>
     * The return is an AggregationTableInfo that should be used (for the given node type if provided) - this contains information
     * on an aggregation key, and on the timeranges that the aggregation is available for
     *
     * @param type type of node, eg TAC or APN (not required if there is only one aggregation possible for service)
     * @return map of aggregation views
     */
    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
    }

    /**
     * Fetch the list of tech packs that should be used in this query
     *
     * @param requestParameters request parameters provided by user
     * @return the list of tech packs that are applicable to this tech pack
     */
    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_SGEH);
        return techPacks;
     }

    /**
     * Some aggregation queries use raw tables ie if the number of impacted subscribers is required in the query result
     *
     * @return boolean  true if raw tables are required for aggregation queries for this service, false otherwise
     */
    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return false;
    }

    /**
     * Get the maximum result size that should be returned for this query
     *
     * @return int
     */
    @Override
    public int getMaxAllowableSize() {
        return MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
    }

    /**
     * @param requestParameters request parameters provided by user
     * @return boolean          true if the parameter values should be checked to see if they are valid values
     */
    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        return false;
    }

    /**
     * Get table suffix key
     *
     * @return table suffix key
     */
    @Override
    public String getTableSuffixKey() {
        return null;
    }

    /**
     * Get the measurement types of tech pack tables
     *
     * @return List<String> measurement types
     */
    @Override
    public List<String> getMeasurementTypes() {
        return null;
    }

    /**
     * Get the raw table key(s)
     *
     * @return List<String> raw table keys
     */
    @Override
    public List<String> getRawTableKeys() {
        return null;
    }
}
