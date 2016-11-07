package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import trainning.broad.bean.Comment;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.helpers.Constants;

public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO {

	public CommentDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_COMMENT;
		this.con = con;
		this.id = Constants.ATTR_COMMENT_ID;

	}

	@Override
	public int getNumCommentOfPost(int postId) throws SQLException {

		String query = "SELECT COUNT(*) as num FROM " + tableName + " WHERE " + Constants.ATTR_POST_ID + " = ?";
		statement = con.prepareStatement(query);
		statement.setInt(1, postId);
		result = statement.executeQuery();
		if (result.next())
			return result.getInt("num");
		else
			return 0;
	}
}
