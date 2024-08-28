/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.text.DecimalFormat;
import java.util.*;

import javax.ejb.*;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.*;

/**
 * Desrible RNCCellKPIServiceParamUtil This is a utility class that convert different kinds of format to the format we need to pass to BIS.
 * 
 * @author ezhelao
 * @since 11/2011
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class RNCCellKPIServiceParamUtil {

    public static final String WRONG_URL_FORMAT_ERROR_MSG = "Wrong URL format";

    public static final String OBJECT_MAPPER_ERROR_MSG = "Failed to convert object to JSON String";

    public static final String UNKOWN_ERROR_MSG = "Unknown error";

    public static final String DATETIME_CONVERSION_ERROR_MSG = "Datetime conversion failed";

    public static final String DECIMAL_FORMAT_STRING = "#.##";

    DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_STRING);

    /**
     * @param longID a long format cell (access area) od
     * @return a list of string with exactly one element.The string is the cell ID in short format.
     */
    public List<String> extractCellIDFromURLParam(final String longID) {
        final List<String> cellId = new ArrayList<String>();
        if (longID.contains(COMMA)) // its from the UI
        {
            final String[] splitedString = longID.split(COMMA);
            if (splitedString.length > 0) {
                cellId.add(splitedString[0]);
                return cellId;
            }
            throw new InvalidParameterError();
        }
        if (longID.contains(COLON)) {
            final String[] splitedString = longID.split(COLON);
            if ((splitedString.length > 0) && (splitedString.length == 9)) {
                cellId.clear();
                cellId.add(splitedString[3]);
                return cellId;

            }
        } else {
            cellId.clear();
            cellId.add(longID);
            return cellId;
        }
        throw new InvalidParameterError();
    }

    /**
     * @param longID a long format Controller ID
     * @return a List of String that contains only one element.That element is a short format RNC ID
     */
    public List<String> extractRNCIDFromURLParam(final String longID) {
        final List<String> rncID = new ArrayList<String>();

        if (longID.contains(COMMA) || longID.contains(COLON)) {
            final String[] splitedString = longID.split("[,:]");
            if (splitedString.length > 2) {
                rncID.clear();
                rncID.add(splitedString[2]);
            } else {
                throw new InvalidParameterError();
            }

        } else {
            rncID.clear();
            rncID.add(longID);
        }
        return rncID;

    }

    /**
     * @param commaSeparatedList a String of comma separated IDs ,the id could be long format or short format.
     * @return a list of id String. all id is in short format.
     */
    public List<String> convertCommaSeparatedStringToList(final String commaSeparatedList) {
        final List<String> returnedParams = new ArrayList<String>();
        try {
            final StringTokenizer tokenizer1 = new StringTokenizer(commaSeparatedList, COMMA);
            while (tokenizer1.hasMoreElements()) {
                final String param = tokenizer1.nextToken();
                final String[] longParam = param.split(COLON);

                switch (longParam.length) {
                    case 1:
                        returnedParams.add(longParam[0]);
                        break;
                    case 9:
                        returnedParams.add(longParam[3]);
                        break;
                    case 6:
                        returnedParams.add(longParam[3]);
                        break;
                    default:
                        //do nothing
                        break;
                }
            }
        } catch (final Exception ex) { // catch any runtime exception
            ServicesLogger.warn(this.getClass().getName(), "convertCommaSeparatedStringToList", "invalid param format for CELL or RNC ",
                    ex.getMessage());
        }
        return returnedParams;

    }

    /**
     * @param dataTypes a list of NodeQueryKPIDrillDownDataType.
     * @return KPIDataType that will be converted to JSON String.
     */
    @Lock(LockType.READ)
    public KPIDataType getKPIDataTypeFromReturnedData(final List<NodeQueryKPIDrillDownDataType> dataTypes) {

        final KPIDataType kpiDataType = new KPIDataType(true, EMPTY_STRING);
        for (final NodeQueryKPIDrillDownDataType dataType : dataTypes) {
            final Map<String, String> map = createMap(dataType);

            kpiDataType.getData().add(map);
        }
        return kpiDataType;
    }

    /**
     * @param dataType
     * @return
     */
    private Map<String, String> createMap(final NodeQueryKPIDrillDownDataType dataType) {
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", dataType.getCellName());
        map.put("2", dataType.getRncName());
        map.put("3", dataType.getReqSucc().toString());
        map.put("4", dataType.getRequestTotal().toString());
        map.put("5", decimalFormat.format(dataType.getSuccRate()));
        return map;
    }

    /**
     * @param day the data of current day.
     * @param previousDay the data of present ay
     * @return a KPIDataType object that contain the changes rate to the previous day.It also contains the information of the current day.
     */
    public KPIDataType getKPIDataTypeFromReturnedData(final NodeQueryKPIDataType day, final NodeQueryKPIDataType previousDay) {
        final HashMap<String, String> map = new HashMap<String, String>();
        final KPIDataType data = new KPIDataType();

        final Double doubleNumber = day.getSuccRate();

        if (doubleNumber.isNaN()) {
            return data;
        }

        final int requestTotalChange = day.getRequestTotal() - previousDay.getRequestTotal();

        if (previousDay.getRequestTotal() == 0) {
            if (day.getRequestTotal() == 0) {
                map.put("4", "EQ");
                map.put("2", "0");
            } else {
                map.put("2", "NaN");
                map.put("4", "GT");
            }
        } else {
            final Double requestTotalChangeRate = (100 * (double) requestTotalChange) / (double) previousDay.getRequestTotal();
            map.put("2", decimalFormat.format(requestTotalChangeRate));
            if (requestTotalChange > 0) {
                map.put("4", "GT");
            } else if (requestTotalChange < 0) {
                map.put("4", "LT");
            } else {
                map.put("4", "EQ");
            }
        }

        map.put("1", decimalFormat.format(doubleNumber));

        final Integer intNumber = day.getRequestTotal();
        map.put("3", intNumber.toString());
        data.getData().add(map);
        return data;
    }

}

class InvalidParameterError extends RuntimeException {
    private static final String INVALID_PARAM_ERROR_MSG = "the value of param is invalid";

    public InvalidParameterError() {
        super(INVALID_PARAM_ERROR_MSG);
    }

}
