package com.ericsson.eniq.events.server.integritytests.dashboard.terminal;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.*;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.terminal.SummaryTerminalWCDMAService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.terminal.SummaryTerminalResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * User: eeikbe Date: 15/11/11 Time: 16:04
 */
@Ignore
public class SummaryWCDMATerminalRawTest extends BaseDataIntegrityTest<SummaryTerminalResult> {

    private SummaryTerminalWCDMAService summaryTerminalWCDMAService;

    @Before
    public void onSetup() {
        final String EVENT_E_RAN_CFA_TAC_ERR_DAY_TABLE = "#EVENT_E_RAN_CFA_TAC_ERR_DAY";
        final String EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_TABLE = "#EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_DAY";
        final String EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY_TABLE = "#EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY";
        final String EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY_TABLE = "#EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY";
        final String EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY_TABLE = "#EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY";

        summaryTerminalWCDMAService = new SummaryTerminalWCDMAService();
        attachDependencies(summaryTerminalWCDMAService);
        try {
            createTable(EVENT_E_RAN_CFA_TAC_ERR_DAY_TABLE);
            createTable(EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_TABLE);
            createTable(EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY_TABLE);
            createTable(EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY_TABLE);
            createTable(EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY_TABLE);
        } catch (final Exception e) {
            fail("Couldn't create table :(");
        }
        try {
            insertRows(EVENT_E_RAN_CFA_TAC_ERR_DAY_TABLE);
            insertRows(EVENT_E_RAN_HFA_HSDSCH_TAC_ERR_TABLE);
            insertRows(EVENT_E_RAN_HFA_IFHO_TAC_ERR_DAY_TABLE);
            insertRows(EVENT_E_RAN_HFA_IRAT_TAC_ERR_DAY_TABLE);
            insertRows(EVENT_E_RAN_HFA_SOHO_TAC_ERR_DAY_TABLE);
        } catch (final SQLException e) {
            fail("Couldn't load the table :(");
        }

    }

    @Test
    public void testRunQuery() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, ONE_DAY);

        final String result = runQuery(summaryTerminalWCDMAService, requestParameters);
        verifyResult(result);
    }

    private void insertRows(final String temporaryTable) throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);

        insertTableRow(temporaryTable, 1253700, 3, 5, timestamp); //Apple, iPhone4
        insertTableRow(temporaryTable, 1253400, 4, 5, timestamp); //Apple, iPhone4
        insertTableRow(temporaryTable, 1253500, 6, 5, timestamp); //Apple, iPhone4
        insertTableRow(temporaryTable, 1253600, 7, 5, timestamp); //Apple, iPhone4
        insertTableRow(temporaryTable, 1280600, 7, 100, timestamp); //Apple, iPad2
        insertTableRow(temporaryTable, 1280400, 8, 100, timestamp); //Apple, iPad2
        insertTableRow(temporaryTable, 1280500, 9, 100, timestamp); //Apple, iPad2
        insertTableRow(temporaryTable, 1280300, 5, 100, timestamp); //Apple, iPad2
        insertTableRow(temporaryTable, 1244800, 1, 1, timestamp); //Sony Ericsson, Xperia X8
        insertTableRow(temporaryTable, 1244900, 3, 200000, timestamp); //Sony Ericsson, Xperia X8
        insertTableRow(temporaryTable, 1256000, 4, 200000, timestamp); //Sony Ericsson, Xperia X8
        insertTableRow(temporaryTable, 1268400, 5, 200000, timestamp); //Sony Ericsson, Xperia X8
        insertTableRow(temporaryTable, 1268500, 5, 200000, timestamp); //Sony Ericsson, Xperia X8
        insertTableRow(temporaryTable, 52034363, 1, 50000, timestamp); //Sony Ericsson, T39m
        insertTableRow(temporaryTable, 52034369, 2, 50000, timestamp); //Sony Ericsson, T39m
        insertTableRow(temporaryTable, 52034371, 3, 50000, timestamp); //Sony Ericsson, T39m
        insertTableRow(temporaryTable, 101300, 8, 89, timestamp); //Nortel, LMU
        insertTableRow(temporaryTable, 1002810, 5, 100000, timestamp); //Nokia, 6190
        insertTableRow(temporaryTable, 1002830, 6, 100000, timestamp); //Nokia, 6190
        insertTableRow(temporaryTable, 1002850, 8, 100000, timestamp); //Nokia, 6190
        insertTableRow(temporaryTable, 1039500, 6, 500000, timestamp); //Samsung, SGH-X427M
        insertTableRow(temporaryTable, 1046000, 9, 500000, timestamp); //Samsung, SGH-X427M
        insertTableRow(temporaryTable, 1047900, 1, 1000000, timestamp); //Samsung, SGH-X427M
        insertTableRow(temporaryTable, 1048000, 3, 1000000, timestamp); //Samsung, SGH-X427M
        insertTableRow(temporaryTable, 1054600, 4, 1000000, timestamp); //Samsung, SGH-X427M
        insertTableRow(temporaryTable, 1003469, 2, 5, timestamp); //Motorola, StarTac 7000

    }

    private void verifyResult(final String jsonResult) throws Exception {
        final List<SummaryTerminalResult> results = getTranslator().translateResult(jsonResult, SummaryTerminalResult.class);
        assertEquals(5, results.size());

        SummaryTerminalResult resultFromQuery = results.get(0);
        assertEquals("1", resultFromQuery.getTerminalRank());
        assertEquals("Apple Inc", resultFromQuery.getTerminalMake());
        assertEquals("iPad 2 A1396", resultFromQuery.getTerminalModel());
        assertEquals("145", resultFromQuery.getTerminalFailures());
        assertEquals("2000", resultFromQuery.getTerminalSubscribers());

        resultFromQuery = results.get(3);
        assertEquals("4", resultFromQuery.getTerminalRank());
        assertEquals("Nokia", resultFromQuery.getTerminalMake());
        assertEquals("6190", resultFromQuery.getTerminalModel());
        assertEquals("95", resultFromQuery.getTerminalFailures());
        assertEquals("1500000", resultFromQuery.getTerminalSubscribers());

    }

    public void createTable(final String temporaryTable) throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(TAC);
        columns.add(NO_OF_ERRORS);
        columns.add(NO_OF_TOTAL_ERR_SUBSCRIBERS);
        columns.add(DATETIME_ID);

        createTemporaryTable(temporaryTable, columns);
    }

    private void insertTableRow(final String temporaryTable, final int tac, final int no_of_errors, final int no_of_total_err_subscribers,
                                final String timestamp) throws SQLException {
        final Map<String, Object> field = new HashMap<String, Object>();
        field.put(TAC, tac);
        field.put(NO_OF_ERRORS, no_of_errors);
        field.put(NO_OF_TOTAL_ERR_SUBSCRIBERS, no_of_total_err_subscribers);
        field.put(DATETIME_ID, timestamp);
        insertRow(temporaryTable, field);
    }

}
