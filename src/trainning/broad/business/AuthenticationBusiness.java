package trainning.broad.business;

import java.sql.SQLException;

import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.Table;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Helpers;

public class AuthenticationBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;

	public AuthenticationBusiness() {
		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean checkLogin(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Table.USER);
			if (Helpers.isEmpty(userDAO.findByEmail(user))) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw e;
		}
		finally {
			daoManager.close();
		}
	}

}
