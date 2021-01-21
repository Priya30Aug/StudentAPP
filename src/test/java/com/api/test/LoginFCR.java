package com.api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.api.model.LoginPOJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LoginFCR {

	public Cookie cookiedetail;

//	@Test
//	public void Login_FCR()
//	{	
//		LoginPOJO loginpojo = new LoginPOJO();
//	    loginpojo.setUsername("priya");
//		loginpojo.setPassword("priya");
//		
//		System.out.println(loginpojo);
//		
//		RequestSpecification rs=given().auth().basic("priya","priya").
//				contentType(ContentType.JSON).when().body(loginpojo);
//		
//		Response response= rs.post("https://fcr-qa.fareye.co/app/authentication");
//		
//	String code=response.getStatusLine();
//		
//		System.out.println(code);
//		
//		
//		String cookie=response.getCookie("FCRSESSIONID");
//		
//		System.out.println(cookie);
//		
//	}

//	@Test
//	public void Login() {
//		RequestSpecification rs;
//		 Response response;
//
//
//		rs= given().param("username", "priya").param("password", "priya").
//				header("Content-Type", "application/json")
//					.header("Accept-Encoding", "gzip, deflate, br")
//					.header("Accept", "*/*").header("Connection", "keep-alive").
//					header("Host", "<calculated when request is sent>").when();
//					
//		
//
//		 response= rs.post("https://fcr-qa.fareye.co/app/authentication");
//		 response.body().prettyPrint();
//		
//
//		
//
//	}

	@BeforeSuite
	public void Intialise() {

		Properties prop = new Properties();

		RestAssured.baseURI = "https://fcr-qa.fareye.co";
		RestAssured.basePath = "/app";

	}

	@BeforeClass
	public void FcrLogin() {

		System.out.println("--Login API hits--");

		Response login_response = given().param("username", "automation_qa")
				.param("password", "admin").header("Accept", ContentType.JSON.getAcceptHeader())
				.post("/authentication");

		int loginstatuscode = login_response.getStatusCode();
		System.out.println("FCR login API status code is : "+loginstatuscode);

		if (loginstatuscode != 200) {
			System.out.print("user is not authenticated, check login details. status code is: " + loginstatuscode);
		}

		else {

			cookiedetail = login_response.then().extract().response().getDetailedCookie("FCRSESSIONID");
			System.out.println("User gets authenticated and his FCRsessionId is: " + cookiedetail);
		}

	}



}
