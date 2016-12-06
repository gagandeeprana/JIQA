package com.jiqa.service;

import java.util.List;

import com.jiqa.entity.MultipleQuestionBean;

public interface MultipleChoiceQuestionService {
	
	public int addMultipleChoiceQuestion(MultipleQuestionBean multipleQuestionBean, List<String> options, int index);
	
	public List<MultipleQuestionBean> getAllMultipleChoiceQuestions(String question, String answer, int categoryId);

}
