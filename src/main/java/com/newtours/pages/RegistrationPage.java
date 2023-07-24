package com.newtours.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(name = "firstName")
	private WebElement firstNameTxt;

	@FindBy(name = "lastName")
	private WebElement lastNameTxt;

	@FindBy(name = "phone")
	private WebElement phoneTxt;

	@FindBy(name = "userName")
	private WebElement emailTxt;

	@FindBy(name = "email")
	private WebElement userNameTxt;

	@FindBy(name = "password")
	private WebElement passwordTxt;

	@FindBy(name = "confirmPassword")
	private WebElement confirmPasswordTxt;

	@FindBy(name = "register")
	private WebElement submitButton;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	public void goTo() {
		this.driver.get("https://vins-udemy.s3.amazonaws.com/docker/docker-book-flight.html");
		this.wait.until(ExpectedConditions.visibilityOf(firstNameTxt));
	}

	public void enterUserDetail(String firstName, String lastName) {
		this.firstNameTxt.sendKeys(firstName);
		this.userNameTxt.sendKeys(lastName);
	}

	public void enterUserCredential(String userName, String password) {
		this.userNameTxt.sendKeys(userName);
		this.passwordTxt.sendKeys(password);
		this.confirmPasswordTxt.sendKeys(password);
	}

	public void submit() {
		this.submitButton.click();
	}
}
