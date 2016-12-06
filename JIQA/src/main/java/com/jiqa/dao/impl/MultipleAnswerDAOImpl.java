package com.jiqa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jiqa.dao.MultipleAnswerDAO;
import com.jiqa.entity.MultipleAnswerBean;

@Component
class MultipleAnswerDAOImpl implements MultipleAnswerDAO {

	Logger logger = Logger.getLogger(MultipleAnswerDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public int addAnswer(MultipleAnswerBean multipleAnswerBean, Session session) {
		int maxId = 0;
		try {
			maxId = (Integer) session.save(multipleAnswerBean);
		} catch (Exception e) {
			logger.error("OptionDAOImpl: Inside addAnswer: Exception is: "
					+ e.getMessage());
		}
		return maxId;
	}

//	public boolean updateOption(OptionBean optionBean) {
//		Session session = null;
//		Transaction tx = null;
//		boolean flag = false;
//		try {
//			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//			session.saveOrUpdate(optionBean);
//			tx.commit();
//			flag = true;
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			logger.error("OptionDAOImpl: Inside updateOption: Exception is: "
//					+ e.getMessage());
//		} finally {
//			try {
//				if (session != null) {
//					session.close();
//				}
//			} catch (Exception e2) {
//				logger.error("OptionDAOImpl: Inside updateOption: Inside Finally: Exception is: "
//						+ e2.getMessage());
//			}
//		}
//		return flag;
//	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<MultipleAnswerBean> getAllAnswersByQuestionId(int questionId) {
		Session session = null;
		List<MultipleAnswerBean> lstAnswers = new ArrayList<MultipleAnswerBean>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MultipleAnswerBean.class);
			
			if (questionId != 0) {
				criteria.createCriteria("multipleQuestionBean");
				criteria.add(Restrictions.eq("multipleQuestionBean.questionId", questionId));
			}
			lstAnswers = (List<MultipleAnswerBean>) criteria.list();
		} catch (Exception e) {
			logger.error("OptionDAOImpl: Inside getAllAnswersByQuestionId: Exception is: " + e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("OptionDAOImpl: Inside getAllAnswersByQuestionId: Inside Finally: Exception is: " + e2.getMessage());
			}
		}
		return lstAnswers;
	}

	/*@Override
	public OptionBean getOptionInfoById(int optionId) {
		Session session = null;
		OptionBean optionBean = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(OptionBean.class);
			criteria.add(Restrictions.eq("optionId", optionId));
			optionBean = (OptionBean) criteria.list().get(0);
		} catch (Exception e) {
			logger.error("OptionDAOImpl: Inside getOptionInfoById: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("OptionDAOImpl: Inside getOptionInfoById: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return optionBean;
	}*/
}