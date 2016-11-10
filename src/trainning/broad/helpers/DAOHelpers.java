package trainning.broad.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.Comment;
import trainning.broad.bean.Post;
import trainning.broad.bean.PostTag;
import trainning.broad.bean.Tag;
import trainning.broad.bean.User;

public class DAOHelpers {

	public static List<User> convertResultToUsers(ResultSet result) throws SQLException {

		List<User> users = new ArrayList<User>();

		while (result.next()) {

			users.add(getUserFromResult(result));
		}
		return users;
	}

	public static User convertResultToUser(ResultSet result) throws SQLException {

		if (result.next()) {
			return getUserFromResult(result);
		} else
			return null;
	}

	public static List<Post> convertResultToPosts(ResultSet result) throws SQLException {
		List<Post> posts = new ArrayList<Post>();
		while (result.next()) {
			posts.add(getPostFromResult(result));
		}

		return posts;
	}

	public static Post convertResultToPost(ResultSet result) throws SQLException {

		if (result.next()) {
			return getPostFromResult(result);
		} else {
			return null;
		}
	}

	public static PostTag convertResultToPostTag(ResultSet result) throws SQLException {

		if (result.next()) {
			return getPostTagFromResult(result);
		} else
			return null;
	}

	public static List<Tag> convertResultToTags(ResultSet result) throws SQLException {

		List<Tag> tags = new ArrayList<Tag>();
		while (result.next()) {
			tags.add(getTagFromResult(result));
		}

		return tags;
	}

	public static Tag convertResultToTag(ResultSet result) throws SQLException {

		if (result.next()) {
			return getTagFromResult(result);
		} else
			return null;
	}

	public static List<Comment> convertResultToPostComments(ResultSet result) throws SQLException {

		List<Comment> comments = new ArrayList<Comment>();
		while (result.next()) {
			comments.add(getCommentFromResult(result));
		}

		return comments;
	}

	public static Comment convertResultToComment(ResultSet result) throws SQLException {

		if (result.next()) {
			return getCommentFromResult(result);
		} else
			return null;
	}

	public static List<PostTag> convertResultToPostTags(ResultSet result) throws SQLException {

		List<PostTag> postTags = new ArrayList<PostTag>();
		while (result.next()) {
			postTags.add(getPostTagFromResult(result));
		}

		return postTags;
	}

	public static User getUserFromResult(ResultSet result) throws SQLException {

		User user = new User();
		user.setUserId(result.getInt(Constants.ATTR_USER_ID));
		user.setUserName(result.getString(Constants.ATTR_USER_NAME));
		user.setEmail(result.getString(Constants.ATTR_EMAIL));
		user.setPassword(result.getString(Constants.ATTR_PASSWORD));
		user.setIsRole(result.getBoolean(Constants.ATTR_IS_ROLE));
		user.setIsActive(result.getBoolean(Constants.ATTR_IS_ACTIVE));
		user.setCreateAt(result.getTimestamp(Constants.ATTR_CREATE_AT));
		user.setUpdateAt(result.getTimestamp(Constants.ATTR_UPDATE_AT));
		return user;
	}

	public static Post getPostFromResult(ResultSet result) throws SQLException {

		Post post = new Post();
		post.setPostId(result.getInt(Constants.ATTR_POST_ID));
		post.setUserId(result.getInt(Constants.ATTR_USER_ID));
		post.setContent(result.getString(Constants.ATTR_CONNTENT));
		post.setPostName(result.getString(Constants.ATTR_POST_NAME));
		post.setCreateAt(result.getTimestamp(Constants.ATTR_CREATE_AT));
		post.setUpdateAt(result.getTimestamp(Constants.ATTR_UPDATE_AT));
		return post;

	}

	public static PostTag getPostTagFromResult(ResultSet result) throws SQLException {

		PostTag postTag = new PostTag();
		postTag.setPostTagId(result.getInt(Constants.ATTR_POST_TAG_ID));
		postTag.setPostId(result.getInt(Constants.ATTR_POST_ID));
		postTag.setTagId(result.getInt(Constants.ATTR_TAG_ID));

		return postTag;
	}

	public static Tag getTagFromResult(ResultSet result) throws SQLException {

		Tag tag = new Tag();
		tag.setTagId(result.getInt(Constants.ATTR_TAG_ID));
		tag.setTagName(result.getString(Constants.ATTR_TAG_NAME));

		return tag;
	}

	public static Comment getCommentFromResult(ResultSet result) throws SQLException {

		Comment comment = new Comment();
		comment.setCommentId(result.getInt(Constants.ATTR_COMMENT_ID));
		comment.setPostId(result.getInt(Constants.ATTR_POST_ID));
		comment.setUserId(result.getInt(Constants.ATTR_USER_ID));
		comment.setContent(result.getString(Constants.ATTR_CONNTENT));
		comment.setCreateAt(result.getTimestamp(Constants.ATTR_CREATE_AT));
		comment.setUpdateAt(result.getTimestamp(Constants.ATTR_UPDATE_AT));

		return comment;
	}

	public static int getGenerateKey(ResultSet result) throws SQLException {

		if (result.next())
			return result.getInt(1);
		else {
			return 0;
		}
	}

}
