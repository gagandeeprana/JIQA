package com.jiqa.dao;

import java.util.List;

import com.jiqa.entity.MultipleQuestionBean;

public interface MultipleChoiceQuestionDAO {

	public int addMultipleChoiceQuestion(MultipleQuestionBean multipleQuestionBean, List<String> options, int index);

	public boolean updateMultipleChoiceQuestion(MultipleQuestionBean multipleQuestionBean);

	public int softDeleteMultipleChoiceQuestion(int status, int questionId);

	public List<MultipleQuestionBean> getAllMultipleChoiceQuestions(String question, String answer, int categoryId);
	
	public MultipleQuestionBean getMultipleChoiceQuestionInfoById(int questionId);
	
	public MultipleQuestionBean getMultipleChoiceQuestionInfoByCategoryId(int categoryId);

}
