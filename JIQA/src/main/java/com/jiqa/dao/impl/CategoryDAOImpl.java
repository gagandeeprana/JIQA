package com.jiqa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiqa.dao.CategoryDAO;
import com.jiqa.entity.CategoryBean;
import com.jiqa.entity.QuestionBean;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	Logger logger = Logger.getLogger(CategoryDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public int addCategory(CategoryBean categoryBean) {
		Session session = null;
		int maxId = 0;
		try {
			session = sessionFactory.openSession();
			maxId = (Integer) session.save(categoryBean);
		} catch (Exception e) {
			logger.error("CategoryDAOImpl: Inside addCategory: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("CategoryDAOImpl: Inside addCategory: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return maxId;
	}

	public boolean updateCategory(CategoryBean categoryBean) {
		Session session = null;
		boolean flag = false;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(categoryBean);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			System.out.println(e.getMessage());
			logger.error("CategoryDAOImpl: Inside updateCategory: Exception is: " + e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("CategoryDAOImpl: Inside updateCategory: Inside Finally: Exception is: " + e2.getMessage());
			}
		}
		return flag;
	}

	@Transactional
	public int softDeleteCategory(int status, int categoryId) {
		Session session = null;
		int result = 0;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("update CategoryBean set status = :status where categoryId = :categoryId");
			query.setParameter("status", status);
			query.setParameter("categoryId", categoryId);
			result = query.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			logger.error("CategoryDAOImpl: Inside softDeleteCategory: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("CategoryDAOImpl: Inside softDeleteCategory: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CategoryBean> getAllCategories(String title) {
		Session session = null;
		List<CategoryBean> lstCategories = new ArrayList<CategoryBean>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(CategoryBean.class);
			if(!"".equals(title)) {
				criteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
			}
			criteria.addOrder(Order.desc("createdOn"));
			lstCategories = (List<CategoryBean>) criteria.list();
		} catch (Exception e) {
			logger.error("CategoryDAOImpl: Inside getAllCategories: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("CategoryDAOImpl: Inside getAllCategories: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return lstCategories;
	}
	
	@Transactional
	public Long getQuestionsCountByCategory(int categoryId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(QuestionBean.class);
			criteria.add(Restrictions.eq("categoryBean.categoryId", categoryId));
			criteria.setProjection(Projections.rowCount());
			return (Long)criteria.uniqueResult();
		} catch (Exception e) {
			logger.error("CategoryDAOImpl: Inside getQuestionsCountByCategory: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("CategoryDAOImpl: Inside getQuestionsCountByCategory: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return 0l;
	}

	@Transactional
	@Override
	public CategoryBean getCategoryInfoById(int id) {
		Session session = null;
		CategoryBean categoryBean = new CategoryBean();
		try {
			session = sessionFactory.openSession();
			logger.info("getCategoryInfoById: categoryId is: " + id);
			Criteria criteria = session.createCriteria(CategoryBean.class);
			criteria.add(Restrictions.eq("categoryId", id));
			categoryBean = (CategoryBean) criteria.list().get(0);
		} catch (Exception e) {
			System.err.println(e);
			logger.info("CategoryDAOImpl: Inside getCategoryInfoById: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("CategoryDAOImpl: Inside getCategoryInfoById: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return categoryBean;
	}

}
