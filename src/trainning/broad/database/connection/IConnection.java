package trainning.broad.database.connection;

import java.sql.Connection;

public interface IConnection {

	public Connection openConnection();

	public void closeConnection(Connection con);

}