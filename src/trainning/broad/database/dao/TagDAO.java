package trainning.broad.database.dao;

import java.sql.SQLException;
import java.util.List;

import trainning.broad.bean.Tag;

public interface TagDAO extends GenericDAO<Tag> {

	public int save(String tagName) throws SQLException;

	public List<Tag> search(String keyWord) throws SQLException;
}
