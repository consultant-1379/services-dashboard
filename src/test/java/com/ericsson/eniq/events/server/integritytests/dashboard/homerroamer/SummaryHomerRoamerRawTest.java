/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.dashboard.homerroamer;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.homerroamer.SummaryHomerRoamerService;
import com.ericsson.eniq.events.server.test.queryresults.dashboard.homerroamer.SummaryHomerRoamerResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eeikbe
 * @since 2011
 *
 */
public class SummaryHomerRoamerRawTest extends BaseDataIntegrityTest<SummaryHomerRoamerResult> {

    private SummaryHomerRoamerService summaryHomerRoamerService;

    final String table = "#EVENT_E_SGEH_MCC_MNC_ROAM_DAY";

    @Before
    public void onSetUp() throws Exception {
        summaryHomerRoamerService = new SummaryHomerRoamerService();
        attachDependencies(summaryHomerRoamerService);
        createTable();
        insertTabelData();
    }

    @Test
    public void testRunQuery() throws URISyntaxException, Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.add(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        requestParameters.add(TIME_QUERY_PARAM, ONE_DAY);
        final String result = runQuery(summaryHomerRoamerService, requestParameters);
        verifyResult(result);
    }

    /**
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
        assertEquals("19", resultFromQuery.getRoamers());

        //Check if the Previous day is correct...
        resultFromQuery = results.get(6);
        assertEquals("7", resultFromQuery.getRank());
        assertEquals("Previous", resultFromQuery.getCountry());
        assertEquals("2", resultFromQuery.getRoamers());
    }

    /**
     * @throws java.sql.SQLException
     *
     */
    private void insertTabelData() throws SQLException {
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
        insertTableRow("310", "160", 1, timestamp); //Guam
        insertTableRow("460", "04", 1, timestamp); //China (1)
        insertTableRow("310", "140", 1, timestampPrevious); //Guam
        insertTableRow("460", "04", 1, timestampPrevious); //China (1)
    }

    /**
     * @param timestamp
     * @param imsi_mcc
     * @param imsi_mnc
     * @throws java.sql.SQLException
     */
    private void insertTableRow(final String imsi_mcc, final String imsi_mnc, final int no_of_total_err_roamers,
            final String timestamp) throws SQLException {
        final Map<String, Object> valuesForTable = new HashMap<String, Object>();
        valuesForTable.put(IMSI_MCC, imsi_mcc);
        valuesForTable.put(IMSI_MNC, imsi_mnc);
        valuesForTable.put(NO_OF_TOTAL_ERR_ROAMERS, no_of_total_err_roamers);
        valuesForTable.put(DATETIME_ID, timestamp);
        insertRow(table, valuesForTable);
    }

    /**
     * @throws Exception 
     */
    private void createTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(IMSI_MCC);
        columns.add(IMSI_MNC);
        columns.add(NO_OF_TOTAL_ERR_ROAMERS);
        columns.add(DATETIME_ID);
        createTemporaryTable(table, columns);
    }

}
