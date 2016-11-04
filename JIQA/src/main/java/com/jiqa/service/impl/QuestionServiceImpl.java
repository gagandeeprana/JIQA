package com.jiqa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.QuestionDAO;
import com.jiqa.entity.QuestionBean;
import com.jiqa.service.QuestionService;

@Component
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionDAO questionDAO;

	public int addQuestion(QuestionBean QuestionBean) {
		return questionDAO.addQuestion(QuestionBean);
	}

	public boolean updateQuestion(QuestionBean questionBean) {
		return questionDAO.updateQuestion(questionBean);
	}

	public int softDeleteQuestion(int status, int questionId) {
		return questionDAO.softDeleteQuestion(status, questionId);
	}

	public List<QuestionBean> getAllQuestions(String question, String answer,
			int categoryId) {
		return questionDAO.getAllQuestions(question, answer, categoryId);
	}

	@Override
	public QuestionBean getQuestionInfoById(int questionId) {
		return questionDAO.getQuestionInfoById(questionId);
	}

	@Override
	public List<QuestionBean> getQuestionsByCategoryId(int catId) {
		return questionDAO.getQuestionsByCategoryId(catId);
	}

}
