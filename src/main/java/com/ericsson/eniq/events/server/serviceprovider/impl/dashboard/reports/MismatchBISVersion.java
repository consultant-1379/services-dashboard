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

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports;

/**
 * Thrown to indicate that the version of configured BIS server is not matched with specified version.
 *
 */
public class MismatchBISVersion extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new <code>MismatchBISVersion</code> with the
     * specified detail message.
     *
     * @param   host   the detail message.
     */
    public MismatchBISVersion(String host) {
    super(host);
    }

    /**
     * Constructs a new <code>MismatchBISVersion</code> with no detail
     * message.
     */
    public MismatchBISVersion() {
    }
}
