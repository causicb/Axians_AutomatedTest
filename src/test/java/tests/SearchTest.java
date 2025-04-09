package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import pages.Search;
import utilities.DriverSetup;

public class SearchTest {

    WebDriver driver;
    Search search;

    @BeforeClass
    public void setUp() {
        DriverSetup driverSetup = new DriverSetup();
        driver = driverSetup.getDriver();
        search = new Search(driver);
    }

    @Test(priority = 1)
    public void testFullNameSearch() {
        search.runSearchTest("SergejOvoLodej", "SergejOvoLodej");
    }

    @Test(priority = 2)
    public void testPartialNameSearch() {
        search.runSearchTest("Se", "SergejOvoLodej");
    }

    @Test(priority = 3)
    public void testNonExistingNameSearch() {
        search.runSearchTest("Harakraokwfaok", "Harakraokwfaok");
    }

    @Test(priority = 4)
    public void testFullLastNameSearch() {
        search.runSearchTest("Kokosević", "Kokosević");
    }

    @Test(priority = 5)
    public void testPartialLastNameSearch() {
        search.runSearchTest("Kok", "Kokosević");
    }

    @Test(priority = 6)
    public void testEmailSearch() {
        search.runSearchTest("realmailadress@gmail.com", "realmailadress@gmail.com");
    }

    @Test(priority = 7)
    public void testPartialEmailSearch() {
        search.runSearchTest("ss@gm", "ss@gm");
    }

    @Test(priority = 8)
    public void testNonExistingEmailSearch() {
        search.runSearchTest("notexisting@notexisting.com", "notexisting@notexisting.com");
    }

    @Test(priority = 9)
    public void testDateSearch() {
        search.runSearchTest("17/12/2020", "17/12/2020");
    }

    @Test(priority = 10)
    public void testFullPhoneNumberSearch() {
        search.runSearchTest("0915460711", "0915460711");
    }

    @Test(priority = 11)
    public void testPartialPhoneNumberSearch() {
        search.runSearchTest("0915", "0915460711");
    }

    @Test(priority = 12)
    public void testNonExistingPhoneNumberSearch() {
        search.runSearchTest("1231114467", "1231114467");
    }

    @Test(priority = 13)
    public void testFullAddressSearch() {
        search.runSearchTest("Ive Lole Ribara 3", "Ive Lole Ribara 3");
    }

    @Test(priority = 14)
    public void testIDSearch() {
        search.runSearchTest("3176", "3176");
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }

}
