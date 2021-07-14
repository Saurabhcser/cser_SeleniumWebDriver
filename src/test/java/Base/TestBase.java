package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ApplicationPages.TopNavigation;

public class TestBase {
	
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties Config;
	public static Logger log;
	public static WebDriverWait wait;
	public static Actions action;
	public static TopNavigation topnav;
	
	@BeforeSuite
	public void setUp(){
		if(driver==null)
		{
			log = Logger.getLogger("devpinoyLogger");
		
		
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Config=new Properties();
				Config.load(fis);
				log.debug("Config file got loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			//------------------------------------------------------------------------------------------
		
			if(Config.getProperty("browser").equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
				log.debug("Firefoxdriver launched");
			}else if(Config.getProperty("browser").equalsIgnoreCase("chrome")){
			
				System.setProperty("webdriver.chrome.driver",System.getProperty(System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe"));
				driver = new ChromeDriver();
				log.debug("Chromedriver launched");
			}
		
		
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			wait =new WebDriverWait(driver,5);
			action =new Actions(driver);
			topnav= new TopNavigation(driver);
		
			driver.get(Config.getProperty("testsiteurl"));
			log.debug("Application launched on selected browser");
		
		}
		
	}
	
	@AfterSuite
	public void tearDown() throws IOException{
		
		fis.close();
		driver.quit();
	}
	
	

}
