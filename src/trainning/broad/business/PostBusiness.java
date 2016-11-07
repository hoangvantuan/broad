package trainning.broad.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trainning.broad.bean.InfoPostHomepage;
import trainning.broad.bean.Post;
import trainning.broad.bean.PostTag;
import trainning.broad.bean.Tag;
import trainning.broad.database.DAOManager;
import trainning.broad.database.connection.PostgresSQLConnection;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.database.dao.TagDAO;
import trainning.broad.database.dao.UserDAO;
import trainning.broad.helpers.Constants;

public class PostBusiness {

	private DAOManager daoManager;
	private UserDAO userDAO;
	private PostDAO postDAO;
	private TagDAO tagDAO;
	private PostTagDAO postTagDAO;
	private CommentDAO commentDAO;

	public PostBusiness() {

		try {
			this.daoManager = new DAOManager(new PostgresSQLConnection());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public InfoPostHomepage getPostDetails(int postId) {

		InfoPostHomepage infoPostHomepage = new InfoPostHomepage();
		List<Tag> tags = new ArrayList<Tag>();
		List<PostTag> postTags;
		int commentOnPost = 0;
		Post post;

		postDAO = (PostDAO) daoManager.getDAO(Constants.TABLE_POST);
		postTagDAO = (PostTagDAO) daoManager.getDAO(Constants.TABLE_POSTTAG);
		tagDAO = (TagDAO) daoManager.getDAO(Constants.TABLE_TAG);
		commentDAO = (CommentDAO) daoManager.getDAO(Constants.TABLE_COMMENT);
		userDAO = (UserDAO) daoManager.getDAO(Constants.TABLE_USER);
		try {
			post = postDAO.findById(postId);
			postTags = postTagDAO.findByProperty(Constants.ATTR_POST_ID, post.getPostId());
			for (PostTag postTag : postTags) {
				tags.add(tagDAO.findById(postTag.getTagId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
