package database;

public class DatabaseFunctions {

	
	public static Integer getAccountUIDByName(String name)
	{
		return DatabaseTransactions.IntegerQuery("account", "uid", "user = '" + name + "'");
	}
	
	public static String getAccountNameByUID(Integer uid)
	{
		return DatabaseTransactions.StringQuery("account", "user", "uid = '" + uid + "'");
	}
	
	public static String getPseudoByUID(Integer uid)
	{
		return DatabaseTransactions.StringQuery("pseudo", "user", "uid = '" + uid + "'");
	}
}
