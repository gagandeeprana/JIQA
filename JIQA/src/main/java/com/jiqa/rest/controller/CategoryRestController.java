package com.jiqa.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiqa.model.Category;

public interface CategoryRestController {

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Object getCategories();
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public Object addCategory(@RequestBody Category category);
	
	@RequestMapping(value = "status/{status}/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public Object softDeleteCategory(@PathVariable("status") int status, @PathVariable("id") int id);
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public Object updateCategory(@RequestBody Category category);
	
	@RequestMapping(value = "{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Object getCategoryInfoById(@PathVariable("categoryId") int id);
}
