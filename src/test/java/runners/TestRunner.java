package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features-package/BookFeature.feature",
        glue = {"stepDefinitions"},
        
        plugin = {
                "pretty",
                "html:reports/cucumber_mycases.html",
                "json:reports/cucumber_mycases.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}