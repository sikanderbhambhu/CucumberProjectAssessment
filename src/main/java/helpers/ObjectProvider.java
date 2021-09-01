package helpers;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;

import managers.DriverManager;
import managers.PageObjectManager;

public class ObjectProvider {

	private DriverManager driverManager;
	private PageObjectManager pageObjectManager;

	public ObjectProvider() throws IOException {
		driverManager = new DriverManager();
		WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10);
		pageObjectManager = new PageObjectManager(driverManager.getDriver(), wait);
	}

	public DriverManager getWebDriverManager() {
		return driverManager;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}

}