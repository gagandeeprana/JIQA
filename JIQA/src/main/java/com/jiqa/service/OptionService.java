package com.jiqa.service;

import java.util.List;

import com.jiqa.entity.OptionBean;

public interface OptionService {

	public List<OptionBean> getAllOptionsByQuestionId(int questionId);

}
