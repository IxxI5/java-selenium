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