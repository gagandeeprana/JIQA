package com.jiqa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.CategoryDAO;
import com.jiqa.entity.CategoryBean;
import com.jiqa.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDAO;

	public int addCategory(CategoryBean categoryBean) {
		return categoryDAO.addCategory(categoryBean);
	}

	public boolean updateCategory(CategoryBean categoryBean) {
		return categoryDAO.updateCategory(categoryBean);
	}

	public int softDeleteCategory(int status, int categoryId) {
		return categoryDAO.softDeleteCategory(status, categoryId);
	}

	public List<CategoryBean> getAllCategories(String title) {
		return categoryDAO.getAllCategories(title);
	}

	@Override
	public CategoryBean getCategoryInfoById(int id) {
		return categoryDAO.getCategoryInfoById(id);
	}

	@Override
	public Long getQuestionsCountByCategory(int categoryId) {
		return categoryDAO.getQuestionsCountByCategory(categoryId);
	}
	

}
