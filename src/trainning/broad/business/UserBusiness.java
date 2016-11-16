package trainning.broad.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.Comment;
import trainning.broad.bean.Post;
import trainning.broad.bean.PostTag;
import trainning.broad.bean.User;
import trainning.broad.bean.UserPostComment;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;

public class UserBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	private PostTagDAO postTagDAO;

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

	public List<UserPostComment> getUserPostComments() throws SQLException {

		List<UserPostComment> userPostComments = new ArrayList<UserPostComment>();
		UserPostComment userPostComment;
		List<User> users;

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);

			users = userDAO.findAll();

			for (User user : users) {
				userPostComment = new UserPostComment();
				userPostComment.setUser(user);
				userPostComment.setPosts(postDAO.findByProperty(Constants.ATTR_USER_ID, user.getUserId()));
				userPostComment.setComments(commentDAO.findByProperty(Constants.ATTR_USER_ID, user.getUserId()));
				userPostComments.add(userPostComment);
			}

			return userPostComments;
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public UserPostComment getUserPostComment(int userId) throws SQLException {

		UserPostComment userPostComment = new UserPostComment();
		User user;

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);

			user = userDAO.findById(userId);
			userPostComment.setUser(user);
			userPostComment.setPosts(postDAO.findByProperty(Constants.ATTR_USER_ID, user.getUserId()));
			userPostComment.setComments(commentDAO.findByProperty(Constants.ATTR_USER_ID, user.getUserId()));

			return userPostComment;
		} catch (

		SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public List<Post> getPosts(int userId) throws SQLException {

		List<Post> posts;

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			posts = postDAO.findByProperty(Constants.ATTR_USER_ID, userId);

			return posts;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public List<Comment> getComments(int userId) throws SQLException {

		List<Comment> comments;

		try {
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
			comments = commentDAO.findByProperty(Constants.ATTR_USER_ID, userId);

			return comments;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void update(User user) throws SQLException {

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			userDAO.save(user);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void delete(int userId) throws SQLException {

		List<Post> posts;
		List<Comment> comments;
		List<PostTag> postTags;

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);

			daoManager.setAutoCommit(false);
			userDAO.delete(userId);
			posts = postDAO.findByProperty(Constants.ATTR_USER_ID, userId);
			if (!Helpers.isEmpty(posts)) {
				for (Post post : posts) {
					postDAO.delete(post.getPostId());
					postTags = postTagDAO.findByProperty(Constants.ATTR_POST_ID, post.getPostId());

					if (!Helpers.isEmpty(postTags))
						for (PostTag postTag : postTags) {
							postTagDAO.delete(postTag.getPostTagId());
						}
				}
			}

			comments = commentDAO.findByProperty(Constants.ATTR_USER_ID, userId);
			if (!Helpers.isEmpty(comments)) {
				for (Comment comment : comments) {
					commentDAO.delete(comment.getCommentId());
				}
			}

			daoManager.commit();

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.setAutoCommit(true);
			daoManager.close();
		}
	}
}
