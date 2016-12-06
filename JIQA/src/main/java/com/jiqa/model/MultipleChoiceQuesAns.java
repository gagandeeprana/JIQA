package com.jiqa.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.jiqa.entity.OptionBean;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class MultipleChoiceQuesAns {

	private String question;
	
	private List<OptionBean> options;
	
	private Integer correctAnswer;

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<OptionBean> getOptions() {
		return options;
	}

	public void setOptions(List<OptionBean> options) {
		this.options = options;
	}
	
}
