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

	public PostUserTag getPost(int postId) throws SQLException {

		Post post;
		User user;
		List<PostTag> postTags;
		List<Tag> tags = new ArrayList<Tag>();
		PostUserTag postUserTag = new PostUserTag();

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);

			post = postDAO.getById(postId);

			if (Helpers.isEmpty(post)) {
				return null;
			}

			user = userDAO.getById(post.getUserId());
			postTags = postTagDAO.getByProperty(Constants.ATTR_POST_ID, post.getPostId());

			for (PostTag postTag : postTags) {
				tags.add(tagDAO.getById(postTag.getTagId()));
			}

			postUserTag.setPost(post);
			postUserTag.setTags(tags);
			postUserTag.setUser(user);

			return postUserTag;

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
			comments = commentDAO.getByProperty(Constants.ATTR_POST_ID, postId);

			for (Comment comment : comments) {
				userComment = new UserComment();
				user = userDAO.getById(comment.getUserId());
				userComment.setComment(comment);
				userComment.setUser(user);
				userComments.add(userComment);
			}

			return userComments;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public void addPost(String postName, String content, String[] tags, int userId) throws SQLException {

		int postId;
		int tagId;

		List<Integer> tagsId = new ArrayList<Integer>();

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);

			daoManager.noAutoCommit();
			postId = postDAO.save(postName, content, userId);

			if (!Helpers.isEmpty(tags)) {
				for (int i = 0; i < tags.length; i++) {
					List<Tag> temps = tagDAO.getByproperty(Constants.ATTR_TAG_NAME, tags[i].toLowerCase());
					if (Helpers.isEmpty(temps)) {
						tagId = tagDAO.save(tags[i].toLowerCase());
					} else {
						tagId = temps.get(0).getTagId();
					}
					tagsId.add(tagId);
				}
				tagsId = Helpers.removeDuplicateValue(tagsId);
				for (Integer id : tagsId) {
					postTagDAO.save(postId, id);
				}
			}

			daoManager.commit();

		} catch (SQLException e) {
			daoManager.rollBack();
			throw e;
		} finally {
			daoManager.autoCommit();
			daoManager.close();
		}
	}

	public void editPost(int postId, String postName, String content, String[] tags) throws SQLException {

		List<Tag> temps;
		List<PostTag> postTags;
		int tagId;
		List<Integer> tagsId = new ArrayList<Integer>();

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			daoManager.noAutoCommit();
			postDAO.update(postId, postName, content);
			if (!Helpers.isEmpty(tags)) {
				tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
				postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);

				postTags = postTagDAO.getByProperty(Constants.ATTR_POST_ID, postId);

				for (PostTag postTag : postTags) {
					postTagDAO.delete(postTag.getPostTagId());
				}

				for (int i = 0; i < tags.length; i++) {
					temps = tagDAO.getByproperty(Constants.ATTR_TAG_NAME, tags[i]);
					if (Helpers.isEmpty(temps)) {
						tagId = tagDAO.save(tags[i].toLowerCase());
					} else {
						tagId = temps.get(0).getTagId();
					}
					tagsId.add(tagId);
				}
				tagsId = Helpers.removeDuplicateValue(tagsId);
				for (Integer id : tagsId) {
					postTagDAO.save(postId, id);
				}

				daoManager.commit();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.autoCommit();
			daoManager.close();
		}

	}

	public void deletePostAndAllReferences(int postId) throws SQLException {

		List<PostTag> postTags;
		List<Comment> comments;

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);

			daoManager.noAutoCommit();
			postDAO.delete(postId);
			postTags = postTagDAO.getByProperty(Constants.ATTR_POST_ID, postId);
			comments = commentDAO.getByProperty(Constants.ATTR_POST_ID, postId);
			for (PostTag postTag : postTags) {
				postTagDAO.delete(postTag.getPostTagId());
			}

			for (Comment comment : comments) {
				commentDAO.delete(comment.getCommentId());
			}

			daoManager.commit();

		} catch (SQLException e) {
			daoManager.rollBack();
			throw e;
		} finally {
			daoManager.autoCommit();
			daoManager.close();
		}
	}

	public boolean isMyPost(int userId, int postId) throws SQLException {

		Post post;

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			post = postDAO.getById(postId);
			if (Helpers.isEmpty(post))
				return false;
			else
				return userId == post.getUserId() ? true : false;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}

	public List<PostUserTag> getPosts(int userId) throws SQLException {

		List<PostUserTag> postUserTags = new ArrayList<PostUserTag>();
		List<Tag> tags;
		List<Post> posts;
		List<PostTag> postTags;
		postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
		userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
		postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
		tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);

		try {
			posts = postDAO.getByProperty(Constants.ATTR_USER_ID, userId);

			for (Post post : posts) {
				tags = new ArrayList<Tag>();
				PostUserTag postUserTag = new PostUserTag();
				post.setContent(Helpers.cutString(post.getContent()));
				postUserTag.setPost(post);
				postUserTag.setUser(userDAO.getById(post.getUserId()));
				postTags = postTagDAO.getByProperty(Constants.ATTR_POST_ID, post.getPostId());

				for (PostTag postTag : postTags) {
					tags.add(tagDAO.getById(postTag.getTagId()));
				}

				postUserTag.setTags(tags);
				postUserTags.add(postUserTag);
			}

			return postUserTags;

		} catch (SQLException e) {
			throw e;
		} finally {
			daoManager.close();
		}
	}
}
