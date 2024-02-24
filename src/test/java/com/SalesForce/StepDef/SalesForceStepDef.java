package com.SalesForce.StepDef;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.SalesForce.Utilities.Constants;
import com.SalesForce.Utilities.Log4jUtility;
import com.SalesForce.Utilities.PropertiesUtility;
import com.SalesForce.pages.ForgotpasswordPage.CheckyourEmailPage;
import com.SalesForce.pages.ForgotpasswordPage.ForgotPasswordPage;
import com.SalesForce.pages.Login.SalesForceLoginPage;
import com.SalesForce.pages.home.SalesforceHomepage;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceStepDef {
	protected static Logger log;
	public  WebDriver driver;
	protected static Log4jUtility logObject=Log4jUtility.getInstance();
	SalesForceLoginPage login;
	SalesforceHomepage home;
	ForgotPasswordPage forgotpassword;
	CheckyourEmailPage checkEmail;
	
	
	public  void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "chrome":
		 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
		System.out.println(browserName+" browser opened");
	}
	
	public  void goToUrl(String url) {
		driver.get(url);
		log.info(url+ "is entered");
		
	}

	public  void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		System.out.println("before all");
		log=logObject.getLogger();
	}
	@Before
	public void setUpEachScenario() {
		
		launchBrowser("chrome");
		
	}
	@After
	public void tearDown() {
		closeBrowser();
	}
	@AfterStep
	public void after_each_step(Scenario sc) {
		if(sc.isFailed()){
			
		}
	}
	@Given("the url")
	public void the_url(){
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");
		String url= appProp.getProperty("url");
		goToUrl(url);
		System.out.println("driver in baseTest="+driver);
	}
	@When("user on {string}")
	public void user_on(String page) {
		 if(page.equalsIgnoreCase("loginpage"))
		    	login=new SalesForceLoginPage(driver);
		    else if(page.equalsIgnoreCase("homepage"))
		    	home=new SalesforceHomepage(driver);
		    else if(page.equalsIgnoreCase("forgotpasswordpage"))
		    	forgotpassword=new ForgotPasswordPage(driver);
		    else if(page.equalsIgnoreCase("checkyouremailpage"))
		    	checkEmail= new CheckyourEmailPage(driver);
	}

	@When("enter username as {string}")
	public void enter_username_as(String string) {
		login.enterUserName(string);
	}

	@When("enter password as {string}")
	public void enter_password_as(String string) {
	    login.enterPassword(string);
	}

	@When("click login button")
	public void click_login_button() {
		driver= login.clickLoginButton();
	}
	
	@Then("should see the error message")
	public void should_see_the_error_message() {
		String act=login.getTextFromError();
		String exp="Please enter your password.";
		assertEquals(act,exp);
		
	}
	


@When("click remember me checkbox")
public void click_remember_me_checkbox() {
    login.clickRememberMe();
}
@Then("should see the home page")
public void should_see_the_home_page() {
   String actu=home.getTitleHome();
   String expe="Home Page ~ Salesforce - Developer Edition";
   assertEquals(actu, expe);
}
@Then("click logout")
public void click_logout() {
   home.clicklogout();
}
@Then("the username field should display the username {string}")
public void the_username_field_should_display_the_username(String string) {
   assertEquals(login.getTextFromUsernameField(), string);
}

@Then("should see different error message")
public void should_see_different_error_message() {
	String expected="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
   assertEquals(login.getTextfromIvalidUsernameAndPassword(), expected);
}

@When("click forgotpassword")
public void click_forgotpassword() {
    login.clickForgotPassword();
}
@When("enter usernameField as {string}")
public void enter_username_field_as(String string) {
    forgotpassword.enterUsename(string);
}
@When("click continue button")
public void click_continue_button() throws InterruptedException {
   forgotpassword.clickContinue();
}
@Then("should see the password reset message")
public void should_see_the_password_reset_message() throws InterruptedException {
	String actmes=checkEmail.getTitleofPage();
	String expPage="Check Your Email | Salesforce";
	assertEquals(actmes,expPage);

  
    
}
	}


	


	


