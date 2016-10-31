package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

	public List<T> listAll();

	public T findById(int id) throws SQLException;

	public void delete(int id) throws SQLException;

}
