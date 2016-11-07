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

import com.jiqa.dao.OptionDAO;
import com.jiqa.entity.CategoryBean;
import com.jiqa.entity.OptionBean;
import com.jiqa.entity.QuestionBean;

@Component
class OptionDAOImpl implements OptionDAO {

	Logger logger = Logger.getLogger(OptionDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public int addOption(OptionBean optionBean) {
		Session session = null;
		Transaction tx = null;
		int maxId = 0;
		try {
			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
			maxId = (Integer) session.save(optionBean);
//			tx.commit();
		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
			logger.error("OptionDAOImpl: Inside addOption: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("OptionDAOImpl: Inside addOption: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return maxId;
	}

	public boolean updateOption(OptionBean optionBean) {
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(optionBean);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("OptionDAOImpl: Inside updateOption: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("OptionDAOImpl: Inside updateOption: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return flag;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<OptionBean> getAllOptionsByQuestionId(int questionId) {
		Session session = null;
		List<OptionBean> lstQuestions = new ArrayList<OptionBean>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(OptionBean.class);
			
			if (questionId != 0) {
				criteria.createCriteria("multipleQuestionBean");
				criteria.add(Restrictions.eq("multipleQuestionBean.questionId",
						questionId));
			}
			lstQuestions = (List<OptionBean>) criteria.list();
		} catch (Exception e) {
			logger.error("OptionDAOImpl: Inside getAllOptionsByQuestionId: Exception is: "
					+ e.getMessage());
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e2) {
				logger.error("OptionDAOImpl: Inside getAllOptionsByQuestionId: Inside Finally: Exception is: "
						+ e2.getMessage());
			}
		}
		return lstQuestions;
	}

	@Override
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
	}
}