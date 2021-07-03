package automation.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.APIbase.ServiceTestInitiator;
import automation.utils.InvalidTemperatureValueException;

public class Weather_Verification_Compare_Data_Test{
	ServiceTestInitiator serviceTest;
	
	@BeforeClass
	@Parameters("browser")
	public void initialize_service_Object() {
		serviceTest=new ServiceTestInitiator();
	}
	
	@Test
	public void Test01_Compare_Weather_Condition_Data_From_UI_And_API(){
		serviceTest.weatherAPI.compareWeatherConditionDataFromUIAndAPI();
    }
	
	@Test
	public void Test02_Compare_Weather_Temperature_Data_From_UI_And_API() throws InvalidTemperatureValueException{
		serviceTest.weatherAPI.compareWeatherTemperatureDataFromUIAndAPI();
    }
}
