package com.jiqa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.MultipleAnswerDAO;
import com.jiqa.entity.MultipleAnswerBean;
import com.jiqa.service.MultipleAnswerService;

@Component
public class MultipleAnswerServiceImpl implements MultipleAnswerService{

	@Autowired
	MultipleAnswerDAO multipleAnswerDAO;
	
	public List<MultipleAnswerBean> getAllAnswersByQuestionId(int questionId){
		return multipleAnswerDAO.getAllAnswersByQuestionId(questionId);
	}

}
