package by.taskManager.dao;

import by.taskManager.dao.exceptions.DaoException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public class BaseDao<T> implements Dao<T>{
	
	final static Logger logger = Logger.getLogger(BaseDao.class);
    private SessionFactory sessionFactory;
    private static final String HQL_GET_All = "FROM ";


    @Autowired
    public BaseDao (SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Add or edit an object in the database
     * @param t - an object
     */
    @Override
    public T saveOrUpdate(T t) throws DaoException {
    	try {
            getSession().saveOrUpdate(t);
            logger.info("saveOrUpdate(t):" + t);
        } catch (HibernateException e) {
            logger.error("Error save or update in Dao" + e);
            throw new DaoException(e);
        }

        return t;
    }

    /**
     * Get an object from the database
     *
     * @param id - Serializable id
     * @return an object from the database
     */
    @SuppressWarnings("unchecked")
	@Override
    public T get(Serializable id) throws DaoException {
        T t;
        try {
            t = (T) getSession().get(getPersistentClass(), id);
            logger.info("get class:" + t);
        } catch (HibernateException e) {
            logger.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }

        return t;
    }

    /**
     * Load an object from the database
     * @param id - Serializable id
     * @return an object from the database
     */
    @SuppressWarnings("unchecked")
	@Override
    public T load(Serializable id) throws DaoException {
        T t;
        try {
            t = (T) getSession().load(getPersistentClass(), id);
            logger.info("load() class:" + t);
        } catch (HibernateException e) {
            logger.error("Error load() " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    /**
     * Delete an object
     * @param t - an object
     */
    @Override
    public void delete(T t) throws DaoException {
    	try {
            getSession().delete(t);
            logger.info("Delete:" + t);
        } catch (HibernateException e) {
            logger.error("Error delete in Dao" + e);
            throw new DaoException(e);
        }
    }

    /**
     * Refresh an object
     * @param t - an object
     */
    @Override
    public void refresh(T t) throws DaoException {
        try {
            getSession().refresh(t);
            logger.info("Refresh:" + t);
            } catch (HibernateException e) {
            logger.error("Error refresh in Dao" + e);
            throw new DaoException(e);
            }
    }

    /**
     * Get a list
     * @return a list
     */
    @Override
    public List<T> getAll() throws DaoException{

        Query query = getSession().createQuery(HQL_GET_All + getPersistentClass().getSimpleName());
        @SuppressWarnings("unchecked")
		List<T> results = query.list();
        return results;
    }

    @SuppressWarnings("unchecked")
	private Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
