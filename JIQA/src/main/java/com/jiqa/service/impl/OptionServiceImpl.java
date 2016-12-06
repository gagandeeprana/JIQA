package com.jiqa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.OptionDAO;
import com.jiqa.entity.OptionBean;
import com.jiqa.service.OptionService;

@Component
public class OptionServiceImpl implements OptionService{

	@Autowired
	OptionDAO optionDAO;

	public List<OptionBean> getAllOptionsByQuestionId(int questionId) {
		return optionDAO.getAllOptionsByQuestionId(questionId);
	}
}
