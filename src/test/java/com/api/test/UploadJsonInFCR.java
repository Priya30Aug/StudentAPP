package com.api.test;

import java.io.File;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UploadJsonInFCR extends ProjectCreationFCR {

	@Test(dependsOnMethods = { "FcrProjectCreation" })
	public void UploadJsonInFcr() {

		System.out.println("--Upload json in FCR project api hit--");
		File jsonDataInFile = new File(
				System.getProperty("user.dir") + "/src/test/resources/PayloadsToUpload/Configuration(qatested).json");
		System.out.println(jsonDataInFile);

		given().queryParam("repoId", repoid).cookie(cookiedetail).header("Content-Type", "application/json")
				.body(jsonDataInFile).when().post("/uploadCompanyJson").

				then().log().all().assertThat().statusCode(200);

	}
}
