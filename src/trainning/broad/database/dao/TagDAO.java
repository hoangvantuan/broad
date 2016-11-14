package trainning.broad.database.dao;

import java.sql.SQLException;

import trainning.broad.bean.Tag;

public interface TagDAO extends GenericDAO<Tag> {

	public int save(String tagName) throws SQLException;
}

