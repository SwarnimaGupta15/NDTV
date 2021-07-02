package automation.tests;
import static automation.utils.YamlReader.getData;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.APIbase.ServiceTestInitiator;

public class Weather_Verification_API_Test {
	public static String city;
	ServiceTestInitiator test;

	@BeforeClass
	public void start_Service_Test(){		
		test=new ServiceTestInitiator();
		city=getData("city");
	}
	
	@Test
	public void Test01_verify_Access_To_Weather_API_For_invalid_CityName_Input_And_Check_Response_Status_Code() {	
		test.weatherAPI.accessWeatherAPI(getData("invalidCityName"));
		test.weatherAPI.verifyStatusCode(getData("invalidCityName"),404);
	}
	
	@Test
	public void Test02_verify_Access_To_Weather_API_And_Check_Response_Status_Code() {	
		test.weatherAPI.accessWeatherAPI(city);
		test.weatherAPI.verifyStatusCode(city,200);
	}
	
	@Test
	public void Test03_verify_Weather_Condition_In_WeatherAPI_Response() {	
		test.weatherAPI.storeResponse();
		test.weatherAPI.verifyWeatherConditionKeyInResponse(city);
	}
	
	@Test
	public void Test04_verify_Weather_Temperature_In_WeatherAPI_Response() {	
		test.weatherAPI.verifyWeatherTemperatureKeyInResponse(city);
	}	
	
}
