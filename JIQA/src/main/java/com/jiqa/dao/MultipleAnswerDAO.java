package com.jiqa.dao;

import java.util.List;

import org.hibernate.Session;

import com.jiqa.entity.MultipleAnswerBean;

public interface MultipleAnswerDAO {

	public int addAnswer(MultipleAnswerBean multipleAnswerBean, Session session);
	public List<MultipleAnswerBean> getAllAnswersByQuestionId(int questionId);

}
