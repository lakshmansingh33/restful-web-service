package com.practice.rest.webservice.restfulwebservice.versioning;

public class PersonV1 {

	private String firstName;

	public PersonV1() {
		
	}
	
	public PersonV1(String firstName) {
		super();
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}
