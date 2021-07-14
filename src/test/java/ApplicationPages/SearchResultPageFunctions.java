package ApplicationPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import Base.TestBase;

public class SearchResultPageFunctions extends TestBase{
	WebDriver driver;
	
	public SearchResultPageFunctions(WebDriver driver)
	{
		this.driver = driver;
		
		
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(how = How.XPATH,using="//*[@class='_10Ermr']")
	WebElement resultCount;
	
	
	
	@FindBys(@FindBy(xpath="//*[@class='_4rR01T']"))//test the list in findbys
    List<WebElement> AllData24;
	
	@FindBys(@FindBy(xpath="//*[@class='s1Q9rs']"))//test the list in findbys
    List<WebElement> AllData40;
	
	
	
	
	
	
	public String searchResultText() throws InterruptedException
	{
		Thread.sleep(5000);
		String search_resultString = resultCount.getText();
		return search_resultString;

	}
	
	public void searchResultCount(String text)
	{
		CharSequence temp =  resultCount.getText().subSequence(12, 14);
		int num =Integer.parseInt((String) temp);
		//System.out.println(Integer.parseInt((String) temp)); 
		
		if(num==24)
		{
			System.out.println("These are the Top20 results based on search text: "+text);
			Reporter.log("These are the Top20 results based on search text: "+text);

			int count=1;
			for(WebElement single24: AllData24)
			{ 
				if(count<=20)
				{count++;
					System.out.println(single24.getText());
					Reporter.log(single24.getText());
				}
				else
				{
					break;
				}
			}
		}
		else if(num==40)
		{
			System.out.println("These are the Top20 results based on search text: "+text);
			Reporter.log("These are the Top20 results based on search text: "+text);

			int count=1;
			for(WebElement single40: AllData40)
			{ 
				if(count<=20)
				{count++;
					System.out.println(single40.getText());
					Reporter.log(single40.getText());
				}
				else
				{
					break;
				}
				
			}
		}
	}
	
	public void searchAndOpenItem(String text) throws InterruptedException
	{
		CharSequence temp =  resultCount.getText().subSequence(12, 14);
		int numSearch =Integer.parseInt((String) temp);
		
		String tempItemNumber = Config.getProperty("OpenSearchItem");
		int itemNumber =Integer.parseInt(tempItemNumber);
		
		if(numSearch==24)
		{
			int count=1;
			for(WebElement single24: AllData24)
			{ 
				if(count==itemNumber)
				{
					System.out.println("Clicking "+itemNumber+"th item to open: "+single24.getText());
					Reporter.log("Clicking "+itemNumber+"th item to open: "+single24.getText());
					
					single24.click();
					Thread.sleep(1000);
					break;
				}
				count++;
			}
		}
		else if(numSearch==40)
		{ 
		int count=1;
		for(WebElement single40: AllData40)
		{ 
			if(count==itemNumber)
			{
				System.out.println("Clicking "+itemNumber+"th item to open: "+single40.getText());
				Reporter.log("Clicking "+itemNumber+"th item to open: "+single40.getText());
				
				single40.click();
				Thread.sleep(1000);
				break;
			}
			count++;
		}
	  }
	}
}
