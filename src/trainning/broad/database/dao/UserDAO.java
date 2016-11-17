package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

import trainning.broad.bean.User;

public interface UserDAO extends GenericDAO<User> {

	public User getByEmail(String email) throws SQLException;

	public int countEmail(String email) throws SQLException;

	public void saveEmail(String email) throws SQLException;

	public void active(String email, String password) throws SQLException;

	public void save(User user) throws SQLException;

	public List<User> search(String keyWord) throws SQLException;

}
