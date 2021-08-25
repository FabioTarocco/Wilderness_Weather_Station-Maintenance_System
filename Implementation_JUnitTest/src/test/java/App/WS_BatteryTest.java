package App;

import static org.junit.Assert.*;
import org.junit.Test;

public class WS_BatteryTest {

    private WeatherStation ws;
    private WS_Battery battery, empty_battery;
    private WS_Sensor sensor;
    private double fr_b1, fr_b2, fr_b3;
    public void setup(){
        ws = new WeatherStation("Italia", "Verona",100.00);
        ws.initialize();
        sensor = ws.getSensor();
        battery = ws.getBattery();
    }

    @Test
    public void testEmptyConstructor(){
        empty_battery = new WS_Battery();
        assertNull("Id should be null",empty_battery.getId());
        assertNull("Owner should be null", empty_battery.getOwner());
        assertFalse(empty_battery.getB_1_status());
        assertFalse(empty_battery.getB_2_status());
        assertFalse(empty_battery.getB_3_status());

    }

    @Test
    public void testBatteryConstructorOwner(){
        setup();
        assertEquals("Owner and WeatherStation should be the same",battery.getOwner(),ws);
        assertEquals("WS_sensor and WeatherStation.sensor should be the same",battery,ws.getBattery());
    }

    @Test
    public void testBatteryConstructorWithInitialize(){
        setup();
        assertTrue(battery.getB_1_status());
        assertTrue(battery.getB_2_status());
        assertTrue(battery.getB_3_status());
        assertEquals(0, battery.getIn_use_battery());
        assertEquals(ColorCode.GREEN, battery.getBatteryStatus());
    }


    @Test
    public void testGetSetBatteryStatus(){
        setup();
        battery.setB_1_status(true);
        battery.setB_2_status(true);
        battery.setB_3_status(true);
        assertEquals(true, battery.getB_1_status());
        assertEquals(true, battery.getB_2_status());
        assertEquals(true, battery.getB_3_status());
    }

    @Test
    public void testGetInUseBattery_Ordinal(){
        //This should add 1 to the in_use_battery, used in detail_view to show which battery the ws is using
        //And it's implemented for showing the right "name" of the battery: battery_1, battery_2, battery_3
        setup();
        assertEquals(1, battery.getIn_use_battery_ordinal());
        battery.setIn_use_battery(1);
        assertEquals(2, battery.getIn_use_battery_ordinal());
        battery.setIn_use_battery(2);
        assertEquals(3, battery.getIn_use_battery_ordinal());
        battery.setIn_use_battery(3);
        assertEquals(4,battery.getIn_use_battery_ordinal());
    }

    @Test
    public void testBatteriesFailureRatesDefault(){
        setup();
        assertEquals(0.985, battery.getFailure_rate_b1(),0.01);
        assertEquals(0.985, battery.getFailure_rate_b2(),0.01);
        assertEquals(0.75, battery.getFailure_rate_b3(),0.01);

    }

    @Test
    public void testBatteriesFailureRatesSetToDefault(){
        setup();
        battery.setFailureRates(false,0,0,0);
        assertEquals(0.985, battery.getFailure_rate_b1(),0.01);
        assertEquals(0.985, battery.getFailure_rate_b2(),0.01);
        assertEquals(0.75, battery.getFailure_rate_b3(),0.01);

    }

    @Test
    public void testBatteriesFailureRatesCustomValues(){
        setup();
        fr_b1=0;
        fr_b2=0;
        fr_b3=0;
        battery.setFailureRates(true,0,0,0);
        assertEquals(fr_b1, battery.getFailure_rate_b1(),0.01);
        assertEquals(fr_b2, battery.getFailure_rate_b2(),0.01);
        assertEquals(fr_b3, battery.getFailure_rate_b3(),0.01);

    }

    @Test
    public void testMockAllTrue(){
        setup();
        battery.setFailureRates(true, 1,1,1);
        battery.ws_mock_battery();
        assertTrue(battery.getB_1_status());
        assertTrue(battery.getB_2_status());
        assertTrue(battery.getB_3_status());
        assertEquals(0,battery.getIn_use_battery());

    }


    @Test
    public void testMockFailure1(){
        setup();
        battery.setFailureRates(true, 0,1,1);
        battery.ws_mock_battery();
        assertFalse(battery.getB_1_status());
        assertTrue(battery.getB_2_status());
        assertTrue(battery.getB_3_status());
        assertEquals(0,battery.getIn_use_battery());

    }

    @Test
    public void testMockFailure2(){
        setup();
        battery.setFailureRates(true, 0,0,1);
        battery.setB_1_status(false);
        battery.setIn_use_battery(1);
        battery.ws_mock_battery();
        assertFalse(battery.getB_1_status());
        assertFalse(battery.getB_2_status());
        assertTrue(battery.getB_3_status());

    }

    @Test
    public void testMockFailure3(){
        setup();
        battery.setFailureRates(true, 0,0,0);
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setIn_use_battery(2);
        battery.ws_mock_battery();
        assertFalse(battery.getB_1_status());
        assertFalse(battery.getB_2_status());
        assertFalse(battery.getB_3_status());

    }

    @Test
    public void testMockFailure_Other(){
        setup();
        battery.setIn_use_battery(3);
        battery.ws_mock_battery();
        assertFalse(battery.getB_1_status());
        assertFalse(battery.getB_2_status());
        assertFalse(battery.getB_3_status());
    }

    @Test
    public void testBatteryStatus_0(){
        setup();
        battery.ws_battery_status();
        assertEquals(0, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_0_to_1(){
        setup();
        battery.setB_1_status(false);
        battery.ws_battery_status();
        assertEquals(1, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_1(){
        setup();
        battery.setB_1_status(false);
        battery.setIn_use_battery(1);
        battery.ws_battery_status();
        assertEquals(1, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_1_to_2(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setIn_use_battery(1);
        battery.ws_battery_status();
        assertEquals(2, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_2(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setIn_use_battery(2);
        battery.ws_battery_status();
        assertEquals(2, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_2_to_3(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setB_3_status(false);
        battery.setIn_use_battery(2);
        battery.ws_battery_status();
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_3(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setB_3_status(false);
        battery.setIn_use_battery(3);
        battery.ws_battery_status();
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_3_Others_TFT(){
        setup();
        battery.setB_2_status(false);
        battery.ws_battery_status();
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_3_Others_TFF(){
        setup();
        battery.setB_2_status(false);
        battery.setB_3_status(false);
        battery.ws_battery_status();
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_3_Others_FTF(){
        setup();
        battery.setB_1_status(false);
        battery.setB_3_status(false);
        battery.ws_battery_status();
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testBatteryStatus_3_Others_TTF(){
        setup();
        battery.setB_3_status(false);
        battery.ws_battery_status();
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testPrintBatteryStatus_OK_OK_OK(){
        setup();
        battery.setB_1_status(true);
        battery.setB_2_status(true);
        battery.setB_3_status(true);
        assertEquals("[ OK ][ OK ][ OK ]", battery.printBatteryStatus());
    }


    @Test
    public void testPrintBatteryStatus_FAIL_OK_OK(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(true);
        battery.setB_3_status(true);
        assertEquals("[ FAIL ][ OK ][ OK ]", battery.printBatteryStatus());
    }

    @Test
    public void testPrintBatteryStatus_FAIL_FAIL_OK(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setB_3_status(true);
        assertEquals("[ FAIL ][ FAIL ][ OK ]", battery.printBatteryStatus());
    }
    @Test
    public void testPrintBatteryStatus_FAIL_FAIL_FAIL(){
        setup();
        battery.setB_1_status(false);
        battery.setB_2_status(false);
        battery.setB_3_status(false);
        assertEquals("[ FAIL ][ FAIL ][ FAIL ]", battery.printBatteryStatus());
    }

    @Test
    public void testSetBatteryFailure(){
        setup();
        battery.setBatteryFailure();
        assertFalse(battery.getB_1_status());
        assertFalse(battery.getB_2_status());
        assertFalse(battery.getB_3_status());
    }

    @Test
    public void testSetBatteryRecoveryAllTrue(){
        setup();
        battery.setBatteryFailure();
        battery.setRecoveryAllTrue();
        assertTrue(battery.getB_1_status());
        assertTrue(battery.getB_2_status());
        assertTrue(battery.getB_3_status());
    }

    @Test
    public void testGetInUseBattery(){
        setup();
        battery.setIn_use_battery(0);
        assertEquals(0, battery.getIn_use_battery());
    }

    @Test
    public void testSet_In_use_Battery_SET_0(){
        setup();
        battery.setIn_use_battery(0);
        assertEquals(0, battery.getIn_use_battery());
    }

    @Test
    public void testSet_In_use_Battery_SET_1(){
        setup();
        battery.setIn_use_battery(1);
        assertEquals(1, battery.getIn_use_battery());
    }

    @Test
    public void testSet_In_use_Battery_SET_2(){
        setup();
        battery.setIn_use_battery(2);
        assertEquals(2, battery.getIn_use_battery());
    }

    @Test
    public void testSet_In_use_Battery_SET_3(){
        setup();
        battery.setIn_use_battery(3);
        assertEquals(3, battery.getIn_use_battery());
    }

    @Test
    public void testSet_In_use_Battery_SET_others(){
        setup();
        battery.setIn_use_battery(4);
        assertEquals(3, battery.getIn_use_battery());
        battery.setIn_use_battery(-1);
        assertEquals(3, battery.getIn_use_battery());

    }

    @Test
    public void testRepairBattery(){
        setup();
        battery.setBatteryFailure();
        battery.battery_new_infos();

        assertFalse(battery.getB_1_status());
        assertFalse(battery.getB_2_status());
        assertFalse(battery.getB_3_status());
        assertEquals(3,battery.getIn_use_battery());
        assertEquals(ColorCode.BLACK, battery.getBatteryStatus());

        battery.repairBattery();

        assertTrue(battery.getB_1_status());
        assertTrue(battery.getB_2_status());
        assertTrue(battery.getB_3_status());
        assertEquals(0,battery.getIn_use_battery());
        assertEquals(ColorCode.GREEN, battery.getBatteryStatus());

    }

}
