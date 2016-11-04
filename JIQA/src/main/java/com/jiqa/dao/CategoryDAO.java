package com.jiqa.dao;

import java.util.List;

import com.jiqa.entity.CategoryBean;

public interface CategoryDAO {

	public int addCategory(CategoryBean categoryBean);

	public boolean updateCategory(CategoryBean categoryBean);

	public int softDeleteCategory(int status, int categoryId);

	public List<CategoryBean> getAllCategories(String title);

	public CategoryBean getCategoryInfoById(int id);
	
	public Long getQuestionsCountByCategory(int categoryId);

}
