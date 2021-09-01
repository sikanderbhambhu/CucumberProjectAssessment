package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import helpers.ObjectProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	ObjectProvider objectProvider;

	public Hooks(ObjectProvider objectProvider) {
		this.objectProvider = objectProvider;
	}

	@Before
	public void setUpBeforeScenario() throws IOException {
		objectProvider.getWebDriverManager().getDriver().manage().deleteAllCookies();
	}
	
	@After
	public void tearDownAfterScenario(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			byte[] screenShot = ((TakesScreenshot) objectProvider.getWebDriverManager().getDriver())
					.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenShot, "image/png", scenario.getName());
			System.out.println("Screen Shot Attached");
		}
		objectProvider.getWebDriverManager().quit();
	}
}
