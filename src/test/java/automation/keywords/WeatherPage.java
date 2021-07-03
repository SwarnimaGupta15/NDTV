package automation.keywords;

import static automation.utils.PropFileHandler.properties;
import static automation.utils.YamlReader.getData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import automation.base.GetPage;

public class WeatherPage extends GetPage {
	public static HashMap<String,String> cityInfo;
	
	public WeatherPage(WebDriver driver) {
		super(driver, "WeatherPage");
	}

	public void verifyUserIsOnWeatherPage() {
		String expectedTitle=getData("title.weatherPage");
		boolean flag=verifyUserIsOnDesiredPageByTitle(expectedTitle);
		Assert.assertTrue(flag,"[ASSERT FAILED]: Page title found on Weather page is incorrect. "+
				"Expected to contain: "+expectedTitle);
		logMessage("[ASSERT PASSED]: Page title found on Weather page is correct.");
	}

	public void enterCityInPinYourCityTextFieldAndSelect(String city){
		wait.applyFluentWait(getLocator("pinCityField")).sendKeys(Keys.F5);
		wait.applyFluentWait(getLocator("pinCityField")).sendKeys(city);
		element("pinCityField").sendKeys(Keys.ENTER);
		selectCheckboxForCityIfNotSelected(city);
	}

	public void selectCheckboxForCityIfNotSelected(String city){
		boolean isSel=elemconstructed_dynamically("cityCheckbox", city).isSelected();
		if(!isSel)
			elemconstructed_dynamically("cityCheckbox", city).click();	
	}

	public void verifySelectedCityIsDisplayedOnMap(String city){
		boolean res=elemconstructed_dynamically("mapCity",city).isDisplayed();
		Assert.assertTrue(res,"[ASSERT FAILED]: Selected city is not dipalyed on map. City is: "+city);
		logMessage("[ASSERT PASSED]: Selected city is displayed on map");
	}

	public void storeCityWeatherInformation(String city){
		elemconstructed_dynamically("mapCity",city).click();
		cityInfo=new HashMap<>();
		List<WebElement> infoList=elements("contentCityLeaflet");
		for(WebElement e:infoList){
			String temp[]=e.getText().split(":");
			cityInfo.put(temp[0].trim(), temp[1].trim());
		}
		properties.writeHahMaptoDataPropertyFile(cityInfo);
	}	

	public void verifyCityWeatherInformationIsDisplayedOnMap(String city) {
		ArrayList<String> expected=new ArrayList<String>();
		String temp[]=getData("cityWeatherKeys").split(",");
		for(String a:temp)
			expected.add(a);
		
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(cityInfo.keySet().toString(), expected.toString(),"[ASSERT FAILED]: Fields in city weather information is incorrect"
				+ " Found: "+cityInfo.keySet().toString()+" Expected: "+expected);
		sa.assertNotNull(cityInfo.get("Temp in Degrees"),"[ASSERT FAILED]: Temperature in degrees value is empty");
		sa.assertNotNull(cityInfo.get("Temp in Fahrenheit"),"[ASSERT FAILED]: Temperature in fahrenheit value is empty");
		sa.assertAll();
		logMessage("[ASSERT PASSED]: Fields in city weather information is correct and temperature is displayed appropriately for city: "+city);
	}

	
}
