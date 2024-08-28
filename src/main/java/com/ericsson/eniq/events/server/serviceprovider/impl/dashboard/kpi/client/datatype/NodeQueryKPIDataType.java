package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype;

/**
 * Desrible NodeQueryKPIDataType
 *
 * @author ezhelao
 * @since 11/2011
 */
public class NodeQueryKPIDataType {

    final protected Double succRate;
    final protected Integer requestTotal;

    public NodeQueryKPIDataType(final Double succRate, final int requestTotal) {
        this.succRate = succRate;
        this.requestTotal = requestTotal;
    }

    public Double getSuccRate() {
        return succRate;
    }

    public Integer getRequestTotal() {
        return requestTotal;
    }
}
