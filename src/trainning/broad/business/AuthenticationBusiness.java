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
import trainning.broad.mail.Mails;

public class AuthenticationBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;

	public AuthenticationBusiness() throws ClassNotFoundException {
		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	public boolean checkLogin(String email, String password) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			User tempUser = userDAO.findByEmail(email);

			if (!Helpers.isEmpty(tempUser) && tempUser.getIsActive() == true
					&& tempUser.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public boolean isAvalibledEmail(String email) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			int numEmail = userDAO.countEmail(email);

			return numEmail == 0 ? true : false;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void register(String email) throws SQLException, EmailException, NoSuchAlgorithmException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.saveEmail(email);
			Mails mail = new Mails();
			String subject = "新しいアカウントアクティブ";
			String content = "http://localhost:8080/broad/active?email=" + email + "&code="
					+ Helpers.getCodeActive(email);
			mail.sendEmail(Constants.FROM_EMAIL, email, subject, content);

		} catch (SQLException | EmailException | NoSuchAlgorithmException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public boolean checkActiveInfo(String email, String code) throws SQLException, NoSuchAlgorithmException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			User tempUser = userDAO.findByEmail(email);

			if (!Helpers.isEmpty(tempUser) && !tempUser.getIsActive() && code.equals(Helpers.getCodeActive(email))) {
				return true;
			} else {
				return false;
			}

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

	public User getUser(String email) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			return userDAO.findByEmail(email);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void sendPassword(User user, String email) throws EmailException {

		try {
			Mails mail = new Mails();
			String subject = "パスワードを忘れてしまった";
			String content = user.getPassword();
			mail.sendEmail(Constants.FROM_EMAIL, email, subject, content);
		} catch (EmailException e) {
			throw e;
		}
	}
}
