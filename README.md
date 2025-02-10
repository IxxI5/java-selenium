# Testing with JUnit, Java Selenium and Cucumber

Author: IxxI5

### Description

This repository features examples of web frontend (UI) testing using **JUnit**, **Java Selenium**, and **Cucumber**.

### Prerequisites

Before you begin, ensure you have the following installed:

1.  [**Java Development Kit (JDK)** from Oracle](https://www.oracle.com/java/technologies/downloads/)

2.  [**Apache-maven-3.9.9** or latest](https://maven.apache.org/download.cgi)

    - Extract **apache-maven-3.9.9-bin.zip** and copy the entire **apache-maven-3.9.9** folder to `C:/` directory
    - Edit the system variables:

      - **System Properties**
      - **Environment Variables**
      - **System variables** > Double click on **Path** > **New**

        Enter:

        ```
        C:\apache-maven-3.9.9\bin
        ```

      - Apply

3.  [**VS Code**](https://code.visualstudio.com/)

    - Verify **Java** installation by running in VS Code Powershell (Terminal):

      ```
      java -version
      ```

      Output (or similar):

      ```python
      """
      java version "23.0.2" 2025-01-21
      Java(TM) SE Runtime Environment (build 23.0.2+7-58)
      Java HotSpot(TM) 64-Bit Server VM (build 23.0.2+7-58, mixed mode, sharing)
      """
      ```

    - Verify **Maven** installation by running in VS Code Powershell (Terminal):

      ```
      mvn -v
      ```

      Output (or similar):

      ```python
      """
      Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
      Maven home: C:\apache-maven-3.9.9
      Java version: 23.0.2, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-23
      Default locale: en_US, platform encoding: UTF-8
      OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
      """
      ```

4.  **VS Code Extensions**: `Extension Pack for Java` from Microsoft

5.  **VS Code Extensions**: `Cucumber (Gherkin) Full Support` from A. Krechik

### Launch a Test

After downloading the java-selenium repository, follow these steps:

- **Unzip** the downloaded file.
- **Open** VS Code Terminal
- **Install Dependencies** through VS Code Terminal:

  ```
  cd junit-ui-test

  mvn clean install

  ```

- **Run a Test**

  ```
  cd junit-ui-test

  mvn test -Dtest=GoogleSearchTest_JUnit
  ```

  **Note**: Another option is to utilize the **Testing** (🧪) function in VS Code.

### Create a JUnit, Selenium Project from Scratch

1.  **Run the following commands** in the VS Code Powershell (Terminal):

    ```
    mkdir java-ui-api-tests

    cd java-ui-api-tests

    mvn archetype:generate -U
    ```

    **For the displayed prompts**, enter the following values (when [Enter] appears, simply press Enter):

    ```
    Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 2217: [Enter]

    Choose a number: 9: [Enter]

    Define value for property 'groupId': com.junit.ui.test

    Define value for property 'artifactId': junit-ui-test

    Define value for property 'version' 1.0-SNAPSHOT: [Enter]

    Define value for property 'package' com.junit.ui.test: [Enter]
    ```

    Output (or similar):

    ```python
    """
    Confirm properties configuration:
    javaCompilerVersion: 17
    junitVersion: 5.11.0
    groupId: com.junit.ui.test
    artifactId: junit-ui-test
    version: 1.0-SNAPSHOT
    package: com.junit.ui.test
    Y:
    """
    ```

    Press [Enter].

    **Note**: JUnit package is installed by default (see `junitVersion: 5.11.0`).

    Project Structure:

    ```
    java-selenium
    └──junit-ui-test
       ├──.mvn
       ├── src
       │   ├── main
       │   └── test
       ├── target
       └──pom.xml
    ```

2.  Download [**chromedriver-win64.zip** from Google](https://googlechromelabs.github.io/chrome-for-testing/#stable)

    - Unzip by selecting `Extract here` in Downloads folder
    - Copy the **chromedriver-win64** folder into `C:/`
    - Ensure the `System.setProperty` the test scripts point to the correct path. In our case:

      ```java
      System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
      ```

3.  **Selenium Web Driver Integration**: Open the pom.xml file and under `<dependencies>` add the following:

    ```xml
    <!-- Selenium Java bindings for browser automation -->
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.28.1</version>
    </dependency>
    ```

4.  **Cucumber JUnit Integration**: Open the pom.xml file and under `<dependencies>` add the following:

    ```xml
    <!-- Cucumber Core -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.20.1</version>
    </dependency>

    <!-- Cucumber JUnit Integration -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.20.1</version>
        <scope>test</scope>
    </dependency>

    <!-- Bridge between Cucumber and JUnit 5, allowing to execute Cucumber scenarios as JUnit tests.  -->
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-junit-platform-engine</artifactId>
      <version>7.20.1</version>
      <scope>test</scope>
    </dependency>
    ```

    **Note**: Check the [**MVN Repository**](https://mvnrepository.com/) for the latest dependency versions.

5.  **Delete** the **AppTest.java**: Go to `../junit-ui-test/src/test/java/com/junit/ui/test` and delete the **AppTest.java** file.

6.  **Install the Dependencies**. Run the following commands in the VS Code Powershell (Terminal):

    ```
    cd junit-ui-test

    mvn clean install
    ```

7.  **GoogleSearchTest_JUnit**: Go to `../junit-ui-test/src/test/java/com/junit/ui/test` and create the **GoogleSearchTest_JUnit.java** file:

    ```java
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

            // Enter the search query "Selenium WebDriver" into the search box
            searchBox.sendKeys("Selenium WebDriver");

            // Submit the search form (simulates pressing Enter)
            searchBox.submit();

            // ASSERT: Verify that the expected outcome has occurred

            WebElement results = driver.findElement(By.id("search")); // Locate the search results container by its 'id'

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
                driver.quit(); // Quit the browser and end the WebDriver session
            }
        }
    }

    ```

8.  **Launch a Test**: Run the following commands in the VS Code Powershell (Terminal):

    ```
    cd junit-ui-test

    mvn test -Dtest=GoogleSearchTest_JUnit
    ```

    **Note**: Another option is to utilize the **Testing** (🧪) function in VS Code.

### Create a JUnit, Selenium-Cucumber Test

1.  **Features**: Go to `../junit-ui-test/src/test/java/com/junit/ui/test` and create the `resources/features` folder. Inside that folder, create the **googleSearch.feature** file and paste the following content:

    ```gherkin
    Feature: Google Search
    Scenario: Google Search
    Given I open the Google homepage
    And I accept the consent dialog
    When I search for "Selenium WebDriver"
    Then I should see search results containing "Selenium" and "WebDriver"
    ```

2.  **JUnit Cucumber Test Runner**: Go to `../junit-ui-test/src/test/java/com/junit/ui/test` then create the `GoogleSearchTest_JUnit_Cucumber_Runner.java` file and paste the following content:

    ```java
    // Defines the namespace for the JUnit Cucumber Test Runner
    package com.junit.ui.test;

    // Import the Cucumber JUnit runner
    import io.cucumber.junit.Cucumber;

    // Import the Cucumber options
    import io.cucumber.junit.CucumberOptions;

    // Import the JUnit runner
    import org.junit.runner.RunWith;

    // Define the JUnit Cucumber Test Runner
    @RunWith(Cucumber.class)
    @CucumberOptions(
        // Path to feature files
        features = "src/test/java/com/junit/ui/test/resources/features",

        // Package containing your step definitions (Gherkin Steps in features) in JUnit Cucumber Test
        glue = "com.junit.ui.test",

        // Optional: Reporting plugins
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}
    )

    // Mark the class as a JUnit runner
    public class GoogleSearchTest_JUnit_Cucumber_Runner {
        // This class remains empty.  It's just a marker for JUnit.
    }
    ```

3.  **GoogleSearchTest_JUnit_Cucumber**: Go to `../junit-ui-test/src/test/java/com/junit/ui/test` and create the **GoogleSearchTest_JUnit_Cucumber.java** file:

    ```java
    // Defines the namespace for the JUnit Cucumber Test class
    package com.junit.ui.test;

    // Import Java Duration class for time-related operations
    import java.time.Duration;

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

    // Import Cucumber step definitions
    import io.cucumber.java.en.*;

    // Import Cucumber hook for after-scenario execution
    import io.cucumber.java.After;

    // Import Cucumber hook for before-scenario execution
    import io.cucumber.java.Before;

    // Defines the JUnit Cucumber Test class
    public class GoogleSearchTest_JUnit_Cucumber {
        private WebDriver driver;

        @Before
        public void setUp() {
            // Specify the path to ChromeDriver
            System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");

            // Create ChromeOptions
            ChromeOptions options = new ChromeOptions();

            // prevent automation (driven by Selenium WebDriver) from being detected
            options.addArguments("--disable-blink-features=AutomationControlled"); // prevents Captcha from appearing

            // Initialize the Chrome browser
            driver = new ChromeDriver(options);

            // Set a timeout for asynchronous scripts
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

            // Set a timeout for implicit waits
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Maximize the browser window for better visibility
            driver.manage().window().maximize();
        }

        @Given("I open the Google homepage")
        public void iOpenTheGoogleHomepage() {
            // Navigate to Google's homepage
            driver.get("https://www.google.com");
        }

        @And("I accept the consent dialog")
        public void iAcceptTheConsentDialog() {
            // Locate the Accept All element
            WebElement consent = driver.findElement(By.id("L2AGLb"));

            // Click on the Accept All to dismiss the dialog
            consent.click();
        }

        @When("I search for {string}")
        public void iSearchFor(String query) {
            // Locate the search box by its 'name' attribute
            WebElement searchBox = driver.findElement(By.name("q"));

            // Clear any existing search query
            searchBox.clear();

            // Enter the search query "Selenium WebDriver" into the search box
            searchBox.sendKeys(query);

            // Submit the search form (simulates pressing Enter)
            searchBox.submit();
        }

        @Then("I should see search results containing {string} and {string}")
        public void iShouldSeeSearchResultsContainingAnd(String keyword1, String keyword2) {
            // Locate the search results container by its 'id'
            WebElement results = driver.findElement(By.id("search"));

            // Retrieve the text content of the search results
            String pageText = results.getText();

            // Verify that the search results contain the words "Selenium" AND "WebDriver"
            assertTrue(pageText.contains(keyword1) && pageText.contains(keyword2),
                    "Search results should contain both '" + keyword1 + "' and '" + keyword2 + "'");
        }

        // Clean up: Close the browser after all tests are completed
        @After
        public void tearDown() {
            if (driver != null) {
                // Quit the browser and end the WebDriver session
                driver.quit();
            }
        }
    }
    ```

4.  **Launch a Test**: Run the following commands in the VS Code Powershell (Terminal):

    ```
    cd junit-ui-test

    mvn test -Dtest=GoogleSearchTest_JUnit_Cucumber
    ```

    **Note**: Another option is to utilize the **Testing** (🧪) function in VS Code.

## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

Copyright (c) 2015 Chris Kibble

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
