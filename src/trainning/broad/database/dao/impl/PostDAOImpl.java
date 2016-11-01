package trainning.broad.database.dao.impl;

import java.sql.Connection;

import trainning.broad.bean.Post;
import trainning.broad.database.dao.PostDAO;
import trainning.broad.helpers.Constants;

public class PostDAOImpl extends GenericDAOImpl<Post> implements PostDAO {

	public PostDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_POST;
		this.con = con;
		this.id = Constants.ATTR_POST_ID;
	}
}
