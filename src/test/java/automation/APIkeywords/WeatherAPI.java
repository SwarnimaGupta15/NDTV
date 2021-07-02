package automation.APIkeywords;

import static automation.base.BaseUI.logMessage;
import static automation.utils.YamlReader.getData;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;

import com.jayway.restassured.response.Response;

import automation.APIbase.BaseAPI;

public class WeatherAPI extends BaseAPI{
	HashMap<String,Object> jsonToMap=new HashMap<>();;
	public WeatherAPI(Response response){
		super(response);
	}

	public void accessWeatherAPI(String city) {
		String getURL=getData("WeatherAPI.service_url")+city+getData("WeatherAPI.api_key");
		response=rest_assured_Get_Method(getURL);	
	}

	public void verifyWeatherConditionKeyInResponse(String city) {		
		String condition=getValueFromJsonResponse("weather.main");
		Assert.assertNotNull(condition,"[ASSERT FAILED]: Weather condition returned is null value");
		logMessage("[ASSERT PASSED]: Weather condition returned through weather API for city "+city+" is "+condition);
	}
	
	public void verifyWeatherTemperatureKeyInResponse(String city) {
		String condition=getValueFromJsonResponse("main");
		Assert.assertNotNull(condition,"[ASSERT FAILED]: Weather condition returned is null value");
		logMessage("[ASSERT PASSED]: Weather condition returned through weather API for city "+city+" is "+condition);
	}

	public void verifyStatusCode(String city,int expected) {
		int statusActual=getStatusCodeFromResponse();
		Assert.assertEquals(statusActual,expected,"[ASSERT FAILED]: Status code returned is: "+statusActual);
		logMessage("[ASSERT PASSED]: "+expected+" Status Code returned on accessing weather API for city "+city);		
	}

	public void storeResponse() {
		jsonToMap=convertJsonToHashMap();	
	}

}
