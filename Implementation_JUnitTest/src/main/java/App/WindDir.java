package App;

import java.util.Random;

public enum WindDir {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;

    private static Random rand = new Random();

    public static WindDir getRandomDir(){
        return values()[rand.nextInt(values().length)];
    }
}
