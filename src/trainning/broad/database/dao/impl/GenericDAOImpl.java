package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.util.List;

import trainning.broad.database.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected String tableName;
	protected Connection con;

	public GenericDAOImpl(String tableName, Connection con) {
		this.tableName = tableName;
		this.con = con;
	}

	@Override
	public void delete(T object) {

	}

	@Override
	public T findById(T object) {
		return null;
	}

	@Override
	public List<T> listAll() {
		return null;
	}

	@Override
	public void save(T object) {

	}

	@Override
	public void update(T object) {

	}
}
