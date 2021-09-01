package stepDefinitions;

import org.testng.asserts.SoftAssert;

import helpers.ObjectProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePageObjects;

public class HomePage {

	HomePageObjects homePage;
	SoftAssert softAssert;
	ObjectProvider testContext;

	public HomePage(ObjectProvider context) {
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage();
		softAssert = new SoftAssert();
	}

	@Given("^I am on the HomePage$")
	public void i_am_on_the_HomePage() throws Throwable {
		homePage.launchHomePage();
	}

	@Given("^User enters text \"([^\"]*)\" in the search bar$")
	public void user_enters_text_in_the_search_bar(String itemName) throws Throwable {
		homePage.searchForItem(itemName);
		homePage.selectItemFromList();
	}

	@When("^User adds the very first item of list to the cart$")
	public void user_adds_the_very_first_item_of_list_to_the_cart() throws Throwable {
		homePage.addSelectedItemToCart();
	}

	@Then("^User should see the selected item on payment page$")
	public void user_should_see_the_selected_item_on_payment_page() throws Throwable {
		homePage.sizeOfAddedItemsListOnPaymentPage();
	}
	
	@Given("User cliks on the category {string} link")
	public void user_cliks_on_the_category_link(String categoryName) {
		homePage.clickOnCategoryLink(categoryName);
	}
	
	@Then("User should see the fashion items listed on the page")
	public void user_should_see_the_fashion_items_listed_on_the_page() {
		int countOfFashionProductsDisplayed = homePage.countOfFashionCategories();
		softAssert.assertEquals(countOfFashionProductsDisplayed, 10);
		softAssert.assertAll();
	}
}
