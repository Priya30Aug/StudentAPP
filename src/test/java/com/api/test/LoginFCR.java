package com.api.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import com.relevantcodes.extentreports.*;

public class LoginFCR {

	public Cookie cookiedetail;
	public String configfilepath = "/src/test/resources/config.properties";
	Properties prop;
	public ExtentReports extentreports;
	public static com.relevantcodes.extentreports.ExtentTest extenttest;

	

	

	@BeforeSuite
	public void Intialise() throws IOException {

		File configfile = new File(System.getProperty("user.dir") + configfilepath);
		System.out.println(configfile);
	prop= new Properties();
		FileInputStream fis= new FileInputStream(configfile);

		prop.load(fis);
		RestAssured.baseURI = "https://fcr-qa.fareye.co";
		RestAssured.basePath = "/app";
		extentreports= new ExtentReports(System.getProperty("user.dir")+"/extentreport.html", true);
		extentreports.addSystemInfo("OS", "linux");
		extentreports.addSystemInfo("Environment", "fcr-qa.fareye.co");
		extentreports.addSystemInfo("hostname", "FCR-QA");
		extentreports.addSystemInfo("username", "priya kapoor");
		extentreports.loadConfig(new File(System.getProperty("user.dir")+"/src/main/java/com/api/model/extentconfig.xml"));

	}

	@BeforeClass
	public void FcrLogin() {
		extenttest= extentreports.startTest("Login API testcases started");
		System.out.println("--Login API hits--");
		String username=prop.getProperty("FCR_username");
		String password=prop.getProperty("FCR_password");
		Response login_response = given().param("username", username).param("password", password)
				.header("Accept", ContentType.JSON.getAcceptHeader()).post("/authentication");

		int loginstatuscode = login_response.getStatusCode();
		System.out.println("FCR login API status code is : " + loginstatuscode);

		if (loginstatuscode != 200) {
			extenttest.log(LogStatus.FAIL, "Login not possible");
			System.out.print("user is not authenticated, check login details. status code is: " + loginstatuscode);
		}

		else {
			extenttest.log(LogStatus.PASS, "login done");
			cookiedetail = login_response.then().extract().response().
					getDetailedCookie("FCRSESSIONID");
			System.out.println("User gets authenticated and his FCRsessionId is: " + cookiedetail);
		}
		extentreports.endTest(extenttest);

	}
	@AfterSuite
	public void End()
	{

		extentreports.flush();
	}
	
	
	
}
