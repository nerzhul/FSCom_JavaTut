package database;

import java.io.IOException;
import java.sql.*;

import misc.Log;
import misc.error_codes;

public class DatabaseTransactions {
	
	
	final static String URL = "jdbc:mysql://localhost/realmd";
	final static String username = "nerzhul";
	final static String passwd = "root";
	
	static Connection connect;
	public DatabaseTransactions() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		InitConnection();
	}

	private void InitConnection() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(URL,username,passwd);
		}
		catch(SQLException e)
		{
			Log.outError("Database connect error !");
			e.getStackTrace();
			System.exit(-1);
		}
	}
	
	public void CloseConnection() throws IOException
	{
		try
		{
			connect.close();
		}
		catch (SQLException e)
		{
			Log.outError("Cannot close Database connection.");
		}
	}
	
	public ResultSet DatabaseQuery(String query) throws IOException
	{
		ResultSet results = null;
		try 
		{
			Statement stmt = connect.createStatement();
			results = stmt.executeQuery(query); 
		} 
		catch (SQLException e) 
		{
		      Log.outError("Query : " + query + " failed. Maybe one resource doesn't exist");
		}

		return results;
	}
	
	public void ExecuteQuery(String query) throws IOException
	{
		try 
		{
			Statement stmt = connect.createStatement();
		} 
		catch (SQLException e) 
		{
		      Log.outError("Query : " + query + " failed. Maybe one resource doesn't exist");
		}
	}
}
