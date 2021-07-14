package ApplicationTestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ApplicationPages.HomePageFunctions;
import ApplicationPages.LoginPageFunctions;
import ApplicationPages.SearchResultPageFunctions;
import Base.TestBase;

public class LoginPageTestcase extends TestBase{
@BeforeTest
public void closeLoginPanel()
{
	topnav.closeLoginPanelOnLandingPage();
}
	
	@Test(priority=1)
	public void login() throws InterruptedException {
		LoginPageFunctions gustLogin = topnav.doLogin();
		String uname = Config.getProperty("username");
		String pword = Config.getProperty("password");
				
		gustLogin.typeUsername(uname);
		gustLogin.typePassword(pword);
				
		HomePageFunctions hpf = gustLogin.clickLogin();
		Assert.assertEquals(topnav.login_MyAccount_tabText(), "My Account");
	}
	
	
	@Test(priority=2)
	public void searchingText() throws InterruptedException  {
		String searchText= Config.getProperty("SearchText");
		SearchResultPageFunctions searchRelsultPage = topnav.doSearch(searchText);

		Assert.assertTrue(((SearchResultPageFunctions) searchRelsultPage).searchResultText().contains("\""+searchText+"\""));
				
		searchRelsultPage.searchResultCount(searchText);
	}
	
	@Test(priority=3)
	public void searchAndOpenItem() throws InterruptedException  {
		String searchText= Config.getProperty("SearchText");
		SearchResultPageFunctions searchRelsultPage = topnav.doSearch(searchText);
		

		Assert.assertTrue(((SearchResultPageFunctions) searchRelsultPage).searchResultText().contains("\""+searchText+"\""));
				
		searchRelsultPage.searchAndOpenItem(searchText);
	}
	
		
	@Test(priority=4, dependsOnMethods="login")
	public void logOut() throws InterruptedException {
		topnav.doLogout(action);
	}
}
