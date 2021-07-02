package automation.APIbase;

import com.jayway.restassured.response.Response;

import automation.APIkeywords.WeatherAPI;

public class ServiceTestInitiator {
	Response response;
	public WeatherAPI weatherAPI;

	private void initServiceObjects(){
		weatherAPI = new WeatherAPI(response);
	}

	public ServiceTestInitiator() {		
		initServiceObjects();
	}
}
