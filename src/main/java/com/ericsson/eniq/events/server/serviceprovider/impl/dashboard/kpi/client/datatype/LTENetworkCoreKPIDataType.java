/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype;

/**
*
* @author ejamves
* @since 2012
*/
public class LTENetworkCoreKPIDataType {

    private Double attachFailureRatio;

    private Double serviceReqFailureRatio;

    private Double pagingFailureRatio;

    private Double supportedSubscribers;

    private Double attachedUsers;

    public LTENetworkCoreKPIDataType(final Double attachFailureRatio, final Double serviceReqFailureRatio,
            final Double pagingFailureRatio, final Double supportedSubscribers, final Double attachedUsers) {
        this.attachFailureRatio = getValue(attachFailureRatio);
        this.serviceReqFailureRatio = getValue(serviceReqFailureRatio);
        this.pagingFailureRatio = getValue(pagingFailureRatio);
        this.supportedSubscribers = getValue(supportedSubscribers);
        this.attachedUsers = getValue(attachedUsers);

    }

    public Double getAttachFailureRatio() {
        return attachFailureRatio;
    }

    public Double getServiceReqFailureRatio() {
        return serviceReqFailureRatio;
    }

    public Double getPagingFailureRatio() {
        return pagingFailureRatio;
    }

    public Double getSupportedSubscribers() {
        return supportedSubscribers;
    }

    public Double getAttachedUsers() {
        return attachedUsers;
    }

    public void setAttachFailureRatio(final Double attachFailureRatio) {
        this.attachFailureRatio = getValue(attachFailureRatio);
    }

    public void setServiceReqFailureRatio(final Double serviceReqFailureRatio) {
        this.serviceReqFailureRatio = getValue(serviceReqFailureRatio);
    }

    public void setPagingFailureRatio(final Double pagingFailureRatio) {
        this.pagingFailureRatio = getValue(pagingFailureRatio);
    }

    public void setSupportedSubscribers(final Double supportedSubscribers) {
        this.supportedSubscribers = getValue(supportedSubscribers);
    }

    public void setAttachedUsers(final Double attachedUsers) {
        this.attachedUsers = getValue(attachedUsers);
    }

    private double getValue(final Double value){
        if(value.equals(Double.NaN) || value.equals(Double.POSITIVE_INFINITY) || value.equals(Double.NEGATIVE_INFINITY)){
            return 0 ;
        }
        return value ;
    }
}
