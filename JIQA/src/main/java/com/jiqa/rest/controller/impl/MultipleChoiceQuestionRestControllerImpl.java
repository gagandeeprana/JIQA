package com.jiqa.rest.controller.impl;


import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiqa.entity.MultipleAnswerBean;
import com.jiqa.entity.MultipleQuestionBean;
import com.jiqa.entity.OptionBean;
import com.jiqa.model.MultipleChoiceQuesAns;
import com.jiqa.rest.controller.MultipleChoiceQuestionRestController;
import com.jiqa.service.MultipleAnswerService;
import com.jiqa.service.MultipleChoiceQuestionService;
import com.jiqa.service.OptionService;
import com.jiqa.util.HelperUtil;
import com.jiqa.util.MessageVariables;

@RestController
@RequestMapping(value = "/multiplechoicequestion")
public class MultipleChoiceQuestionRestControllerImpl extends MessageVariables implements MultipleChoiceQuestionRestController{

	@Autowired
	MultipleChoiceQuestionService multipleChoiceQuestionService;
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	MultipleAnswerService multipleAnswerService;
	
	HelperUtil helperUtil = new HelperUtil();
	
	@Override
	public Object getQuestionAnswers() {
		String json = null;
		try {
			List<MultipleQuestionBean> lstQuestions = multipleChoiceQuestionService.getAllMultipleChoiceQuestions("", "", 0);

			List<MultipleChoiceQuesAns> response = new ArrayList<MultipleChoiceQuesAns>();
			
			for(int i=0;i<lstQuestions.size();i++) {
				MultipleQuestionBean mqb = lstQuestions.get(i);
			
				MultipleChoiceQuesAns mcqa = new MultipleChoiceQuesAns();
				mcqa.setQuestion(mqb.getQuestion());
				
				List<OptionBean> lstOptions = optionService.getAllOptionsByQuestionId(mqb.getQuestionId());
				
				mcqa.setCorrectAnswer(correctAnswer(mqb.getQuestionId()));
				mcqa.setOptions(lstOptions);
				response.add(mcqa);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(response);
		} catch (Exception e) {
			System.out.println(e);
		}
		return json;
	}
	
	Integer correctAnswer(Integer questionId) {
		List<MultipleAnswerBean> lstAnswers = multipleAnswerService.getAllAnswersByQuestionId(questionId);
		return lstAnswers.get(0).getOptionBean().getOptionId();
	}

	
}
