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

		Post post;
		User user;
		List<PostTag> postTags;
		List<Tag> tags = new ArrayList<Tag>();
		PostUserTag postUserTagComment = new PostUserTag();

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);

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

	public List<UserComment> getPostComment(int postId) throws SQLException {

		User user;
		UserComment userComment;
		List<Comment> comments;
		List<UserComment> userComments = new ArrayList<UserComment>();

		try {
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
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

	public void addPost(String postName, String content, String[] tags, String email) throws SQLException {

		int postId;
		int tagId;
		User user;
		List<Integer> tagsId = new ArrayList<Integer>();

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);

			user = userDAO.findByEmail(email);
			daoManager.setAutoCommit(false);
			postId = postDAO.save(postName, content, user.getUserId());

			for (int i = 0; i < tags.length; i++) {
				List<Tag> temps = tagDAO.findByProperty(Constants.ATTR_TAG_NAME, tags[i]);
				if (Helpers.isEmpty(temps)) {
					tagId = tagDAO.save(tags[i]);
				} else {
					tagId = temps.get(0).getTagId();
				}
				tagsId.add(tagId);
			}

			for (Integer id : tagsId) {
				postTagDAO.save(postId, id);
			}

			daoManager.commit();

		} catch (SQLException e) {
			daoManager.rollBack();
			throw e;
		} finally {
			daoManager.setAutoCommit(true);
			daoManager.close();
		}
	}

	public void editPost(int postId, String postName, String content) throws SQLException {

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			postDAO.update(postId, postName, content);
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}

	}
}
