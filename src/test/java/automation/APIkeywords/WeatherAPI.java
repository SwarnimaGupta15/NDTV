package automation.APIkeywords;

import static automation.base.BaseUI.logMessage;
import static automation.utils.PropFileHandler.properties;
import static automation.utils.YamlReader.getData;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;

import com.jayway.restassured.response.Response;

import automation.APIbase.BaseAPI;
import automation.utils.InvalidTemperatureValueException;

public class WeatherAPI extends BaseAPI{
	public static HashMap<String,Object> jsonToMap=new HashMap<>();
	public static String filePath="src/test/resources/testData/weatherAPI.json";
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
		String temp=getValueFromJsonResponse("main.temp");
		Assert.assertNotNull(temp,"[ASSERT FAILED]: Weather temperature returned is null value");
		logMessage("[ASSERT PASSED]: Weather temperature returned through weather API for city "+city+" is "+temp);
	}

	public void verifyStatusCode(String city,int expected) {
		int statusActual=getStatusCodeFromResponse();
		Assert.assertEquals(statusActual,expected,"[ASSERT FAILED]: Status code returned is: "+statusActual);
		logMessage("[ASSERT PASSED]: "+expected+" Status Code returned on accessing weather API for city "+city);		
	}

	public void storeResponse() {
		jsonToMap=convertJsonToHashMap();	
		writeJsonToFile(filePath,response.getBody().asString());
	}

	public String getWeatherConditionDataFromJSONResponse(){
		JSONArray weather=readJSONArrayFromJSONFile(filePath, "weather");
		JSONObject obj=(JSONObject) weather.get(0);
		String condition=(String) obj.get("main");
		return condition;
	}

	public void compareWeatherConditionDataFromUIAndAPI() {
		String apiData=getWeatherConditionDataFromJSONResponse();
		String uiData=properties.readProperty("Condition");
		checkIfDataIsEmpty(apiData,uiData);
		Assert.assertEquals(apiData, uiData,"[ASSERT FAILED]: Condition value is different in API and UI. "+
		       "Found: API response: "+apiData+" UI data: "+uiData);
		logMessage("[ASSERT PASSED]: Weather condition value is matching in UI and API response");
	}

	public void compareWeatherTemperatureDataFromUIAndAPI() throws InvalidTemperatureValueException {
		String apiData=getWeatherTemepratureDataFromJSONResponse();
		String uiData=properties.readProperty("Temp in Degrees");
		checkIfDataIsEmpty(apiData,uiData);
		String tempCelcius=convertFromKelvinToCelcius(apiData);
		String offset=getData("offSetForTemperature");
		
		boolean flag=((-1)*(Double.valueOf(tempCelcius)-Double.valueOf(uiData))<Double.valueOf(offset));		
		
		if(!flag){
			throw new InvalidTemperatureValueException();
		}
		else{
			Assert.assertTrue(flag);
			logMessage("[ASSERT PASSED]: Weather Temperature value in UI and API response is within the offset difference: "+offset);
		}
	}

	private String getWeatherTemepratureDataFromJSONResponse()  {
		JSONObject obj=readJSONObjectFromJSONFile(filePath, "main");
		double temp=(double) obj.get("temp");
		return String.valueOf(temp);
	}

	public void checkIfDataIsEmpty(String apiData, String uiData){
		if(uiData.isEmpty() || apiData.isEmpty())
			throw new SkipException("Data received from either UI or API is emptyp");
	}



}
