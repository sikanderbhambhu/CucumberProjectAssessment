package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.ConfigFileReader;

public class HomePageObjects extends BasePageObject {

	@FindBy(xpath = "(//ul[@class = 'hl-cat-nav__container']//li//a[text() = 'Fashion'])[1]")
	private WebElement fashionLink;

	@FindBy(xpath = "//div[@class = 'b-visualnav__grid']//a")
	private List<WebElement> categoryItems;
	
	@FindBy(xpath = "//label[text() = 'Enter your search keyword']//following-sibling::input")
	private WebElement searchBox;
	
	@FindBy(xpath = "//input[@type = 'submit']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//ul[@class = 'srp-results srp-list clearfix']//li[1]//div[@class = 's-item__image-section']")
	private WebElement firstItemFromSearchedList;
	
	@FindBy(xpath = "//a[contains(text(), 'Add to cart')]")
	private WebElement addToCart;
		
	@FindBy(xpath = "//div[@class = 'listsummary']")
	private WebElement itemsListAddedToCart;
	
	
	public HomePageObjects(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		initialise(this);
	}
		
	public void launchHomePage() {
		try {
			String url = ConfigFileReader.getHomePageURL();
			driver.get(url);
			waitForPageToLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchForItem(String itemNameToSearch) {
		enterText(searchBox, itemNameToSearch);
		Click(searchBtn);
	}
	
	public void selectItemFromList() {
		Click(firstItemFromSearchedList);
	}
	
	public void addSelectedItemToCart() {
		Click(addToCart);
	}
	
	public boolean sizeOfAddedItemsListOnPaymentPage() {
		return itemsListAddedToCart.isDisplayed();
	}
	
	public void clickFashionLink() {
		Click(fashionLink);
	}

	public void clickElectronicsLink() {
		Click(fashionLink);
	}
	
	public void clickOnCategoryLink(String categoryName) {
		if(categoryName.equalsIgnoreCase("Electronics")) {
			clickElectronicsLink();
		} else {
			clickFashionLink();
		}
	}
	
	public int countOfFashionCategories() {
		return categoryItems.size();
	}
}







