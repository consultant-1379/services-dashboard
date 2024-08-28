/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2011
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client;

import java.util.List;

/**
 * Describe BIObjectDataType the class represents the report tree structure. It holds the information
 * of a node (which can be a file or a folder) and their children nodes.It can be used for POJO-JSON mapping in
 * the future .
 *
 * @author ezhelao
 */
public class BIObjectTreeDataType {

    private ObjectType type;       // the type of an item . It can be a folder or file.

    private List<BIObjectTreeDataType> children; // the children elements of a folder node.

    private String fileURL; // will be used only when the object type is a file

    private String displayName;    // the displayable name of the item

    public ObjectType getType() {
        return type;
    }

    public void setType(final ObjectType type) {
        this.type = type;
    }

    public List<BIObjectTreeDataType> getChildren() {
        return children;
    }

    public void setChildren(final List<BIObjectTreeDataType> children) {
        this.children = children;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(final String fileURL) {
        this.fileURL = fileURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public enum ObjectType {
        FILE, FOLDER
    }
}