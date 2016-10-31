package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.util.List;

import trainning.broad.database.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected String tableName;
	protected Connection con;
	protected String id;

	public GenericDAOImpl() {
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public T findById(int id) {
		return null;
	}

	@Override
	public List<T> listAll() {
		return null;
	}
}
