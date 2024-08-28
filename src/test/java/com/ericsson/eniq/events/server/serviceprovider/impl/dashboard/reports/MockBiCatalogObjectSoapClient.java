package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BICatalogObjectSoapClient;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BIObjectTreeDataType;

/**
 * Desrible MockBiCatalogObjectSoapClient
 */
public class MockBiCatalogObjectSoapClient extends BICatalogObjectSoapClient {
    @Override
    public void postConstruct() {
    }

    @Override
    public void connectSoap3() {
    }

    @Override
    public void connectSoap4() {
    }

    @Override
    public BIObjectTreeDataType getDocumentFolderList() throws Exception {
        try {
            final BIObjectTreeDataType biObjectTreeDataType = new BIObjectTreeDataType();
            final BIObjectTreeDataType theOnlyFile = new BIObjectTreeDataType();
            theOnlyFile.setDisplayName("TheOnlyOneFileIntheFolder.txt");
            theOnlyFile.setType(BIObjectTreeDataType.ObjectType.FILE);
            theOnlyFile.setFileURL("http://fileobjectmockurl/TheOnlyOneFileIntheFolder.txt");
            final List<BIObjectTreeDataType> childrenList = new ArrayList<BIObjectTreeDataType>();
            childrenList.add(theOnlyFile);
            biObjectTreeDataType.setChildren(childrenList);
            return biObjectTreeDataType;
        } catch (final Exception ex) {
            throw ex;
        }
    }
}
