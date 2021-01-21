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

	Integer repoid;
	String reponame;
	
	@Test()
	public void FcrProjectCreation()

	{
		
		System.out.println("--Project Creation API hit--");
		

		RequestSpecification ProjectCreationReq=given().urlEncodingEnabled(true).cookie(cookiedetail).
				header("Content-Type","application/json").
				
				when().body("{\"name\":\"automation1\",\"repoType\":\"PRIVATE\"}");
		
		Response ProjectCreationResponse= ProjectCreationReq.post("/add_repo");
	
	
		ValidatableResponse ValidatingProjectResponse = ProjectCreationResponse.then().log().all();
		repoid=ValidatingProjectResponse.extract().path("id");
		reponame=ValidatingProjectResponse.extract().path("name");
	System.out.println("Repo id created :"+repoid + "for RepoName: "+reponame);
	
	Assert.assertEquals(reponame, "automation1");	

	}

}
