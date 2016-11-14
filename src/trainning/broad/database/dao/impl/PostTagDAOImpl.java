package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import trainning.broad.bean.PostTag;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.helpers.Constants;

public class PostTagDAOImpl extends GenericDAOImpl<PostTag> implements PostTagDAO {

	public PostTagDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_POSTTAG;
		this.con = con;
		this.id = Constants.ATTR_POST_TAG_ID;
	}

	@Override
	public void save(int postId, int tagId) throws SQLException {

		PreparedStatement statement;
		String query = "INSERT INTO " + tableName + "(" + Constants.ATTR_POST_ID + "," + Constants.ATTR_TAG_ID + ")"
				+ " VALUES(?,?)";

		statement = con.prepareStatement(query);
		statement.setInt(1, postId);
		statement.setInt(2, tagId);
		statement.executeUpdate();
	}
}

