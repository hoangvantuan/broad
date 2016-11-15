package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainning.broad.bean.Comment;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.DAOHelpers;

public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO {

	public CommentDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_COMMENT;
		this.con = con;
		this.id = Constants.ATTR_COMMENT_ID;

	}

	@Override
	public int save(int userId, int postId, String content) throws SQLException {

		PreparedStatement statement = null;
		String query = "INSERT INTO " + tableName + "(" + Constants.ATTR_POST_ID + "," + Constants.ATTR_USER_ID + ","
				+ Constants.ATTR_CONNTENT + ")" + " VALUES(?,?,?)";
		statement = con.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, postId);
		statement.setInt(2, userId);
		statement.setString(3, content);
		statement.executeUpdate();
		ResultSet result = statement.getGeneratedKeys();
		return DAOHelpers.getGenerateKey(result);
	}
}
