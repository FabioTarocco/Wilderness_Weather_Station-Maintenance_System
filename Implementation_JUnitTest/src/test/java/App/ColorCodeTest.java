package App;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorCodeTest {

    private ColorCode cc = ColorCode.TEST, bcc = ColorCode.TEST, scc = ColorCode.TEST;
    private WeatherStation ws;
    private WS_Battery battery;
    private WS_Sensor sensor;
    public void setup(){
        ws = new WeatherStation("Italia", "Verona",100.00);
        ws.initialize();
        sensor = ws.getSensor();
        battery = ws.getBattery();
    }
    @Test
    public void testGreenField(){
        assertEquals("GREEN", ColorCode.GREEN.toString());
    }

    @Test
    public void testYellowField(){
        assertEquals("YELLOW", ColorCode.YELLOW.toString());
    }

    @Test
    public void testOrangeField(){
        assertEquals("ORANGE", ColorCode.ORANGE.toString());
    }

    @Test
    public void testRedField(){
        assertEquals("RED", ColorCode.RED.toString());
    }

    @Test
    public void testBlackField(){
        assertEquals("BLACK", ColorCode.BLACK.toString());
    }

    @Test
    public void testGreyField(){
        assertEquals("GREY", ColorCode.GREY.toString());
    }

    @Test
    public void testStatusToColorCode_Sensor_BLACK(){
        cc=cc.statusToColorCode_Sensor(false,false,false,false,false,false);
        assertEquals("ColorCode with all sensor in failure should be BLACK", ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_1_T(){
        cc=cc.statusToColorCode_Sensor(true,false,false,false,false,false);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }


    @Test
    public void testStatusToColorCode_Sensor_YELLOW_2_T(){
        cc=cc.statusToColorCode_Sensor(false,true,false,false,false,false);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_3_T(){
        cc=cc.statusToColorCode_Sensor(false,false,true,false,false,false);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_4_T(){
        cc=cc.statusToColorCode_Sensor(false,false,false,true,false,false);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_5_T(){
        cc=cc.statusToColorCode_Sensor(false,false,false,false,true,false);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_6_T(){
        cc=cc.statusToColorCode_Sensor(false,false,false,false,false,true);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }


    @Test
    public void testStatusToColorCode_Sensor_YELLOW_1_F(){
        cc=cc.statusToColorCode_Sensor(false,true,true,true,true,true);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_2_F(){
        cc=cc.statusToColorCode_Sensor(true,false,true,true,true,true);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_3_F(){
        cc=cc.statusToColorCode_Sensor(true,true,false,true,true,true);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_4_F(){
        cc=cc.statusToColorCode_Sensor(true,true,true,false,true,true);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_5_F(){
        cc=cc.statusToColorCode_Sensor(true,true,true,true,false,true);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_YELLOW_6_F(){
        cc=cc.statusToColorCode_Sensor(true,true,true,true,true,false);
        assertEquals("ColorCode with all sensor in failure should be YELLOW", ColorCode.YELLOW, cc);
    }

    @Test
    public void testStatusToColorCode_Sensor_GREEN(){
        cc=cc.statusToColorCode_Sensor(true,true,true,true,true,true);
        assertEquals("ColorCode with all sensor in failure should be GREEN", ColorCode.GREEN, cc);
    }


    @Test
    public void testSumSensorFailAllTrue(){
        assertEquals("SumSensorFail(t,t,t,t,t,t) should return 6", 6, cc.sumSensorFail(true,true,true,true,true,true));
    }

    @Test
    public void testSumSensorFailAllFalse(){
        assertEquals("SumSensorFail(f,f,f,f,f,f) should return 0", 0, cc.sumSensorFail(false,false,false,false,false,false));
    }

    @Test
    public void testStatusToColorCode_Battery_GREEN_initialize(){
        setup();
        cc = battery.getBatteryStatus();
        assertEquals(ColorCode.GREEN, cc);
    }


    @Test
    public void testStatusToColorCode_Battery_GREEN(){
        setup();
        cc=cc.statusToColorCode_Battery(true, true,true, 0);
        assertEquals(ColorCode.GREEN, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_YELLOW(){
        setup();
        cc = cc.statusToColorCode_Battery(false, true, true, 1);
        assertEquals(ColorCode.YELLOW, cc);
    }

     @Test
     public void testStatusToColorCode_Battery_RED(){
        setup();
        cc = cc.statusToColorCode_Battery(false, false, true, 2);
        assertEquals(ColorCode.RED, cc);
     }

     @Test
     public void testStatusToColorCode_Battery_BLACK(){
        setup();
        cc = cc.statusToColorCode_Battery(false, false, false, 3);
        assertEquals(ColorCode.BLACK, cc);
     }

     //This sequence of 11 test is made for control that any other combination outside the normal flow leads to a failure state

     @Test
     public void testStatusToColorCode_Battery_BLACK_TFT0(){
        setup();
        cc = cc.statusToColorCode_Battery(true, false, true, 0);
        assertEquals(ColorCode.BLACK, cc);
     }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TFT1(){
        setup();
        cc = cc.statusToColorCode_Battery(true, false, true, 1);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TFT2(){
        setup();
        cc = cc.statusToColorCode_Battery(true, false, true, 2);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TTF0(){
        setup();
        cc = cc.statusToColorCode_Battery(true, true, false,0);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TTF1(){
        setup();
        cc = cc.statusToColorCode_Battery(true, true, false,1);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TTF2(){
        setup();
        cc = cc.statusToColorCode_Battery(true, true, false,2);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TFF0(){
        setup();
        cc = cc.statusToColorCode_Battery(true, false, false,0);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TFF1(){
        setup();
        cc = cc.statusToColorCode_Battery(true, false, false,1);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_TFF2(){
        setup();
        cc = cc.statusToColorCode_Battery(true, false, false,2);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_FFF0(){
        setup();
        cc = cc.statusToColorCode_Battery(false, false, false,0);
        assertEquals(ColorCode.BLACK, cc);
    }

    @Test
    public void testStatusToColorCode_Battery_BLACK_FFF1(){
        setup();
        cc = cc.statusToColorCode_Battery(false, false, false,1);
        assertEquals(ColorCode.BLACK, cc);
    }

    //Weather station ColorCode
    /*   BATTERY |Green   |Yellow |Red    |Black
     *   SENSOR  +--------+-------+-------+------
     *   green   |Green   |Yellow |Orange |Black
     *   yellow  |Yellow  |Orange |Red    |Black
     *   black   |Red     |Red    |Red    |Black
     *
     *    WS_ColorCode:
     *       GREEN: Tutto ok, sia batteria che sensori
     *
     *       YELLOW: Batteria OK, sensori da 1 a 5 non funzionanti
     *               Batteria 2 in uso, sensori OK
     *
     *       ORANGE: Batteria 2 in uso, sensori da 1 a 5 non funzionanti
     *               Batteria 3 in uso, sensori OK
     *
     *       RED:    Batteria 1 in uso, sensori tutti non funzionanti
     *               Batteria 2 in uso, sensori tutti non funzionanti
     *               Batteria 3 in uso, sensori tutti non funzionanti
     *
     *       BLACK:  Nessuna batteria in uso, qualsiasi sensor_status
     *
     * */
    @Test
    public void testStatusToCode_WS_GREEN(){
        bcc = ColorCode.GREEN;
        scc = ColorCode.GREEN;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.GREEN, bcc);
        assertEquals(ColorCode.GREEN, scc);
        assertEquals(ColorCode.GREEN, cc);

    }

    @Test
    public void testStatusToCode_WS_YELLOW_1(){
        bcc = ColorCode.GREEN;
        scc = ColorCode.YELLOW;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.GREEN, bcc);
        assertEquals(ColorCode.YELLOW, scc);
        assertEquals(ColorCode.YELLOW, cc);

    }

    @Test
    public void testStatusToCode_WS_YELLOW_2(){
        bcc = ColorCode.YELLOW;
        scc = ColorCode.GREEN;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.YELLOW, bcc);
        assertEquals(ColorCode.GREEN, scc);
        assertEquals(ColorCode.YELLOW, cc);

    }

    @Test
    public void testStatusToCode_WS_ORANGE_1(){
        bcc = ColorCode.YELLOW;
        scc = ColorCode.YELLOW;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.YELLOW, bcc);
        assertEquals(ColorCode.YELLOW, scc);
        assertEquals(ColorCode.ORANGE, cc);

    }

    @Test
    public void testStatusToCode_WS_ORANGE_2(){
        bcc = ColorCode.RED;
        scc = ColorCode.GREEN;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.RED, bcc);
        assertEquals(ColorCode.GREEN, scc);
        assertEquals(ColorCode.ORANGE, cc);

    }

    @Test
    public void testStatusToCode_WS_RED_1(){
        bcc = ColorCode.RED;
        scc = ColorCode.GREEN;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.RED, bcc);
        assertEquals(ColorCode.GREEN, scc);
        assertEquals(ColorCode.ORANGE, cc);

    }

    @Test
    public void testStatusToCode_WS_RED_2(){
        bcc = ColorCode.RED;
        scc = ColorCode.YELLOW;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.RED, bcc);
        assertEquals(ColorCode.YELLOW, scc);
        assertEquals(ColorCode.RED, cc);

    }

    @Test
    public void testStatusToCode_WS_RED_3(){
        bcc = ColorCode.RED;
        scc = ColorCode.BLACK;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.RED, bcc);
        assertEquals(ColorCode.BLACK, scc);
        assertEquals(ColorCode.RED, cc);

    }

    @Test
    public void testStatusToCode_WS_RED_4(){
        bcc = ColorCode.YELLOW;
        scc = ColorCode.BLACK;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.YELLOW, bcc);
        assertEquals(ColorCode.BLACK, scc);
        assertEquals(ColorCode.RED, cc);

    }

    @Test
    public void testStatusToCode_WS_RED_5(){
        bcc = ColorCode.GREEN;
        scc = ColorCode.BLACK;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.GREEN, bcc);
        assertEquals(ColorCode.BLACK, scc);
        assertEquals(ColorCode.RED, cc);

    }


    @Test
    public void testStatusToCode_WS_BLACK_1(){
        bcc = ColorCode.BLACK;
        scc = ColorCode.GREEN;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.BLACK, bcc);
        assertEquals(ColorCode.GREEN, scc);
        assertEquals(ColorCode.BLACK, cc);

    }

    @Test
    public void testStatusToCode_WS_BLACK_2(){
        bcc = ColorCode.BLACK;
        scc = ColorCode.YELLOW;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.BLACK, bcc);
        assertEquals(ColorCode.YELLOW, scc);
        assertEquals(ColorCode.BLACK, cc);

    }

    @Test
    public void testStatusToCode_WS_BLACK_3(){
        bcc = ColorCode.BLACK;
        scc = ColorCode.BLACK;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.BLACK, bcc);
        assertEquals(ColorCode.BLACK, scc);
        assertEquals(ColorCode.BLACK, cc);

    }

    //WS ColorCOde with any other combination of Status and Battery codes should be BLACK
    @Test
    public void testStatusToCode_WS_BLACK_OTHER(){
        bcc = ColorCode.GREY;
        scc = ColorCode.GREY;
        cc = cc.statusToColorCode_WS(scc, bcc);
        assertEquals(ColorCode.GREY, bcc);
        assertEquals(ColorCode.GREY, scc);
        assertEquals(ColorCode.BLACK, cc);

    }

}
