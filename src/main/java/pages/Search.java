package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Search {

    WebDriver driver;

    // Locators for Search functionality
    private By searchField = By.id("searchQuery");
    private By searchButton = By.id("searchBtn");
    private By resultsTable = By.xpath("//*[@id=\"gbox_contactsGrid\"]");
    private By errorMessage = By.xpath("//*[@id=\"gview_contactsGrid\"]/div[3]/span/span/h1");

    public Search(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter search data into the search field
    public void enterSearchData(String searchData) {
        System.out.println("Entering search data: " + searchData);
        WebElement searchBox = driver.findElement(searchField);
        searchBox.clear();
        searchBox.sendKeys(searchData);
        System.out.println("Data entered successfully in search field.");
    }

    // Method to click on the search button
    public void clickSearchButton() {
        System.out.println("Clicking the search button...");
        WebElement button = driver.findElement(searchButton);
        button.click();
        System.out.println("Search button clicked.");
    }

    // Method to check if the name exists in results table
    public boolean isNameInResults(String expectedName) {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {}

        WebElement table = driver.findElement(resultsTable);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));

            for (WebElement column : columns) {
                if (column.getText().contains(expectedName)) {
                    return true;
                }
            }
        }

        return false;
    }

    //Method that executes the search process, checks for errors, and validates if the expected name is found in the results.
    public void runSearchTest(String searchData, String expectedName) {
        System.out.println("Running search test...");

        enterSearchData(searchData);
        clickSearchButton();
        boolean isNameFound = isNameInResults(expectedName);

        // Check for error message and fail the test if error is found (true)
        if (isErrorPresent()) {
            System.out.println("Test failed: Server Error");
            throw new AssertionError("Server Error occurred during the search.");
        }

        else if (isNameFound) {
            System.out.println("Test passed: Expected name is in the results.");
        }
        else {
            System.out.println("Test passed: Expected name is not in the results.");
        }

        System.out.println(".............................................................");
    }

    //Method to check if Server Error is present
    public boolean isErrorPresent() {
        try {
            WebElement error = driver.findElement(errorMessage);
            return error.isDisplayed();  // Return true if error message is visible
        }
        catch (Exception e) {
            return false;  // Return false if error message is not found
        }
    }

}
