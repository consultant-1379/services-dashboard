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
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.ejb.*;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.*;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class LTEKpiUtility {

    public static final String DECIMAL_FORMAT_STRING = "#.##";

    final DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STRING);

    final static String L11B = "L11B";

    @Lock(LockType.WRITE)
    public LTENetworkRanKPIDataType calculateRanKpisSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows) {
        final LTENetworkRanKPIDataType lteRanKpiDataType = new LTENetworkRanKPIDataType(0.0, 0.0);
        if (kpiRows.isEmpty()) {
            return lteRanKpiDataType;
        }
        lteRanKpiDataType.setInitialErabEstabSuccessRate(calcInitialErabEstabSuccessRateSoap3(kpiRows));
        lteRanKpiDataType.setAddedErabEstabSuccessRate(calcAddedErabEstabSuccessRateSoap3(kpiRows));
        return lteRanKpiDataType;

    }

    @Lock(LockType.WRITE)
    public LTENetworkRanKPIDataType calculateRanKpisSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows) {
        final LTENetworkRanKPIDataType lteRanKpiDataType = new LTENetworkRanKPIDataType(0.0, 0.0);
        if (kpiRows.isEmpty()) {
            return lteRanKpiDataType;
        }
        lteRanKpiDataType.setInitialErabEstabSuccessRate(calcInitialErabEstabSuccessRateSoap4(kpiRows));
        lteRanKpiDataType.setAddedErabEstabSuccessRate(calcAddedErabEstabSuccessRateSoap4(kpiRows));
        return lteRanKpiDataType;

    }

    @Lock(LockType.WRITE)
    public LTENetworkCoreKPIDataType calculateCoreKpisSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        final LTENetworkCoreKPIDataType lteCoreKpiDataType = new LTENetworkCoreKPIDataType(0.0, 0.0, 0.0, 0.0, 0.0);
        if (kpiRows.isEmpty()) {
            return lteCoreKpiDataType;
        }
        lteCoreKpiDataType.setAttachFailureRatio(calcCoreAttchFailureRatioSoap3(kpiRows));
        lteCoreKpiDataType.setServiceReqFailureRatio(calcCoreServiceReqFailureRatioSoap3(kpiRows));
        lteCoreKpiDataType.setPagingFailureRatio(calcCorePagingFailureRatioSoap3(kpiRows));
        lteCoreKpiDataType.setSupportedSubscribers(calcCoreSupportedSubscribersSoap3(kpiRows));
        lteCoreKpiDataType.setAttachedUsers(calcCoreAttachedUsersSoap3(kpiRows));
        return lteCoreKpiDataType;

    }

    @Lock(LockType.WRITE)
    public LTENetworkCoreKPIDataType calculateCoreKpisSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        final LTENetworkCoreKPIDataType lteCoreKpiDataType = new LTENetworkCoreKPIDataType(0.0, 0.0, 0.0, 0.0, 0.0);
        if (kpiRows.isEmpty()) {
            return lteCoreKpiDataType;
        }
        lteCoreKpiDataType.setAttachFailureRatio(calcCoreAttchFailureRatioSoap4(kpiRows));
        lteCoreKpiDataType.setServiceReqFailureRatio(calcCoreServiceReqFailureRatioSoap4(kpiRows));
        lteCoreKpiDataType.setPagingFailureRatio(calcCorePagingFailureRatioSoap4(kpiRows));
        lteCoreKpiDataType.setSupportedSubscribers(calcCoreSupportedSubscribersSoap4(kpiRows));
        lteCoreKpiDataType.setAttachedUsers(calcCoreAttachedUsersSoap4(kpiRows));
        return lteCoreKpiDataType;

    }

    private Double calcInitialErabEstabSuccessRateSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows) {
        Double initialErabEstabSuccessRate = 0.0;
        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row row : kpiRows) {
            initialErabEstabSuccessRate += calcInitialErabEstabSuccessRateSoap3(row);
        }
        //Take the average -> all rows KPI added and divided by no of rows
        initialErabEstabSuccessRate = initialErabEstabSuccessRate / kpiRows.size();
        return initialErabEstabSuccessRate;
    }

    private Double calcInitialErabEstabSuccessRateSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows) {
        Double initialErabEstabSuccessRate = 0.0;
        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row row : kpiRows) {
            initialErabEstabSuccessRate += calcInitialErabEstabSuccessRateSoap4(row);
        }
        //Take the average -> all rows KPI added and divided by no of rows
        initialErabEstabSuccessRate = initialErabEstabSuccessRate / kpiRows.size();
        return initialErabEstabSuccessRate;
    }

    private Double calcAddedErabEstabSuccessRateSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows) {
        double pmErabEstabSuccAddedCount = 0;
        double pmErabEstabAttAddedCount = 0;
        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row row : kpiRows) {
            pmErabEstabSuccAddedCount += getDoubleValue(row.getPmErabEstabSuccAdded());
            pmErabEstabAttAddedCount += getDoubleValue(row.getPmErabEstabAttAdded());
        }
        return 100 * (pmErabEstabSuccAddedCount / pmErabEstabAttAddedCount);
    }

    private Double calcAddedErabEstabSuccessRateSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row> kpiRows) {
        double pmErabEstabSuccAddedCount = 0;
        double pmErabEstabAttAddedCount = 0;
        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row row : kpiRows) {
            pmErabEstabSuccAddedCount += getDoubleValue(row.getPmErabEstabSuccAdded());
            pmErabEstabAttAddedCount += getDoubleValue(row.getPmErabEstabAttAdded());
        }
        return 100 * (pmErabEstabSuccAddedCount / pmErabEstabAttAddedCount);
    }

    private Double calcInitialErabEstabSuccessRateSoap3(final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.ran.EniqEventsLTERanKpi.Row kpiRow) {
        String neVersion = kpiRow.getNEVersion();
        if (neVersion == null) {
            neVersion = "";
        }
        if (neVersion.compareTo(L11B) < 0) {
            return 100 * (getDoubleValue(kpiRow.getPmRrcConnEstabSucc()) / getDoubleValue(kpiRow.getPmRrcConnEstabAtt()))
                    * (getDoubleValue(kpiRow.getPmS1SigConnEstabSucc()) / getDoubleValue(kpiRow.getPmS1SigConnEstabAtt()))
                    * (getDoubleValue(kpiRow.getPmErabEstabSuccInit()) / getDoubleValue(kpiRow.getPmErabEstabAttInit()));
        } else if (neVersion.equals(L11B)) {
            return 100
                    * (getDoubleValue(kpiRow.getPmRrcConnEstabSucc()) / (getDoubleValue(kpiRow.getPmRrcConnEstabAtt()) - getDoubleValue(kpiRow
                            .getPmZtemporary9())))
                    * (getDoubleValue(kpiRow.getPmS1SigConnEstabSucc()) / getDoubleValue(kpiRow.getPmS1SigConnEstabAtt()))
                    * (getDoubleValue(kpiRow.getPmErabEstabSuccInit()) / getDoubleValue(kpiRow.getPmErabEstabAttInit()));
        } else {
            return 100
                    * (getDoubleValue(kpiRow.getPmRrcConnEstabSucc()) / (getDoubleValue(kpiRow.getPmRrcConnEstabAtt()) - getDoubleValue(kpiRow
                            .getPmRrcConnEstabAttReatt())))
                    * (getDoubleValue(kpiRow.getPmS1SigConnEstabSucc()) / (getDoubleValue(kpiRow.getPmS1SigConnEstabAtt())))
                    * (getDoubleValue(kpiRow.getPmErabEstabSuccInit()) / getDoubleValue(kpiRow.getPmErabEstabAttInit()));
        }

    }

    private Double calcInitialErabEstabSuccessRateSoap4(final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.ran.EniqEventsLTERanKpi.Row kpiRow) {
        String neVersion = kpiRow.getNEVersion();
        if (neVersion == null) {
            neVersion = "";
        }
        if (neVersion.compareTo(L11B) < 0) {
            return 100 * (getDoubleValue(kpiRow.getPmRrcConnEstabSucc()) / getDoubleValue(kpiRow.getPmRrcConnEstabAtt()))
                    * (getDoubleValue(kpiRow.getPmS1SigConnEstabSucc()) / getDoubleValue(kpiRow.getPmS1SigConnEstabAtt()))
                    * (getDoubleValue(kpiRow.getPmErabEstabSuccInit()) / getDoubleValue(kpiRow.getPmErabEstabAttInit()));
        } else if (neVersion.equals(L11B)) {
            return 100
                    * (getDoubleValue(kpiRow.getPmRrcConnEstabSucc()) / (getDoubleValue(kpiRow.getPmRrcConnEstabAtt()) - getDoubleValue(kpiRow
                            .getPmZtemporary9())))
                    * (getDoubleValue(kpiRow.getPmS1SigConnEstabSucc()) / getDoubleValue(kpiRow.getPmS1SigConnEstabAtt()))
                    * (getDoubleValue(kpiRow.getPmErabEstabSuccInit()) / getDoubleValue(kpiRow.getPmErabEstabAttInit()));
        } else {
            return 100
                    * (getDoubleValue(kpiRow.getPmRrcConnEstabSucc()) / (getDoubleValue(kpiRow.getPmRrcConnEstabAtt()) - getDoubleValue(kpiRow
                            .getPmRrcConnEstabAttReatt())))
                    * (getDoubleValue(kpiRow.getPmS1SigConnEstabSucc()) / (getDoubleValue(kpiRow.getPmS1SigConnEstabAtt())))
                    * (getDoubleValue(kpiRow.getPmErabEstabSuccInit()) / getDoubleValue(kpiRow.getPmErabEstabAttInit()));
        }

    }

    private Double calcCoreAttchFailureRatioSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        Double coreAttchFailureRatio = 0.0;
        double vSMMUnsuccAttachECount = 0;
        double vSMMEpsAttachFail7ECount = 0;
        double vSMMEpsAttachFail8ECount = 0;
        double vSMMEpsAttachFail11ECount = 0;
        double vSMMEpsAttachFail12ECount = 0;
        double vSMMEpsAttachFail13ECount = 0;
        double vSMMEpsAttachFail14ECount = 0;
        double vSMMEpsAttachFail15ECount = 0;
        double vSMMUnsuccAttachCC2728ECount = 0;
        double vSMMUnsuccAttachCC3233ECount = 0;
        double vSMMAttAttachECount = 0;

        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMUnsuccAttachECount += getDoubleValue(row.getVSMMUnsuccAttachE());
            vSMMEpsAttachFail7ECount += getDoubleValue(row.getVSMMEpsAttachFail7E());
            vSMMEpsAttachFail8ECount += getDoubleValue(row.getVSMMEpsAttachFail8E());
            vSMMEpsAttachFail11ECount += getDoubleValue(row.getVSMMEpsAttachFail11E());
            vSMMEpsAttachFail12ECount += getDoubleValue(row.getVSMMEpsAttachFail12E());
            vSMMEpsAttachFail13ECount += getDoubleValue(row.getVSMMEpsAttachFail13E());
            vSMMEpsAttachFail14ECount += getDoubleValue(row.getVSMMEpsAttachFail14E());
            vSMMEpsAttachFail15ECount += getDoubleValue(row.getVSMMEpsAttachFail15E());
            vSMMUnsuccAttachCC2728ECount += getDoubleValue(row.getVSMMUnsuccAttachCC2728E());
            vSMMUnsuccAttachCC3233ECount += getDoubleValue(row.getVSMMUnsuccAttachCC3233E());
            vSMMAttAttachECount += getDoubleValue(row.getVSMMAttAttachE());
        }
        coreAttchFailureRatio = 100 * ((vSMMUnsuccAttachECount - vSMMEpsAttachFail7ECount - vSMMEpsAttachFail8ECount - vSMMEpsAttachFail11ECount
                - vSMMEpsAttachFail12ECount - vSMMEpsAttachFail13ECount - vSMMEpsAttachFail14ECount - vSMMEpsAttachFail15ECount
                - vSMMUnsuccAttachCC2728ECount - vSMMUnsuccAttachCC3233ECount) / vSMMAttAttachECount);
        return coreAttchFailureRatio;
    }

    private Double calcCoreAttchFailureRatioSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        Double coreAttchFailureRatio = 0.0;
        double vSMMUnsuccAttachECount = 0;
        double vSMMEpsAttachFail7ECount = 0;
        double vSMMEpsAttachFail8ECount = 0;
        double vSMMEpsAttachFail11ECount = 0;
        double vSMMEpsAttachFail12ECount = 0;
        double vSMMEpsAttachFail13ECount = 0;
        double vSMMEpsAttachFail14ECount = 0;
        double vSMMEpsAttachFail15ECount = 0;
        double vSMMUnsuccAttachCC2728ECount = 0;
        double vSMMUnsuccAttachCC3233ECount = 0;
        double vSMMAttAttachECount = 0;

        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMUnsuccAttachECount += getDoubleValue(row.getVSMMUnsuccAttachE());
            vSMMEpsAttachFail7ECount += getDoubleValue(row.getVSMMEpsAttachFail7E());
            vSMMEpsAttachFail8ECount += getDoubleValue(row.getVSMMEpsAttachFail8E());
            vSMMEpsAttachFail11ECount += getDoubleValue(row.getVSMMEpsAttachFail11E());
            vSMMEpsAttachFail12ECount += getDoubleValue(row.getVSMMEpsAttachFail12E());
            vSMMEpsAttachFail13ECount += getDoubleValue(row.getVSMMEpsAttachFail13E());
            vSMMEpsAttachFail14ECount += getDoubleValue(row.getVSMMEpsAttachFail14E());
            vSMMEpsAttachFail15ECount += getDoubleValue(row.getVSMMEpsAttachFail15E());
            vSMMUnsuccAttachCC2728ECount += getDoubleValue(row.getVSMMUnsuccAttachCC2728E());
            vSMMUnsuccAttachCC3233ECount += getDoubleValue(row.getVSMMUnsuccAttachCC3233E());
            vSMMAttAttachECount += getDoubleValue(row.getVSMMAttAttachE());
        }
        coreAttchFailureRatio = 100 * ((vSMMUnsuccAttachECount - vSMMEpsAttachFail7ECount - vSMMEpsAttachFail8ECount - vSMMEpsAttachFail11ECount
                - vSMMEpsAttachFail12ECount - vSMMEpsAttachFail13ECount - vSMMEpsAttachFail14ECount - vSMMEpsAttachFail15ECount
                - vSMMUnsuccAttachCC2728ECount - vSMMUnsuccAttachCC3233ECount) / vSMMAttAttachECount);
        return coreAttchFailureRatio;
    }

    private Double calcCoreServiceReqFailureRatioSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMUnsuccServiceReqECount = 0;
        double vSMMAttServiceRequestECount = 0;

        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMUnsuccServiceReqECount += getDoubleValue(row.getVSMMUnsuccServiceReqE());
            vSMMAttServiceRequestECount += getDoubleValue(row.getVSMMAttServiceRequestE());
        }
        return 100 * (vSMMUnsuccServiceReqECount / vSMMAttServiceRequestECount);
    }

    private Double calcCoreServiceReqFailureRatioSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMUnsuccServiceReqECount = 0;
        double vSMMAttServiceRequestECount = 0;

        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMUnsuccServiceReqECount += getDoubleValue(row.getVSMMUnsuccServiceReqE());
            vSMMAttServiceRequestECount += getDoubleValue(row.getVSMMAttServiceRequestE());
        }
        return 100 * (vSMMUnsuccServiceReqECount / vSMMAttServiceRequestECount);
    }

    private Double calcCorePagingFailureRatioSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMAttPsPagingECount = 0;
        double vSMMSuccPsPagingECount = 0;

        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMAttPsPagingECount += getDoubleValue(row.getVSMMAttPsPagingE());
            vSMMSuccPsPagingECount += getDoubleValue(row.getVSMMSuccPsPagingE());
        }
        return 100 * ((vSMMAttPsPagingECount - vSMMSuccPsPagingECount) / vSMMAttPsPagingECount);
    }

    private Double calcCorePagingFailureRatioSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMAttPsPagingECount = 0;
        double vSMMSuccPsPagingECount = 0;

        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMAttPsPagingECount += getDoubleValue(row.getVSMMAttPsPagingE());
            vSMMSuccPsPagingECount += getDoubleValue(row.getVSMMSuccPsPagingE());
        }
        return 100 * ((vSMMAttPsPagingECount - vSMMSuccPsPagingECount) / vSMMAttPsPagingECount);
    }

    private Double calcCoreSupportedSubscribersSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMNbrHomeSubESumCount = 0;
        double vSMMNbrVisitingForeignESumCount = 0;
        double vSMMNbrVisitingNatSubESumCount = 0;

        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMNbrHomeSubESumCount += getDoubleValue(row.getVSMMNbrHomeSubESum());
            vSMMNbrVisitingForeignESumCount += getDoubleValue(row.getVSMMNbrVisitingForeignESum());
            vSMMNbrVisitingNatSubESumCount += getDoubleValue(row.getVSMMNbrVisitingNatSubESum());
        }

        return (vSMMNbrHomeSubESumCount + vSMMNbrVisitingForeignESumCount + vSMMNbrVisitingNatSubESumCount);
    }

    private Double calcCoreSupportedSubscribersSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMNbrHomeSubESumCount = 0;
        double vSMMNbrVisitingForeignESumCount = 0;
        double vSMMNbrVisitingNatSubESumCount = 0;

        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMNbrHomeSubESumCount += getDoubleValue(row.getVSMMNbrHomeSubESum());
            vSMMNbrVisitingForeignESumCount += getDoubleValue(row.getVSMMNbrVisitingForeignESum());
            vSMMNbrVisitingNatSubESumCount += getDoubleValue(row.getVSMMNbrVisitingNatSubESum());
        }

        return (vSMMNbrHomeSubESumCount + vSMMNbrVisitingForeignESumCount + vSMMNbrVisitingNatSubESumCount);
    }

    private Double calcCoreAttachedUsersSoap3(final List<com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMNbrActAttachedSubESumCount = 0;
        for (final com.ericsson.eniq.events.server.services.soap3.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMNbrActAttachedSubESumCount += getDoubleValue(row.getVSMMNbrActAttachedSubESum());
        }
        return vSMMNbrActAttachedSubESumCount;
    }

    private Double calcCoreAttachedUsersSoap4(final List<com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row> kpiRows) {
        double vSMMNbrActAttachedSubESumCount = 0;
        for (final com.ericsson.eniq.events.server.services.soap4.network.kpi.lte.core.EniqEventsLTECoreKPI.Row row : kpiRows) {
            vSMMNbrActAttachedSubESumCount += getDoubleValue(row.getVSMMNbrActAttachedSubESum());
        }
        return vSMMNbrActAttachedSubESumCount;
    }

    public KPIDataType getLteNetworkRanKpiData(final LTENetworkRanKPIDataType lteNetworkRanKPIDataType) {
        final KPIDataType kpiDataType = new KPIDataType(true, EMPTY_STRING);
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", decimalFormat.format(lteNetworkRanKPIDataType.getInitialErabEstabSuccessRate()));
        map.put("2", decimalFormat.format(lteNetworkRanKPIDataType.getAddedErabEstabSuccessRate()));
        kpiDataType.getData().add(map);
        return kpiDataType;
    }

    public KPIDataType getLteNetworkCoreKpiData(final LTENetworkCoreKPIDataType lteNetworkCoreKPIDataType) {
        final KPIDataType kpiDataType = new KPIDataType(true, EMPTY_STRING);
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", decimalFormat.format(lteNetworkCoreKPIDataType.getSupportedSubscribers()));
        map.put("2", decimalFormat.format(lteNetworkCoreKPIDataType.getAttachedUsers()));
        map.put("3", decimalFormat.format(lteNetworkCoreKPIDataType.getAttachFailureRatio()));
        map.put("4", decimalFormat.format(lteNetworkCoreKPIDataType.getServiceReqFailureRatio()));
        map.put("5", decimalFormat.format(lteNetworkCoreKPIDataType.getPagingFailureRatio()));

        kpiDataType.getData().add(map);
        return kpiDataType;
    }

    private double getDoubleValue(final Double value) {
        if (value == null) {
            return 0;
        }
        return value.doubleValue();
    }
}
