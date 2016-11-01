package trainning.broad.business;

import java.sql.SQLException;

import org.apache.commons.mail.EmailException;

import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
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
			return userDAO.findByEmailAndPassword(user);
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

	public void register(User user) throws SQLException, EmailException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.saveForRegister(user);

			MailUtils mail = new MailUtils();
			String subject = "新しいアカウントアクティブ";
			String content = "Dear " + user.getEmail() + " Thanks you for your register. ";
			mail.sendEmail(Constants.FROM_EMAIL, user.getEmail(), subject, content);
		} catch (SQLException | EmailException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

}
