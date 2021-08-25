package App;

import org.junit.Test;
import static org.junit.Assert.*;

public class WS_SensorTest {


    private WeatherStation ws;
    private WS_Sensor empty_sensors, sensors;
    private double fr_temp,fr_pres,fr_sun,fr_rain,fr_wind_sp,fr_wind_dir, fr_tot;

    private double temperature;
    private double pressure;
    private double sunshine;
    private double rainfall;
    private double windspeed;
    private WindDir winddir;

    private final ColorCode GREEN=ColorCode.GREEN, GREY=ColorCode.GREY;
    private ColorCode cc;
    public void setup(){
        ws = new WeatherStation("Italia", "Verona",100.00);
        ws.initialize();
        sensors = ws.getSensor();
    }

    @Test
    public void testEmptyConstructor(){
        empty_sensors = new WS_Sensor();
        assertNull("Id should be null",empty_sensors.getId());
        assertNull("Owner should be null", empty_sensors.getOwner());
        assertEquals("Temperature should be 0.0",0.0, empty_sensors.getTemperature(),0.01);
        assertEquals("Pressure should be 0.0",0.0, empty_sensors.getPressure(),0.01);
        assertEquals("Sunshine should be 0.0",0.0, empty_sensors.getSunshine(),0.01);
        assertEquals("Rainfall should be 0.0",0.0, empty_sensors.getRainfall(),0.01);
        assertEquals("Windspeed should be 0.0",0.0, empty_sensors.getWindspeed(),0.01);
        assertNull("Winddir should be 0.0", empty_sensors.getWinddir());
        assertFalse("Temperature sensor status flag should be false", empty_sensors.getTemp_sens_status());
        assertFalse("Pressure sensor status flag should be false", empty_sensors.getPres_sens_status());
        assertFalse("Rainfall sensor status flag should be false", empty_sensors.getRain_sens_status());
        assertFalse("Sunshine sensor status flag should be false", empty_sensors.getSun_sens_status());
        assertFalse("Windspeed sensor status flag should be false", empty_sensors.getWind_sp_sens_status());
        assertFalse("Winddir sensor status flag should be false", empty_sensors.getWind_dir_sens_status());
        assertNull("SensorStatus should be null", empty_sensors.getSensorStatus());

    }

    @Test
    public void testSensorConstructorOwner(){
        setup();
        assertEquals("Owner and WeatherStation should be the same",sensors.getOwner(),ws);
        assertEquals("WS_sensor and WeatherStation.sensor should be the same",sensors,ws.getSensor());
    }
    @Test
    public void testSensorConstructorWithInitialize(){
        setup();
        assertNotEquals("Temperature should be 0.0",0.0, sensors.getTemperature(),0.0001);
        assertNotEquals("Pressure should be 0.0",0.0, sensors.getPressure(),0.0001);
        assertNotEquals("Sunshine should be 0.0",0.0, sensors.getSunshine(),0.0001);
        assertNotEquals("Rainfall should be 0.0",0.0, sensors.getRainfall(),0.0001);
        assertNotEquals("Windspeed should be 0.0",0.0, sensors.getWindspeed(),0.0001);
        assertNotNull("Winddir should not be null", sensors.getWinddir());
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());
        assertFalse("First run should be false", sensors.isFirst_run());
        assertEquals("Sensor Status should be GREEN", ColorCode.GREEN, sensors.getSensorStatus());
    }

    @Test
    public void testSensorFailureRatesDefault(){
        setup();
        assertEquals("Temperature sensor failure rate should be the 0.98",0.98, sensors.getFailure_rate_temp(),0.01);
        assertEquals("Pressure sensor failure rate should be the 0.98",0.98, sensors.getFailure_rate_pressure(),0.01);
        assertEquals("Sunshine sensor failure rate should be the 0.98",0.99, sensors.getFailure_rate_sunshine(),0.01);
        assertEquals("Rainfall sensor failure rate should be the 0.98",0.95, sensors.getFailure_rate_rainfall(),0.01);
        assertEquals("Windspeed sensor failure rate should be the 0.98",0.90, sensors.getFailure_rate_windspeed(),0.01);
        assertEquals("Winddir sensor failure rate should be the 0.98",0.90, sensors.getFailure_rate_winddir(),0.01);
        assertEquals("Total failure rate should be the 0.98",0.99, sensors.getFailure_rate_total(),0.01);
    }

    @Test
    public void testSensorFailureRatesSetToDefault(){
        setup();
        //Setting false to the test_flag we assure to change rates to the default value, after the flag all the rates can be put to any value, they will not be used for the resets.
        //Test_flag is used only for testing
        sensors.setFailureRates(false,0,0,0,0,0,0,0);
        assertEquals("Temperature sensor failure rate should be the 0.98",0.98, sensors.getFailure_rate_temp(),0.01);
        assertEquals("Pressure sensor failure rate should be the 0.98",0.98, sensors.getFailure_rate_pressure(),0.01);
        assertEquals("Sunshine sensor failure rate should be the 0.98",0.99, sensors.getFailure_rate_sunshine(),0.01);
        assertEquals("Rainfall sensor failure rate should be the 0.98",0.95, sensors.getFailure_rate_rainfall(),0.01);
        assertEquals("Windspeed sensor failure rate should be the 0.98",0.90, sensors.getFailure_rate_windspeed(),0.01);
        assertEquals("Winddir sensor failure rate should be the 0.98",0.90, sensors.getFailure_rate_winddir(),0.01);
        assertEquals("Total failure rate should be the 0.98",0.99, sensors.getFailure_rate_total(),0.01);
    }

    @Test
    public void testSensorFailureRatesCustomValues(){
        setup();
        //Setting false to the test_flag we assure to change rates to the default value, after the flag all the rates can be put to any value, they will not be used for the resets.
        //Test_flag is used only for testing
        fr_temp=0;
        fr_pres=0;
        fr_sun=0;
        fr_rain=0;
        fr_wind_sp=0;
        fr_wind_dir=0;
        fr_tot=0;
        sensors.setFailureRates(true,0,0,0,0,0,0,0);
        assertEquals("Temperature sensor failure rate should be the equals",fr_temp, sensors.getFailure_rate_temp(),0.01);
        assertEquals("Pressure sensor failure rate should be the equals",fr_pres, sensors.getFailure_rate_pressure(),0.01);
        assertEquals("Sunshine sensor failure rate should be the equals",fr_sun, sensors.getFailure_rate_sunshine(),0.01);
        assertEquals("Rainfall sensor failure rate should be the equals",fr_rain, sensors.getFailure_rate_rainfall(),0.01);
        assertEquals("Windspeed sensor failure rate should be the equals",fr_wind_sp, sensors.getFailure_rate_windspeed(),0.01);
        assertEquals("Winddir sensor failure rate should be equals",fr_wind_dir, sensors.getFailure_rate_winddir(),0.01);
        assertEquals("Total failure rate should be equals",fr_tot, sensors.getFailure_rate_total(),0.01);
    }

    @Test
    public void testSensorTotalFailure(){
        setup();
        sensors.setSensorFailure();
        assertFalse("Temperature sensor status flag should be false",sensors.getTemp_sens_status());
        assertFalse("Pressure sensor status flag should be false",sensors.getPres_sens_status());
        assertFalse("Sunshine sensor status flag should be false",sensors.getSun_sens_status());
        assertFalse("Rainfall sensor status flag should be false",sensors.getRain_sens_status());
        assertFalse("Winddir sensor status flag should be false",sensors.getWind_dir_sens_status());
        assertFalse("Windspeed sensor status flag should be false",sensors.getWind_sp_sens_status());
    }

    @Test
    public void testSensorSetRecoveryALlTrue(){
        setup();
        sensors.setSensorFailure();
        sensors.setRecoveryAllTrue();
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());
    }

    @Test
    public void testSensorStatusOnlyTemperatureFailure() {
        setup();
        sensors.setFailureRates(true,0,1,1,1,1,1,1);
        sensors.ws_sensor_status();
        assertFalse("Temperature sensor status flag should be False",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());

    }

    @Test
    public void testSensorStatusOnlyPressureFailure() {
        setup();
        sensors.setFailureRates(true,1,0,1,1,1,1,1);
        sensors.ws_sensor_status();
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertFalse("Pressure sensor status flag should be false",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());

    }

    @Test
    public void testSensorStatusOnlySunshineFailure() {
        setup();
        sensors.setFailureRates(true,1,1,0,1,1,1,1);
        sensors.ws_sensor_status();
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertFalse("Sunshine sensor status flag should be false",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());

    }

    @Test
    public void testSensorStatusOnlyRainfallFailure() {
        setup();
        sensors.setFailureRates(true,1,1,1,0,1,1,1);
        sensors.ws_sensor_status();
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertFalse("Rainfall sensor status flag should be false",sensors.getRain_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());
    }

    @Test
    public void testSensorStatusOnlyWindSpeedFailure() {
        setup();
        sensors.setFailureRates(true,1,1,1,1,0,1,1);
        sensors.ws_sensor_status();
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertFalse("Windspeed sensor status flag should be false",sensors.getWind_sp_sens_status());
        assertTrue("Winddir sensor status flag should be true",sensors.getWind_dir_sens_status());
    }

    @Test
    public void testSensorStatusOnlyWindDirFailure() {
        setup();
        sensors.setFailureRates(true,1,1,1,1,1,0,1);
        sensors.ws_sensor_status();
        assertTrue("Temperature sensor status flag should be true",sensors.getTemp_sens_status());
        assertTrue("Pressure sensor status flag should be true",sensors.getPres_sens_status());
        assertTrue("Sunshine sensor status flag should be true",sensors.getSun_sens_status());
        assertTrue("Rainfall sensor status flag should be true",sensors.getRain_sens_status());
        assertTrue("Windspeed sensor status flag should be true",sensors.getWind_sp_sens_status());
        assertFalse("Winddir sensor status flag should be false",sensors.getWind_dir_sens_status());
    }

    @Test
    public void testSensorStatusAllFail() {
        setup();
        sensors.setFailureRates(true,0,0,0,0,0,0,1);
        sensors.ws_sensor_status();
        assertFalse("Temperature sensor status flag should be false",sensors.getTemp_sens_status());
        assertFalse("Pressure sensor status flag should be false",sensors.getPres_sens_status());
        assertFalse("Sunshine sensor status flag should be false",sensors.getSun_sens_status());
        assertFalse("Rainfall sensor status flag should be false",sensors.getRain_sens_status());
        assertFalse("Windspeed sensor status flag should be false",sensors.getWind_sp_sens_status());
        assertFalse("Winddir sensor status flag should be false",sensors.getWind_dir_sens_status());
    }

    @Test
    public void testSensorStatusTotalFailure() {
        setup();
        sensors.setFailureRates(true,1,1,1,1,1,1,0);
        sensors.ws_sensor_status();
        assertFalse("Temperature sensor status flag should be false",sensors.getTemp_sens_status());
        assertFalse("Pressure sensor status flag should be false",sensors.getPres_sens_status());
        assertFalse("Sunshine sensor status flag should be false",sensors.getSun_sens_status());
        assertFalse("Rainfall sensor status flag should be false",sensors.getRain_sens_status());
        assertFalse("Windspeed sensor status flag should be false",sensors.getWind_sp_sens_status());
        assertFalse("Winddir sensor status flag should be false",sensors.getWind_dir_sens_status());
    }
    @Test
    public void testSensorStatusAllFailAndTotalFailure() {
        setup();
        sensors.setFailureRates(true,0,0,0,0,0,0,0);
        sensors.ws_sensor_status();
        assertFalse("Temperature sensor status flag should be false",sensors.getTemp_sens_status());
        assertFalse("Pressure sensor status flag should be false",sensors.getPres_sens_status());
        assertFalse("Sunshine sensor status flag should be false",sensors.getSun_sens_status());
        assertFalse("Rainfall sensor status flag should be false",sensors.getRain_sens_status());
        assertFalse("Windspeed sensor status flag should be false",sensors.getWind_sp_sens_status());
        assertFalse("Winddir sensor status flag should be false",sensors.getWind_dir_sens_status());
    }

    @Test
    public void testSensorWithDisabledWS(){
        setup();
        assertEquals("Before initialize() the ColorCode of ws should be GREEN", GREEN,ws.getWs_status());
        assertEquals("Before the disable the ColorCode should be GREEN", GREEN,sensors.getSensorStatus());
        cc = sensors.getSensorStatus();
        ws.setDisabled(true); //TODO
        sensors.sensor_new_infos();
        assertEquals("After the disable the ColorCode should be the same as previous", cc, sensors.getSensorStatus());
    }

    @Test
    public void testSensorMeasurementsWithDisabledWS(){
        setup();
        assertEquals("Before initialize() the ColorCode should be GREEN", GREEN,ws.getWs_status());

        temperature = sensors.getTemperature();
        pressure = sensors.getPressure();
        sunshine = sensors.getSunshine();
        rainfall = sensors.getRainfall();
        windspeed = sensors.getWindspeed();
        winddir = sensors.getWinddir();

        ws.setDisabled(true);
        sensors.sensor_new_infos();


        assertEquals("Temperature should be equals the previous",temperature, sensors.getTemperature(),0.01);
        assertEquals("Pressure should be equals the previous",pressure, sensors.getPressure(),0.01);
        assertEquals("Sunshine should be equals the previous",sunshine, sensors.getSunshine(),0.01);
        assertEquals("Rainfall should be equals the previous",rainfall, sensors.getRainfall(),0.01);
        assertEquals("Windspeed should be equals the previous",windspeed, sensors.getWindspeed(),0.01);
        assertEquals("Winddir should be equals the previous", winddir,sensors.getWinddir());

    }
    @Test
    public void testSensorMeasurements(){
        setup();

        temperature = sensors.getTemperature();
        pressure = sensors.getPressure();
        sunshine = sensors.getSunshine();
        rainfall = sensors.getRainfall();
        windspeed = sensors.getWindspeed();

        sensors.sensor_new_infos();

        assertNotEquals("Temperature should not be equals the previous",temperature, sensors.getTemperature());
        assertNotEquals("Pressure should not be equals the previous",pressure, sensors.getPressure());
        assertNotEquals("Sunshine should not be equals the previous",sunshine, sensors.getSunshine());
        assertNotEquals("Rainfall should not be equals the previous",rainfall, sensors.getRainfall());
        assertNotEquals("Windspeed should not be equals the previous",windspeed, sensors.getWindspeed());
    }

    @Test
    public void testRepairSensor(){
        setup();
        sensors.setSensorFailure();
        sensors.sensor_new_infos();
        assertFalse(sensors.getTemp_sens_status());
        assertFalse(sensors.getPres_sens_status());
        assertFalse(sensors.getSun_sens_status());
        assertFalse(sensors.getRain_sens_status());
        assertFalse(sensors.getWind_dir_sens_status());
        assertFalse(sensors.getWind_sp_sens_status());
        assertEquals(ColorCode.BLACK, sensors.getSensorStatus());

        sensors.repairSensor();

        assertTrue(sensors.getTemp_sens_status());
        assertTrue(sensors.getPres_sens_status());
        assertTrue(sensors.getSun_sens_status());
        assertTrue(sensors.getRain_sens_status());
        assertTrue(sensors.getWind_dir_sens_status());
        assertTrue(sensors.getWind_sp_sens_status());
        assertEquals(ColorCode.GREEN, sensors.getSensorStatus());


    }
}
