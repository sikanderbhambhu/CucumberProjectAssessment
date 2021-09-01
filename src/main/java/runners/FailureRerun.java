package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "cucumberHooks.CustomReportListener", "rerun:target/failed_scenarios.txt" },
				monochrome = true,
				features = { "@target/failed_scenarios.txt" }, glue = { "stepDefinitions" })
public class FailureRerun extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
