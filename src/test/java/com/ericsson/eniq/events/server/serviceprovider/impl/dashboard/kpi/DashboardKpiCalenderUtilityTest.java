/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import static junit.framework.Assert.assertEquals;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 *
 * @author echchik
 * @since 2012
 */

@ContextConfiguration(locations = {
        "classpath:com/ericsson/eniq/events/server/resources/BaseServiceIntegrationTest-context.xml",
        "classpath:dashboard-service-context.xml"})
public class DashboardKpiCalenderUtilityTest {

    TestContextManager testContextManager;

    @Resource
    DashboardKpiCalenderUtility dashboardKpiCalenderUtility;


    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }
    
    @Test
    public void testGetXMLGregorianCalendarFromParams() throws DatatypeConfigurationException {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("timeFrom", "0000");
        map.add("timeTo", "0000");
        map.add("dateFrom", "11122011");
        map.add("dateTo", "11122011");
        map.add("tzOffset", "+0000");
        XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility.getXMLGregorianCalendarFromParams(map);
        assertEquals(12, xmlGregorianCalendar.getHour());
        assertEquals(11, xmlGregorianCalendar.getDay());
        assertEquals(12, xmlGregorianCalendar.getMonth());
        assertEquals(2011, xmlGregorianCalendar.getYear());
    }
    
    @Test
    public void testGetPreviousDayXMLGregorianCalendarFromParams() throws DatatypeConfigurationException {
        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("timeFrom", "0000");
        map.add("timeTo", "0000");
        map.add("dateFrom", "11122011");
        map.add("dateTo", "11122011");
        map.add("tzOffset", "+0000");
        XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility.getPreviousDayXMLGregorianCalendarFromParams(map);
        assertEquals(12, xmlGregorianCalendar.getHour());
        assertEquals(10, xmlGregorianCalendar.getDay());
        assertEquals(12, xmlGregorianCalendar.getMonth());
        assertEquals(2011, xmlGregorianCalendar.getYear());
    }

    @Ignore
    @Test
    public void testgetXMLGreograinCalendarFromParams() throws DatatypeConfigurationException {
        XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility.getXMLGregorianCalendarFromParams(setUpRequestParam());
        assertEquals(12, xmlGregorianCalendar.getHour());
        assertEquals(0, xmlGregorianCalendar.getMinute());
        assertEquals(11, xmlGregorianCalendar.getDay());
        assertEquals(2011, xmlGregorianCalendar.getYear());
        assertEquals(11, xmlGregorianCalendar.getMonth());
    }

    private MultivaluedMap<String, String> setUpRequestParam() {

        MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.add("dateFrom", "02022011");
        map.add("timeFrom", "1200");
        map.add("dateTo", "02022011");
        map.add("timeTo", "1200");
        return map;
    }

}
