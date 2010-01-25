package database;

public class DatabaseFunctions {

	
	public static Integer getAccountUIDByName(String name)
	{
		return DatabaseTransactions.IntegerQuery("account", "uid", "name = '" + name + "'");
	}
}
