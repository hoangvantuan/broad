package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.Comment;

public interface CommentDAO extends GenericDAO<Comment> {

	public int getNumCommentOfPost(int postId) throws SQLException;
}
