package trainning.broad.database.dao.impl;

import java.sql.Connection;

import trainning.broad.bean.Tag;
import trainning.broad.database.dao.TagDAO;
import trainning.broad.helpers.Constants;

public class TagDAOImpl extends GenericDAOImpl<Tag> implements TagDAO {

	public TagDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_TAG;
		this.con = con;
		this.id = Constants.ATTR_TAG_ID;
	}
}
