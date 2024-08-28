/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype;

import java.util.*;

/**
 * This class represents an object with attributes that 1. describe an error message, 2. report a success or failure and, 3. shows the relevant data.
 */
public class KPIDataType {

    private String success = "false";
    private String errorDescription = "";
    final private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

    public KPIDataType(final Boolean succ, final String errorDesc) {
        this.success = succ.toString();
        this.errorDescription = errorDesc;
    }

    /**
     * the default KPIDataType is of type Success
     */
    public KPIDataType() {
        this(true, "");
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(final Boolean success) {
        this.success = success.toString();
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(final String desc) {
        this.errorDescription = desc;
    }

    public List<Map<String, String>> getData() {
        return data;
    }
}