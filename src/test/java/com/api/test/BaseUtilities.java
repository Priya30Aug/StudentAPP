package com.api.test;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseUtilities {
	
	@BeforeSuite
	public void Intialise()
	{
		RestAssured.baseURI="http://localhost";
		RestAssured.basePath="/student";
		RestAssured.port=8080;
	}
	

}
