package by.taskManager.services;

import java.util.List;

import by.taskManager.beans.Task;

public interface ITaskService extends Service<Task> {

	List<Task> getTasks(Integer page);

	Long getCountTasks();

}
