/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.dashboard.network.lte;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.network.lte.LTEHFASummaryEcellService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.network.lte.LTEHFASummaryEcellResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_LTE_HIER321;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author ejamves
 * @since 2012
 *
 */
public class LTEHFASummaryEcellServiceAggTest extends BaseDataIntegrityTest<LTEHFASummaryEcellResult> {

    private LTEHFASummaryEcellService lteHfaSummaryEcellService;

    private final String temporaryTable = "#EVENT_E_LTE_HFA_HIER321_ERR_DAY";

    @Before
    public void onSetup() {
        lteHfaSummaryEcellService = new LTEHFASummaryEcellService();
        attachDependencies(lteHfaSummaryEcellService);
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

        final String result = runQuery(lteHfaSummaryEcellService, requestParameters);
        verifyResult(result);
    }

    private void createTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(HIER321_ID);
        columns.add(NO_OF_ERRORS);
        columns.add(DATETIME_ID);
        createTemporaryTable(temporaryTable, columns);

        columns.clear();
        columns.add(HIER321_ID);
        columns.add(HIERARCHY_1);
        columns.add(VENDOR);
        columns.add(HIERARCHY_3);
        columns.add(RAT);
        createTemporaryTable(TEMP_DIM_E_LTE_HIER321, columns);
    }

    private void insertTopologyData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(HIER321_ID, 265855783933059174L);
        valuesForTable.put(HIERARCHY_1, "LTE01ERBS00001-4");
        valuesForTable.put(HIERARCHY_3, SAMPLE_HIERARCHY_3);
        valuesForTable.put(VENDOR, TEST_VALUE_VENDOR);
        valuesForTable.put(RAT, RAT_FOR_LTE);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, 1150444940909485207L);
        valuesForTable.put(HIERARCHY_1, "LTE01ERBS00004-4");
        valuesForTable.put(HIERARCHY_3, SAMPLE_HIERARCHY_3);
        valuesForTable.put(VENDOR, TEST_VALUE_VENDOR);
        valuesForTable.put(RAT, RAT_FOR_LTE);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, 1541001788189517424L);
        valuesForTable.put(HIERARCHY_1, "LTE01ERBS00002-3");
        valuesForTable.put(HIERARCHY_3, SAMPLE_HIERARCHY_3);
        valuesForTable.put(VENDOR, TEST_VALUE_VENDOR);
        valuesForTable.put(RAT, RAT_FOR_LTE);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, 1721246927770829421L);
        valuesForTable.put(HIERARCHY_1, "LTE01ERBS00004-1");
        valuesForTable.put(HIERARCHY_3, SAMPLE_HIERARCHY_3);
        valuesForTable.put(VENDOR, TEST_VALUE_VENDOR);
        valuesForTable.put(RAT, RAT_FOR_LTE);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(HIER321_ID, 4421313258598367302L);
        valuesForTable.put(HIERARCHY_1, "LTE01ERBS00002-1");
        valuesForTable.put(HIERARCHY_3, SAMPLE_HIERARCHY_3);
        valuesForTable.put(VENDOR, TEST_VALUE_VENDOR);
        valuesForTable.put(RAT, RAT_FOR_LTE);
        insertRow(TEMP_DIM_E_LTE_HIER321, valuesForTable);
    }

    private void insertRows() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);

        //HIER321_ID | no_of_errors | timestamp.
        insertTableRow(265855783933059174L, 5000, timestamp);
        insertTableRow(265855783933059174L, 5000, timestamp);
        insertTableRow(265855783933059174L, 5000, timestamp);
        insertTableRow(265855783933059174L, 5000, timestamp);
        insertTableRow(1150444940909485207L, 4000, timestamp);
        insertTableRow(1150444940909485207L, 4000, timestamp);
        insertTableRow(1150444940909485207L, 4000, timestamp);
        insertTableRow(1150444940909485207L, 4000, timestamp);
        insertTableRow(1541001788189517424L, 3000, timestamp);
        insertTableRow(1541001788189517424L, 3000, timestamp);
        insertTableRow(1541001788189517424L, 3000, timestamp);
        insertTableRow(1541001788189517424L, 3000, timestamp);
        insertTableRow(1721246927770829421L, 2000, timestamp);
        insertTableRow(1721246927770829421L, 2000, timestamp);
        insertTableRow(1721246927770829421L, 2000, timestamp);
        insertTableRow(1721246927770829421L, 2000, timestamp);
        insertTableRow(4421313258598367302L, 1000, timestamp);
        insertTableRow(4421313258598367302L, 1000, timestamp);
        insertTableRow(4421313258598367302L, 1000, timestamp);
        insertTableRow(4421313258598367302L, 1000, timestamp);
    }

    private void insertTableRow(final long hier3_id, final int no_of_errors, final String timestamp)
            throws SQLException {
        final Map<String, Object> field = new HashMap<String, Object>();
        field.put(HIER321_ID, hier3_id);
        field.put(NO_OF_ERRORS, no_of_errors);
        field.put(DATETIME_ID, timestamp);
        insertRow(temporaryTable, field);
    }

    private void verifyResult(final String jsonResult) throws Exception {
        final List<LTEHFASummaryEcellResult> results = getTranslator().translateResult(jsonResult,
                LTEHFASummaryEcellResult.class);
        assertEquals(5, results.size());

        LTEHFASummaryEcellResult resultFromQuery = results.get(0);
        assertEquals("LTE01ERBS00001-4,,ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,LTE", resultFromQuery.getName());
        assertEquals("265855783933059174", resultFromQuery.getHashId());
        assertEquals("1", resultFromQuery.getRank());
        assertEquals("20000", resultFromQuery.getFailures());

        resultFromQuery = results.get(3);
        assertEquals("LTE01ERBS00004-1,,ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,LTE", resultFromQuery.getName());
        assertEquals("1721246927770829421", resultFromQuery.getHashId());
        assertEquals("4", resultFromQuery.getRank());
        assertEquals("8000", resultFromQuery.getFailures());

    }
}