package misc;

/*
 * depreciated class to set System.exit codes
 */
public enum error_codes {

	DATABASE_ERROR(-1);
	
	int value;
	error_codes(int val)
	{
		this.value = val;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
