package com.api.test;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class APIKeyFCR extends ProjectCreationFCR {
	public String FCRApiKey;

	@Test(dependsOnMethods = {"FcrProjectCreation"})
	public void FetchFcrApiKey()
	{
		System.out.println("--Generate FCR key API hit--");
		Response fetchApiKeyResponse = given().cookie(cookiedetail).header("RepoId", repoid_output).when()
				.get("/user/api_key");
		int APIkeystatuscode = fetchApiKeyResponse.getStatusCode();
		
		if (APIkeystatuscode != 200) {
			System.out.println("Not able to fetch FCR api key for project whose repo id is : " + fcr_reponame_inputted
					+ " as status code for api is :  " + APIkeystatuscode);
		}
		
		else
		{
			ValidatableResponse ValidatingFcrApiKey = fetchApiKeyResponse.then().log().all();
			FCRApiKey=ValidatingFcrApiKey.extract().path("token");
			System.out.println("API key gets fetched for repoid :"+fcr_reponame_inputted + " and its value is : "+FCRApiKey);

}
	}
}
