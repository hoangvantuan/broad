package trainning.broad.business;

import java.sql.SQLException;

import trainning.broad.bean.Comment;
import trainning.broad.bean.Post;
import trainning.broad.bean.User;
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

	public boolean isMyComment(String email, int commentId) throws SQLException {

		Comment comment;
		User user;

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);

			user = userDAO.findByEmail(email);
			comment = commentDAO.findById(commentId);

			return user.getUserId() == comment.getUserId() ? true : false;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public boolean isCommentOfPost(int commentId, int postId) throws SQLException {

		Comment comment;
		Post post;

		try {
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);

			comment = commentDAO.findById(commentId);
			post = postDAO.findById(postId);

			return comment.getPostId() == post.getPostId() ? true : false;

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
