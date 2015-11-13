package by.taskManager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.taskManager.beans.Priority;
import by.taskManager.dao.IPriorityDao;
import by.taskManager.services.BaseService;
import by.taskManager.services.IPriorityService;

@Service
@Transactional
public class PriorityServiceImpl extends BaseService<Priority> implements IPriorityService{
	
	@Autowired
	public PriorityServiceImpl(IPriorityDao priorityDaoImpl) {
		super(priorityDaoImpl);
	}

}
 