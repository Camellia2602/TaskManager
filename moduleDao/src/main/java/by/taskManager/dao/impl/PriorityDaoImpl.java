package by.taskManager.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.taskManager.beans.Priority;
import by.taskManager.dao.BaseDao;
import by.taskManager.dao.IPriorityDao;

@Repository
public class PriorityDaoImpl extends BaseDao<Priority> implements IPriorityDao{

	@Autowired
	public PriorityDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

}
