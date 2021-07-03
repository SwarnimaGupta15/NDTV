package automation.tests;
import static automation.utils.YamlReader.getData;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.APIbase.ServiceTestInitiator;

public class Weather_Verification_API_Test {
	public static String city;
	ServiceTestInitiator serviceTest;

	@BeforeClass
	public void start_Service_Test(){		
		serviceTest=new ServiceTestInitiator();
		city=getData("city");
	}
	
	@Test
	public void Test01_verify_Access_To_Weather_API_For_invalid_CityName_Input_And_Check_Response_Status_Code() {	
		serviceTest.weatherAPI.accessWeatherAPI(getData("invalidCityName"));
		serviceTest.weatherAPI.verifyStatusCode(getData("invalidCityName"),404);
	}
	
	@Test
	public void Test02_verify_Access_To_Weather_API_And_Check_Response_Status_Code() {	
		serviceTest.weatherAPI.accessWeatherAPI(city);
		serviceTest.weatherAPI.verifyStatusCode(city,200);
	}
	
	@Test
	public void Test03_verify_Weather_Condition_In_WeatherAPI_Response() {	
		serviceTest.weatherAPI.storeResponse();
		serviceTest.weatherAPI.verifyWeatherConditionKeyInResponse(city);
	}
	
	@Test
	public void Test04_verify_Weather_Temperature_In_WeatherAPI_Response() {	
		serviceTest.weatherAPI.verifyWeatherTemperatureKeyInResponse(city);
	}	
	
}
