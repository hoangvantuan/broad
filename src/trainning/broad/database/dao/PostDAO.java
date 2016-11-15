package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

import trainning.broad.bean.Post;

public interface PostDAO extends GenericDAO<Post> {

	public int save(String postName, String content, int userId) throws SQLException;

	public void update(int postId, String postName, String content) throws SQLException;

	public List<Post> search(String keyWord) throws SQLException;

}
