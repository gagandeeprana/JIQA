package com.jiqa.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiqa.dao.MultipleChoiceQuestionDAO;
import com.jiqa.entity.CategoryBean;
import com.jiqa.entity.MultipleQuestionBean;

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

	/*@Transactional
	@SuppressWarnings("unchecked")
	public List<QuestionBean> getAllMultipleChoiceQuestions(String question, String answer, int categoryId) {
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
	}*/

	/*@Override
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
	}*/

	@Override
	public MultipleQuestionBean getMultipleChoiceQuestionInfoById(int questionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultipleQuestionBean getMultipleChoiceQuestionInfoByCategoryId(
			int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MultipleQuestionBean> getAllMultipleChoiceQuestions(
			String question, String answer, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
}