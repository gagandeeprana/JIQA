package com.jiqa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiqa.entity.CategoryBean;
import com.jiqa.entity.MultipleAnswerBean;
import com.jiqa.entity.MultipleQuestionBean;
import com.jiqa.entity.OptionBean;
import com.jiqa.entity.QuestionBean;
import com.jiqa.service.CategoryService;
import com.jiqa.service.MultipleAnswerService;
import com.jiqa.service.MultipleChoiceQuestionService;
import com.jiqa.service.OptionService;

@Controller
public class MultipleQuestionController {

	@Autowired
	MultipleChoiceQuestionService multipleChoiceQuestionService;
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	MultipleAnswerService multipleAnswerService;
	
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/showmultipleques", method = RequestMethod.GET)
	public ModelAndView showQuestionScreen(@ModelAttribute("ques") QuestionBean questionBean) {
		ModelAndView modelAndView = new ModelAndView();
		int categoryId = 0;
		if(questionBean.getCategoryId() != 0) {
			categoryId = questionBean.getCategoryId();
		}
		List<MultipleQuestionBean> lstQuestions = multipleChoiceQuestionService.getAllMultipleChoiceQuestions("", "", 0);
		Map<Object, Object> optionsMap = new LinkedHashMap();
		for(MultipleQuestionBean mqb:lstQuestions) {
			List<OptionBean> lstOptions = optionService.getAllOptionsByQuestionId(mqb.getQuestionId());
			optionsMap.put(mqb, lstOptions);
		}
		Map<Object, Object> answersMap = new LinkedHashMap();
		for(MultipleQuestionBean mqb:lstQuestions) {
			List<MultipleAnswerBean> lstAnswers = multipleAnswerService.getAllAnswersByQuestionId(mqb.getQuestionId());
			answersMap.put(mqb, lstAnswers);
		}
		List<CategoryBean> lstCategories = categoryService.getAllCategories("");
		modelAndView.addObject("MAP_OPTIONS", optionsMap);
		modelAndView.addObject("MAP_ANSWERS", answersMap);
		modelAndView.addObject("LIST_QUES", lstQuestions);
		modelAndView.addObject("LIST_CAT", lstCategories);
		modelAndView.setViewName("multiplequestions");
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveMultipleQues" , method = RequestMethod.POST)
	public ModelAndView saveQuestion(@ModelAttribute("ques") MultipleQuestionBean multipleQuestionBean,
			@RequestParam("categoryId") int categoryId,
			@RequestParam("op1") String op1,
			@RequestParam("op2") String op2,
			@RequestParam("op3") String op3,
			@RequestParam("op4") String op4,
			@RequestParam("correctOption") int correctOption,
			HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		CategoryBean categoryBean = categoryService.getCategoryInfoById(categoryId);
		multipleQuestionBean.setCategoryBean(categoryBean);
		HttpSession session = request.getSession();
		String createdBy = "";
		if(session != null) {
			createdBy = session.getAttribute("un").toString();
		}
		multipleQuestionBean.setCreatedBy(createdBy);
		multipleQuestionBean.setCreatedOn(new Date());
		List<String> options = new ArrayList<String>();
		options.add(op1);
		options.add(op2);
		options.add(op3);
		options.add(op4);
		multipleChoiceQuestionService.addMultipleChoiceQuestion(multipleQuestionBean, options, correctOption);
		modelAndView.setViewName("redirect:showmultipleques");
		return modelAndView;
	}
	
	/*@RequestMapping(value = "/deleteQues/sta/{sta}/quesId/{quesId}" , method = RequestMethod.GET)
	public ModelAndView deleteQuestion(@PathVariable("quesId") int questionId, @PathVariable("sta") int status) {
		ModelAndView modelAndView = new ModelAndView();
		questionService.softDeleteQuestion(status, questionId);
		modelAndView.setViewName("redirect:/showques");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getQues/quesId" , method = RequestMethod.GET)
	@ResponseBody  public QuestionBean getQuestion(@RequestParam("quesId") int questionId) {
		QuestionBean questionBean = null;
		try {
			if(questionId != 0) {
				questionBean = questionService.getQuestionInfoById(questionId);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return questionBean;
	}
	
	@RequestMapping(value = "/updateQuestion" , method = RequestMethod.POST)
	public ModelAndView updateQuestion(@ModelAttribute("ques") QuestionBean questionBean, @RequestParam("quesid") int questionId) {
		ModelAndView modelAndView = new ModelAndView();
		questionBean.setQuestionId(questionId);
		questionService.updateQuestion(questionBean);
		modelAndView.setViewName("redirect:/showques");
		return modelAndView;
	}
	
	@RequestMapping(value = "/show" , method = RequestMethod.GET)
	public ModelAndView show(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String question = session.getAttribute("q").toString();
		String answer = session.getAttribute("a").toString();
		modelAndView.addObject("question", question);
		modelAndView.addObject("answer", answer);
		modelAndView.setViewName("showquestion");
		return modelAndView;
	}
	
	@RequestMapping(value = "/showquestionbyid/{q}" , method = RequestMethod.GET)
	public ModelAndView getQuestionById(@PathVariable("q") int questionId, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			if(questionId != 0) {
				QuestionBean questionBean = questionService.getQuestionInfoById(questionId);
				modelAndView.addObject("questionBean", questionBean);
				HttpSession session = request.getSession();
				session.setAttribute("q", questionBean.getQuestion());
				session.setAttribute("a", questionBean.getAnswer());
//				modelAndView.setViewName("redirect:/show?question=" + questionBean.getQuestion() + "&answer=" + questionBean.getAnswer());
				modelAndView.setViewName("redirect:/show");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return modelAndView;
	}*/
}
