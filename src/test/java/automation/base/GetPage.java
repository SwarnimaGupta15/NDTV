package automation.base;

import static org.testng.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetPage extends BaseUI {
	protected WebDriver webdriver;
	String pageName;
	static String filepath = "src/test/resources/PageObjectRepository/";
	
	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
	}
		
	protected WebElement element(String elementToken) {
		WebElement elem = null;
		elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken)));
		return elem;
	}
	
	protected List<WebElement> elements(String elementToken) {
		try {
			return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken)));
		} catch (Exception e) {
			return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken)));
		}

	}
	
	protected WebElement elemconstructed_dynamically(String elementToken, String replacement) {
		WebElement elem = null;		
		elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocatorByReplacing(elementToken, replacement)));
		return elem;
	}
	
	protected By getLocatorByReplacing(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = StringUtils.replace(locator[2], "$", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}
	
	protected By getLocator(String elementToken) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		return getBy(locator[1].trim(), locator[2].trim());
	}
	
	private By getBy(String locatorType, String locatorValue) {
		switch (locatorType) {
		case "id":
			return By.id(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		case "css":
			return By.cssSelector(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
			return By.className(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		case "tagName":
			return By.tagName(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	public static String[] getELementFromFile(String pageName,String elementName) {
		try {
			FileReader specFile = new FileReader(filepath + pageName + ".spec");
			return getElement(specFile, elementName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String[] getElement(FileReader specFile, String elementName)
			throws Exception {

		ArrayList<String> elementLines = getSpecSection(specFile);
		for (String elementLine : elementLines) {
			if (elementLine.startsWith(elementName)) {
				return elementLine.split(" ", 3);
			}
		}
		throw new Exception();
	}
	
	private static ArrayList<String> getSpecSection(FileReader specfile) {
		String readBuff = null;
		ArrayList<String> elementLines = new ArrayList<String>();

		try {
			BufferedReader buff = new BufferedReader(specfile);
			try {
				boolean flag = false;
				readBuff = buff.readLine();
				while ((readBuff = buff.readLine()) != null) {
					if (readBuff.startsWith("========")) {
						flag = !flag;
					}
					if (flag) {
						elementLines.add(readBuff.trim().replaceAll("[ \t]+",
								" "));
					}
					if (!elementLines.isEmpty() && !flag) {
						break;
					}
				}
			} finally {
				buff.close();
				if (elementLines.get(0).startsWith("========")) {
					elementLines.remove(0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out
					.println("Spec File not found at location :- " + filepath);
		} catch (IOException e) {
			System.out.println("exceptional case");
		}
		return elementLines;
	}



}
