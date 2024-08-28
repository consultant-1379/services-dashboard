/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.resources.automation.dataproviders.dashboard;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.convertToArrayOfMultivaluedMap;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * @author ericker
 */
public class CoreNetworkKPIServiceDataProvider {
    public static Object[] provideTestDataForSGSN() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(DISPLAY_PARAM, CHART_PARAM).add(TIME_QUERY_PARAM, ONE_DAY)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(SEARCH_PARAM, TEST_VALUE_SGSN).add(TYPE_PARAM, TYPE_SGSN).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForAPN() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(DISPLAY_PARAM, CHART_PARAM).add(TIME_QUERY_PARAM, ONE_DAY)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(SEARCH_PARAM, TEST_VALUE_APN).add(TYPE_PARAM, TYPE_APN).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForSGSNGroup() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(DISPLAY_PARAM, CHART_PARAM).add(TIME_QUERY_PARAM, ONE_DAY)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(SEARCH_PARAM, TEST_VALUE_SGSN_GROUP).add(TYPE_PARAM, TYPE_SGSN).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForAPNGroup() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(DISPLAY_PARAM, CHART_PARAM).add(TIME_QUERY_PARAM, ONE_DAY)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(SEARCH_PARAM, TEST_VALUE_APN_GROUP).add(TYPE_PARAM, TYPE_APN).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
