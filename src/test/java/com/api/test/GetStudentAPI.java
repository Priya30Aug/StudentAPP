package com.api.test;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
        

public class GetStudentAPI extends BaseUtilities {
	
@Test
	public void GetAllStudents() {
		
		System.out.println("---Get the entire response--");

		RequestSpecification rs= RestAssured.given();
		
		Response response= rs.get("/list");
		
		response.prettyPrint();
		
	}


@Test
public void GetStatusCode()
{
	System.out.println("---Get the Status code--");
	

RequestSpecification requestspecification=RestAssured.given();

Response response=requestspecification.get("/list");
System.out.println("Status code is  : " + response.getStatusCode());
System.out.println("Status line is  : " + response.getStatusLine());
System.out.println("Response in prettyprint format: " +response.body().prettyPrint());;
System.out.println("Content type : "+response.contentType());
System.out.println("ResponseBody in String : "+response.getBody().asString());
System.out.println("Response headers : "+response.getHeaders());
System.out.println("Response time : "+response.getTime());
System.out.println("Session id :"+response.getSessionId());

ValidatableResponse validatingResponse= response.then();

validatingResponse.statusCode(200);

	
	}
	
@Test

public void GetSingleLawStudent()
{
	System.out.println("Information of 1 student of law programme");
	Response response=
	given().queryParam("programme", "Law").
	queryParam("Limit", 1).
	when()
	.get("/list");
	
	response.prettyPrint();
	
	}

@Test
public void GetStudentOfId7()
{
	System.out.println("Getting student details of id 7");
	
	Response response=given().pathParam("id", "7")
	.when().get("/{id}");
	
	response.prettyPrint();
	
	
	}
}
