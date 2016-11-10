package trainning.broad.database;

import java.sql.Connection;
import java.sql.SQLException;

import trainning.broad.database.connection.IConnection;
import trainning.broad.database.dao.GenericDAO;
import trainning.broad.database.dao.impl.CommentDAOImpl;
import trainning.broad.database.dao.impl.PostDAOImpl;
import trainning.broad.database.dao.impl.PostTagDAOImpl;
import trainning.broad.database.dao.impl.TagDAOImpl;
import trainning.broad.database.dao.impl.UserDAOImpl;
import trainning.broad.helpers.Constants;

public class DAOManager {

	private Connection con;
	private IConnection myConnection;

	public DAOManager(IConnection myConnection) {

		this.myConnection = myConnection;
	}

	public void open() throws SQLException {

		if (this.con == null || this.con.isClosed()) {

			this.con = myConnection.openConnection();
		}
	}

	public void close() throws SQLException {

		if (this.con != null && !this.con.isClosed()) {

			this.con.close();
		}
	}

	public void setAutoCommit(boolean b) throws SQLException {

		if (this.con != null && !this.con.isClosed()) {

			this.con.setAutoCommit(b);
		}

	}

	public void commit() throws SQLException {

		if (this.con != null && !this.con.isClosed()) {

			this.con.commit();
		}

	}

	public void rollBack() throws SQLException {

		if (this.con != null && !this.con.isClosed()) {

			this.con.rollback();
		}
	}

	@SuppressWarnings("rawtypes")
	public GenericDAO getDAO(String table) throws SQLException {

		if (this.con == null || this.con.isClosed()) {
			this.open();
		}
		switch (table) {
		case Constants.TABLE_USER:
			return new UserDAOImpl(this.con);
		case Constants.TABLE_POST:
			return new PostDAOImpl(this.con);
		case Constants.TABLE_COMMENT:
			return new CommentDAOImpl(this.con);
		case Constants.TABLE_POSTTAG:
			return new PostTagDAOImpl(this.con);
		case Constants.TABLE_TAG:
			return new TagDAOImpl(this.con);
		default:
			return null;
		}
	}
}
