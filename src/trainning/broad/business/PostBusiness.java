package trainning.broad.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.Comment;
import trainning.broad.bean.Post;
import trainning.broad.bean.PostTag;
import trainning.broad.bean.PostUserTag;
import trainning.broad.bean.Tag;
import trainning.broad.bean.User;
import trainning.broad.bean.UserComment;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.database.dao.TagDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;

public class PostBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private TagDAO tagDAO;
	private PostTagDAO postTagDAO;
	private CommentDAO commentDAO;

	public PostBusiness() throws ClassNotFoundException {

		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	public PostUserTag getPostDetails(int postId) throws SQLException {

		PostUserTag postUserTagComment = new PostUserTag();
		List<Tag> tags = new ArrayList<Tag>();
		List<PostTag> postTags;
		Post post;
		User user;

		postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
		postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
		tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
		userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);

		try {
			post = postDAO.findById(postId);

			if (Helpers.isEmpty(post)) {
				return null;
			}

			user = userDAO.findById(post.getUserId());
			postTags = postTagDAO.findByProperty(Constants.ATTR_POST_ID, post.getPostId());

			for (PostTag postTag : postTags) {
				tags.add(tagDAO.findById(postTag.getTagId()));
			}

			postUserTagComment.setPost(post);
			postUserTagComment.setTags(tags);
			postUserTagComment.setUser(user);

			return postUserTagComment;
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public List<UserComment> getUserComment(int postId) throws SQLException {

		List<UserComment> userComments = new ArrayList<UserComment>();
		List<Comment> comments;
		UserComment userComment;
		User user;

		commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
		userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);

		try {
			comments = commentDAO.findByProperty(Constants.ATTR_POST_ID, postId);

			if (Helpers.isEmpty(comments)) {
				return null;
			}
			for (Comment comment : comments) {
				userComment = new UserComment();
				user = userDAO.findById(comment.getUserId());
				userComment.setComment(comment);
				userComment.setUser(user);
				userComments.add(userComment);
			}
			return userComments;
		} catch (SQLException e) {
			throw e;
		}
	}
}
