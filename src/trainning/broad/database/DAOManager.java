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

	public void open() {

		try {
			if (this.con == null || this.con.isClosed()) {

				this.con = myConnection.openConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {

		try {
			if (this.con != null && !this.con.isClosed()) {

				this.con.close();;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public GenericDAO getDAO(String table) {

		try {
			if (this.con == null || this.con.isClosed()) {
				this.open();
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
