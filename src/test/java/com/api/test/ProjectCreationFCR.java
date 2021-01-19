package com.api.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.api.model.ProjectCreationPOJO;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProjectCreationFCR {

	@Test
	public void Project_creation()
	{
		
		ProjectCreationPOJO projcreationpojo = new ProjectCreationPOJO();
		projcreationpojo.setName("test_automation");
		projcreationpojo.setRepoType("PUBLIC");
		
		RequestSpecification rs=given().header("Content-Type", "application/json").
				header("FCRSESSIONID","YzdkNzZhZmUtMTc1Ny00ZDZlLWI2ZGItOGQ1YTZmODNjZDcz").
				when().body(projcreationpojo);
		
		Response response= rs.post("https://fcr-qa.fareye.co/app/add_repo");
		
		System.out.println(response.getStatusCode());
		

				
		

	}

}
