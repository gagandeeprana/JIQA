package com.jiqa.rest.controller.impl;


import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiqa.entity.CategoryBean;
import com.jiqa.model.Category;
import com.jiqa.rest.controller.CategoryRestController;
import com.jiqa.service.CategoryService;
import com.jiqa.util.HelperUtil;
import com.jiqa.util.MessageVariables;

@RestController
@RequestMapping(value = "/category")
public class CategoryRestControllerImpl extends MessageVariables implements CategoryRestController{

	@Autowired
	CategoryService categoryService;
	
	HelperUtil helperUtil = new HelperUtil();
	
	@Override
	public Object getCategories() {
		String json = null;
		try {
			List<CategoryBean> lstCategories = categoryService.getAllCategories("");
			for(CategoryBean categoryBean : lstCategories) {
				long questionCount = categoryService.getQuestionsCountByCategory(categoryBean.getCategoryId());
				System.out.println("QC: " + questionCount);
				categoryBean.setQuestionCount(questionCount);
			}
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("REQUEST RECEIVED>>>>>> " + lstCategories.get(0).getQuestionCount());
			json = mapper.writeValueAsString(lstCategories);
		} catch (Exception e) {
			System.out.println(e);
		}
		return json;
	}

	@Override
	public Object addCategory(@RequestBody Category category) {
		String json = null;
		try {
			CategoryBean categoryBean = new CategoryBean();
//			json = helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			if(category == null) {
				return helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			}
			if(!helperUtil.checkValue(category.getTitle())) {
				return helperUtil.getErrorJSONString(categoryTitleNotValidCode, categoryTitleNotValidMessage);
			}
			if(!(category.getStatus() >= 0 && category.getStatus() <= 1)) {
				return helperUtil.getErrorJSONString(statusNotValidCode, statusNotValidMessage);
			}
			categoryBean.setTitle(category.getTitle());
			categoryBean.setStatus(category.getStatus());
			int categoryId = categoryService.addCategory(categoryBean);
			if(categoryId > 0) {
				return helperUtil.getSuccessJSONString(categoryAddedCode, categoryAddedMessage);
			} else {
				return helperUtil.getErrorJSONString(failedToAddCategoryCode, failedToAddCategoryMessage);
			}
		} catch (Exception e) {	
			System.out.println(e);
		}
		return json;
	}

	@Override
	public Object softDeleteCategory(@PathVariable("status") int status, @PathVariable("id") int id) {
		Object obj = null;
		boolean userExists = false;
		try {
			obj = helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);

			if(!(status >= 0 && status <= 1)) {
				return helperUtil.getErrorJSONString(statusNotValidCode, statusNotValidMessage);
			}
			List<CategoryBean> lstCategories = categoryService.getAllCategories("");
			for(CategoryBean categoryBean: lstCategories) {
				if(categoryBean.getCategoryId() == id) {
					userExists = true;
					break;
				}
			}
			if(!userExists) {
				return helperUtil.getErrorJSONString(noSuchCategoryExistsCode, noSuchCategoryExistsMessage);
			}
			int result = categoryService.softDeleteCategory(status, id);
			if(result > 0) {
				return helperUtil.getSuccessJSONString(categoryDeletedCode, categoryDeletedMessage);
			} else {
				return helperUtil.getErrorJSONString(failedToDeleteCategoryCode, failedToDeleteCategoryMessage);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

	@Override
	public Object updateCategory(@RequestBody Category category) {
		Object obj = null;
		boolean userExists = false;

		try {
			CategoryBean categoryBean = new CategoryBean();
			obj = helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			if(category == null) {
				return helperUtil.getErrorJSONString(requestInvalidCode, requestInvalidMessage);
			}
			List<CategoryBean> lstCategories = categoryService.getAllCategories("");
			for(CategoryBean cb: lstCategories) {
				if(cb.getCategoryId() == category.getId()) {
					userExists = true;
					break;
				}
			}
			if(!userExists) {
				return helperUtil.getErrorJSONString(noSuchCategoryExistsCode, noSuchCategoryExistsMessage);
			}
			if(!helperUtil.checkValue(category.getTitle())) {
				return helperUtil.getErrorJSONString(categoryTitleNotValidCode, categoryTitleNotValidMessage);
			}
			if(!(category.getStatus() >= 0 && category.getStatus() <= 1)) {
				return helperUtil.getErrorJSONString(statusNotValidCode, statusNotValidMessage);
			}
			categoryBean.setTitle(category.getTitle());
			categoryBean.setStatus(category.getStatus());
			boolean result = categoryService.updateCategory(categoryBean);
			if(result) {
				return helperUtil.getSuccessJSONString(categoryUpdatedCode, categoryUpdatedMessage);
			} else {
				return helperUtil.getErrorJSONString(failedToAddCategoryCode, failedToAddCategoryMessage);
			}
		} catch (Exception e) {	
			System.out.println(e);
		}
		return obj;
	}

	@Override
	public Object getCategoryInfoById(@PathVariable("categoryId") int id) {
		String json = null;
		try {
			CategoryBean categoryBean = categoryService.getCategoryInfoById(id);
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(categoryBean);
		} catch (Exception e) {
			System.out.println(e);
		}
		return json;
	}
}
