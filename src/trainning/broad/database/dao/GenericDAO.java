package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public void delete(int id) throws SQLException;

	public T getById(int id) throws SQLException;

	public List<T> getAll() throws SQLException;

	public List<T> getByProperty(String property, int value) throws SQLException;

	public List<T> getByproperty(String property, String value) throws SQLException;

}
