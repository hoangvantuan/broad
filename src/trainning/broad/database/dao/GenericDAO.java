package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public void delete(int id) throws SQLException;

	public T findById(int id) throws SQLException;

	public List<T> findAll() throws SQLException;

	public List<T> findByProperty(String property, int value) throws SQLException;

	public List<T> findByProperty(String property, String value) throws SQLException;

}
