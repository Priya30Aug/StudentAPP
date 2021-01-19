package com.api.test;

import org.testng.annotations.Test;

import com.api.model.LoginPOJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class LoginFCR {
	
	
	@Test
	public void Login_FCR()
	{
		LoginPOJO loginpojo = new LoginPOJO();
		loginpojo.setUsername("template");
		loginpojo.setPassword("admin");
		
		RequestSpecification rs=given().contentType(ContentType.JSON).when().body(loginpojo);
		
		Response response= rs.post("https://fcr-qa.fareye.co/app/authentication");
		
		response.getCookie("FCRSESSIONID");
		
		
	}

}
