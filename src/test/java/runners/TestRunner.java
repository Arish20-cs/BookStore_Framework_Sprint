package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features-package",
	    glue = "stepDefinitions",
	    tags = "@BookStoreWebAPI01TC_01 or @BookStoreWebAPI01TC_02 or @BookStoreWebAPI01TC_03 or @BookStoreWebAPI01TC_04 or @BookStoreWebAPI01TC_05 or @BookStoreWebAPI01TC_06 or @BookStoreWebAPI01TC_07 or @BookStoreWebAPI01TC_08 or @BookStoreWebAPI01TC_09 or @BookStoreWebAPI01TC_10 or @BookStoreWebAPI01TC_11 or @BookStoreWebAPI01TC_12 or @BookStoreWebAPI01TC_13 or @BookStoreWebAPI01TC_14 or @BookStoreWebAPI01TC_15 or @BookStoreWebAPI01TC_16 or @BookStoreWebAPI01TC_17"
	)
public class TestRunner extends AbstractTestNGCucumberTests {
}