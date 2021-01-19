package com.api.model;

import java.util.List;

public class StudentPOJO {
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	private String firstName;
	private String lastName;
	private String email;
	private String Programme;
	public String getProgramme() {
		return Programme;
	}
	public void setProgramme(String programme) {
		Programme = programme;
	}
	private List <String> courses;

}
