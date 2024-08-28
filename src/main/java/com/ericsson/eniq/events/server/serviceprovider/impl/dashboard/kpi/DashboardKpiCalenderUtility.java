/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.utils.DateTimeRange;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.datetime.DateTimeHelper;

/**
 * 
 * This is a utility class that convert URL date parameters
 * to XMLGregorianCalendar instance that is passed to BIS.
 *
 * @author echchik
 * @since 2012
 */

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class DashboardKpiCalenderUtility {

    @EJB
    DateTimeHelper dateTimeHelper;

    /**
     * @param serviceProviderParameters the params from URL .
     * @return an XMLGreograinClanedar that parse from the dateFrom field. HOUR_OF_DAY is set to 12 (midday) .
     * @throws DatatypeConfigurationException if something wrong with the xml to normal caldendar conversion.
     */
    public XMLGregorianCalendar getXMLGregorianCalendarFromParams(
            final MultivaluedMap<String, String> serviceProviderParameters) throws DatatypeConfigurationException {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add("STATS");
        final FormattedDateTimeRange formattedDateTimeRange = dateTimeHelper.translateDateTimeParameters(
                serviceProviderParameters, techPacks);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(formattedDateTimeRange.getStartDate());
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 12);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        return convertCalendarToXMLGregorianCalendar(gregorianCalendar);
    }

    /**
     * @param serviceProviderParameters params from URL
     * @return the previous day of the dataFrom field indicates.HOUR_OF_DAY is set ti 12 (midday)
     * @throws DatatypeConfigurationException
     */
    public XMLGregorianCalendar getPreviousDayXMLGregorianCalendarFromParams(
            final MultivaluedMap<String, String> serviceProviderParameters) throws DatatypeConfigurationException {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add("STATS");
        final FormattedDateTimeRange formattedDateTimeRange = dateTimeHelper.translateDateTimeParameters(
                serviceProviderParameters, techPacks);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(formattedDateTimeRange.getStartDate());
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 12);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.add(Calendar.DATE, -1);
        return convertCalendarToXMLGregorianCalendar(gregorianCalendar);

    }

    /**
     * @param serviceProviderParameters the params from URL .
     * @return an XMLGreograinClanedar that parse local date always
     * @throws DatatypeConfigurationException if something wrong with the xml to normal caldendar conversion.
     */
    public XMLGregorianCalendar getXMLLocalGregorianCalendarFromParams(
            final MultivaluedMap<String, String> serviceProviderParameters) throws DatatypeConfigurationException {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add("STATS");

        final String dateFrom = serviceProviderParameters.getFirst("dateFrom");
        final String dateTo = serviceProviderParameters.getFirst("dateTo");
        final String timeFrom = serviceProviderParameters.getFirst("timeFrom");
        final String timeTo = serviceProviderParameters.getFirst("timeTo");

        final String formattedStartDateTime = DateTimeUtils.formattedDateTime(dateFrom, timeFrom);
        final String formattedEndDateTime = DateTimeUtils.formattedDateTime(dateTo, timeTo);

        final FormattedDateTimeRange formattedDateTimeRange = DateTimeRange.getFormattedDateTimeRangeWithOutOffset(
                formattedStartDateTime, formattedEndDateTime);

        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(formattedDateTimeRange.getStartDate());
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 12);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        return convertCalendarToXMLGregorianCalendar(gregorianCalendar);
    }

    /**
     * @param date the GreogrianCalendar
     * @return the XMLGregorianCalendar
     * @throws DatatypeConfigurationException if the convertion failed.
     */
    private XMLGregorianCalendar convertCalendarToXMLGregorianCalendar(final GregorianCalendar date)
            throws DatatypeConfigurationException {
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        return xmlGregorianCalendar;
    }

}
