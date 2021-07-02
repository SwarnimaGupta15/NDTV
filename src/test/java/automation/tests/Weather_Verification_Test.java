package automation.tests;

import static automation.utils.YamlReader.getData;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.base.TestSessionInitiator;


public class Weather_Verification_Test {
	TestSessionInitiator test;
	public static String city;

	@BeforeClass
	@Parameters("browser")
	public void start_test_session(@Optional("chrome") String browser) {
		test = new TestSessionInitiator(browser);
		city=getData("city");
	}

	@Test
	public void Test01_Launch_Application_And_Verify_User_Navigation_To_HomePage(){
		test.homePage.launchApplicationURL(getData("base_url"));
		test.homePage.verifyUserIsOnHomePage();
	}

	@Test
	public void Test02_Click_On_Weather_Tab_And_Verify_User_Navigates_To_Weather_Page(){
		test.homePage.clickOnTab("Weather");
		test.weatherPage.verifyUserIsOnWeatherPage();
	}

	@Test
	public void Test03_enter_City_And_Verify_City_Is_Displayed_In_Map(){
		test.weatherPage.enterCityInPinYourCityTextFieldAndSelect(city);
		test.weatherPage.verifySelectedCityIsDisplayedOnMap(city);
	}

	@Test
	public void Test04_Verify_City_Weather_Information_Displayed_In_Map(){
		test.weatherPage.storeCityWeatherInformation(city);
		test.weatherPage.verifyCityWeatherInformationIsDisplayedOnMap(city);
	}

	@AfterClass()
	public void stop_test_session(){
		test.closeTestSession();
	}
}
