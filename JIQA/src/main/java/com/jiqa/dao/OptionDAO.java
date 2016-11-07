package com.jiqa.dao;

import java.util.List;

import com.jiqa.entity.OptionBean;

public interface OptionDAO {

	public int addOption(OptionBean optionBean);

	public boolean updateOption(OptionBean optionBean);

	public List<OptionBean> getAllOptionsByQuestionId(int questionId);
	
	public OptionBean getOptionInfoById(int optionId);
}
