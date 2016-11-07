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

import com.jiqa.dao.MultipleChoiceQuestionDAO;
import com.jiqa.entity.CategoryBean;
import com.jiqa.entity.MultipleQuestionBean;
import com.jiqa.entity.QuestionBean;

@Component
class MultipleChoiceQuestionDAOImpl implements MultipleChoiceQuestionDAO {

	Logger logger = Logger.getLogger(MultipleChoiceQuestionDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public int addMultipleChoiceQuestion(MultipleQuestionBean multipleQuestionBean) {
		Session session = null;
		Transaction tx = null;
		int maxId = 0;
		try {
			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
			maxId = (Integer) session.save(multipleQuestionBean);
//			tx.commit();
		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
			logger.error("MultipleChoiceQuestionDAOImpl: Inside addMultipleChoiceQuestion: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("MultipleChoiceQuestionDAOImpl: Inside addMultipleChoiceQuestion: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return maxId;
	}

	public boolean updateMultipleChoiceQuestion(MultipleQuestionBean multipleQuestionBean) {
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			CategoryBean categoryBean = (CategoryBean) session.get(CategoryBean.class, multipleQuestionBean.getCategoryId());
			multipleQuestionBean.setCategoryBean(categoryBean);
			session.update(multipleQuestionBean);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("MultipleChoiceQuestionDAOImpl: Inside updateMultipleChoiceQuestion: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("MultipleChoiceQuestionDAOImpl: Inside updateMultipleChoiceQuestion: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return flag;
	}

	@Transactional
	public int softDeleteMultipleChoiceQuestion(int status, int questionId) {
		Session session = null;
		int result = 0;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createQuery("update MultipleQuestionBean set status = :status where questionId = :questionId");
			query.setParameter("status", status);
			query.setParameter("questionId", questionId);
			result = query.executeUpdate();
		} catch (Exception e) {
			logger.error("MultipleChoiceQuestionDAOImpl: Inside softDeleteMultipleChoiceQuestion: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("MultipleChoiceQuestionDAOImpl: Inside softDeleteMultipleChoiceQuestion: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return result;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<MultipleQuestionBean> getAllMultipleChoiceQuestions(String question, String answer, int categoryId) {
		Session session = null;
		List<MultipleQuestionBean> lstQuestions = new ArrayList<MultipleQuestionBean>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MultipleQuestionBean.class);
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
			lstQuestions = (List<MultipleQuestionBean>) criteria.list();
		} catch (Exception e) {
			logger.error("MultipleChoiceQuestionDAOImpl: Inside getAllMultipleChoiceQuestions: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("MultipleChoiceQuestionDAOImpl: Inside getAllMultipleChoiceQuestions: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return lstQuestions;
	}

	@Override
	public MultipleQuestionBean getMultipleChoiceQuestionInfoById(int questionId) {
		Session session = null;
		MultipleQuestionBean questionBean = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MultipleQuestionBean.class);
			criteria.add(Restrictions.eq("questionId", questionId));
			questionBean = (MultipleQuestionBean) criteria.list().get(0);
		} catch (Exception e) {
			logger.error("MultipleChoiceQuestionDAOImpl: Inside getMultipleChoiceQuestionInfoById: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("MultipleChoiceQuestionDAOImpl: Inside getMultipleChoiceQuestionInfoById: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return questionBean;
	}

	@Override
	public MultipleQuestionBean getMultipleChoiceQuestionInfoByCategoryId(int categoryId) {
		Session session = null;
		MultipleQuestionBean questionBean = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MultipleQuestionBean.class);
			criteria.add(Restrictions.eq("categoryBean.categoryId", categoryId));
			questionBean = (MultipleQuestionBean) criteria.list().get(0);
		} catch (Exception e) {
			logger.error("MultipleChoiceQuestionDAOImpl: Inside getMultipleChoiceQuestionInfoByCategoryId: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("MultipleChoiceQuestionDAOImpl: Inside getMultipleChoiceQuestionInfoByCategoryId: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return questionBean;
	}
}