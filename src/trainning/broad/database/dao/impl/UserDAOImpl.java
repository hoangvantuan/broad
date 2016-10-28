package trainning.broad.database.dao.impl;

import java.sql.Connection;

import trainning.broad.bean.User;
import trainning.broad.database.Table;
import trainning.broad.database.dao.UserDAO;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public UserDAOImpl(Connection con) {
		super(Table.USER, con);
	}
}
