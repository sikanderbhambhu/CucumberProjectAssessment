Feature: Test Background Feature
Description: The purpose of this feature is to test the Add To Cart functionality

	Background: User has landed on home page
		Given I am on the HomePage

	Scenario: Search for a product and add it to cart
		Given User enters text "Cannon Camera" in the search bar
		When User adds the very first item of list to the cart
		Then User should see the selected item on payment page
		
	Scenario Outline: Count the total categories of products
		Given User cliks on the category "<category>" link
		Then User should see the fashion items listed on the page
		
	Examples:
		| category |
		| Fashion |
		| Electronics |