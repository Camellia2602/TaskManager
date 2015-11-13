package by.taskManager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.taskManager.beans.Task;
import by.taskManager.dao.ITaskDao;
import by.taskManager.services.BaseService;
import by.taskManager.services.ITaskService;

@Service
@Transactional
public class TaskServiceImpl extends BaseService<Task> implements ITaskService{

	@Autowired
	public TaskServiceImpl(ITaskDao taskDaoImpl) {
		super(taskDaoImpl);
	}

}
 