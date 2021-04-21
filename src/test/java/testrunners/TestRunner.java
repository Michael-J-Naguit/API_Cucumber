package testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"steps", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"}
)
public class TestRunner {

}
