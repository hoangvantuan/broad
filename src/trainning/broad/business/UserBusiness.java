package trainning.broad.business;

import java.sql.SQLException;
import java.util.List;

import trainning.broad.bean.Comment;
import trainning.broad.bean.Post;
import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;

public class UserBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

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

	public int getNumPost(int userId) throws SQLException {

		List<Post> posts;

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			posts = postDAO.findByProperty(Constants.ATTR_USER_ID, userId);

			return posts.size();

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public int getNumComment(int userId) throws SQLException {

		List<Comment> comments;

		try {
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
			comments = commentDAO.findByProperty(Constants.ATTR_USER_ID, userId);

			return comments.size();

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void updateUser(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.save(user);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void deleteUser(int userId) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.delete(userId);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}
}
