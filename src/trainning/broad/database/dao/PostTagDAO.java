package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.PostTag;

public interface PostTagDAO extends GenericDAO<PostTag> {

	public void save(int postId, int tagId) throws SQLException;

}

