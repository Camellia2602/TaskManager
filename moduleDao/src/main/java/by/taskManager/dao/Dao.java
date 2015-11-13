package by.taskManager.dao;

import java.io.Serializable;
import java.util.List;

import by.taskManager.dao.exceptions.DaoException;

public interface Dao<T> {
	
	T saveOrUpdate(T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    T load(Serializable id) throws DaoException;

    void delete(T t) throws DaoException;

    void refresh(T t)throws DaoException;

    List<T> getAll() throws DaoException;

}
