package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports;

import java.util.List;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BIObjectTreeDataType;

/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 06/03/12
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public class ReportServiceResult {
    private String success;

    private String bisServiceSuccess;

    private String errorDescription;

    private List<BIObjectTreeDataType> data;

    public ReportServiceResult(final String success, final String bisServiceSuccess, final String errorDescription,
            final List<BIObjectTreeDataType> data) {
        this.success = success;
        this.bisServiceSuccess = bisServiceSuccess;
        this.errorDescription = errorDescription;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(final String success) {
        this.success = success;
    }

    public String getBisServiceSuccess() {
        return bisServiceSuccess;
    }

    public void setBisServiceSuccess(final String bisServiceSuccess) {
        this.bisServiceSuccess = bisServiceSuccess;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(final String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public List<BIObjectTreeDataType> getData() {
        return data;
    }

    public void setData(final List<BIObjectTreeDataType> data) {
        this.data = data;
    }
}
