package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public List<T> listAll();

	public T findById(T object) throws SQLException;

	public void delete(T object) throws SQLException;

	public void save(T object) throws SQLException;

	public void update(T object) throws SQLException;

}
