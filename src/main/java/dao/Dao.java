package dao;

import pojo.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
	Integer create(T t);

	T read(Integer id);

	void update(T t);

	void delete(Integer id);
	List<T> readAll();
}