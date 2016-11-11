package trainning.broad.business;

import java.sql.SQLException;

import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.UserDAO;

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

	public boolean isMyComment(int commentId) throws SQLException {
		return false;
	}

	public boolean checkCommentOfPost(int commentId, int postId) throws SQLException {
		return false;
	}

	public void deleteComment(int commentId) throws SQLException {

	}
}
