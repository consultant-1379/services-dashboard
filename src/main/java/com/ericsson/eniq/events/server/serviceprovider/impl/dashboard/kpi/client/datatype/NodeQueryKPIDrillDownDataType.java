package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype;

/**
 * Desrible NodeQueryKPIDrillDownDataType
 *
 * @author ezhelao
 * @since 11/2011
 */
public class NodeQueryKPIDrillDownDataType extends NodeQueryKPIDataType {
    protected String cellName;
    protected String rncName;
    protected Integer reqSucc;

    public NodeQueryKPIDrillDownDataType(final String cellName, final String rncName, final int reqSucc, final Double succRate, final int requestTotal) {
        super(succRate, requestTotal);
        this.cellName = cellName;
        this.rncName = rncName;
        this.reqSucc = reqSucc;
    }

    public String getCellName() {
        return cellName;
    }

    public Integer getReqSucc() {
        return reqSucc;
    }

    public String getRncName() {
        return rncName;
    }
}
