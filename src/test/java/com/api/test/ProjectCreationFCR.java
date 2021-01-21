package com.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.api.model.ProjectCreationPOJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ProjectCreationFCR extends LoginFCR {

	Integer repoid_output;
	String reponame_output;
	public String fcr_reponame_inputted;
	public String fcr_repotype_inputted;
	
	@Test()
	public void FcrProjectCreation()

	{
		System.out.println("--Project Creation API hit--");
		fcr_reponame_inputted=prop.getProperty("FCR_reponame");
		fcr_repotype_inputted=prop.getProperty("FCR_repotype");
		
		ProjectCreationPOJO projectpojo= new ProjectCreationPOJO();
		projectpojo.setName(fcr_reponame_inputted);
		projectpojo.setRepoType(fcr_repotype_inputted);

		RequestSpecification ProjectCreationReq=given().urlEncodingEnabled(true).cookie(cookiedetail).
				header("Content-Type","application/json").
				
				when().body(projectpojo);
		
		Response ProjectCreationResponse= ProjectCreationReq.post("/add_repo");
	
	
		ValidatableResponse ValidatingProjectResponse = ProjectCreationResponse.then().log().all();
		repoid_output=ValidatingProjectResponse.extract().path("id");
		reponame_output=ValidatingProjectResponse.extract().path("name");
	System.out.println("Repo id created :"+repoid_output + " for RepoName: "+reponame_output);
	
	Assert.assertEquals(reponame_output,fcr_reponame_inputted );	

	}

}
