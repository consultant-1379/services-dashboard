/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.dashboard.network.lte;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.network.lte.LTECFASummaryEnodeBService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.network.lte.LTECFASummaryEnodeBResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_LTE_HIER321;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author echchik
 * @since 2012
 *
 */
public class LTECFASummaryEnodeBServiceAggTest extends BaseDataIntegrityTest<LTECFASummaryEnodeBResult> {

    private LTECFASummaryEnodeBService lteCfaSummaryEnodeBService;

    private final String temporaryTable = "#EVENT_E_LTE_CFA_HIER3_ERR_DAY";

    @Before
    public void onSetup() {
        lteCfaSummaryEnodeBService = new LTECFASummaryEnodeBService();
        attachDependencies(lteCfaSummaryEnodeBService);
        try {
            createTable();
        } catch (final Exception e) {
            fail("FAIL: Couldn't create temporary (" + temporaryTable + ") table");
        }
        try {
            insertTopologyData();
            insertRows();
        } catch (final SQLException e) {
            fail("FAIL: Couldn't populate temporary (" + temporaryTable + ") table");
        }
    }

    @Test
    public void testRunQuery() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, ONE_DAY);

        final String result = runQuery(lteCfaSummaryEnodeBService, requestParameters);
        verifyResult(result);
    }

    private void createTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(HIER3_ID);
        columns.add(NO_OF_ERRORS);
        columns.add(DATETIME_ID);
        createTemporaryTable(temporaryTable, columns);
        
        columns.clear();
        columns.add(HIER3_ID);
        columns.add(HIERARCHY_3);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columns);
    }

    private void insertTopologyData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(HIER3_ID, 3135210477467174988L);
        valuesForTable.put(HIERARCHY_3, "ONRM_RootMo_R:LTE01ERBS00001");
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
        
        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, 4809532081614999117L);
        valuesForTable.put(HIERARCHY_3, "ONRM_RootMo_R:LTE01ERBS00004");
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
        
        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, 6002958395111905063L);
        valuesForTable.put(HIERARCHY_3, "ONRM_RootMo_R:LTE01ERBS00003");
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
        
        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, 6563198411286284929L);
        valuesForTable.put(HIERARCHY_3, "ONRM_RootMo_R:LTE01ERBS00002");
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
        
        valuesForTable.clear();
        valuesForTable.put(HIER3_ID, 7302598826786562037L);
        valuesForTable.put(HIERARCHY_3, "ONRM_RootMo_R:LTE01ERBS00005");
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
    }
    
    private void insertRows() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);

        //HIER3_ID | no_of_errors | timestamp.
        insertTableRow(3135210477467174988L, 5000, timestamp);
        insertTableRow(3135210477467174988L, 5000, timestamp);
        insertTableRow(3135210477467174988L, 5000, timestamp);
        insertTableRow(3135210477467174988L, 5000, timestamp);
        insertTableRow(4809532081614999117L, 4000, timestamp);
        insertTableRow(4809532081614999117L, 4000, timestamp);
        insertTableRow(4809532081614999117L, 4000, timestamp);
        insertTableRow(4809532081614999117L, 4000, timestamp);
        insertTableRow(6002958395111905063L, 3000, timestamp);
        insertTableRow(6002958395111905063L, 3000, timestamp);
        insertTableRow(6002958395111905063L, 3000, timestamp);
        insertTableRow(6002958395111905063L, 3000, timestamp);
        insertTableRow(6563198411286284929L, 2000, timestamp);
        insertTableRow(6563198411286284929L, 2000, timestamp);
        insertTableRow(6563198411286284929L, 2000, timestamp);
        insertTableRow(6563198411286284929L, 2000, timestamp);
        insertTableRow(7302598826786562037L, 1000, timestamp);
        insertTableRow(7302598826786562037L, 1000, timestamp);
        insertTableRow(7302598826786562037L, 1000, timestamp);
        insertTableRow(7302598826786562037L, 1000, timestamp);
    }

    private void insertTableRow(final long hier3_id, final int no_of_errors, final String timestamp)
            throws SQLException {
        final Map<String, Object> field = new HashMap<String, Object>();
        field.put(HIER3_ID, hier3_id);
        field.put(NO_OF_ERRORS, no_of_errors);
        field.put(DATETIME_ID, timestamp);
        insertRow(temporaryTable, field);
    }

    private void verifyResult(final String jsonResult) throws Exception {
        final List<LTECFASummaryEnodeBResult> results = getTranslator().translateResult(jsonResult,
                LTECFASummaryEnodeBResult.class);
        assertEquals(5, results.size());

        LTECFASummaryEnodeBResult resultFromQuery = results.get(0);
        assertEquals("ONRM_RootMo_R:LTE01ERBS00001", resultFromQuery.getName());
        assertEquals("3135210477467174988", resultFromQuery.getHashId());
        assertEquals("1", resultFromQuery.getRank());
        assertEquals("20000", resultFromQuery.getFailures());

        resultFromQuery = results.get(3);
        assertEquals("ONRM_RootMo_R:LTE01ERBS00002", resultFromQuery.getName());
        assertEquals("6563198411286284929", resultFromQuery.getHashId());
        assertEquals("4", resultFromQuery.getRank());
        assertEquals("8000", resultFromQuery.getFailures());

    }
}
