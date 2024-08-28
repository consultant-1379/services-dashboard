package com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard.kpi;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.convertToArrayOfMultivaluedMap;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DEFAULT_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TIMEZONE_OFFSET;

/**
 * Desrible SummaryWCDMADrillDownServiceTestDataProvider
 *
 * @author ezhelao
 * @since 11/2011
 */
public class SummaryWCDMADrillDownServiceTestDataProvider {
    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(DISPLAY_PARAM, CHART_PARAM).add(TIME_FROM_QUERY_PARAM, "1200").add(DATE_FROM, "27112011")
                .add(TIME_TO_QUERY_PARAM, "1200").add(DATE_TO_QUERY_PARAM, "28122011")
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
