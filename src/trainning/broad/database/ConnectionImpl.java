package trainning.broad.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImpl implements IConnection {

	private String driver = "org.postgresql.Driver";
	private String host = "localhost";
	private String dbname = "broad";
	private String url = "jdbc:postgresql://" + host + "/" + dbname;
	private String username = "postgres";
	private String password = "zxcvbn";

	public ConnectionImpl() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public Connection getConnection() {

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return con;

	}
}
