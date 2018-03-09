package com.practice.rest.webservice.restfulwebservice.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Filtering {
	
	// Below mapping are related to static filter 
	// where on bean we are configuring @JsonIgnore
	@GetMapping("/filter")
	public SomeBean filter(){
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filter-list")
	public List<SomeBean> filteredList(){
		return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value1","value2","value3"));
	}
	
	@GetMapping("/filterBean")
	public SomeOtherBean filterBean(){
		return new SomeOtherBean("value1","value2","value3");
	}
	
	// Below mapping are related to dynamic filter
	@GetMapping("/dynamicFilter")
	public MappingJacksonValue dynamicFilter(){
		DynamicFilteredBean filteredBean = new DynamicFilteredBean("value1","value2","value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFilterOfBean",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(filteredBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/dynamicFilter-list")
	public MappingJacksonValue dynamicFilteredList(){
		List<DynamicFilteredBean> beanList = Arrays.asList(new DynamicFilteredBean("value1","value2","value3"),new DynamicFilteredBean("value1","value2","value3"));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFilterOfBean",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(beanList);
		mapping.setFilters(filters);
		return mapping;
	}

}
