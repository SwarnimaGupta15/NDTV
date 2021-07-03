package automation.APIbase;

import static com.jayway.restassured.RestAssured.given;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class BaseAPI {

	protected Response response;

	public BaseAPI(Response response){
		this.response=response;
	}

	public String getValueFromJsonResponse(String key){
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		String value = jsonPath.getString(key);
		return value;
	}	

	public Response rest_assured_Get_Method(String getURL){		
		response= given().header("Accept", "application/json")
				.when().get(getURL)
				.then().extract().response();
		return response;
	}

	public int getStatusCodeFromResponse(){
		int status=response.getStatusCode();
		return status;
	}

	public HashMap<String,Object> convertJsonToHashMap(){
		String json=response.getBody().asString();
		HashMap<String,Object> jsonToMap=new HashMap<>();
		ObjectMapper map=new ObjectMapper();		
		try {
			jsonToMap=map.readValue(json, new TypeReference<HashMap<String,Object>>() {	});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonToMap;
	}

	protected void writeJsonToFile(String filePath, String jsonFormat) {
		try{
			FileWriter file = new FileWriter(filePath);
			file.write(jsonFormat);
			file.flush();
			file.close();
		} catch (IOException e) {	e.printStackTrace();	}
	}

	public JSONArray readJSONArrayFromJSONFile(String filePath, String key) {
		JSONParser parser=new JSONParser();
		try{
			Object obj=parser.parse(new FileReader(filePath));
			JSONObject jsonObj=(JSONObject) obj;
			JSONArray value=(JSONArray) jsonObj.get(key);
			return value;
		}catch(Exception e){}
		return null;
	}

	public JSONObject readJSONObjectFromJSONFile(String filePath, String key) {
		JSONParser parser=new JSONParser();
		try{
			Object obj=parser.parse(new FileReader(filePath));
			JSONObject jsonObj=(JSONObject) obj;
			return (JSONObject) jsonObj.get(key);
		}catch(Exception e){}
		return null;
	}

	public String convertFromKelvinToCelcius(String temp) {
        float kelvinValue=Float.valueOf(temp);
        float celciusValue=(float) (kelvinValue-273.15);
		return String.valueOf(celciusValue);
	}
}
