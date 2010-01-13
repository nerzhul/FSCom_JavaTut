package thread;

import java.io.IOException;
import java.sql.SQLException;

import misc.Log;

import database.DatabaseTransactions;

public class thr_database extends Thread{

	DatabaseTransactions DB;
	public void run()
	{
		try 
		{
			Log.outString("Starting Database Thread...");
			initDB();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initDB() throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		DB = new DatabaseTransactions();
	}
	
	public Object DBQuery(String query) throws IOException
	{
		return DB.DatabaseQuery(query);
	}
	
	public void ExecQuery(String query) throws IOException
	{
		DB.ExecuteQuery(query);
	}
}
