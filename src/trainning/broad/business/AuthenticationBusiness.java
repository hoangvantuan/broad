package trainning.broad.business;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.apache.commons.mail.EmailException;

import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.mail.MailUtils;

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
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			User tempUser = userDAO.findByEmail(user.getEmail());
			if (!Helpers.isEmpty(tempUser)) {
				if (tempUser.getIsActive() && tempUser.getPassword().equals(user.getPassword())) {
					return tempUser;
				}
			}
			return null;
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public boolean isAvalibleUser(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			return userDAO.isAvalibleUser(user);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void register(User user) throws SQLException, EmailException, NoSuchAlgorithmException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.saveEmail(user.getEmail());

			MailUtils mail = new MailUtils();
			String subject = "新しいアカウントアクティブ";
			String content = "http://localhost:8080/broad/active?email=" + user.getEmail() + "&code="
					+ Helpers.getCodeActive(user.getEmail());
			mail.sendEmail(Constants.FROM_EMAIL, user.getEmail(), subject, content);
		} catch (SQLException | EmailException | NoSuchAlgorithmException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public boolean isActive(String email, String code) throws SQLException, NoSuchAlgorithmException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			User tempUser = userDAO.findByEmail(email);
			if (!Helpers.isEmpty(tempUser)) {
				if (!tempUser.getIsActive()) {
					if (Helpers.getCodeActive(email).equals(code)) {
						return true;
					}
				}
			}
			return false;
		} catch (SQLException | NoSuchAlgorithmException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void active(String email, String password) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.active(email, password);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}

	}

}
