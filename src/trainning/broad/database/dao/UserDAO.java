package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.User;

public interface UserDAO extends GenericDAO<User> {

	public User findByEmail(User user) throws SQLException;


}
