package by.taskManager.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T> {

	T saveOrUpdate(T t);

	T get(Serializable id);

	T load(Serializable id);

	void delete(T t);

	void refresh(T t);

	List<T> getAll();
}
