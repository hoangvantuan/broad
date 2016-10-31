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
		super(Table.USER, con);
	}

	@Override
	public User findByEmail(User user) throws SQLException {

		String query = "SELECT * FROM public.user WHERE email = ? and password = ?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPassword());
		ResultSet result = statement.executeQuery();

		if (result.next()) {
			do {
				user.setUserId(result.getInt("user_id"));
				user.setUserName(result.getString("user_name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setIsRole(result.getBoolean("is_role"));
				user.setIsActive(result.getBoolean("is_active"));
				user.setCreateAt(result.getTimestamp("create_at"));
				user.setCreateAt(result.getTimestamp("update_at"));
			} while (result.next());
		} else {
			user = null;
		}
		return user;
	}
}
