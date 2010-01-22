package database;

import java.sql.*;

import misc.Log;

public class DatabaseTransactions {
	
	
	final static String URL = "jdbc:mysql://localhost/messenger";
	final static String username = "nerzhul";
	final static String passwd = "root";
	
	static Connection connect;
	public DatabaseTransactions() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		InitConnection();
	}

	private static void InitConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
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
	
	public static void CloseConnection()
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
	
	public static ResultSet DatabaseQuery(String query)
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
	
	public static void ExecuteQuery(String query)
	{
		try 
		{
			Statement stmt = connect.createStatement();
			stmt.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
		      Log.outError("Query : " + query + " failed. Maybe one resource doesn't exist");
		}
	}
	
	public static Object SingleObjectQuery(String query,String col)
	{
		Object res = null;
		ResultSet qr = DatabaseQuery(query);
		
		if(qr != null)
		{
			try 
			{
				if(qr.next())
					res = qr.getObject(col);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return res;
	}
}
