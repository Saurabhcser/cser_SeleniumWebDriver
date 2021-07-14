package ApplicationPages;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Base.TestBase;
import Utilities.UtilityMethods;

public class LoginPageFunctions extends TestBase{
	WebDriver driver;
	
	public LoginPageFunctions(WebDriver driver)
	{
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
		
	
	@FindBy(how = How.XPATH, using ="//*[@class='_2IX_2- VJZDxU']")
	WebElement username;
		
	@FindBy(how = How.XPATH, using ="//*[@class='_2IX_2- _3mctLh VJZDxU']")
	WebElement password;
	
	@FindBy(how = How.XPATH, using ="//*[@class='_2KpZ6l _2HKlqd _3AWRsL']")
	WebElement loginButton;
	
	@FindBy(how = How.XPATH, using ="//*[@class='_2YULOR']")
	WebElement errorMessage;
	
	
	public void typeUsername(String uname)
	{
		username.sendKeys(uname);
	}
	
	public void typePassword(String pword)
	{
		password.sendKeys(pword);
	}
	
	public HomePageFunctions clickLogin()
	{
		loginButton.click();
		try {
			if(	errorMessage.isEnabled())
			{
				System.out.println("error came, "+errorMessage.getText());
				Reporter.log("Login failed with Error message: "+errorMessage.getText());
				topnav.closeLoginPanelOnLandingPage();
			}
		} 
		catch (NoSuchElementException e) 	{e.printStackTrace();} 
	
			
		HomePageFunctions hpf = new HomePageFunctions(driver);
		return hpf;
	}
	
	
	
}
