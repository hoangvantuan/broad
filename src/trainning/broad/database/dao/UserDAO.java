package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.User;

public interface UserDAO extends GenericDAO<User> {

	public User findByEmail(String email) throws SQLException;

	public boolean isAvalibleUser(User user) throws SQLException;

	public void saveEmail(String email) throws SQLException;

	public void active(String email, String password) throws SQLException;

}
