
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AcceptanceTest {
    private WebTester tester;

    @Before
    public void prepare(){
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/");
    }

    @Test
    public void testInitialView(){
        tester.beginAt("/");
        tester.clickButtonWithText("Add Weather Station");
    }

    @Test
    public void addnewWeatherStationTest(){
        tester.beginAt("/");
        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Italy");
        tester.setTextField("Location","Chiampo");
        tester.setTextField("Altitude","300");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");
    }

    @Test
    public void IfAddWSTest(){
        tester.beginAt("/main_view");

        int before = tester.getElementsByXPath("//td").size();

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Norway");
        tester.setTextField("Location","Bergen");
        tester.setTextField("Altitude","0");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        int after = tester.getElementsByXPath("//td").size();

        Assert.assertTrue(after>before);

    }

    @Test
    public void personOperationsTest(){
        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Veneto");
        tester.setTextField("Location","Breganze");
        tester.setTextField("Altitude","200");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");
        tester.assertLinkPresentWithText("Detail");
        tester.assertLinkPresentWithText("Disable");
        tester.assertLinkPresentWithText("Reactivate");
        tester.assertLinkPresentWithText("Request Maintenance");
        tester.assertLinkPresentWithText("Delete");
    }

    @Test
    public void detailsTest(){
        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Spain");
        tester.setTextField("Location","Barcelona");
        tester.setTextField("Altitude","10");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Detail");
        tester.assertTextPresent("Station Hardware Status");
        tester.clickLinkWithText("Back to main page");
        tester.assertTextPresent("Weather Stations List");
    }


    @Test
    public void nondisableTest(){
        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Spain");
        tester.setTextField("Location","Barcelona");
        tester.setTextField("Altitude","10");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Disable");
        tester.assertTextPresent("Disable Weather Station Monitoring");
        tester.clickLinkWithText("Back to main page");
        tester.assertTextPresent("Weather Stations List");
    }

    @Test
    public void disableTest(){
        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Spain2");
        tester.setTextField("Location","Barcelona2");
        tester.setTextField("Altitude","10");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Disable");
        tester.assertTextPresent("Disable Weather Station Monitoring");
        tester.setTextField("op_name","Leonardo");
        tester.setTextField("op_surname","Zecchin");
        tester.setTextField("reason","Testing");
        tester.submit("dis_butt");
        tester.assertTextPresent("GREY");
        tester.assertTextPresent("Weather Stations List");
    }



    @Test
    public void nonreactivateTest(){

        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Francia1");
        tester.setTextField("Location","Parigi1");
        tester.setTextField("Altitude","56");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Reactivate");
        tester.assertTextPresent("Reactivating Weather Station Monitoring");
        tester.clickLinkWithText("Back to main page");

    }

    @Test
    public void reactivateTest(){

        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Francia");
        tester.setTextField("Location","Parigi");
        tester.setTextField("Altitude","56");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");
        tester.clickLinkWithText("Reactivate");
        tester.assertTextPresent("Reactivating Weather Station Monitoring");
        tester.submit("reactivate");
        tester.assertTextPresent("GREEN");

    }

    @Test
    public void disableReactivateTest(){
        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Francia");
        tester.setTextField("Location","Parigi");
        tester.setTextField("Altitude","56");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Disable");
        tester.assertTextPresent("Disable Weather Station Monitoring");
        tester.setTextField("op_name","Leonardo");
        tester.setTextField("op_surname","Zecchin");
        tester.setTextField("reason","Testing");
        tester.submit("dis_butt");
        tester.assertTextPresent("GREY");
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Reactivate");
        tester.assertTextPresent("Reactivating Weather Station Monitoring");
        tester.submit("reactivate");
        tester.assertTextPresent("GREEN");
    }



    @Test
    public void requestTest(){
        tester.beginAt("/main_view");

        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Francia2");
        tester.setTextField("Location","Parigi2");
        tester.setTextField("Altitude","56");
        tester.submit();
        tester.assertTextPresent("Weather Stations List");

        tester.clickLinkWithText("Request Maintenance");
        tester.assertTextPresent("Request On-Site Maintenance");
        tester.setTextField("op_name","Leonardo");
        tester.setTextField("op_surname","Zecchin");
        tester.setTextField("report","Testing");
        tester.setTextField("notes","Passed");
        tester.submit("request");
        tester.assertTextPresent("Weather Stations List");

    }

    @Test
    public void deleteTest(){
        tester.beginAt("/");
        
        tester.clickButtonWithText("Add Weather Station");
        tester.assertTextPresent("Integrate New Weather Station");
        tester.setTextField("Nation","Italia33");
        tester.setTextField("Location","Altissimo2");
        tester.setTextField("Altitude","600");
        tester.submit();
        int before = tester.getElementsByXPath("//td").size();
        tester.assertTextPresent("Weather Stations List");
        tester.clickLinkWithText("Delete");
        tester.assertTextPresent("Delete a Station using code");
        tester.submit("delete");
        tester.assertTextPresent("Weather Stations List");

        int after = tester.getElementsByXPath("//td").size();

        Assert.assertTrue(after<before); //== Because i create a weather station and after i delete it.


    }

}

