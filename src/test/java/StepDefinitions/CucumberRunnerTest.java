package StepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"html:target/cucumber-html-report"},
		tags = "@SetInnertTextOnDivElement",
		glue= {"StepDefinitions"},
		monochrome = false,
		features = {"src/test/resources/Resources"},
		dryRun = false	
			
		)


public class CucumberRunnerTest {

	

	
	
}
