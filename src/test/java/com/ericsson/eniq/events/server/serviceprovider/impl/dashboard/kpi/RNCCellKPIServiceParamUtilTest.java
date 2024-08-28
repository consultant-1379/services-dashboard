package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Desrible RNCCellKPIServiceParamUtilTest
 *
 * @author ezhelao
 * @since 11/2011
 */
@ContextConfiguration(locations = {
        "classpath:com/ericsson/eniq/events/server/resources/BaseServiceIntegrationTest-context.xml",
        "classpath:dashboard-service-context.xml"})
public class RNCCellKPIServiceParamUtilTest extends BaseJMockUnitTest {


    TestContextManager testContextManager;

    @Resource
    RNCCellKPIServiceParamUtil rncCellKPIServiceParamUtil;


    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    @Test
    public void testconvertCommaSeparatedStringToList() {
        String commaSeparatedString = "RNC1,RNC2,RNC3";
        List<String> result = rncCellKPIServiceParamUtil.convertCommaSeparatedStringToList(commaSeparatedString);
        assertEquals("RNC2", result.get(1));
        assertEquals("RNC3", result.get(2));
    }

    @Test
    public void testconvertCommaSeparatedStringToList_RNCLongIDs() {

        String commaSeparatedString = "8389478750768551654:2571769785375881194:3307896086522623269:RNC04-11-1:ONRM_ROOT_MO_R:RNC04:RNCID04:1:ERICSSON,8389478750768551654:2571769785375881194:3307896086522623269:RNC04-11-2:ONRM_ROOT_MO_R:RNC04:RNCID05:1:ERICSSON,8389478750768551654:2571769785375881194:3307896086522623269:RNC04-11-3:ONRM_ROOT_MO_R:RNC04:RNCID06:1:ERICSSON";
        List<String> result = rncCellKPIServiceParamUtil.convertCommaSeparatedStringToList(commaSeparatedString);
        assertEquals("RNC04-11-1", result.get(0));
        assertEquals("RNC04-11-3", result.get(2));
    }

    @Test
    public void testconvertCommaSeparatedStringToList_CellLongIDs() {
        String commaSeparatedString = "4470351485507678406:ONRM_RootMo_R:RNC02:RNC01ID:1:ERICSSON,4470351485507678406:ONRM_RootMo_R:RNC02:RNC02ID:1:ERICSSON,4470351485507678406:ONRM_RootMo_R:RNC02:RNC03ID:1:ERICSSON";
        List<String> result = rncCellKPIServiceParamUtil.convertCommaSeparatedStringToList(commaSeparatedString);
        assertEquals("RNC02ID", result.get(1));
        assertEquals("RNC03ID", result.get(2));
    }

    @Test
    public void extractCellIDFromURLParam_longIDFromGroup() {
        String longId = "8389478750768551654:2571769785375881194:3307896086522623269:RNC04-11-3:ONRM_ROOT_MO_R:RNC04:RNC04:1:ERICSSON";
        List<String> result = rncCellKPIServiceParamUtil.extractCellIDFromURLParam(longId);
        assertEquals("RNC04-11-3", result.get(0));

    }

    @Test
    public void extractCellIDFromURLParam_longID() {
        String longId = "RNC01-00-1,,ONRM_RootMo_R:RNC01:RNC01,Ericsson,3G";
        List<String> result = rncCellKPIServiceParamUtil.extractCellIDFromURLParam(longId);
        assertEquals("RNC01-00-1", result.get(0));

    }

    @Test
    public void extractCellIDFromURLParam_shortID() {
        String longId = "RNC01-00-1";
        List<String> result = rncCellKPIServiceParamUtil.extractCellIDFromURLParam(longId);
        assertEquals("RNC01-00-1", result.get(0));

    }

    @Test
    public void extractRNCIDFromURLParam_longID() {
        String longRNCID = "ONRM_RootMo_R:RNC02:RNC1ID,ERICSSON,3G";
        List<String> result = rncCellKPIServiceParamUtil.extractRNCIDFromURLParam(longRNCID);
        assertEquals("RNC1ID", result.get(0));
    }

    @Test
    public void extractRNCIDFromURLParam_shortID() {
        String longRNCID = "RNC1ID";
        List<String> result = rncCellKPIServiceParamUtil.extractRNCIDFromURLParam(longRNCID);
        assertEquals("RNC1ID", result.get(0));
    }


    @Test
    public void testgetKPIDataTypeFromReturnedDataDoubleNaN() {
        NodeQueryKPIDataType day = new NodeQueryKPIDataType(120.0, 3000);
        NodeQueryKPIDataType previousDay = new NodeQueryKPIDataType(Double.NaN, 1000);
        KPIDataType data = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(day, previousDay);
        assertEquals("120", data.getData().get(0).get("1"));
        assertEquals("200", data.getData().get(0).get("2"));
        assertEquals("3000", data.getData().get(0).get("3"));
        assertEquals("GT", data.getData().get(0).get("4"));
    }


    @Test
    public void testgetKPIDataTypeFromReturnedDataEmptyDataReturned() {
        NodeQueryKPIDataType day = new NodeQueryKPIDataType(Double.NaN, 0);
        NodeQueryKPIDataType previousDay = new NodeQueryKPIDataType(Double.NaN, 0);
        KPIDataType data = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(day, previousDay);
        assertEquals(0, data.getData().size());
    }

    @Test
    public void testgetKPIDataTypeFromReturnedDataChangeRate() {
        NodeQueryKPIDataType day = new NodeQueryKPIDataType(12.0, 0);
        NodeQueryKPIDataType previousDay = new NodeQueryKPIDataType(13.0, 0);
        KPIDataType data = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(day, previousDay);
        assertEquals("12", data.getData().get(0).get("1"));
        assertEquals("0", data.getData().get(0).get("2"));
    }

    @Test
    public void testgetKPIDataTypeFromReturnedDataChangeRate1() {
        NodeQueryKPIDataType day = new NodeQueryKPIDataType(13.0, 100);
        NodeQueryKPIDataType previousDay = new NodeQueryKPIDataType(10.0, 0);
        KPIDataType data = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(day, previousDay);
        assertEquals("NaN", data.getData().get(0).get("2"));
    }

}
