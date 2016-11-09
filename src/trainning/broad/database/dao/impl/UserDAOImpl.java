package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainning.broad.bean.User;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.DAOHelpers;
import trainning.broad.helpers.Helpers;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public UserDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_USER;
		this.con = con;
		this.id = Constants.ATTR_USER_ID;

	}

	@Override
	public User findByEmail(String email) throws SQLException {

		String query = "SELECT * FROM " + tableName + " WHERE " + Constants.ATTR_EMAIL + " = ?";
		statement = con.prepareStatement(query);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		return DAOHelpers.convertResultToUser(result);
	}

	@Override
	public int countEmail(String email) throws SQLException {

		String query = "SELECT COUNT(*) AS num FROM " + tableName + " WHERE " + Constants.ATTR_EMAIL + " = ?";
		statement = con.prepareStatement(query);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			return result.getInt("num");
		} else
			return 0;
	}

	@Override
	public void saveEmail(String email) throws SQLException {

		String query = "INSERT INTO " + tableName + "(" + Constants.ATTR_EMAIL + ") VALUES(?)";
		statement = con.prepareStatement(query);
		statement.setString(1, email);
		statement.executeUpdate();

	}

	@Override
	public void active(String email, String password) throws SQLException {

		String query = "UPDATE " + tableName + " SET " + Constants.ATTR_PASSWORD + " = ?, " + Constants.ATTR_IS_ACTIVE
				+ " = ?, " + Constants.ATTR_UPDATE_AT + " = ?  WHERE " + Constants.ATTR_EMAIL + " = ?";
		statement = con.prepareStatement(query);
		statement.setString(1, password);
		statement.setBoolean(2, true);
		statement.setString(4, email);
		statement.setTimestamp(3, Helpers.getCurrenTimeStamp());
		statement.executeUpdate();

	}
}
