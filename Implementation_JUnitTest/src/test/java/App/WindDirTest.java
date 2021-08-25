package App;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindDirTest {

    @Test
    public void TestNorthField(){
        assertEquals("N", WindDir.N.toString());
    }

    @Test
    public void TestNorthEastField(){
        assertEquals("NE", WindDir.NE.toString());
    }

    @Test
    public void TestEastField(){
        assertEquals("E", WindDir.E.toString());
    }

    @Test
    public void TestSouthEastField(){
        assertEquals("SE", WindDir.SE.toString());
    }

    @Test
    public void TestSouthField(){
        assertEquals("S", WindDir.S.toString());
    }

    @Test
    public void TestSouthWestField(){
        assertEquals("SW", WindDir.SW.toString());
    }

    @Test
    public void TestWestField(){
        assertEquals("W", WindDir.W.toString());
    }

    @Test
    public void TestNorthWestField(){
        assertEquals("NW", WindDir.NW.toString());
    }

    @Test
    public void RandomWindDirNotNull(){
        WindDir wd = WindDir.W;
        assertNotNull( "The object WindDir after .getRandomDir() is not null",wd.getRandomDir());
    }

}
