package by.taskManager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.taskManager.beans.Task;
import by.taskManager.dao.ITaskDao;
import by.taskManager.dao.exceptions.DaoException;
import by.taskManager.services.BaseService;
import by.taskManager.services.ITaskService;

@Service
@Transactional
public class TaskServiceImpl extends BaseService<Task> implements ITaskService {

	@Autowired
	public TaskServiceImpl(ITaskDao taskDaoImpl) {
		super(taskDaoImpl);
	}

	public List<Task> getTasks(Integer page) {
		List<Task> list = null;
		try {
			list = ((ITaskDao) dao).getTasks(page);
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
		return list;
	}

	public Long getCountTasks() {
		Long count = null;
		try {
			count = ((ITaskDao) dao).getCountTasks();
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
		return count;
	}

}
