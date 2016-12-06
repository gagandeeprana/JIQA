package com.jiqa.dao;

import java.util.List;

import org.hibernate.Session;

import com.jiqa.entity.OptionBean;

public interface OptionDAO {

	public int addOption(OptionBean optionBean, Session session);

	public boolean updateOption(OptionBean optionBean);

	public List<OptionBean> getAllOptionsByQuestionId(int questionId);
	
	public OptionBean getOptionInfoById(int optionId);
}
