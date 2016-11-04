package com.jiqa.rest.controller.impl;


import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.jiqa.entity.QuestionBean;
import com.jiqa.model.QuestionAnswer;
import com.jiqa.rest.controller.QuestionRestController;
import com.jiqa.service.QuestionService;
import com.jiqa.util.HelperUtil;
import com.jiqa.util.MessageVariables;

@RestController
@RequestMapping(value = "/question")
public class QuestionRestControllerImpl extends MessageVariables implements QuestionRestController{

	@Autowired
	QuestionService questionService;
	
	HelperUtil helperUtil = new HelperUtil();
	
	@Override
	public Object getQuestionAnswers() {
		String json = null;
		try {
			List<QuestionBean> lstQuestions = questionService.getAllQuestions("", "", 0);
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(lstQuestions);
		} catch (Exception e) {
			System.out.println(e);
		}
		return json;
	}

	@Override
	public Object addQuestionAnswer(@RequestBody QuestionAnswer question) {
		String json = null;
		try {
			QuestionBean questionBean = new QuestionBean();
//			json = helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			if(question == null) {
				return helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			}
			if(!helperUtil.checkValue(question.getQuestion())) {
//				return helperUtil.getErrorJSONString(questionTitleNotValidCode, questionTitleNotValidMessage);
			}
			if(!(question.getStatus() >= 0 && question.getStatus() <= 1)) {
				return helperUtil.getErrorJSONString(statusNotValidCode, statusNotValidMessage);
			}
			questionBean.setQuestion(question.getQuestion());
			questionBean.setStatus(question.getStatus());
			int questionId = questionService.addQuestion(questionBean);
			if(questionId > 0) {
//				return helperUtil.getSuccessJSONString(questionAddedCode, questionAddedMessage);
			} else {
//				return helperUtil.getErrorJSONString(failedToAddQuestionCode, failedToAddQuestionMessage);
			}
		} catch (Exception e) {	
			System.out.println(e);
		}
		return json;
	}

	@Override
	public Object softDeleteQuestionAnswer(@PathVariable("status") int status, @PathVariable("id") int id) {
		Object obj = null;
		boolean userExists = false;
		try {
			obj = helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);

			if(!(status >= 0 && status <= 1)) {
				return helperUtil.getErrorJSONString(statusNotValidCode, statusNotValidMessage);
			}
//			List<QuestionBean> lstCategories = questionService.getAllCategories("");
//			for(QuestionBean questionBean: lstCategories) {
//				if(questionBean.getQuestionId() == id) {
//					userExists = true;
//					break;
//				}
//			}
			if(!userExists) {
//				return helperUtil.getErrorJSONString(noSuchQuestionExistsCode, noSuchQuestionExistsMessage);
			}
			int result = questionService.softDeleteQuestion(status, id);
			if(result > 0) {
//				return helperUtil.getSuccessJSONString(questionDeletedCode, questionDeletedMessage);
			} else {
//				return helperUtil.getErrorJSONString(failedToDeleteQuestionCode, failedToDeleteQuestionMessage);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

	@Override
	public Object updateQuestionAnswer(@RequestBody QuestionAnswer question) {
		Object obj = null;
		boolean userExists = false;

		try {
			QuestionBean questionBean = new QuestionBean();
			obj = helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			if(question == null) {
//				return helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			}
//			List<QuestionBean> lstCategories = questionService.getAllCategories("");
//			for(QuestionBean cb: lstCategories) {
//				if(cb.getQuestionId() == question.getId()) {
//					userExists = true;
//					break;
//				}
//			}
			if(!userExists) {
//				return helperUtil.getErrorJSONString(noSuchQuestionExistsCode, noSuchQuestionExistsMessage);
			}
//			if(!helperUtil.checkValue(question.getTitle())) {
//				return helperUtil.getErrorJSONString(questionTitleNotValidCode, questionTitleNotValidMessage);
//			}
//			if(!(question.getStatus() >= 0 && question.getStatus() <= 1)) {
//				return helperUtil.getErrorJSONString(statusNotValidCode, statusNotValidMessage);
//			}
//			questionBean.setTitle(question.getTitle());
//			questionBean.setStatus(question.getStatus());
			boolean result = questionService.updateQuestion(questionBean);
//			if(result) {
//				return helperUtil.getSuccessJSONString(questionUpdatedCode, questionUpdatedMessage);
//			} else {
//				return helperUtil.getErrorJSONString(failedToAddQuestionCode, failedToAddQuestionMessage);
//			}
		} catch (Exception e) {	
			System.out.println(e);
		}
		return obj;
	}

	@Override
	public Object getQuestionAnswerInfoById(@PathVariable("questionId") int id) {
		String json = null;
		try {
			QuestionBean questionBean = questionService.getQuestionInfoById(id);
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(questionBean);
		} catch (Exception e) {
			System.out.println(e);
		}
		return json;
	}

	@Override
	public Object getQuestionsByCategoryId(@PathVariable("catId") int id) {
		String json = null;
		try {
			List<QuestionBean> lstQuestions = questionService.getQuestionsByCategoryId(id);
//			ObjectMapper mapper = new ObjectMapper();
//			json = mapper.writeValueAsString(lstQuestions);
//			QuestionAnswer questionAnswer = mapper.readValue(json, QuestionAnswer.class);
//			return questionAnswer;
		} catch (Exception e) {
			System.out.println(e);
		}
		return json;
	}

	
}
