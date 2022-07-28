package automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber"},
    snippets = CAMELCASE,
    glue = {"automation.stepDefs"},
    features = {"src/test/resources/automation/features"})
public class TestNGRunnerTest extends AbstractTestNGCucumberTests {}
