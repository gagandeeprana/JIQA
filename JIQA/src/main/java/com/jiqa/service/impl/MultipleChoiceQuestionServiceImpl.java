package com.jiqa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.MultipleChoiceQuestionDAO;
import com.jiqa.entity.MultipleQuestionBean;
import com.jiqa.service.MultipleChoiceQuestionService;

@Component
public class MultipleChoiceQuestionServiceImpl implements MultipleChoiceQuestionService {

	@Autowired
	MultipleChoiceQuestionDAO multipleChoiceQuestionDAO;

	@Override
	public int addMultipleChoiceQuestion(MultipleQuestionBean multipleQuestionBean, List<String> options, int index) {
		return multipleChoiceQuestionDAO.addMultipleChoiceQuestion(multipleQuestionBean, options, index);
	}
	
	public List<MultipleQuestionBean> getAllMultipleChoiceQuestions(String question, String answer, int categoryId) {
		return multipleChoiceQuestionDAO.getAllMultipleChoiceQuestions(question, answer, categoryId);
	}
}
