package com.practice.rest.webservice.restfulwebservice.versioning;

public class Name {

	private String firstName;
	private String lastNamel;

	public Name() {

	}

	public Name(String firstName, String lastNamel) {
		super();
		this.firstName = firstName;
		this.lastNamel = lastNamel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNamel() {
		return lastNamel;
	}

	public void setLastNamel(String lastNamel) {
		this.lastNamel = lastNamel;
	}

}
