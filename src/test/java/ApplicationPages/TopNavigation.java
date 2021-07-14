package ApplicationPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.TestBase;

public class TopNavigation {

WebDriver driver;

	public TopNavigation(WebDriver driver)
	{
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(how = How.XPATH, using="//*[@class='_2KpZ6l _2doB4z']")//login panel which comes first on screen
	WebElement crossOnLoginPanel;
	
	@FindBy(how = How.XPATH,using="//*[@class='_2xm1JU']")
	WebElement homeLink;
	
	@FindBy(how = How.XPATH, using = "//*[@class='_3704LK']")
	WebElement searchBox;
	
	@FindBy(how = How.XPATH, using="//*[@class='L0Z3Pu']")
	WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//*[@class='pO9syL undefined']//child::li[1]")
	WebElement myProfileLink;
	
	@FindBy(how = How.XPATH, using = "//*[@class='pO9syL undefined']//child::li[4]")
	WebElement myOrderLink;
	
	@FindBy(how = How.XPATH, using = "//*[@class='pO9syL undefined']//child::li[10]")
	WebElement logoutButton;
	
	@FindBy(how = How.XPATH, using = "//*[@class='_3SkBxJ']")
	WebElement cartButton;
	
	@FindBys(@FindBy(xpath="//*[@class='_1psGvi _3BvnxG']"))//login/myAccount and MORE
    List<WebElement> LoginMyAccountMore;
	


	
	public void closeLoginPanelOnLandingPage()
	{
		crossOnLoginPanel.click();
	}
	
	public SearchResultPageFunctions doSearch(String searchText) 
	{
		if(searchBox.getText()==null)
		{
			searchBox.sendKeys(searchText);
			searchButton.click();
		}
		else{
			searchBox.clear();
			searchBox.sendKeys(searchText);
			searchButton.click();
		}
		SearchResultPageFunctions searchResult = new SearchResultPageFunctions(driver);
		return searchResult;
	}
	
	
	
	public LoginPageFunctions doLogin() 
	{
		//guestWantsToLogin.click();
		for(WebElement loginLink: LoginMyAccountMore)
		{
			String text= loginLink.getText();
			if(text.equalsIgnoreCase("Login"))
			{
				loginLink.click();
				break;
			}
		}
		LoginPageFunctions loginAsGuest = new LoginPageFunctions(driver);
		return loginAsGuest;
	}
	
	public void doLogout(Actions action) throws InterruptedException
	{	Thread.sleep(5000);
	
		for(WebElement loginLink: LoginMyAccountMore)
		{
		    String text= loginLink.getText();
		    if(text.equalsIgnoreCase("My Account"))
		    {
		    	action.moveToElement(loginLink).perform();
				
				Thread.sleep(1000);
				
				logoutButton.click();
		    }
	    }
		
	}
	
		
	public void goToCart() 
	{
		cartButton.click();
	}
	
	public String login_MyAccount_tabText()
	{
		String text= LoginMyAccountMore.get(0).getText();
		return text;
	}
}
