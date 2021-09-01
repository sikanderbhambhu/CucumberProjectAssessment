package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "cucumberHooks.CustomReportListener", "rerun:target/failed_scenarios.txt" },
				monochrome = true,
				features = { "src/test/java/features" }, glue = { "stepDefinitions" })
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}

//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

//import cucumber.api.testng.CucumberFeatureWrapper;
//import cucumber.api.testng.TestNGCucumberRunner;
//import io.cucumber.testng.CucumberOptions;

//@SuppressWarnings({ "unused", "deprecation" })
//@CucumberOptions(features = {"src/test/java/features" }, glue = { "stepDefinitions" })
//public class TestRunner extends AbstractTestNGCucumberTests {
//
//}

//@SuppressWarnings("deprecation")
//@CucumberOptions(features = {"src/test/java/features" }, glue = { "stepDefinitions" })
//public class TestRunner {
//	private TestNGCucumberRunner testNGCucumberRunner;
//
//	@BeforeClass(alwaysRun = true)
//	public void setUpClass() throws Exception {
//		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//	}
//
////	@Test(groups = "Cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
////	public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
////		testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
////	}
////
////	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
////	public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
////		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
////	}
////
////	@DataProvider
////	public Object[][] scenarios() {
////		return testNGCucumberRunner.provideScenarios();
////	}
//
//	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
//	public void feature(CucumberFeatureWrapper cucumberFeature) {
//		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
//	}
//
//	@DataProvider
//	public Object[][] features() {
//		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//		return testNGCucumberRunner.provideFeatures();
//	}
//
//	@AfterClass(alwaysRun = true)
//	public void tearDownClass() throws Exception {
//		testNGCucumberRunner.finish();
//	}
//}
