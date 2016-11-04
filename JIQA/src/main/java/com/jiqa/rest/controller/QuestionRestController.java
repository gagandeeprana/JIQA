package com.jiqa.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiqa.model.QuestionAnswer;

public interface QuestionRestController {

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Object getQuestionAnswers();
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public Object addQuestionAnswer(@RequestBody QuestionAnswer questionAnswer);
	
	@RequestMapping(value = "status/{status}/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public Object softDeleteQuestionAnswer(@PathVariable("status") int status, @PathVariable("id") int id);
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public Object updateQuestionAnswer(@RequestBody QuestionAnswer questionAnswer);
	
	@RequestMapping(value = "{questionAnswerId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Object getQuestionAnswerInfoById(@PathVariable("questionAnswerId") int id);
	
	@RequestMapping(value = "/catId/{catId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Object getQuestionsByCategoryId(@PathVariable("catId") int id);
}
