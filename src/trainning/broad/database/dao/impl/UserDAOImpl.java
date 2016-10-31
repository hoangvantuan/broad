package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainning.broad.bean.User;
import trainning.broad.database.Table;
import trainning.broad.database.dao.UserDAO;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public UserDAOImpl(Connection con) {

		this.tableName = Table.USER;
		this.con = con;
		this.id = Table.USER_ID;

	}

	@Override
	public User findByEmailAndPassword(User user) throws SQLException {

		String query = "SELECT * FROM " + Table.USER + " WHERE email = ? and password = ?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPassword());
		ResultSet result = statement.executeQuery();
		return convertResultToUser(result, user);
	}

	@Override
	public boolean isAvalibleUser(User user) throws SQLException {

		String query = "SELECT COUNT(*) AS num FROM " + tableName + " WHERE email = ?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1, user.getEmail());
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			return result.getInt("num") > 0 ? true : false;
		} else
			return false;
	}

	@Override
	public void saveForRegister(User user) throws SQLException {

		String query = "INSERT INTO " + tableName + "(email) VALUES(?)";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1, user.getEmail());
		statement.executeUpdate();

	}

	private User convertResultToUser(ResultSet result, User user) throws SQLException {

		if (result.next()) {
			do {
				user.setUserId(result.getInt("user_id"));
				user.setUserName(result.getString("user_name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setIsRole(result.getBoolean("is_role"));
				user.setIsActive(result.getBoolean("is_active"));
				user.setCreateAt(result.getDate("create_at"));
				user.setCreateAt(result.getDate("update_at"));
			} while (result.next());
		} else {
			user = null;
		}
		return user;
	}
}
