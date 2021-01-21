package com.api.test;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DownloadversionFCR extends ProjectCreationFCR{

	
	@Test
	public void DownloadJson() throws IOException
	{
		RequestSpecification rs= given().cookie(cookiedetail).queryParam("repoId",repoid).header("Content-Type","application/json");
		
				Response response=rs.get("/downloadOldCompanyJson");
				ValidatableResponse validatable_resp=response.then().statusCode(200);
			
			
				String responseBody=response.body().asString();
				File file= new File(System.getProperty("user.dir")+"/src/test/resources/PayloadDownloaded");
			FileWriter filewriter= new FileWriter(file+"/output"+reponame+repoid+".json");
			
			filewriter.flush();
			filewriter.close();
			
		
}
}
