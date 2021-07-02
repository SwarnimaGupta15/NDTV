package automation.keywords;

import static automation.utils.YamlReader.getData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import automation.base.GetPage;


public class HomePage extends GetPage {
	
	public HomePage(WebDriver driver) {
		super(driver, "HomePage");
	}

	public void acceptAlert(){
		wait.waitForPageToLoadCompletely();
		wait.applyFluentWait(getLocator("allowBtn")).click();
		
	}
	
	public void verifyUserIsOnHomePage() {
		String expectedTitle=getData("title.homePage");
		boolean flag=verifyUserIsOnDesiredPageByTitle(expectedTitle);
		Assert.assertTrue(flag,"[ASSERT FAILED]: Page title found on Home page is incorrect. "+
				"Expected to contain: "+expectedTitle);
		logMessage("[ASSERT PASSED]: Page title found on Home page is correct.");
	}

	public void clickOnTab(String tab) {
		element("moreMenuOptionButton").click();
		elemconstructed_dynamically("menuOptions", tab).click();
	}


}
