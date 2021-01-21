package com.api.test;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DeleteProjectFCR extends ProjectCreationFCR {

	@Test(dependsOnMethods= {"FcrProjectCreation"})
	public void DeleteFcrProject() {

		System.out.println("--delete project API hit--");

		Response deleteresponse = given().cookie(cookiedetail).queryParam("name",reponame_output).delete("/delete_repo");

		int deletestatuscode = deleteresponse.getStatusCode();
		System.out.println(deletestatuscode);

		if (deletestatuscode != 200) {
			System.out.println("Project of name "+reponame_output+"  isnt deleted as status code is : " + deletestatuscode
					+ " and response received is: " + deleteresponse.body().prettyPrint());
			
		}

		else {

			System.out.println("Project with name "+reponame_output+" gets deleted as the status code for delete project is : " +deletestatuscode);
			deleteresponse.body().prettyPrint();
		}

	}

}
