/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype;

/**
*
* @author echchik
* @since 2012
*/
public class LTENetworkRanKPIDataType {

    private Double initialErabEstabSuccessRate;
    private Double addedErabEstabSuccessRate;

    public LTENetworkRanKPIDataType(final Double initialErabEstabSuccessRate, final Double addedErabEstabSuccessRate) {
        this.initialErabEstabSuccessRate = getValue(initialErabEstabSuccessRate);
        this.addedErabEstabSuccessRate = getValue(addedErabEstabSuccessRate);
    }

    public Double getInitialErabEstabSuccessRate() {
        return initialErabEstabSuccessRate;
    }

    public Double getAddedErabEstabSuccessRate() {
        return addedErabEstabSuccessRate;
    }

    public void setInitialErabEstabSuccessRate(final Double initialErabEstabSuccessRate) {
        this.initialErabEstabSuccessRate = getValue(initialErabEstabSuccessRate);
    }

    public void setAddedErabEstabSuccessRate(final Double addedErabEstabSuccessRate) {
        this.addedErabEstabSuccessRate = getValue(addedErabEstabSuccessRate);
    }
    
    private double getValue(final Double value){
        if(value.equals(Double.NaN) || value.equals(Double.POSITIVE_INFINITY) || value.equals(Double.NEGATIVE_INFINITY)){
            return 0 ;
        }
        return value ;
    }
    
}
