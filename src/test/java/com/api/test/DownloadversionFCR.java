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

	
	@Test(dependsOnMethods = {"UploadJsonInFcr"})
	public void DownloadJson() throws IOException

	{
		
	System.out.println(repoid_output);
		RequestSpecification rs= given().cookie(cookiedetail).queryParam("repoId",repoid_output).header("Content-Type","application/json");
		
				Response response=rs.get("/downloadOldCompanyJson");
				
				String responseBody=response.body().asString();
				int downloadjsonStatusCode=response.getStatusCode();
				
				
				System.out.println("DownloadJson api status code is: "+downloadjsonStatusCode);
			
				ValidatableResponse validatable_resp=response.then().statusCode(200);	
				File file= new File(System.getProperty("user.dir")+"/src/test/resources/PayloadDownloaded");
			FileWriter filewriter= new FileWriter(file+"/output"+reponame_output+repoid_output+".json");
			
			filewriter.flush();
			filewriter.close();
			
		
}
}
