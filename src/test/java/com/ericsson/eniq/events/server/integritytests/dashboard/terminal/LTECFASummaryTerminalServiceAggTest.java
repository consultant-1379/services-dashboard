/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.dashboard.terminal;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal.LTECFASummaryTerminalService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.terminal.LTECFASummaryTerminalResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author echchik
 * @since 2012
 *
 */
public class LTECFASummaryTerminalServiceAggTest extends BaseDataIntegrityTest<LTECFASummaryTerminalResult> {

    private LTECFASummaryTerminalService lteCfaSummaryTerminalService;

    private final String temporaryTable = "#EVENT_E_LTE_CFA_TAC_ERR_DAY";

    @Before
    public void onSetup() {
        lteCfaSummaryTerminalService = new LTECFASummaryTerminalService();
        attachDependencies(lteCfaSummaryTerminalService);
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

        final String result = runQuery(lteCfaSummaryTerminalService, requestParameters);
        verifyResult(result);
    }

    private void createTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(TAC);
        columns.add(NO_OF_ERRORS);
        columns.add(DATETIME_ID);
        createTemporaryTable(temporaryTable, columns);

        columns.clear();
        columns.add(TAC);
        columns.add(VENDOR_NAME);
        columns.add(MARKETING_NAME);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columns);
    }

    private void insertTopologyData() throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();

        valuesForTable.put(TAC, 107800);
        valuesForTable.put(VENDOR_NAME, "Garmin International");
        valuesForTable.put(MARKETING_NAME, "0247910");
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, 104800);
        valuesForTable.put(VENDOR_NAME, "Arbitron");
        valuesForTable.put(MARKETING_NAME, "1000-1146");
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, 1027400);
        valuesForTable.put(VENDOR_NAME, "Nokia");
        valuesForTable.put(MARKETING_NAME, "1100");
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, 1066500);
        valuesForTable.put(VENDOR_NAME, "Aplicom Oy");
        valuesForTable.put(MARKETING_NAME, "12 (RX-9)");
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);

        valuesForTable.clear();
        valuesForTable.put(TAC, 1002901);
        valuesForTable.put(VENDOR_NAME, "Nortel");
        valuesForTable.put(MARKETING_NAME, "1920");
        insertRow(TEMP_DIM_E_SGEH_TAC, valuesForTable);
    }

    private void insertRows() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);

        //HIER3_ID | no_of_errors | timestamp.
        insertTableRow(107800, 5000, timestamp);
        insertTableRow(107800, 5000, timestamp);
        insertTableRow(107800, 5000, timestamp);
        insertTableRow(107800, 5000, timestamp);
        insertTableRow(104800, 4000, timestamp);
        insertTableRow(104800, 4000, timestamp);
        insertTableRow(104800, 4000, timestamp);
        insertTableRow(104800, 4000, timestamp);
        insertTableRow(1027400, 3000, timestamp);
        insertTableRow(1027400, 3000, timestamp);
        insertTableRow(1027400, 3000, timestamp);
        insertTableRow(1027400, 3000, timestamp);
        insertTableRow(1066500, 2000, timestamp);
        insertTableRow(1066500, 2000, timestamp);
        insertTableRow(1066500, 2000, timestamp);
        insertTableRow(1066500, 2000, timestamp);
        insertTableRow(1002901, 1000, timestamp);
        insertTableRow(1002901, 1000, timestamp);
        insertTableRow(1002901, 1000, timestamp);
        insertTableRow(1002901, 1000, timestamp);
    }

    private void insertTableRow(final int tac, final int no_of_errors, final String timestamp) throws SQLException {
        final Map<String, Object> field = new HashMap<String, Object>();
        field.put(TAC, tac);
        field.put(NO_OF_ERRORS, no_of_errors);
        field.put(DATETIME_ID, timestamp);
        insertRow(temporaryTable, field);
    }

    private void verifyResult(final String jsonResult) throws Exception {
        final List<LTECFASummaryTerminalResult> results = getTranslator().translateResult(jsonResult,
                LTECFASummaryTerminalResult.class);
        assertEquals(5, results.size());

        LTECFASummaryTerminalResult resultFromQuery = results.get(0);
        assertEquals("Garmin International", resultFromQuery.getTerminalManuf());
        assertEquals("0247910", resultFromQuery.getTerminalModel());
        assertEquals("107800", resultFromQuery.getTac());
        assertEquals("1", resultFromQuery.getTerminalRank());
        assertEquals("20000", resultFromQuery.getFailures());

        resultFromQuery = results.get(3);
        assertEquals("Aplicom Oy", resultFromQuery.getTerminalManuf());
        assertEquals("12 (RX-9)", resultFromQuery.getTerminalModel());
        assertEquals("1066500", resultFromQuery.getTac());
        assertEquals("4", resultFromQuery.getTerminalRank());
        assertEquals("8000", resultFromQuery.getFailures());

    }
}
