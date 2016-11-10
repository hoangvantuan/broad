package trainning.broad.business;

import java.sql.SQLException;

import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;

public class UserBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;

	public UserBusiness() throws ClassNotFoundException {

		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	public User getUser(int userId) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			return userDAO.findById(userId);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}

	}

	public User getUSer(String email) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			return userDAO.findByEmail(email);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}
}
