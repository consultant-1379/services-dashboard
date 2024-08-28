package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import java.net.MalformedURLException;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.LTERanNodeQuery;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkRanKPIDataType;

/**
 * Desrible MockLTERanNodeQuery
 *
 */
public class MockLTERanNodeQuery extends LTERanNodeQuery {

    @Override
    public void init() {
        super.init(); //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public LTENetworkRanKPIDataType getNetworkRanKPISoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        return new LTENetworkRanKPIDataType(1.0, 300.0);
    }

    @Override
    public LTENetworkRanKPIDataType getNetworkRanKPISoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        return new LTENetworkRanKPIDataType(1.0, 300.0);
    }
}