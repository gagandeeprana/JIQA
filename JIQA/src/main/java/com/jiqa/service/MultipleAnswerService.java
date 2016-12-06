package com.jiqa.service;

import java.util.List;

import com.jiqa.entity.MultipleAnswerBean;

public interface MultipleAnswerService {
	
	public List<MultipleAnswerBean> getAllAnswersByQuestionId(int questionId);

}
