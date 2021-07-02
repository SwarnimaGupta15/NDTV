package automation.base;

import static automation.utils.ConfigPropertyReader.getProperty;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import automation.utils.SeleniumWait;

public class BaseUI {
	protected WebDriver driver;
	protected SeleniumWait wait;
	private String pageName;

	protected BaseUI(WebDriver driver, String pageName) {
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty("timeout")));
	}

	protected String logMessage(String message) {
		Reporter.log(message, true);
		return message;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void launchApplicationURL(String URL){
		driver.get(URL);		
	}
	
	protected void handleAlert() {
		try {
			wait.waitForAlertToBePresent().accept();
			logMessage("Alert handled..");
			//driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}
	
	
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void clickByJavascript(WebElement element) {
		wait.waitForElementToBeVisible(element);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}
	
	public boolean verifyUserIsOnDesiredPageByTitle(String expPageTitle) {
		wait.waitForPageTitleToContain(expPageTitle);
		String title=getPageTitle();
		logMessage("[INFO]: Title Found: "+title);
		boolean flag=(title.contains(expPageTitle))?  true:  false;
        return flag;
	}
	
}
