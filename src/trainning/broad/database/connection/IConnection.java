package trainning.broad.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnection {

	public Connection openConnection() throws SQLException;

	public void closeConnection(Connection con) throws SQLException;

}
