package trainning.broad.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import trainning.broad.database.dao.GenericDAO;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.DAOHelpers;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	protected String tableName;
	protected Connection con;
	protected String id;
	protected PreparedStatement statement;
	protected ResultSet result;

	public GenericDAOImpl() {
	}

	@Override
	public void delete(int id) throws SQLException {

		String query = "DELETE FROM " + tableName + " WHERE " + this.id + " = ?";
		statement = con.prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();
	}

	@Override
	public List<T> findAll() throws SQLException {

		String query = "SELECT * FROM " + tableName;
		statement = con.prepareStatement(query);
		result = statement.executeQuery();

		switch (tableName) {

		case Constants.TABLE_USER:
			return (List<T>) DAOHelpers.convertResultToUsers(result);
		case Constants.TABLE_POST:
			return (List<T>) DAOHelpers.convertResultToPosts(result);
		default:
			return null;
		}
	}

	@Override
	public T findById(int id) throws SQLException {

		String query = "SELECT * FROM " + tableName + " WHERE " + this.id + " = ?";
		statement = con.prepareStatement(query);
		statement.setInt(1, id);
		result = statement.executeQuery();

		switch (tableName) {

		case Constants.TABLE_USER:
			return (T) DAOHelpers.convertResultToUser(result);
		case Constants.TABLE_POST:
			return (T) DAOHelpers.convertResultToPost(result);
		case Constants.TABLE_TAG:
			return (T) DAOHelpers.convertResultToTag(result);
		case Constants.TABLE_POSTTAG:
			return (T) DAOHelpers.convertResultToPostTag(result);
		default:
			return null;
		}
	}

	@Override
	public List<T> findByProperty(String property, int value) throws SQLException {

		String query = "SELECT * FROM " + tableName + " WHERE " + property + " = ?";
		statement = con.prepareStatement(query);
		statement.setInt(1, value);
		result = statement.executeQuery();

		switch (tableName) {

		case Constants.TABLE_USER:
			return (List<T>) DAOHelpers.convertResultToUsers(result);
		case Constants.TABLE_POST:
			return (List<T>) DAOHelpers.convertResultToPosts(result);
		case Constants.TABLE_POSTTAG:
			return (List<T>) DAOHelpers.convertResultToPostTags(result);
		case Constants.TABLE_COMMENT:
			return (List<T>) DAOHelpers.convertResultToPostComments(result);
		default:
			return null;
		}
	}

	@Override
	public List<T> findByProperty(String property, String value) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
