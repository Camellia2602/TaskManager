package by.taskManager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.taskManager.beans.Task;
import by.taskManager.dao.BaseDao;
import by.taskManager.dao.ITaskDao;
import by.taskManager.dao.exceptions.DaoException;

@Repository
public class TaskDaoImpl extends BaseDao<Task> implements ITaskDao {

	@Autowired
	public TaskDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Task> getTasks(Integer page) throws DaoException {
		Integer maxRows = 50;
		Criteria criteria = getSession().createCriteria(Task.class);
		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult((page - 1) * maxRows).setMaxResults(maxRows);
		@SuppressWarnings("unchecked")
		List<Task> results = criteria.list();
		return results;
	}

	public Long getCountTasks() throws DaoException {
		Criteria criteria = getSession().createCriteria(Task.class);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
