package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.User;

public interface UserDAO extends GenericDAO<User> {

	public User findByEmailAndPassword(User user) throws SQLException;

	public boolean isAvalibleUser(User user) throws SQLException;

	public void saveForRegister(User user) throws SQLException;

}
