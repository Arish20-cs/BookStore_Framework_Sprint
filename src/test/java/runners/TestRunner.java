package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features-package/BookFeature.feature",
        glue = {"stepDefinitions"},
        tags = "@BookStoreWebAPI12TC_18 or " +
               "@BookStoreWebAPI13TC_19 or " +
               "@BookStoreWebAPI15TC_21 or " +
               "@BookStoreWebAPI18TC_24 or " +
               "@BookStoreWebAPI21TC_28 or " +
               "@BookStoreWebAPI21TC_29",
        plugin = {
                "pretty",
                "html:reports/cucumber_mycases.html",
                "json:reports/cucumber_mycases.json"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}