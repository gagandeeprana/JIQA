package com.jiqa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
@Entity
@Table(name = "categorymaster")
public class CategoryBean {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int categoryId;

	
	@Column(name = "title")
	private String title;

	@Column(name = "status")
	private int status;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Transient
	@Column(name = "question_count")
	private Long questionCount;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "created_by")
	private String createdBy;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryBean", cascade=CascadeType.ALL)
//	private Set<QuestionBean> questions;


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

	public int getCategoryId() {
		return categoryId;
	}

	public Long getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Long questionCount) {
		this.questionCount = questionCount;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

//	public Set<QuestionBean> getQuestions() {
//		return questions;
//	}
//
//	public void setQuestions(Set<QuestionBean> questions) {
//		this.questions = questions;
//	}
}
