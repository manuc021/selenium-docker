package com.newtours.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newtours.pages.FindFlightPage;
import com.newtours.pages.FlightConfirmationPage;
import com.newtours.pages.FlightDetailPage;
import com.newtours.pages.RegistrationConfirmationPage;
import com.newtours.pages.RegistrationPage;
import com.tests.BaseTest;

public class BookFlightTest extends BaseTest{

	private String noOfPassengers;
	private String expectedPrice;

	@BeforeTest
	@Parameters({"noOfPassengers", "expectedPrice"})
	public void setup(String noOfPassengers,String expectedPrice) {
		this.noOfPassengers = noOfPassengers;
		this.expectedPrice = expectedPrice;
	}

	@Test
	public void registrationPage() {

		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();
		registrationPage.enterUserDetail("selenium", "docker");
		registrationPage.enterUserCredential("selenium", "docker");
		registrationPage.submit();
	}

	@Test(dependsOnMethods = "registrationPage")
	public void registrationConfirmationPage() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		registrationConfirmationPage.gotToFlightDetailPage();
	}

	@Test(dependsOnMethods = "registrationConfirmationPage")
	public void flightDetailsPage() {
		FlightDetailPage flightDetailsPage = new FlightDetailPage(driver);
		flightDetailsPage.selectPassengers(noOfPassengers);
		flightDetailsPage.goToFindFlightsPage();
	}

	@Test(dependsOnMethods = "flightDetailsPage")
	public void findFlightPage() {
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.goToFlightConfirmationPage();
	}

	@Test(dependsOnMethods = "findFlightPage")
	public void flightConfirmationPage() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		String actualPrice = flightConfirmationPage.getPrice();
		Assert.assertEquals(actualPrice, expectedPrice);
	}
}
