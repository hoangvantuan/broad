package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trainning.broad.bean.Post;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.DAOHelpers;

public class PostDAOImpl extends GenericDAOImpl<Post> implements PostDAO {

	public PostDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_POST;
		this.con = con;
		this.id = Constants.ATTR_POST_ID;
	}

	@Override
	public int save(String postName, String content, int userId) throws SQLException {

		PreparedStatement statement = null;
		ResultSet resultKey;
		String query = "INSERT INTO " + tableName + "(" + Constants.POST_NAME + "," + Constants.ATTR_CONNTENT + ","
				+ Constants.ATTR_USER_ID + ")" + " VALUES(?,?,?)";

		statement = con.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
		statement.setString(1, postName);
		statement.setString(2, content);
		statement.setInt(3, userId);
		statement.executeUpdate();
		resultKey = statement.getGeneratedKeys();

		return DAOHelpers.getGenerateKey(resultKey);

	}
}
