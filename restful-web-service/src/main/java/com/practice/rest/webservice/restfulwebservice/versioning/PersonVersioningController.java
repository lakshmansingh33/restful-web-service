package com.practice.rest.webservice.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Laxmansingh Muriyal");
	}

	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Laxmansingh", "Muriyal"));
	}

	// Versioning using Request parameter
	/*
	 * Example for versioning using Param ==
	 * http://localhost:8080/person/param?version=1
	 */
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Laxmansingh Muriyal");
	}

	/*
	 * Example for versioning using Param ==
	 * http://localhost:8080/person/param?version=2
	 */
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Laxmansingh", "Muriyal"));
	}

	// Versioning using header parameter
	@GetMapping(value = "/person/header", headers = "x-api-version=1")
	public PersonV1 headerV1() {
		return new PersonV1("Laxmansingh Muriyal");
	}

	@GetMapping(value = "/person/header", headers = "x-api-version=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Laxmansingh", "Muriyal"));
	}
	
	// Versioning using produces
		@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
		public PersonV1 producesV1() {
			return new PersonV1("Laxmansingh Muriyal");
		}

		@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
		public PersonV2 producesV2() {
			return new PersonV2(new Name("Laxmansingh", "Muriyal"));
		}

}
