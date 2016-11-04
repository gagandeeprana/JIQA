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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.QuestionDAO;
import com.jiqa.entity.CategoryBean;
import com.jiqa.entity.QuestionBean;

@Component
class QuestionDAOImpl implements QuestionDAO {

	Logger logger = Logger.getLogger(QuestionDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public int addQuestion(QuestionBean questionBean) {
		Session session = null;
		Transaction tx = null;
		int maxId = 0;
		try {
			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
			maxId = (Integer) session.save(questionBean);
//			tx.commit();
		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
			logger.error("QuestionDAOImpl: Inside addQuestion: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("QuestionDAOImpl: Inside addQuestion: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return maxId;
	}

	public boolean updateQuestion(QuestionBean questionBean) {
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			CategoryBean categoryBean = (CategoryBean) session.get(CategoryBean.class, questionBean.getCategoryId());
			questionBean.setCategoryBean(categoryBean);
			session.update(questionBean);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("QuestionDAOImpl: Inside updateQuestion: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("QuestionDAOImpl: Inside updateQuestion: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return flag;
	}

	@Transactional
	public int softDeleteQuestion(int status, int questionId) {
		Session session = null;
		int result = 0;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createQuery("update QuestionBean set status = :status where questionId = :questionId");
			query.setParameter("status", status);
			query.setParameter("questionId", questionId);
			result = query.executeUpdate();
		} catch (Exception e) {
			logger.error("QuestionDAOImpl: Inside softDeleteQuestion: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("QuestionDAOImpl: Inside softDeleteQuestion: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return result;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<QuestionBean> getAllQuestions(String question, String answer,
			int categoryId) {
		Session session = null;
		List<QuestionBean> lstCategories = new ArrayList<QuestionBean>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(QuestionBean.class);
			if (question != null && !"".equals(question)) {
				criteria.add(Restrictions.like("question", question,
						MatchMode.ANYWHERE));
			}
			if (answer != null && !"".equals(answer)) {
				criteria.add(Restrictions.like("answer", answer,
						MatchMode.ANYWHERE));
			}
			if (categoryId != 0) {
				criteria.createCriteria("categoryBean");
				criteria.add(Restrictions.eq("categoryBean.categoryId",
						categoryId));
			}
			criteria.addOrder(Order.desc("createdOn"));
			lstCategories = (List<QuestionBean>) criteria.list();
		} catch (Exception e) {
			logger.error("QuestionDAOImpl: Inside getAllCategories: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("QuestionDAOImpl: Inside getAllCategories: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return lstCategories;
	}

	@Override
	public QuestionBean getQuestionInfoById(int questionId) {
		Session session = null;
		QuestionBean questionBean = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(QuestionBean.class);
			criteria.add(Restrictions.eq("questionId", questionId));
			questionBean = (QuestionBean) criteria.list().get(0);
		} catch (Exception e) {
			logger.error("QuestionDAOImpl: Inside getQuestionInfoById: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("QuestionDAOImpl: Inside getQuestionInfoById: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return questionBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionBean> getQuestionsByCategoryId(int catId) {
		List<QuestionBean> lstQuestions = new ArrayList<QuestionBean>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(QuestionBean.class);
			criteria.add(Restrictions.eq("categoryBean.categoryId", catId));
			lstQuestions = (List<QuestionBean>) criteria.list();
		} catch (Exception e) {
			logger.error("QuestionDAOImpl: Inside getQuestionsByCategoryId: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("QuestionDAOImpl: Inside getQuestionsByCategoryId: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return lstQuestions;
	}
}