package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Test;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkCoreKPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkRanKPIDataType;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

public class LTEKpiUtilityTest extends BaseJMockUnitTest {

    final static Double RANKPISOAP3 = 100.0;

    final static Double COREKPIVALUESOAP3 = 10.0;

    final static String L11B = "L11B";

    List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> mockRanKpiRowsSoap3;

    com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row moclRanKpiRowSoap3;

    List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> mockCoreKpiRowsSoap3;

    com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row mockCoreKpiRowSoap3;

    List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> mockRanKpiRowsSoap4;

    com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row mockRanKpiRowSoap4;

    List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> mockCoreKpiRowsSoap4;

    com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row mockCoreKpiRowSoap4;

    private void expectRanKpiSoap3() {
        mockery.checking(new Expectations() {
            {
                atMost(3).of(moclRanKpiRowSoap3).getPmRrcConnEstabSucc();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmRrcConnEstabAtt();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmS1SigConnEstabSucc();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmS1SigConnEstabAtt();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmErabEstabSuccInit();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmErabEstabAttInit();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmErabEstabSuccAdded();
                will(returnValue(10.0));
                atMost(3).of(moclRanKpiRowSoap3).getPmErabEstabAttAdded();
                will(returnValue(10.0));
            }
        });

        mockRanKpiRowsSoap3 = new ArrayList();
        mockRanKpiRowsSoap3.add(moclRanKpiRowSoap3);

    }

    private void expectCoreKpiSoap3() {
        mockery.checking(new Expectations() {
            {
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMUnsuccAttachE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail7E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail8E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail11E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail12E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail13E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail14E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMEpsAttachFail15E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMUnsuccAttachCC2728E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMUnsuccAttachCC3233E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMAttAttachE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMUnsuccServiceReqE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMAttServiceRequestE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMAttPsPagingE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMSuccPsPagingE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMNbrVisitingForeignESum();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMNbrVisitingNatSubESum();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMNbrHomeSubESum();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap3).getVSMMNbrActAttachedSubESum();
                will(returnValue(10.0));
            }
        });

        mockCoreKpiRowsSoap3 = new ArrayList();
        mockCoreKpiRowsSoap3.add(mockCoreKpiRowSoap3);

    }

    private void expectCoreKpiSoap4() {
        mockery.checking(new Expectations() {
            {
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMUnsuccAttachE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail7E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail8E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail11E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail12E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail13E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail14E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMEpsAttachFail15E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMUnsuccAttachCC2728E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMUnsuccAttachCC3233E();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMAttAttachE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMUnsuccServiceReqE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMAttServiceRequestE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMAttPsPagingE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMSuccPsPagingE();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMNbrVisitingForeignESum();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMNbrVisitingNatSubESum();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMNbrHomeSubESum();
                will(returnValue(10.0));
                atMost(3).of(mockCoreKpiRowSoap4).getVSMMNbrActAttachedSubESum();
                will(returnValue(10.0));
            }
        });

        mockCoreKpiRowsSoap4 = new ArrayList();
        mockCoreKpiRowsSoap4.add(mockCoreKpiRowSoap4);

    }

    private void expectRanKpiSoap4() {
        mockery.checking(new Expectations() {
            {
                atMost(3).of(mockRanKpiRowSoap4).getPmRrcConnEstabSucc();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmRrcConnEstabAtt();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmS1SigConnEstabSucc();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmS1SigConnEstabAtt();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmErabEstabSuccInit();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmErabEstabAttInit();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmErabEstabSuccAdded();
                will(returnValue(10.0));
                atMost(3).of(mockRanKpiRowSoap4).getPmErabEstabAttAdded();
                will(returnValue(10.0));
            }
        });

        mockRanKpiRowsSoap4 = new ArrayList();
        mockRanKpiRowsSoap4.add(mockRanKpiRowSoap4);

    }

    private void setNEVersionSoap3(final String neVersion) {
        mockery.checking(new Expectations() {
            {
                one(moclRanKpiRowSoap3).getNEVersion();
                will(returnValue(neVersion));
                if (neVersion.compareTo(L11B) > 0) {
                    one(moclRanKpiRowSoap3).getPmRrcConnEstabAttReatt();
                    will(returnValue(0.0));
                } else if (neVersion.equals(L11B)) {
                    one(moclRanKpiRowSoap3).getPmZtemporary9();
                    will(returnValue(0.0));
                }
            }
        });
    }

    private void setNEVersionSoap4(final String neVersion) {
        mockery.checking(new Expectations() {
            {
                one(mockRanKpiRowSoap4).getNEVersion();
                will(returnValue(neVersion));
                if (neVersion.compareTo(L11B) > 0) {
                    one(mockRanKpiRowSoap4).getPmRrcConnEstabAttReatt();
                    will(returnValue(0.0));
                } else if (neVersion.equals(L11B)) {
                    one(mockRanKpiRowSoap4).getPmZtemporary9();
                    will(returnValue(0.0));
                }
            }
        });
    }

    @Test
    public void testCalculateRanKpisSoap3() {
        moclRanKpiRowSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row.class);
        setNEVersionSoap3("L11A");
        expectRanKpiSoap3();
        final LTEKpiUtility lteKpiUtility = new LTEKpiUtility();
        LTENetworkRanKPIDataType lteNetworkRanKPIDataType = lteKpiUtility.calculateRanKpisSoap3(mockRanKpiRowsSoap3);
        assertEquals(RANKPISOAP3, lteNetworkRanKPIDataType.getInitialErabEstabSuccessRate());
        assertEquals(RANKPISOAP3, lteNetworkRanKPIDataType.getAddedErabEstabSuccessRate());

        setNEVersionSoap3("L11B");
        lteNetworkRanKPIDataType = lteKpiUtility.calculateRanKpisSoap3(mockRanKpiRowsSoap3);
        assertEquals(RANKPISOAP3, lteNetworkRanKPIDataType.getInitialErabEstabSuccessRate());
        assertEquals(RANKPISOAP3, lteNetworkRanKPIDataType.getAddedErabEstabSuccessRate());

        setNEVersionSoap3("L13A");
        lteNetworkRanKPIDataType = lteKpiUtility.calculateRanKpisSoap3(mockRanKpiRowsSoap3);
        assertEquals(RANKPISOAP3, lteNetworkRanKPIDataType.getInitialErabEstabSuccessRate());
        assertEquals(RANKPISOAP3, lteNetworkRanKPIDataType.getAddedErabEstabSuccessRate());
    }

    @Test
    public void testCalculateRanKpisSoap4() {
        mockRanKpiRowSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row.class);
        setNEVersionSoap4("L11A");
        expectRanKpiSoap4();
        final LTEKpiUtility lteKpiUtility = new LTEKpiUtility();
        lteKpiUtility.calculateRanKpisSoap4(mockRanKpiRowsSoap4);

        setNEVersionSoap4("L11B");
        lteKpiUtility.calculateRanKpisSoap4(mockRanKpiRowsSoap4);

        setNEVersionSoap4("L13A");
        lteKpiUtility.calculateRanKpisSoap4(mockRanKpiRowsSoap4);
    }

    @Test
    public void testCalculateCoreKpisSoap3() {
        mockCoreKpiRowSoap3 = mockery.mock(com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row.class);
        expectCoreKpiSoap3();
        final LTEKpiUtility lteKpiUtility = new LTEKpiUtility();
        final LTENetworkCoreKPIDataType lteNetworkRanKPIDataType = lteKpiUtility.calculateCoreKpisSoap3(mockCoreKpiRowsSoap3);
        assertEquals(COREKPIVALUESOAP3, lteNetworkRanKPIDataType.getAttachedUsers());

    }

    @Test
    public void testCalculateCoreKpisSoap4() {
        mockCoreKpiRowSoap4 = mockery.mock(com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row.class);
        expectCoreKpiSoap4();
        final LTEKpiUtility lteKpiUtility = new LTEKpiUtility();
        final LTENetworkCoreKPIDataType lteNetworkRanKPIDataType = lteKpiUtility.calculateCoreKpisSoap4(mockCoreKpiRowsSoap4);
        assertEquals(COREKPIVALUESOAP3, lteNetworkRanKPIDataType.getAttachedUsers());

    }
}