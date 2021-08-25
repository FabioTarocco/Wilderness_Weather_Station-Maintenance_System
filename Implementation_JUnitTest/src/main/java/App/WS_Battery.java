package App;


import javax.persistence.*;
import java.util.Random;

@Entity
public class WS_Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private WeatherStation owner;

    private ColorCode batteryStatus;
    private Random rand;
    private int in_use_battery;
    private double failure_rate_b1 = 0.985, failure_rate_b2 = 0.985, failure_rate_b3 = 0.75;
    private boolean b_1_status, b_2_status,b_3_status;

    public WS_Battery(){}

    public WS_Battery(WeatherStation owner){
        this.owner=owner;
        initialize();
    }

    private void initialize (){

        setRecoveryAllTrue();

        setIn_use_battery(0);
        setBatteryStatus(ColorCode.GREEN);
    }


    public String printBatteryStatus(){
        String result = "";
        result += (b_1_status)?"[ OK ]":"[ FAIL ]";
        result += (b_2_status)?"[ OK ]":"[ FAIL ]";
        result += (b_3_status)?"[ OK ]":"[ FAIL ]";
        return result;
    }

    public void battery_new_infos(){
        ws_mock_battery();
        ws_battery_status();
        setBatteryStatus(batteryStatus.statusToColorCode_Battery(b_1_status,b_2_status,b_3_status,in_use_battery));
    }

    public void ws_mock_battery(){

        rand = new Random();

        if(in_use_battery==0 && b_1_status){
            if(rand.nextFloat() >= getFailure_rate_b1())
                setB_1_status(false);
        }
        else if (in_use_battery==1 && b_2_status){
            if(rand.nextFloat() >= getFailure_rate_b2())
                setB_2_status(false);
        }
        else if(in_use_battery==2 && b_3_status){
            if(rand.nextFloat() >= getFailure_rate_b3())
                setB_3_status(false);
        }
        else
            setBatteryFailure();
    }



    public void ws_battery_status(){

        if(b_1_status && b_2_status && b_3_status && in_use_battery == 0) {
            setIn_use_battery(0);
        }
        else if(!b_1_status && b_2_status && b_3_status && in_use_battery == 0){
            setIn_use_battery(1);
        }
        else if(!b_1_status && b_2_status && b_3_status && in_use_battery == 1) {
            setIn_use_battery(1);
        }
        else if(!b_1_status && !b_2_status && b_3_status && in_use_battery == 1){
            setIn_use_battery(2);
        }
        else if(!b_1_status && !b_2_status && b_3_status && in_use_battery == 2) {
            setIn_use_battery(2);
        }
        else if(!b_1_status && !b_2_status && !b_3_status && in_use_battery == 2){
            setIn_use_battery(3);
        }
        else
            setIn_use_battery(3);
    }


    public void setBatteryFailure(){
        setB_1_status(false);
        setB_2_status(false);
        setB_3_status(false);
    }

    public void setRecoveryAllTrue(){
        setB_1_status(true);
        setB_2_status(true);
        setB_3_status(true);
    }

    public void repairBattery(){
        setRecoveryAllTrue();
        setIn_use_battery(0);
        setBatteryStatus(ColorCode.GREEN);
    }

    public void setFailureRates(boolean test_flag, double fr_b1,double fr_b2,double fr_b3){

        if(!test_flag){
            setFailure_rate_b1(0.985);
            setFailure_rate_b2(0.985);
            setFailure_rate_b3(0.75);
        }
        else{
            setFailure_rate_b1(fr_b1);
            setFailure_rate_b2(fr_b2);
            setFailure_rate_b3(fr_b3);
        }
    }

    public Long getId() {
        return id;
    }

    public WeatherStation getOwner() {
        return owner;
    }


    public int getIn_use_battery(){return in_use_battery;}

    public int getIn_use_battery_ordinal() {
        return in_use_battery + 1;
    }

    public void setIn_use_battery(int in_use_battery) {
        if(in_use_battery<0 || in_use_battery>3)
            this.in_use_battery = 3;
        else
            this.in_use_battery = in_use_battery;
    }

    public ColorCode getBatteryStatus(){
        return this.batteryStatus;
    }

    public void setBatteryStatus(ColorCode status){
        this.batteryStatus =status;
    }

    public boolean getB_1_status() {
        return b_1_status;
    }

    public void setB_1_status(boolean b_1_status) {
        this.b_1_status = b_1_status;
    }

    public boolean getB_2_status() {
        return b_2_status;
    }

    public void setB_2_status(boolean b_2_status) {
        this.b_2_status = b_2_status;
    }

    public boolean getB_3_status() {
        return b_3_status;
    }

    public void setB_3_status(boolean b_3_status) {
        this.b_3_status = b_3_status;
    }

    public double getFailure_rate_b1() {
        return failure_rate_b1;
    }

    public void setFailure_rate_b1(double failure_rate_b1) {
        this.failure_rate_b1 = failure_rate_b1;
    }

    public double getFailure_rate_b2() {
        return failure_rate_b2;
    }

    public void setFailure_rate_b2(double failure_rate_b2) {
        this.failure_rate_b2 = failure_rate_b2;
    }

    public double getFailure_rate_b3() {
        return failure_rate_b3;
    }

    public void setFailure_rate_b3(double failure_rate_b3) {
        this.failure_rate_b3 = failure_rate_b3;
    }
}
