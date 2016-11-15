package trainning.broad.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.Post;
import trainning.broad.bean.PostTag;
import trainning.broad.bean.PostUserTag;
import trainning.broad.bean.Tag;
import trainning.broad.bean.User;
import trainning.broad.bean.UserPostComment;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.database.dao.TagDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;

public class SearchBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private TagDAO tagDAO;
	private PostTagDAO postTagDAO;
	private CommentDAO commentDAO;

	public SearchBusiness() throws ClassNotFoundException {

		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	public List<PostUserTag> searchPost(String keyWord) throws SQLException {

		List<Post> posts;
		List<Tag> tags;
		User user;
		List<PostTag> postTags;
		Post post;
		List<PostUserTag> postUserTags = new ArrayList<PostUserTag>();
		PostUserTag postUserTag;
		keyWord = keyWord.trim().toLowerCase();

		try {
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);

			posts = postDAO.search(keyWord);
			tags = tagDAO.search(keyWord);

			for (Tag tag : tags) {
				postTags = postTagDAO.findByProperty(Constants.ATTR_TAG_ID, tag.getTagId());

				for (PostTag postTag : postTags) {
					post = postDAO.findById(postTag.getPostId());

					if (!Helpers.isDuplicatePostId(posts, post))
						posts.add(post);
				}
			}

			for (Post temp : posts) {

				temp.setContent(Helpers.cutString(temp.getContent()));
				postUserTag = new PostUserTag();
				user = userDAO.findById(temp.getUserId());
				postTags = postTagDAO.findByProperty(Constants.ATTR_POST_ID, temp.getPostId());
				tags = new ArrayList<Tag>();

				for (PostTag postTag : postTags) {
					tags.add(tagDAO.findById(postTag.getTagId()));
				}

				postUserTag.setUser(user);
				postUserTag.setPost(temp);
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

	public List<PostUserTag> searchByTag(int tagId) throws SQLException {

		List<PostTag> postTags;
		PostUserTag postUserTag;
		List<Tag> tags;
		List<PostUserTag> postUserTags = new ArrayList<PostUserTag>();

		try {
			tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
			postTags = postTagDAO.findByProperty(Constants.ATTR_TAG_ID, tagId);

			for (PostTag postTag : postTags) {
				postUserTag = new PostUserTag();
				Post post = postDAO.findById(postTag.getPostId());
				User user = userDAO.findById(post.getUserId());
				tags = new ArrayList<Tag>();
				List<PostTag> tempPostTags = postTagDAO.findByProperty(Constants.ATTR_POST_ID, post.getPostId());

				for (PostTag tempPostTag : tempPostTags) {
					tags.add(tagDAO.findById(tempPostTag.getTagId()));
				}

				postUserTag.setUser(user);
				postUserTag.setPost(post);
				postUserTag.setTags(tags);
				postUserTags.add(postUserTag);
			}
			return postUserTags;

		} catch (SQLException e) {
			throw e;
		}

	}

	public List<UserPostComment> searchUser(String keyWord) throws SQLException {

		List<User> users;
		List<UserPostComment> userPostComments = new ArrayList<UserPostComment>();
		UserPostComment userPostComment;
		keyWord = keyWord.trim().toLowerCase();

		try {
			userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
			postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
			commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);

			users = userDAO.search(keyWord);

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

}
