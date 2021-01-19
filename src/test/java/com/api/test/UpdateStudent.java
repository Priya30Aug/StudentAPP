package com.api.test;

import org.testng.annotations.Test;

import com.api.model.StudentPOJO;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class UpdateStudent extends BaseUtilities {
	
	

	
	@Test
	public void UpdateStudentPayloadPOJO()

	{
		
		System.out.println("----Creating student using payload as POJO----");
		StudentPOJO stdpojo= new StudentPOJO();
		stdpojo.setFirstName("Updatedpriya");
		stdpojo.setLastName("Updatedkapoor");
		stdpojo.setEmail("updatepriya@gmail.com");
		stdpojo.setProgramme("CSE");
		
		
		
		List<String> courselist= new ArrayList<String>();
		courselist.add("Java");
		courselist.add("C++");
		stdpojo.setCourses(courselist);
		
		given().
		contentType(ContentType.JSON).
		when().
		body(stdpojo).
		put("/101").
		then().
		statusCode(200);
		
	}

}
