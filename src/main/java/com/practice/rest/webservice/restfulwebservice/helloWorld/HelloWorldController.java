package com.practice.rest.webservice.restfulwebservice.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource bundleMessageResource;
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public String helloWorld(){
		return "Hi From Hello World Controller with path /hello-world";
	}
	
	@GetMapping( path="/greetMessage")
	public String greetMessage(){
		return "Hi From greetMessage method";
	}
	
	@GetMapping( path="/hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hi From hello world bean");
	}
	
	@GetMapping( path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
		return new HelloWorldBean(String.format("Hi From hello world bean with path variable named %s",name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return bundleMessageResource.getMessage("good.morning.message", null, locale);
	}
}
