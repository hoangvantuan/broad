package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public void delete(int id) throws SQLException;

	public T findById(int id) throws SQLException;

	public List<T> findAll() throws SQLException;

	public List<T> findByProperty(String attribute, Object value) throws SQLException;

	public void save(String query) throws SQLException;

	public void update(String query) throws SQLException;

}
