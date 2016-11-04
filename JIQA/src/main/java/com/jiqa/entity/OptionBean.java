package com.jiqa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "optionmaster")
public class OptionBean {

	@Id
	@GeneratedValue
	@Column(name = "option_id")
	private Integer optionId;

	@Column(name = "option_value")
	private String optionValue;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private MultipleQuestionBean multipleQuestionBean;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "optionBean", cascade = CascadeType.ALL)
	private MultipleAnswerBean multipleAnswerBean;

	public MultipleAnswerBean getMultipleAnswerBean() {
		return multipleAnswerBean;
	}

	public void setMultipleAnswerBean(MultipleAnswerBean multipleAnswerBean) {
		this.multipleAnswerBean = multipleAnswerBean;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public MultipleQuestionBean getMultipleQuestionBean() {
		return multipleQuestionBean;
	}

	public void setMultipleQuestionBean(MultipleQuestionBean multipleQuestionBean) {
		this.multipleQuestionBean = multipleQuestionBean;
	}
}
