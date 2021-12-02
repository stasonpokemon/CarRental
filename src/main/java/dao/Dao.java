package dao;

import exceptions.NoConnectionJDBCException;
import pojo.Entity;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Entity> {
	Integer create(T t) throws NoConnectionJDBCException, SQLException;

//	T read(Integer id);
//
//	void update(T t);
//
//	void delete(Integer id);
//	List<T> readAll();
}