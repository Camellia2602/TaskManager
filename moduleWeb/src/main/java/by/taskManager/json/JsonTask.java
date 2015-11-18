package by.taskManager.json;

import java.util.List;

import by.taskManager.beans.Priority;
import by.taskManager.beans.Task;

public class JsonTask {

	private Task task;

	private List<Priority> priorities;

	private int currentPage;

	private String error;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Priority> getPriorities() {
		return priorities;
	}

	public void setPriorities(List<Priority> priorities) {
		this.priorities = priorities;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
