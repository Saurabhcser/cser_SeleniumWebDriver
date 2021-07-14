package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import Base.TestBase;

public class UtilityMethods {

	
	public static void captureScreenshot() throws IOException{
		
		
		File scrFile = ((TakesScreenshot)TestBase.driver).getScreenshotAs(OutputType.FILE);
				
		GregorianCalendar cal =new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int months=month+1;
		int day = cal.get(Calendar.DATE);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		
		String screenshotLocation = System.getProperty("user.dir")+"\\src\\test\\resources\\ScreenShots\\"+year+"-"+months+"-"+day+" "+hour+"-"+min+"-"+sec+".png";
		
		FileHandler.copy(scrFile, new File(screenshotLocation));
		Reporter.log("screenshot file location and name is "+screenshotLocation);
		
	}
	
	
	
	
	
}
