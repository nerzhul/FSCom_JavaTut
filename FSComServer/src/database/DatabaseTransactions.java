package database;

import java.io.IOException;
import java.sql.*;

import misc.Log;
import static misc.error_codes.*;

public class DatabaseTransactions {
	
	final static String URL = "jdbc:mysql://localhost/realmd";
	final static String username = "nerzhul";
	final static String passwd = "root";
	
	static Connection connect;
	public DatabaseTransactions() throws SQLException, IOException
	{
		InitConnection();
	}

	void InitConnection() throws SQLException, IOException
	{
		try
		{
			connect = DriverManager.getConnection(URL, username, passwd);
		}
		catch(SQLException e)
		{
			Log.outError("Database connect error !");
			System.exit(DATABASE_ERROR.getValue());
		}
	}
	
}
