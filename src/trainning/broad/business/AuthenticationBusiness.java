package trainning.broad.business;

import java.sql.SQLException;

import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.Table;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.UserDAO;

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

	public User checkLogin(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Table.USER);
			return userDAO.findByEmailAndPassword(user);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public boolean isAvalibleUser(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Table.USER);
			return userDAO.isAvalibleUser(user);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void register(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Table.USER);
			userDAO.saveForRegister(user);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

}
