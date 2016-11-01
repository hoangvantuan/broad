package trainning.broad.database.dao.impl;

import java.sql.Connection;

import trainning.broad.bean.PostTag;
import trainning.broad.database.dao.PostTagDAO;
import trainning.broad.helpers.Constants;

public class PostTagDAOImpl extends GenericDAOImpl<PostTag> implements PostTagDAO {

	public PostTagDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_POSTTAG;
		this.con = con;
		this.id = Constants.ATTR_POST_TAG_ID;
	}
}
