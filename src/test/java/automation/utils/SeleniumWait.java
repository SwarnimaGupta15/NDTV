package automation.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class SeleniumWait {
	WebDriver driver;
	WebDriverWait wait;
	FluentWait fluentWait;
	int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout;
		this.fluentWait=new FluentWait<WebDriver>(driver)
				.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS);		
	}

	public WebElement applyFluentWait(final By locator){
		WebElement element=null;
		element=wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver){
				if(getWhenVisible(locator)!=null) {
					return getWhenVisible(locator);
				}
			   return getWhenVisible(locator);
			}
		});
		return element;
	}
	
	public WebElement applyFluentWaitUntilExpectedTextAppears(final By locator, final String input){
		WebElement ele=null;	
		ele=wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver){
					while(getWhenVisible(locator)!=null) {
						if(getWhenVisible(locator).getText().trim().contains(input))
							return getWhenVisible(locator);
					}
				return getWhenVisible(locator);	
			}
		});
		return ele;
	}
	
	public WebElement getWhenVisible(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions
				.visibilityOfElementLocated(locator));
		return element;
	}
	
	public WebElement getWhenClickable(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
	}

	public boolean waitForPageTitleToContain(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
	}
	
	public Alert waitForAlertToBePresent() {
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebElement waitForElementToBeVisible(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	}


	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
		return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForPageToLoadCompletely() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*")));
	}


}
