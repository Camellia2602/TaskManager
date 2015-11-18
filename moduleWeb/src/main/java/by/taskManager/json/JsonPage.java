package by.taskManager.json;

import java.util.List;

import by.taskManager.beans.Priority;
import by.taskManager.beans.Task;

public class JsonPage {

	private List<Task> tasks;

	private List<Priority> priorities;

	private int currentPage;

	private boolean nextPage;

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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

}
