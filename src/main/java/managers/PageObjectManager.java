package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePageObjects;

public class PageObjectManager {

	private WebDriver driver;
	private WebDriverWait wait;
	private HomePageObjects homePage;

	public PageObjectManager(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public HomePageObjects getHomePage() {
		return (homePage == null) ? homePage = new HomePageObjects(driver, wait) : homePage;
	}

}