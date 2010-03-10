package database;

import java.sql.*;
import java.util.Vector;

import misc.Config;
import misc.Log;

/*
 * This class contains database library with mysql
 */
public class DatabaseTransactions {
	
	// defines we need for connection
	final static String URL = "jdbc:mysql://"+Config.getDbAddress()+"/"+Config.getDbName();
	final static String username = Config.getDbUser();
	final static String passwd = Config.getDbPwd();
	
	static Connection connect;
	public DatabaseTransactions() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		InitConnection();
	}

	private static void InitConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try
		{
			// Connect to database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Log.outString("Connect to database : " + Config.getDbName() + " on "
					+ Config.getDbUser() + ":" + Config.getDbPwd() + "@ "+ Config.getDbAddress()); 
			connect = DriverManager.getConnection(URL,username,passwd);
		}
		catch(SQLException e)
		{
			// connection failed. We cannot continue. Stop server.
			Log.outError("Database connect error !");
			System.exit(-1);
		}
	}
	
	public static void CloseConnection()
	{
		// Trying to close connection with database.
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
		/*
		 * Basic function to make a request to database 
		 * and return the resultset
		 */
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
		/*
		 * this function only execute a query on database
		 */
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
	
	public static void ExecuteUQuery(String tb, String chp, String val, String cond)
	{
		/*
		 * This permit to do fast update into database
		 */
		String query = "UPDATE `" + tb + "` SET `" + chp + "` = '" + val + "'";
		if(!cond.equals(""))
			query += " WHERE " + cond;
		ExecuteQuery(query);
	}
	
	public static Integer IntegerQuery(String table,String col, String cond)
	{
		/* 
		 * Function to get one integer from database
		 */
		Integer res = 0;
		if(cond != null && !cond.equals(""))
			cond = " WHERE " + cond;
		else 
			cond = "";
		ResultSet qr = DatabaseQuery("SELECT `" + col + "` FROM " + table + cond);
		
		if(qr != null)
		{
			try 
			{
				if(qr.next())
					res = (Integer) qr.getObject(col);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static String StringQuery(String table,String col, String cond)
	{
		/*
		 * function to get one string from database
		 */
		String res = new String();
		if(cond != null && !cond.equals(""))
			cond = " WHERE " + cond;
		else 
			cond = "";
		ResultSet qr = DatabaseQuery("SELECT `" + col + "` FROM " + table + cond);
		
		if(qr != null)
		{
			try 
			{
				if(qr.next())
					res = qr.getObject(col).toString();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static Vector<Object> getObjectList(String table, String col, String cond)
	{
		/*
		 * function to get a list of objects from database
		 */
		Vector<Object> objs = new Vector<Object>();
		if(cond != null && !cond.equals(""))
			cond = " WHERE " + cond;
		else 
			cond = "";
		ResultSet qr = DatabaseQuery("SELECT `" + col + "` FROM " + table + cond);
		
		if(qr != null)
		{
			try 
			{
				while(qr.next())
					objs.addElement(qr.getObject(col));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return objs;
	}
	
	public static Vector<Integer> getIntegerList(String table, String col, String cond)
	{
		/*
		 * we want to get list of integer from database
		 */
		Vector<Integer> ints = new Vector<Integer>();
		Vector<Object> tmpvect = getObjectList(table,col,cond);
		for(Object o : tmpvect)
			ints.add(Integer.decode(o.toString()));
		
		return ints;
	}
	public static boolean DataExist(String table, String col, String cond)
	{
		/*
		 * we want to verify if a data exist
		 */
		boolean exist = false;
		if(cond != null && !cond.equals(""))
			cond = " WHERE " + cond;
		else 
			cond = "";
		
		ResultSet qr = DatabaseQuery("SELECT `" + col + "` FROM " + table + cond);
		
		if(qr != null)
		{
			try 
			{
				// if we have a result set the data exist
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
