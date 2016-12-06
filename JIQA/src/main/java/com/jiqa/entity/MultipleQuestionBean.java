package com.jiqa.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name = "multiplechoice_questionmaster")
public class MultipleQuestionBean {

	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private int questionId;

	@Column(name = "question")
	private String question;

	@Column(name = "has_multiple_answers")
	private Integer hasMultipleAnswers;

	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "status")
	private int status;
	
	@JsonIgnore
	@Transient
	private int categoryId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private CategoryBean categoryBean;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getHasMultipleAnswers() {
		return hasMultipleAnswers;
	}

	public void setHasMultipleAnswers(Integer hasMultipleAnswers) {
		this.hasMultipleAnswers = hasMultipleAnswers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
