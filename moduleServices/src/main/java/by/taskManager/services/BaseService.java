package by.taskManager.services;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import by.taskManager.dao.Dao;
import by.taskManager.dao.exceptions.DaoException;

@Transactional
public class BaseService<T> implements Service<T> {

	protected final static Logger logger = Logger.getLogger(BaseService.class);

	protected Dao<T> dao;

	@Autowired
	public BaseService(Dao<T> dao) {
		this.dao = dao;
	}

	@Override
	public T saveOrUpdate(T t) {
		try {
			dao.saveOrUpdate(t);
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
		return t;
	}

	@Override
	public T get(Serializable id) {
		T t = null;
		try {
			t = dao.get(id);
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
		return t;
	}

	@Override
	public T load(Serializable id) {
		T t = null;
		try {
			t = dao.load(id);
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
		return t;
	}

	@Override
	public void delete(T t) {
		try {
			dao.delete(t);
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
	}

	@Override
	public void refresh(T t) {
		try {
			dao.refresh(t);
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
	}

	@Override
	public List<T> getAll() {
		List<T> list = null;
		try {
			list = dao.getAll();
		} catch (DaoException e) {
			logger.error("Error was thrown in DAO:" + e);
		}
		return list;
	}

}
