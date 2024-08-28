/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.network.kpi.datatype;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author ericker
 */
@XmlRootElement
public class RRCKPIDataType {
    Map<String, Double> data;
    String message;
    boolean success = false;

    public RRCKPIDataType(final Map<String, Double> kpiData, final String message) {
        this.data = kpiData;
        this.message = message;
        if (message.length() == 0 || message.equalsIgnoreCase("no data to return")) {
            success = true;
        }
    }

    @Override
    public String toString() {
        return "RRCKPIDataType{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}

