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
	
	public static long LongQuery(String table,String col, String cond)
	{
		long res = 0;
		if(cond != null && !cond.equals(""))
			cond = " WHERE " + cond;
		else 
			cond = "";
		ResultSet qr = DatabaseQuery("SELECT " + col + " FROM " + table + cond);
		
		if(qr != null)
		{
			try 
			{
				if(qr.next())
					res = qr.getLong(col);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static boolean DataExist(String table, String col, String cond)
	{
		boolean exist = false;
		if(cond != null && !cond.equals(""))
			cond = " WHERE " + cond;
		else 
			cond = "";
		
		ResultSet qr = DatabaseQuery("SELECT " + col + " FROM " + table + cond);
		
		if(qr != null)
		{
			try 
			{
				if(qr.next())
					exist = true;
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return exist;
	}

}
