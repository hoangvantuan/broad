package trainning.broad.database.dao.impl;

import java.sql.Connection;

import trainning.broad.bean.Comment;
import trainning.broad.database.dao.CommentDAO;
import trainning.broad.helpers.Constants;

public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO {

	public CommentDAOImpl(Connection con) {

		this.tableName = Constants.TABLE_COMMENT;
		this.con = con;
		this.id = Constants.ATTR_COMMENT_ID;

	}
}
