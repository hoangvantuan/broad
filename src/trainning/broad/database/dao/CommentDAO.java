package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.Comment;

public interface CommentDAO extends GenericDAO<Comment> {

	public int save(int userId, int postId, String content) throws SQLException;
}
