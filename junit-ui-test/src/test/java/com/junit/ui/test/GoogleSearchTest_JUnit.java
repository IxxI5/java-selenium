// Defines the namespace for the classes in this file
package com.junit.ui.test;

// Import Java Duration class for time-related operations
import java.time.Duration;

// Import JUnit 5 annotations for test class and method configuration
import org.junit.jupiter.api.*;

// Import Selenium By class for locating elements on a web page
import org.openqa.selenium.By;

// Import Selenium WebDriver interface for interacting with a browser
import org.openqa.selenium.WebDriver;

// Import Selenium WebElement class for representing HTML elements on a web page
import org.openqa.selenium.WebElement;

// Import Selenium ChromeDriver class for automating Google Chrome browser
import org.openqa.selenium.chrome.ChromeDriver;

// Import Selenium ChromeOptions class for customizing Chrome browser settings
import org.openqa.selenium.chrome.ChromeOptions;

// Import JUnit 5 assertion methods for verifying test results
import static org.junit.jupiter.api.Assertions.*;

// Annotation to mark this class as a JUnit 5 test class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoogleSearchTest_JUnit {
    private WebDriver driver; // Instance variable to hold the WebDriver

    // ARRANGE: Setup the WebDriver before all tests
    @BeforeAll
    void setUp() {
        // Specify the path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe"); 

        // Create ChromeOptions
        ChromeOptions options = new ChromeOptions(); 
        
        // prevent automation (driven by Selenium WebDriver) from being detected
        options.addArguments(" --disable-blink-features=AutomationControlled"); // prevents Captcha from appearing

        // Initialize the Chrome browser
        driver = new ChromeDriver(options); 

        // Set a timeout for asynchronous scripts
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        // Set a timeout for implicit waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximize the browser window for better visibility
        driver.manage().window().maximize(); 
    }

    @Test
    @DisplayName("Google Search for Selenium WebDriver")
    void testGoogleSearch() {
        // ACT: Perform the actions required for the test

        // Navigate to Google's homepage
        driver.get("https://www.google.com");

        // Locate the Accept All element
        WebElement consent = driver.findElement(By.id("L2AGLb"));

        // Click on the Accept All to dismiss the dialog
        consent.click();

        // Locate the search box by its 'name' attribute
        WebElement searchBox = driver.findElement(By.name("q"));

        // Clear any existing search query
        searchBox.clear();

        // Enter the search query "Selenium WebDriver" into the search box
        searchBox.sendKeys("Selenium WebDriver");

        // Submit the search form (simulates pressing Enter)
        searchBox.submit();

        // ASSERT: Verify that the expected outcome has occurred

        // Locate the search results container by its 'id' 
        WebElement results = driver.findElement(By.id("search")); 

        // Retrieve the text content of the search results
        String pageText = results.getText();

        // Verify that the search results contain the words "Selenium" AND "WebDriver"
        assertTrue(pageText.contains("Selenium") && pageText.contains("WebDriver"),
           "Search results should contain both 'Selenium' and 'WebDriver'");
    }

    // Clean up: Close the browser after all tests are completed
    @AfterAll
    void tearDown() {
        if (driver != null) {
            // Quit the browser and end the WebDriver session
            driver.quit(); 
        }
    }
}

/*
 * Assertion Examples in JUnit
   ---------------------------
   assertEquals(expectedValue, actualValue, "Values should be equal"); // Optional message
   assertEquals(expectedName, actualName); // No message provided
   assertEquals(expectedPrice, actualPrice, 0.001); // Delta for doubles
   assertNotEquals(unexpectedValue, actualValue, "Values should not be equal");
   assertSame(str1, str2, "Objects should be the same");
   assertNotSame(str1, str3, "Objects should not be the same");
   assertTrue(isValid, "Condition should be true");
   assertTrue(age > 18, "Age should be greater than 18");
   assertFalse(isLoggedIn, "Condition should be false");
   assertNull(obj, "Object should be null");
   assertNotNull(name, "Object should not be null");
   assertTrue(pageText.contains("Selenium") && pageText.contains("WebDriver"),
           "Search results should contain both 'Selenium' and 'WebDriver'");

   assertThrows(IllegalArgumentException.class, () -> {
        // Code that should throw the exception
        MyClass.myMethod(-1); // Example: Invalid argument
    }, "Should throw IllegalArgumentException");

   int[] expectedArray = {1, 2, 3};
   int[] actualArray = {1, 2, 3};
   assertArrayEquals(expectedArray, actualArray, "Arrays should be equal");
 */