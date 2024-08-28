package com.ericsson.eniq.events.server.integritytests.dashboard.homerroamer;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.homerroamer.SummaryHomerRoamerWCDMAService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.homerroamer.SummaryHomerRoamerResult;
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
import static org.junit.Assert.assertEquals;

/**
 * User: EEIKBE
 * Date: 10/11/11
 * Time: 14:24
 */
public class SummaryWCDMAHomerRoamerRawTest extends BaseDataIntegrityTest<SummaryHomerRoamerResult>{
    private SummaryHomerRoamerWCDMAService serviceTest;

    final String table = "#EVENT_E_RAN_CFA_MCC_MNC_ROAM_DAY";

    @Before
    public void onSetup() throws Exception{
        serviceTest = new SummaryHomerRoamerWCDMAService();
        attachDependencies(serviceTest);
        createTable();
        insertTableData();
    }

    @Test
    public void testRunQuery(){
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, ONE_DAY);
        final String result = runQuery(serviceTest, requestParameters);
    }

    /**
     * Verify the result.
     * @param jsonResult
     * @throws Exception
     */
    private void verifyResult(final String jsonResult) throws Exception {
        final List<SummaryHomerRoamerResult> results = getTranslator().translateResult(jsonResult,
                SummaryHomerRoamerResult.class);
        final int expectedRecordSize = 7;
        assertEquals(expectedRecordSize, results.size());

        //Check the first Row.
        SummaryHomerRoamerResult resultFromQuery = results.get(0);
        assertEquals("1", resultFromQuery.getRank());
        assertEquals("China", resultFromQuery.getCountry());
        assertEquals("6", resultFromQuery.getRoamers());

        //Check if the total is correct...
        resultFromQuery = results.get(5);
        assertEquals("6", resultFromQuery.getRank());
        assertEquals("Total", resultFromQuery.getCountry());
        assertEquals("18", resultFromQuery.getRoamers());

        //Check if the Previous day is correct...
        resultFromQuery = results.get(6);
        assertEquals("7", resultFromQuery.getRank());
        assertEquals("Previous", resultFromQuery.getCountry());
        assertEquals("2", resultFromQuery.getRoamers());
    }


    /**
     * Insert the test data into the temporary test table.
     * @throws java.sql.SQLException
     */
    private void insertTableData() throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinusDay(1);
        final String timestampPrevious = DateTimeUtilities.getDateTimeMinusDay(2);
        System.out.println("TIMESTAMP = " + timestamp);
        insertTableRow("460", "00", 2, timestamp); //China (2)
        insertTableRow("460", "01", 1, timestamp); //China (1)
        insertTableRow("502", "12", 1, timestamp); //Malaysia
        insertTableRow("310", "190", 1, timestamp); //Guam
        insertTableRow("310", "200", 1, timestamp); //Guam
        insertTableRow("202", "01", 1, timestamp); //Greece
        insertTableRow("460", "02", 1, timestamp); //China (1)
        insertTableRow("204", "08", 1, timestamp); //Netherlands
        insertTableRow("310", "150", 1, timestamp); //Guam
        insertTableRow("470", "03", 1, timestamp); //Bangladesh
        insertTableRow("204", "12", 1, timestamp); //Netherlands
        insertTableRow("310", "120", 1, timestamp); //Guam
        insertTableRow("204", "04", 1, timestamp); //Netherlands
        insertTableRow("204", "02", 1, timestamp); //Netherlands
        insertTableRow("460", "03", 1, timestamp); //China (1)
        insertTableRow("310", "140", 1, timestamp); //Guam
        insertTableRow("460", "04", 1, timestamp); //China (1)
        insertTableRow("310", "140", 1, timestampPrevious); //Guam
        insertTableRow("460", "04", 1, timestampPrevious); //China (1)
    }


    /**
     * Insert a row in the temporary test table.
     * @param imsi_mcc
     * @param imsi_mnc
     * @param no_of_total_err_roamers
     * @param timestamp
     * @throws java.sql.SQLException
     */
    private void insertTableRow(final String imsi_mcc, final String imsi_mnc, final int no_of_total_err_roamers,
            final String timestamp) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI_MCC, imsi_mcc);
        valuesForTable.put(IMSI_MNC, imsi_mnc);
        valuesForTable.put(NO_OF_TOTAL_ROAMERS, no_of_total_err_roamers);
        valuesForTable.put(DATETIME_ID, timestamp);
        insertRow(table, valuesForTable);
    }

    /**
     * Create the test table.
     * @throws Exception
     */
    private void createTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI_MCC);
        columns.add(IMSI_MNC);
        columns.add(NO_OF_TOTAL_ROAMERS);
        columns.add(DATETIME_ID);
        createTemporaryTable(table, columns);
    }

}
