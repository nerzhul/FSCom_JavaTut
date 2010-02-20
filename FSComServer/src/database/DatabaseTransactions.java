package database;

import java.sql.*;
import java.util.Vector;

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
	
	public static void ExecuteUQuery(String tb, String chp, String val, String cond)
	{
		String query = "UPDATE `" + tb + "` SET `" + chp + "` = '" + val + "'";
		if(!cond.equals(""))
			query += " WHERE " + cond;
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
	
	public static Integer IntegerQuery(String table,String col, String cond)
	{
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
		Vector<Integer> ints = new Vector<Integer>();
		Vector<Object> tmpvect = getObjectList(table,col,cond);
		for(int i=0;i<tmpvect.size();i++)
		{
			ints.add(Integer.decode(tmpvect.get(i).toString()));
		}
		
		return ints;
	}
	public static boolean DataExist(String table, String col, String cond)
	{
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
