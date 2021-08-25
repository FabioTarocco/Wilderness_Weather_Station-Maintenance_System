package App;


import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class WeatherStationTest {

    private WeatherStation ws_empty = new WeatherStation();
    private WeatherStation ws;
    private String nation, old_nation, location, old_location,  substring, code_sub, name, surname, msg, note;
    private double altitude, old_altitude;
    private ColorCode cc;
    private Date date, old_date;
    private Boolean flag;


    public void setup(){
        nation = "Italia";
        location = "Verona";
        substring = nation.substring(0,2).toUpperCase()+location.substring(0,2).toUpperCase();
        altitude = 123.45;
        ws = new WeatherStation(nation, location,altitude);
    }

    @Test
    public void testEmptyWeatherStationConstructor(){
        assertNull("Id should be null", ws_empty.getId());
        assertNull("Nation should be null", ws_empty.getWs_nation());
        assertNull("Location should be null", ws_empty.getWs_location());
        assertEquals("Altitude should be null",0.0, ws_empty.getWs_altitude(),0.1);
        assertNull("Install_date should be null", ws_empty.getWs_install_date());
        assertNull("Status should be null", ws_empty.getWs_status());
        assertNull("Code should be null", ws_empty.getCode());
        assertNull("Battery should be null", ws_empty.getBattery());
        assertNull("Sensor should be null", ws_empty.getSensor());

    }

    @Test
    public void testWeatherStationConstructor(){
        setup();
        assertEquals("Nation should be equals", nation, ws.getWs_nation());
        assertEquals("Location should be equals", location, ws.getWs_location());
        assertEquals("Altitude should be equals", altitude, ws.getWs_altitude(), 0.1);

    }

    @Test
    public void testInitializeWeatherStation(){
        setup();
        ws.initialize();
        assertNotNull("Battery should not be null", ws.getBattery());
        assertNotNull("Sensor should not be null", ws.getSensor());
        assertEquals("The Status should be GREEN", ColorCode.GREEN, ws.getWs_status());
        assertFalse("isDisabled should be false", ws.isDisabled());
        code_sub = ws.getCode().substring(0,4);
        assertEquals("Being random, the only thing to compare is the substring composed of the first 4 characters",substring, code_sub);
        assertNotNull("Install_date should not be null", ws.getWs_install_date());
        assertNotNull("Report should not be null", ws.printAllReports());
    }

    @Test
    public void testDifferentCodeGeneration(){
        setup();
        ws.initialize();
        code_sub = ws.getCode();
        ws.generateCode();
        assertEquals("Prefix of generated code should be equal", code_sub.substring(0,4),ws.getCode().substring(0,4));
        assertNotEquals("Random generated code except common prefix should be different", code_sub, ws.getCode());

    }
    @Test
    public void testAddReportWithoutNote(){
        setup();
        ws.initialize();
        name = "Fabio";
        surname = "Tarocco";
        msg = "Testing";
        ws.addReport(name, surname, msg);
        assertTrue("The report history should contain the substring ]-name-surname: msg",
                ws.printAllReports().contains(String.format("]-%s-%s: %s",surname,name,msg)));

    }

    @Test
    public void testAddReportWithNote(){
        setup();
        ws.initialize();
        name = "Fabio";
        surname = "Tarocco";
        msg = "Testing";
        note = "No on site maintenance required";
        ws.addReport(name, surname, msg, note);
        assertTrue("The report history should contain the substring ]-name-surname: msg",
                ws.printAllReports().contains(String.format("]-%s-%s: %s\nNotes: %s",surname,name,msg,note)));

    }

    @Test
    public void testAddReportOneString(){
        setup();
        ws.initialize();
        name = "Fabio";
        surname = "Tarocco";
        msg = "Testing";
        ws.addReport(msg);
        assertTrue("The report history should contain the substring ]-name-surname: msg",
                ws.printAllReports().contains(String.format("]: %s", msg)));

    }

    //Test on Setters and Getters can be avoided because we can assume the fact that they "just work"

    @Test
    public void testSetterGetterCodeFalse(){
        setup();
        ws.initialize();
        code_sub = ws.getCode();
        ws.generateCode();
        //In the method generateCode() there is a setCode(String code)
        assertNotEquals("Code should be different!",code_sub, ws.getCode());
    }

    @Test
    public void testSetterGetterCodeTrue(){
        setup();
        ws.initialize();
        code_sub = ws.getCode();
        //In the method initialize() there is a call to the method generateCode() which uses a setCode(String code)
        assertEquals("Code should be the same!",code_sub, ws.getCode());
    }


    @Test
    public void testSetterGetterStatusFalse(){
        setup();
        ws.initialize();
        cc = ws.getWs_status();
        ws.setWs_status(ColorCode.BLACK);
        assertNotEquals("ColorCode should be different!",cc,ColorCode.BLACK);
    }

    @Test
    public void testSetterGetterStatusTrue(){
        setup();
        ws.initialize();
        cc=ColorCode.GREY;
        ws.setWs_status(cc);
        assertEquals("ColorCode should be same!",cc,ws.getWs_status());
    }

    @Test
    public void testSetterGetterNationFalse(){
        setup();
        ws.initialize();
        old_nation = ws.getWs_nation();
        nation = "Germany";
        ws.setWs_nation(nation);
        assertNotEquals("Nation should be different!", old_nation,ws.getWs_nation());
    }
    @Test
    public void testSetterGetterNationTrue(){
        setup();
        ws.initialize();
        nation = ws.getWs_nation();
        ws.setWs_nation(nation);
        assertEquals("Nation should be same!",nation,ws.getWs_nation());
    }

    @Test
    public void testSetterGetterLocationFalse(){
        setup();
        ws.initialize();
        old_location = ws.getWs_location();
        location = "Napoli";
        ws.setWs_location(location);
        assertNotEquals("Nation should be different!", old_location,ws.getWs_location());
    }

    @Test
    public void testSetterGetterLocationTrue(){
        setup();
        ws.initialize();
        location = ws.getWs_location();
        ws.setWs_location(location);
        assertEquals("Nation should be same!",location,ws.getWs_location());
    }

    @Test
    public void testSetterGetterAltitudeFalse(){
        setup();
        ws.initialize();
        old_altitude = ws.getWs_altitude();
        altitude = 100;
        ws.setWs_altitude(altitude);
        assertNotEquals("Altitude should be different!", old_altitude,ws.getWs_altitude());
    }

    @Test
    public void testSetterGetterAltitudeTrue(){
        setup();
        ws.initialize();
        altitude = ws.getWs_altitude();
        ws.setWs_altitude(altitude);
        assertEquals("Altitude should be same!",altitude,ws.getWs_altitude(),0.01);
    }

    @Test
    public void testSetterGetterDateFalse() throws ParseException {
        setup();
        ws.initialize();
        old_date = ws.getWs_install_date();
        date = new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2021");
        ws.setWs_install_date(date);
        assertNotEquals("Date should be different!", old_date, ws.getWs_install_date());
    }

    @Test
    public void testSetterGetterDateTrue(){
        setup();
        ws.initialize();
        date = ws.getWs_install_date();
        ws.setWs_install_date(date);
        assertEquals("Date should be same!",date, ws.getWs_install_date());
    }

    @Test
    public void testDisableFalse(){
        setup();
        ws.initialize();
        flag = ws.isDisabled();
        ws.setDisabled(true);
        assertNotEquals("Disable flag should be different!", flag, ws.isDisabled());
    }

    @Test
    public void testDisableTure(){
        setup();
        ws.initialize();
        flag = true;
        ws.setDisabled(true);
        assertEquals("Disable flag should be same!",flag, ws.isDisabled());
    }

    @Test
    public void testWSNewInfos(){
        setup();
        ws.initialize();
        flag = true;
        assertEquals("Before new_infos() WS_Status should be GREEN", ColorCode.GREEN, ws.getWs_status() );
        ws.setDisabled(flag);
        ws.ws_new_infos();
        assertEquals("In disabled state WS_Status should be GREY after new_infos()", ColorCode.GREY, ws.getWs_status());
    }

    @Test
    //testing the worst cases possible: BLACK Battery and BLACK Sensor
    public void testRepairWS(){
        setup();
        ws.initialize();

        ws.getBattery().setBatteryFailure();
        ws.getBattery().battery_new_infos();

        assertFalse(ws.getBattery().getB_1_status());
        assertFalse(ws.getBattery().getB_2_status());
        assertFalse(ws.getBattery().getB_3_status());
        assertEquals(3,ws.getBattery().getIn_use_battery());
        assertEquals(ColorCode.BLACK, ws.getBattery().getBatteryStatus());

        ws.getSensor().setSensorFailure();
        ws.getSensor().sensor_new_infos();

        assertFalse(ws.getSensor().getTemp_sens_status());
        assertFalse(ws.getSensor().getPres_sens_status());
        assertFalse(ws.getSensor().getSun_sens_status());
        assertFalse(ws.getSensor().getRain_sens_status());
        assertFalse(ws.getSensor().getWind_dir_sens_status());
        assertFalse(ws.getSensor().getWind_sp_sens_status());
        assertEquals(ColorCode.BLACK, ws.getSensor().getSensorStatus());

        ws.ws_new_infos();
        assertEquals(ColorCode.BLACK, ws.getWs_status());


        //After repair:
        ws.repairWS();


        assertTrue(ws.getBattery().getB_1_status());
        assertTrue(ws.getBattery().getB_2_status());
        assertTrue(ws.getBattery().getB_3_status());
        assertEquals(0,ws.getBattery().getIn_use_battery());
        assertEquals(ColorCode.GREEN, ws.getBattery().getBatteryStatus());


        assertTrue(ws.getSensor().getTemp_sens_status());
        assertTrue(ws.getSensor().getPres_sens_status());
        assertTrue(ws.getSensor().getSun_sens_status());
        assertTrue(ws.getSensor().getRain_sens_status());
        assertTrue(ws.getSensor().getWind_dir_sens_status());
        assertTrue(ws.getSensor().getWind_sp_sens_status());
        assertEquals(ColorCode.GREEN, ws.getSensor().getSensorStatus());


        assertEquals(ColorCode.GREEN, ws.getWs_status());

    }

}

