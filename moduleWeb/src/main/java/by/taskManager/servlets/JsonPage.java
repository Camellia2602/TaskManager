package by.taskManager.servlets;

import java.util.List;

import by.taskManager.beans.Priority;
import by.taskManager.beans.Task;

public class JsonPage {
	
	private List<Task> tasks;
	
	private List<Priority> priorities;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Priority> getPriorities() {
		return priorities;
	}

	public void setPriorities(List<Priority> priorities) {
		this.priorities = priorities;
	}
	
	
}
