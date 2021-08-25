package App;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class WeatherStation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String code;
    private ColorCode ws_status;
    private String ws_nation;
    private String ws_location;
    private double ws_altitude;

    private boolean isDisabled;

    private String randCode;

    @OneToOne(mappedBy = "owner", cascade =CascadeType.ALL)
    private WS_Sensor sensor;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private WS_Battery battery;

    private Date ws_install_date;
    
    @Column(columnDefinition = "TEXT")
    private String allReports;

    protected WeatherStation(){}

    public WeatherStation(String ws_nation,String ws_location, double ws_altitude) {
        this.ws_nation=ws_nation;
        this.ws_location = ws_location;
        this.ws_altitude = ws_altitude;
    }

    public void initialize(){

        sensor = new WS_Sensor(this);
        battery = new WS_Battery(this);

        this.setWs_status(ColorCode.GREEN);
        this.setDisabled(false);
        this.generateCode();
        Date today = new Date();
        this.setWs_install_date(today);
        allReports="";
        allReports= String.format("[%s]: WS successfully installed",today.toString()) + allReports;
    }

    public void generateCode(){
        randCode = UUID.randomUUID().toString().substring(0,8);
        this.setCode(String.format("%s%s%s",
                    this.ws_nation.substring(0, 2).toUpperCase(),
                    this.ws_location.substring(0, 2).toUpperCase(),
                    randCode));
    }


    public void addReport(String s){
        Date today = new Date();
        this.allReports = String.format("[%s]: %s\n",today.toString(),s)+allReports;
    }

    public void addReport(String name, String surname, String s){
        Date today = new Date();
        this.allReports = String.format("[%s]-%s-%s: %s\n",today.toString(),surname,name,s)+allReports;
    }

    public void addReport(String name, String surname, String s,String n){
        Date today = new Date();
        this.allReports = String.format("[%s]-%s-%s: %s\nNotes: %s\n",today.toString(),surname,name,s,n)+allReports;
    }

    public void ws_new_infos(){

        battery.battery_new_infos();

        if(!battery.getBatteryStatus().equals(ColorCode.BLACK))
            sensor.sensor_new_infos();

        if(isDisabled)
            setWs_status(ColorCode.GREY);
        else
            setWs_status(ws_status.statusToColorCode_WS(sensor.getSensorStatus(),battery.getBatteryStatus()));
    }

    public void repairWS(){
        this.battery.repairBattery();
        this.sensor.repairSensor();
        setWs_status(ColorCode.GREEN);
    }

    public String printAllReports(){
        return allReports;
    }

    public Long getId() {
        return id;
    }

    public String getCode(){return this.code;}

    public void setCode(String code){this.code=code;}

    public ColorCode getWs_status() {
        return ws_status;
    }

    public void setWs_status(ColorCode ws_state) {
        this.ws_status = ws_state;
    }

    public String getWs_nation() {
        return ws_nation;
    }

    public void setWs_nation(String ws_nation) {
        this.ws_nation = ws_nation;
    }

    public String getWs_location() {
        return ws_location;
    }

    public void setWs_location(String ws_location) {
        this.ws_location = ws_location;
    }

    public double getWs_altitude() {
        return ws_altitude;
    }

    public void setWs_altitude(double ws_altitude) {
        this.ws_altitude = ws_altitude;
    }

    public Date getWs_install_date() {
        return ws_install_date;
    }

    public void setWs_install_date(Date ws_install_date) {
        this.ws_install_date = ws_install_date;
    }

    public WS_Sensor getSensor(){
        return sensor;
    }

    public WS_Battery getBattery(){return battery;}

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
