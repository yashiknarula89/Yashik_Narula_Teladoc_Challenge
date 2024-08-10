package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "Webtables.feature",
    glue = "stepDefinitions,hooks",
    tags="@Teladoc_Webtables_TC1",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class CucumberRunner {

}
