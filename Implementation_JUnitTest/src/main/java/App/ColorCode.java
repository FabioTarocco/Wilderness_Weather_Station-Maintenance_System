package App;


public enum  ColorCode {
    TEST,
    GREEN,  //Situazione normale
    YELLOW, //Situazione non normale
    ORANGE, //Situazione problematica
    RED,    //Situazione critica
    BLACK,  //Failure Sistema
    GREY;   //Disattivazione

    /*  VERDE: Stato della WS nella norma
        GIALLO: 0< #sensori guasti <6
        ORANGE: GIALLO + batteria_1 guasta
        ROSSO: GIALLO + utilizzo batteria_3
        NERO: 6 strumenti danneggiati oppure 3 batterie guaste
        GRIGIO: disattivato
    */


    public ColorCode statusToColorCode_Sensor(boolean s1, boolean s2, boolean s3, boolean s4, boolean s5, boolean s6){
        if (sumSensorFail(s1,s2,s3,s4,s5,s6) ==0){
            return ColorCode.BLACK;
        } else if (sumSensorFail(s1,s2,s3,s4,s5,s6) >0 && (sumSensorFail(s1,s2,s3,s4,s5,s6) <6)){
            return ColorCode.YELLOW;
        }
        return ColorCode.GREEN;
    }

    public ColorCode statusToColorCode_Battery(boolean b1_status, boolean b2_status, boolean b3_status, int in_use){


        if(b1_status && b2_status && b3_status && in_use == 0) {
            return ColorCode.GREEN;
        }
        else if(!b1_status && b2_status && b3_status && in_use == 1) {
            return ColorCode.YELLOW;
        }
        else if(!b1_status && !b2_status && b3_status && in_use == 2){
            return ColorCode.RED;
        }
        else if(!b1_status && !b2_status && !b3_status && in_use == 3){
            return ColorCode.BLACK;
        }
        return BLACK;

    }

    public ColorCode statusToColorCode_WS(ColorCode sensorCode, ColorCode batteryCode){
        //Weather station ColorCode
        /*   BATTERY ||Green   |Yellow |Red    |Black
         *   SENSOR   +--------+-------+-------+------
         *   Green   ||Green   |Yellow |Orange |Black
         *   Yellow  ||Yellow  |Orange |Red    |Black
         *   Black   ||Red     |Red    |Red    |Black
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

        if(sensorCode.equals(ColorCode.GREEN) && batteryCode.equals(ColorCode.GREEN))
            return ColorCode.GREEN;
        else if (sensorCode.equals(ColorCode.YELLOW) && batteryCode.equals(ColorCode.GREEN))
            return ColorCode.YELLOW;
        else if (sensorCode.equals(ColorCode.BLACK) && batteryCode.equals(ColorCode.GREEN))
            return ColorCode.RED;


        else if (sensorCode.equals(ColorCode.GREEN) && batteryCode.equals(ColorCode.YELLOW))
            return ColorCode.YELLOW;
        else if (sensorCode.equals(ColorCode.YELLOW) && batteryCode.equals(ColorCode.YELLOW))
            return ColorCode.ORANGE;
        else if (sensorCode.equals(ColorCode.BLACK) && batteryCode.equals(ColorCode.YELLOW))
            return ColorCode.RED;


        else if (sensorCode.equals(ColorCode.GREEN) && batteryCode.equals(ColorCode.RED))
            return ColorCode.ORANGE;
        else if (sensorCode.equals(ColorCode.YELLOW) && batteryCode.equals(ColorCode.RED))
            return ColorCode.RED;
        else if (sensorCode.equals(ColorCode.BLACK) && batteryCode.equals(ColorCode.RED))
            return ColorCode.RED;


        else if (sensorCode.equals(ColorCode.GREEN) && batteryCode.equals(ColorCode.BLACK))
            return ColorCode.BLACK;
        else if (sensorCode.equals(ColorCode.YELLOW) && batteryCode.equals(ColorCode.BLACK))
            return ColorCode.BLACK;
        else if (sensorCode.equals(ColorCode.BLACK) && batteryCode.equals(ColorCode.BLACK))
            return ColorCode.BLACK;

        return ColorCode.BLACK;
    }

    public int sumSensorFail(boolean... vars){
        int count = 0;
        for (boolean var:vars) {
            count += (var? 1 : 0);
        }
        return count;
    }

}
