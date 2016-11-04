package com.jiqa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "multiplechoice_answermaster")
public class MultipleAnswerBean {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private MultipleQuestionBean multipleQuestionBean;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OptionBean optionBean;

	public MultipleQuestionBean getMultipleQuestionBean() {
		return multipleQuestionBean;
	}


	public void setMultipleQuestionBean(MultipleQuestionBean multipleQuestionBean) {
		this.multipleQuestionBean = multipleQuestionBean;
	}


	public OptionBean getOptionBean() {
		return optionBean;
	}


	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}
}
