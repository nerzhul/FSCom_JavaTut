package thread;

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
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void initDB() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		DB = new DatabaseTransactions();
	}
	
	public void closeDB()
	{
		// TODO: clore la DB
	}
}
