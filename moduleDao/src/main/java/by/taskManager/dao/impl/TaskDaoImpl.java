package by.taskManager.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.taskManager.beans.Task;
import by.taskManager.dao.BaseDao;
import by.taskManager.dao.ITaskDao;

@Repository
public class TaskDaoImpl extends BaseDao<Task> implements ITaskDao{

	@Autowired
	public TaskDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

}
