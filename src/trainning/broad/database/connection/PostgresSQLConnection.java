package trainning.broad.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSQLConnection implements IConnection {

	private final String URL = "jdbc:postgresql://localhost:5432/broad";
	private final String USERNAME = "postgres";
	private final String PASSWORD = "zxcvbn";

	public PostgresSQLConnection() throws ClassNotFoundException{
			Class.forName("org.postgresql.Driver");
	}

	@Override
	public Connection openConnection() throws SQLException {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	@Override
	public void closeConnection(Connection con) throws SQLException {
			con.close();
	}
}

