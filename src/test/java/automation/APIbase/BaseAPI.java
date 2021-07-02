package automation.APIbase;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;

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
}
