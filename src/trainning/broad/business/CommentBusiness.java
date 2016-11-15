package trainning.broad.business;

import java.sql.SQLException;

import trainning.broad.bean.Comment;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;

public class CommentBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	public CommentBusiness() throws ClassNotFoundException {

		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	public boolean isMyComment(int userId, int commentId) throws SQLException {

		Comment comment;

		try {
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
			comment = commentDAO.findById(commentId);

			return userId == comment.getUserId() ? true : false;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void deleteComment(int commentId) throws SQLException {

		try {
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
			commentDAO.delete(commentId);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}
}