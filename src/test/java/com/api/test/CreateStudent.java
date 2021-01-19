package com.api.test;

import org.testng.annotations.Test;

import com.api.model.StudentPOJO;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateStudent extends BaseUtilities {
	
	
	@Test
	public void CreateStudentPayloadString()
	{
	
		System.out.println("---Creating student using payload as string---");
		
		String payload="{\"firstName\":\"Vernon\",\"lastName\":\"Harper\",\"email\":\"egestas.n@massaQuisqueporttitor.org\",\"programme\":\"Financial Analysis\",\"courses\":[\"Accounting\",\"Statistics\"]}";
		
		given().
		contentType(ContentType.JSON).
		when().
		body(payload).
		post().
		then().
		statusCode(200);
	}
	@Test
	public void CreateStudentPayloadPOJO()

	{
		
		System.out.println("----Creating student using payload as POJO----");
		StudentPOJO stdpojo= new StudentPOJO();
		stdpojo.setFirstName("priya");
		stdpojo.setLastName("kapoor");
		stdpojo.setEmail("priya@gmail.com");
		stdpojo.setProgramme("CSE");
		
		
		
		List<String> courselist= new ArrayList<String>();
		courselist.add("Java");
		courselist.add("C++");
		stdpojo.setCourses(courselist);
		
		given().
		contentType(ContentType.JSON).
		when().
		body(stdpojo).
		post().
		then().
		statusCode(201);
		
	}

}
