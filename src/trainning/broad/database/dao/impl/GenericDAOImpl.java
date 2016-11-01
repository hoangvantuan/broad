package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import trainning.broad.database.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected String tableName;
	protected Connection con;
	protected String id;

	public GenericDAOImpl() {
	}

	@Override
	public void delete(int id) throws SQLException {

		String query = "DELETE FROM " + tableName + " WHERE " + this.id + " = ?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setInt(1, 34);
		statement.executeUpdate();
	}


}
