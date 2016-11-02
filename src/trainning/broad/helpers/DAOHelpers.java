package trainning.broad.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.Post;
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
		post.setContent(result.getString(Constants.ATTR_POST_NAME));
		post.setPostName(result.getString(Constants.ATTR_POST_NAME));
		post.setCreateAt(result.getTimestamp(Constants.ATTR_CREATE_AT));
		post.setUpdateAt(result.getTimestamp(Constants.ATTR_UPDATE_AT));
		return post;

	}
}
