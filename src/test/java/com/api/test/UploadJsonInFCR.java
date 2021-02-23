package com.api.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class UploadJsonInFCR extends ProjectCreationFCR {

	public String FCRApiKey;

	@Test(dependsOnMethods = { "FcrProjectCreation" }, priority = 1)
	public void UploadJson() {

		extenttest=extentreports.startTest("Upload json api hit");

		System.out.println("--Upload json in FCR project api hit--");
		File jsonDataInFile = new File(prop.getProperty("FCR_uploadJson"));

		System.out.println(jsonDataInFile);

		Response uploadjsonrespone = given().queryParam("repoId", repoid_output).cookie(cookiedetail)
				.header("Content-Type", "multipart/json").multiPart("file", jsonDataInFile, "application/json").when()
				.post("/uploadCompanyJson");

		ValidatableResponse vr = uploadjsonrespone.then().log().all().assertThat().statusCode(200);
		int uploadJsonStatusCode = uploadjsonrespone.getStatusCode();
		if (uploadJsonStatusCode == 200) {
			extenttest.log(LogStatus.PASS, "upload json in FCR repo is possible");
		} else {
			extenttest.log(LogStatus.FAIL, "upload json in FCR repo isn't possible");
		}
	}

	@Test(priority = 2)

	public void DownloadJson() throws IOException

	{
		extenttest=extentreports.startTest("Download json api hit");
		System.out.println(repoid_output);
		RequestSpecification rs = given().cookie(cookiedetail).queryParam("repoId", repoid_output)
				.header("Content-Type", "application/json");

		Response response = rs.get("/downloadOldCompanyJson");

		String responseBody = response.body().asString();
		int downloadjsonStatusCode = response.getStatusCode();

		System.out.println("DownloadJson api status code is: " + downloadjsonStatusCode);

		ValidatableResponse validatable_resp = response.then().statusCode(200);
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/PayloadDownloaded");
		FileWriter filewriter = new FileWriter(file + "/output" + reponame_output + repoid_output + ".json");

		filewriter.flush();
		filewriter.close();
		if (downloadjsonStatusCode == 200) {
			extenttest.log(LogStatus.PASS, "Download of FCR json is possible");
		} else {
			extenttest.log(LogStatus.FAIL, "Download of FCR json isn't possible");
		}
	}

	@Test(priority = 3)
	public void GenerateFcrApiKey() {
		
		extenttest=	extentreports.startTest("Generating FCRapikey api hit");

		System.out.println("--Generate FCR key API hit--");

		Response GenerateApiKeyResponse = given().cookie(cookiedetail).header("RepoId", repoid_output).when()
				.post("/user/api_key");

		int APIkeystatuscode = GenerateApiKeyResponse.getStatusCode();

		if (APIkeystatuscode != 200) {
			System.out.println("Not able to generate FCR api key for project whose repo id is : "
					+ fcr_reponame_inputted + " as status code for api is :  " + APIkeystatuscode);
			
			extenttest.log(LogStatus.FAIL, "FCRapi key not generated");
		}

		else {
			ValidatableResponse ValidatingFcrApiKey = GenerateApiKeyResponse.then().log().all();
			FCRApiKey = ValidatingFcrApiKey.extract().path("token");
			System.out.println(
					"API key gets generated for repoid :" + fcr_reponame_inputted + " and its value is : " + FCRApiKey);
			extenttest.log(LogStatus.PASS, "FCRapi key is generated");
		}
		
	}
}
