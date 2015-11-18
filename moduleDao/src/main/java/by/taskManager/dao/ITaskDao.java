package by.taskManager.dao;

import java.util.List;

import by.taskManager.beans.Task;
import by.taskManager.dao.exceptions.DaoException;

public interface ITaskDao extends Dao<Task> {

	List<Task> getTasks(Integer page) throws DaoException;

	Long getCountTasks() throws DaoException;

}
