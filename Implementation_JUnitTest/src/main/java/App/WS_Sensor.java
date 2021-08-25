package App;


import javax.persistence.*;
import java.util.Random;

@Entity
public class WS_Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private WeatherStation owner;

    private double temperature;
    private double pressure;
    private double sunshine;
    private double rainfall;
    private double windspeed;
    private WindDir winddir;

    private double failure_rate_temp=0.98;
    private double failure_rate_pressure=0.98;
    private double failure_rate_sunshine=0.99;
    private double failure_rate_rainfall=0.95;
    private double failure_rate_windspeed=0.90;
    private double failure_rate_winddir=0.90;
    private double failure_rate_total=0.99;

    private boolean temp_sens_status;
    private boolean pres_sens_status;
    private boolean sun_sens_status;
    private boolean rain_sens_status;
    private boolean wind_sp_sens_status;
    private boolean wind_dir_sens_status;


    private ColorCode sensorStatus;

    private boolean first_run;
    private Random rand;


    public WS_Sensor() {}

    public WS_Sensor(WeatherStation owner){
        this.owner=owner;
        this.initialize();
    }


    public void initialize(){
        temperature = 0.0;
        pressure = 0.0;
        sunshine = 0.0;
        rainfall = 0.0;
        windspeed = 0.0;
        winddir = WindDir.getRandomDir();

        temp_sens_status=true;
        pres_sens_status=true;
        sun_sens_status=true;
        rain_sens_status=true;
        wind_dir_sens_status=true;
        wind_sp_sens_status=true;

        first_run=true;

        setSensorStatus(ColorCode.GREEN);
        this.ws_mock_sensor();
    }

    public void sensor_new_infos(){
        if(!this.owner.isDisabled()) {
            ws_sensor_status();
            ws_mock_sensor();
            setSensorStatus(sensorStatus.statusToColorCode_Sensor(temp_sens_status,pres_sens_status,sun_sens_status,
                    rain_sens_status,wind_dir_sens_status,wind_sp_sens_status));
        }
    }

    public void ws_mock_sensor() {

        rand = new Random();
        
        if(this.first_run){
            temperature = (rand.nextDouble()-0.5)*80;
            pressure = 1000+ (rand.nextDouble()-0.2*10);
            sunshine =  rand.nextDouble()*2;
            rainfall = rand.nextDouble()*1;
            windspeed = rand.nextDouble()*120;
            winddir = WindDir.getRandomDir();

            first_run=false;
        }
        else{
            temperature += (rand.nextDouble()-0.5)*2;
            pressure += (rand.nextDouble()-0.5)*5;
            sunshine +=  (rand.nextDouble()-0.5)*2;
            rainfall += (rand.nextDouble()-0.5)*0.5;
            windspeed += (rand.nextDouble()-0.5)*10;
            winddir = WindDir.getRandomDir();
        }
    }
    

    public void ws_sensor_status() {
     rand = new Random();
        //temperature sensor failure rate 2%
        if(rand.nextFloat() >= getFailure_rate_temp())
            setTemp_sens_status(false);
        //pressure sensor failure rate 2%
        if(rand.nextFloat() >= getFailure_rate_pressure())
            setPres_sens_status(false);
        //rain sensor failure rate 5%
        if(rand.nextFloat() >= getFailure_rate_rainfall())
            setRain_sens_status(false);
        //sunshine sensor failure rate 1%
        if(rand.nextFloat() >= getFailure_rate_sunshine())
            setSun_sens_status(false);
        //wind speed sensor failure rate 10%
        if(rand.nextFloat() >= getFailure_rate_windspeed())
            setWind_sp_sens_status(false);
        //wind direction sensor failure rate 10%
        if(rand.nextFloat() >= getFailure_rate_winddir())
            setWind_dir_sens_status(false);
        //total system failure 0,001%
        if(rand.nextFloat()>= getFailure_rate_total()){
            setSensorFailure();
        }

    }

    public void setSensorFailure(){
        setTemp_sens_status(false);
        setPres_sens_status(false);
        setRain_sens_status(false);
        setSun_sens_status(false);
        setWind_sp_sens_status(false);
        setWind_dir_sens_status(false);
        
    }

    public void setRecoveryAllTrue(){
        setTemp_sens_status(true);
        setPres_sens_status(true);
        setRain_sens_status(true);
        setSun_sens_status(true);
        setWind_sp_sens_status(true);
        setWind_dir_sens_status(true);

    }

    public void repairSensor(){
        setRecoveryAllTrue();
        setSensorStatus(ColorCode.GREEN);
    }

    public void setFailureRates(boolean test_flag, double fr_temp,double fr_pres,double fr_sun, double fr_rain,
                                   double fr_wind_sp, double fr_wind_dir, double fr_tot){

        if(!test_flag){
            setFailure_rate_temp(0.98);
            setFailure_rate_pressure(0.98);
            setFailure_rate_sunshine(0.99);
            setFailure_rate_rainfall(0.95);
            setFailure_rate_windspeed(0.90);
            setFailure_rate_winddir(0.90);
            setFailure_rate_total(0.99);
        }
        else{
            setFailure_rate_temp(fr_temp);
            setFailure_rate_pressure(fr_pres);
            setFailure_rate_sunshine(fr_sun);
            setFailure_rate_rainfall(fr_rain);
            setFailure_rate_windspeed(fr_wind_sp);
            setFailure_rate_winddir(fr_wind_dir);
            setFailure_rate_total(fr_tot);
        }
    }


    public Long getId(){return id;}

    public WeatherStation getOwner(){return owner;}

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSunshine() {
        return sunshine;
    }

    public double getRainfall() {
        return rainfall;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public WindDir getWinddir() {
        return winddir;
    }

    public void setSensorStatus(ColorCode status){
        this.sensorStatus =status;
    }
    public ColorCode getSensorStatus(){return sensorStatus;}

    public boolean getTemp_sens_status() {
        return temp_sens_status;
    }

    public boolean getPres_sens_status() {
        return pres_sens_status;
    }

    public boolean getSun_sens_status() {
        return sun_sens_status;
    }

    public boolean getRain_sens_status() {
        return rain_sens_status;
    }

    public boolean getWind_sp_sens_status() {
        return wind_sp_sens_status;
    }

    public boolean getWind_dir_sens_status() {
        return wind_dir_sens_status;
    }

    public void setTemp_sens_status(boolean temp_sens_status) {
        this.temp_sens_status = temp_sens_status;
    }

    public void setPres_sens_status(boolean pres_sens_status) {
        this.pres_sens_status = pres_sens_status;
    }

    public void setSun_sens_status(boolean sun_sens_status) {
        this.sun_sens_status = sun_sens_status;
    }

    public void setRain_sens_status(boolean rain_sens_status) {
        this.rain_sens_status = rain_sens_status;
    }

    public void setWind_sp_sens_status(boolean wind_sp_sens_status) {
        this.wind_sp_sens_status = wind_sp_sens_status;
    }

    public void setWind_dir_sens_status(boolean wind_dir_sens_status) {
        this.wind_dir_sens_status = wind_dir_sens_status;
    }

    public double getFailure_rate_temp() {
        return failure_rate_temp;
    }

    public void setFailure_rate_temp(double failure_rate_temp) {
        this.failure_rate_temp = failure_rate_temp;
    }

    public double getFailure_rate_pressure() {
        return failure_rate_pressure;
    }

    public void setFailure_rate_pressure(double failure_rate_pressure) {
        this.failure_rate_pressure = failure_rate_pressure;
    }

    public double getFailure_rate_sunshine() {
        return failure_rate_sunshine;
    }

    public void setFailure_rate_sunshine(double failure_rate_sunshine) {
        this.failure_rate_sunshine = failure_rate_sunshine;
    }

    public double getFailure_rate_rainfall() {
        return failure_rate_rainfall;
    }

    public void setFailure_rate_rainfall(double failure_rate_rainfall) {
        this.failure_rate_rainfall = failure_rate_rainfall;
    }

    public double getFailure_rate_windspeed() {
        return failure_rate_windspeed;
    }

    public void setFailure_rate_windspeed(double failure_rate_windspeed) {
        this.failure_rate_windspeed = failure_rate_windspeed;
    }

    public double getFailure_rate_winddir() {
        return failure_rate_winddir;
    }

    public void setFailure_rate_winddir(double failure_rate_winddir) {
        this.failure_rate_winddir = failure_rate_winddir;
    }

    public double getFailure_rate_total() {
        return failure_rate_total;
    }

    public void setFailure_rate_total(double failure_rate_total) {
        this.failure_rate_total = failure_rate_total;
    }
    public boolean isFirst_run(){return first_run;}
}
