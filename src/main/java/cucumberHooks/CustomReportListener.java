package cucumberHooks;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helpers.ObjectProvider;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.HookTestStep;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;

public class CustomReportListener implements EventListener {

	public CustomReportListener() {

	}

	private ExtentSparkReporter spark;
	private ExtentReports extent;
	Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();
	ExtentTest excutiongScenario;
	ExtentTest step;
	ObjectProvider testContext;
	Scenario scenario;

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		/*
		 * :: is method reference , so this::collecTag means collectTags method in
		 * 'this' instance. Here we says runStarted method accepts or listens to
		 * TestRunStarted event type
		 */
		publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
		publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
		publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
		publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
		publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
		publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
	}

	/*
	 * Here we set argument type as TestRunStarted if you set anything else then the
	 * corresponding register shows error as it doesn't have a listener method that
	 * accepts the type specified in TestRunStarted.class
	 */
	// Here we create the reporter
	private void runStarted(TestRunStarted event) {
		spark = new ExtentSparkReporter("./test-output/ExecutionReports/ExtentReportResults.html");
		extent = new ExtentReports();
		spark.config().setTheme(Theme.STANDARD);

		// Create extent report instance with spark reporter
		extent.attachReporter(spark);
	}

	// This event is triggered when feature file is read
	// Here we create the feature node
	private void featureRead(TestSourceRead event) {
		String featureSource = event.getUri().toString();
		String featureName = featureSource.split(".*/")[1];
		if (feature.get(featureSource) == null) {
			feature.putIfAbsent(featureSource, extent.createTest(featureName));
		}
	}

	// This event is triggered when Test Case is started
	// here we create the scenario node
	private void ScenarioStarted(TestCaseStarted event) {
		String featureName = event.getTestCase().getUri().toString();
		excutiongScenario = feature.get(featureName).createNode(event.getTestCase().getName());
	}

	// Step started event
	// Here we creates the test node
	private void stepStarted(TestStepStarted event) {
		String stepName = " ";
		String keyword = "Hook Triggered";

		// We checks whether the event is from a hook or step
		if (event.getTestStep() instanceof PickleStepTestStep) {
			// TestStepStarted event implements PickleStepTestStep interface
			// Which have additional methods to interact with the event object
			// So we have to cast TestCase object to get those methods
			PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
			stepName = steps.getStep().getText();
			keyword = steps.getStep().getKeyword();
		} else {
			// Same with HookTestStep
			HookTestStep hoo = (HookTestStep) event.getTestStep();
			stepName = hoo.getHookType().name();
		}
		step = excutiongScenario.createNode(Given.class, stepName + " " + keyword);
	}

	// This is triggered when TestStep is finished
	private void stepFinished(TestStepFinished event) {
		if (event.getResult().getStatus().toString() == "PASSED") {
			step.log(Status.PASS, "Test Step Passed Successfully!!");
		} else if (event.getResult().getStatus().toString() == "SKIPPED") {
			step.log(Status.SKIP, "Test Step Was Skipped!!");
		} else {
			step.log(Status.FAIL, "Test Step Has Failed");
			
		}
	}

	// TestRunFinished event is triggered when all feature file executions are
	// completed
	private void runFinished(TestRunFinished event) {
		extent.flush();
	}
}