package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.NodeQuery;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDrillDownDataType;

/**
 * Desrible MockNodeQuery
 *
 */
public class MockNodeQuery extends NodeQuery {
    @Override
    public void init() {
        super.init(); //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public NodeQueryKPIDataType getNetworkKPISoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 300);
    }

    @Override
    public NodeQueryKPIDataType getNetworkKPISoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 300);
    }

    @Override
    public NodeQueryKPIDataType getRNCKPISoap3(final List<String> rncNames, final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 301);
    }

    @Override
    public NodeQueryKPIDataType getRNCKPISoap4(final List<String> rncNames, final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 301);
    }

    @Override
    public NodeQueryKPIDataType getCellKPISoap3(final List<String> cells, final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 302);
    }

    @Override
    public NodeQueryKPIDataType getCellKPISoap4(final List<String> cells, final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 302);
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getNetworkKPIDrillDownSoap3(final XMLGregorianCalendar date) throws MalformedURLException {
        if (date.getMonth() == 11 && date.getDay() == 27 && date.getHour() == 12) {
            final NodeQueryKPIDrillDownDataType dateRow1 = new NodeQueryKPIDrillDownDataType("cellname1", "rncname1", 108, 90.0, 120);
            final List<NodeQueryKPIDrillDownDataType> data = new ArrayList<NodeQueryKPIDrillDownDataType>();
            data.add(dateRow1);
            data.add(dateRow1);
            return data;
        }
        if (date.getMonth() == 11 && date.getDay() == 26 && date.getHour() == 12) {
            final NodeQueryKPIDrillDownDataType dateRow1 = new NodeQueryKPIDrillDownDataType("cellname1", "rncname1", 90, 90.0, 100);
            final List<NodeQueryKPIDrillDownDataType> data = new ArrayList<NodeQueryKPIDrillDownDataType>();
            data.add(dateRow1);
            data.add(dateRow1);
            return data;
        }
        return null;
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getNetworkKPIDrillDownSoap4(final XMLGregorianCalendar date) throws MalformedURLException {
        if (date.getMonth() == 11 && date.getDay() == 27 && date.getHour() == 12) {
            final NodeQueryKPIDrillDownDataType dateRow1 = new NodeQueryKPIDrillDownDataType("cellname1", "rncname1", 108, 90.0, 120);
            final List<NodeQueryKPIDrillDownDataType> data = new ArrayList<NodeQueryKPIDrillDownDataType>();
            data.add(dateRow1);
            data.add(dateRow1);
            return data;
        }
        if (date.getMonth() == 11 && date.getDay() == 26 && date.getHour() == 12) {
            final NodeQueryKPIDrillDownDataType dateRow1 = new NodeQueryKPIDrillDownDataType("cellname1", "rncname1", 90, 90.0, 100);
            final List<NodeQueryKPIDrillDownDataType> data = new ArrayList<NodeQueryKPIDrillDownDataType>();
            data.add(dateRow1);
            data.add(dateRow1);
            return data;
        }
        return null;
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getRNCKPIDrillDownSoap3(final List<String> rncIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getNetworkKPIDrillDownSoap3(date);
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getRNCKPIDrillDownSoap4(final List<String> rncIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getNetworkKPIDrillDownSoap4(date);
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getCellKPIDrillDownSoap3(final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getNetworkKPIDrillDownSoap3(date);
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getCellKPIDrillDownSoap4(final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getNetworkKPIDrillDownSoap4(date);
    }
}
