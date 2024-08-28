/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.dashboard.terminal;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal.SummaryTerminalService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.terminal.SummaryTerminalResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eeikbe
 * @since 2011
 *
 */
public class SummaryTerminalRawTest extends BaseDataIntegrityTest<SummaryTerminalResult> {

    private SummaryTerminalService summaryTerminalService;

    private final String temporaryTable = "#EVENT_E_SGEH_MANUF_TAC_ERR_DAY";

    @Before
    public void onSetup() {
        summaryTerminalService = new SummaryTerminalService();
        attachDependencies(summaryTerminalService);
        try {
            createTable();
        } catch (final Exception e) {
            fail("FAIL: Couldn't create temporary (" + temporaryTable + ") table");
        }
        try {
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

        final String result = runQuery(summaryTerminalService, requestParameters);
        verifyResult(result);
    }

    private void createTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(MANUFACTURER);
        columns.add(TAC);
        columns.add(NO_OF_ERRORS);
        columns.add(NO_OF_TOTAL_ERR_SUBSCRIBERS);
        columns.add(DATETIME_ID);
        createTemporaryTable(temporaryTable, columns);
    }

    private void insertRows() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);

        //Manufacturer | tac | no_of_errors | no_of_total_err_subscribers | timestamp.
        insertTableRow("Sony Ericsson", 1244800, 1000000, 200000, timestamp);
        insertTableRow("Apple", 1253700, 1000, 5, timestamp);
        insertTableRow("Sony Ericsson", 1244900, 1000000, 200000, timestamp);
        insertTableRow("Sony Ericsson", 1256000, 500000, 200000, timestamp);
        insertTableRow("Sony Ericsson", 1268400, 500000, 200000, timestamp);
        insertTableRow("Sony Ericsson", 1268500, 2000000, 200000, timestamp);
        insertTableRow("Samsung", 1039500, 1000000, 500000, timestamp);
        insertTableRow("Apple", 1280600, 500000, 100, timestamp);
        insertTableRow("Nortel", 101300, 14400, 89, timestamp);
        insertTableRow("Samsung", 1046000, 1000000, 500000, timestamp);
        insertTableRow("Samsung", 1047900, 1000000, 1000000, timestamp);
        insertTableRow("Motorola", 1003469, 1000, 5, timestamp);
        insertTableRow("Samsung", 1048000, 500000, 1000000, timestamp);
        insertTableRow("Samsung", 1054600, 500000, 1000000, timestamp);
        insertTableRow("Nokia", 1002810, 2000000, 100000, timestamp);
        insertTableRow("Nokia", 1002830, 500000, 100000, timestamp);
        insertTableRow("Nokia", 1002850, 500000, 100000, timestamp);
        insertTableRow("Apple", 1280400, 500000, 100, timestamp);
        insertTableRow("Apple", 1280500, 500000, 100, timestamp);
        insertTableRow("Sony Ericsson", 52034363, 1000000, 50000, timestamp);
        insertTableRow("Sony Ericsson", 52034369, 250000, 50000, timestamp);
        insertTableRow("Sony Ericsson", 52034371, 250000, 50000, timestamp);
        insertTableRow("Apple", 1253400, 1000, 5, timestamp);
        insertTableRow("Apple", 1280300, 500000, 100, timestamp);
        insertTableRow("Apple", 1253500, 1000, 5, timestamp);
        insertTableRow("Apple", 1253600, 1000, 5, timestamp);

    }

    private void insertTableRow(final String manufacturer, final int tac, final int no_of_errors,
            final int no_of_total_err_subscribers, final String timestamp) throws SQLException {
        final Map<String, Object> field = new HashMap<String, Object>();
        field.put(MANUFACTURER, manufacturer);
        field.put(TAC, tac);
        field.put(NO_OF_ERRORS, no_of_errors);
        field.put(NO_OF_TOTAL_ERR_SUBSCRIBERS, no_of_total_err_subscribers);
        field.put(DATETIME_ID, timestamp);
        insertRow(temporaryTable, field);
    }

    private void verifyResult(final String jsonResult) throws Exception {
        final List<SummaryTerminalResult> results = getTranslator().translateResult(jsonResult,
                SummaryTerminalResult.class);
        assertEquals(5, results.size());

        SummaryTerminalResult resultFromQuery = results.get(0);
        assertEquals("1", resultFromQuery.getTerminalRank());
        assertEquals("Sony Ericsson", resultFromQuery.getTerminalMake());
        assertEquals("Xperia X8", resultFromQuery.getTerminalModel());
        assertEquals("5000000", resultFromQuery.getTerminalFailures());
        assertEquals("1000000", resultFromQuery.getTerminalSubscribers());

        resultFromQuery = results.get(4);
        assertEquals("5", resultFromQuery.getTerminalRank());
        assertEquals("Sony Ericsson", resultFromQuery.getTerminalMake());
        assertEquals("T39m", resultFromQuery.getTerminalModel());
        assertEquals("1500000", resultFromQuery.getTerminalFailures());
        assertEquals("150000", resultFromQuery.getTerminalSubscribers());

    }
}
