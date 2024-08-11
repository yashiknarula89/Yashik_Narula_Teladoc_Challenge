package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {"stepDefinitions","hooks"},
    tags= "@Teladoc_Webtables_TC1 or @Teladoc_Webtables_TC2",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class CucumberRunner {

}
