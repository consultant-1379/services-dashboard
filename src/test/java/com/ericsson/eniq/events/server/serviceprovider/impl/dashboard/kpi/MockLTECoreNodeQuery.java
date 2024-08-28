package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import java.net.MalformedURLException;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.LTECoreNodeQuery;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkCoreKPIDataType;

/**
 * Desrible MockLTECoreNodeQuery
 *
 */
public class MockLTECoreNodeQuery extends LTECoreNodeQuery {

    @Override
    public void init() {
        super.init(); //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public LTENetworkCoreKPIDataType getNetworkCoreKPISoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        return new LTENetworkCoreKPIDataType(1.0, 30.0, 40.0, 50.0, 100.0);
    }

    @Override
    public LTENetworkCoreKPIDataType getNetworkCoreKPISoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        return new LTENetworkCoreKPIDataType(1.0, 30.0, 40.0, 50.0, 100.0);
    }
}