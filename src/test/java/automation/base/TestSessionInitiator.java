package automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import automation.keywords.HomePage;
import automation.keywords.WeatherPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSessionInitiator {

	public WebDriver driver;
	public HomePage homePage;
	public WeatherPage weatherPage;

	private void initPage(){
		homePage=new HomePage(driver);
		weatherPage=new WeatherPage(driver);
	}

	public TestSessionInitiator(String browserName) {		
		initBrowser(browserName);
		initPage();
	}

	public void initBrowser(String browserName){
		switch(browserName)
		{
		case "chrome": WebDriverManager.chromedriver().setup();	
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
					   driver=new ChromeDriver(options);
					   break;
		}
		maximizeWindow();
	}
	
	public void maximizeWindow() {
		try {
			driver.manage().window().maximize();
		} catch (WebDriverException ex) {
			System.out.println("[INFO]: Appears to be a mobile browser, Skipping maximizing");
		}
	}

	public void closeTestSession() {
		driver.quit();
	}
	
	
}
