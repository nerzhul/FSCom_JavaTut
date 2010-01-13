package database;

import java.io.IOException;
import java.sql.*;

import misc.Log;
import misc.error_codes;

public class DatabaseTransactions {
	
	
	final static String URL = "jdbc:mysql://localhost/realmd?user=nerzhul&password=root";
	final static String username = "nerzhul";
	final static String passwd = "root";
	
	static Connection connect;
	public DatabaseTransactions() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		InitConnection();
	}

	void InitConnection() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(URL);
		}
		catch(SQLException e)
		{
			Log.outError("Database connect error !");
			e.getStackTrace();
			System.exit(-1);
		}
	}
	
}
