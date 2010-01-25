package session;

import java.util.Vector;

public class Session extends Thread{

	private static Integer status;
	private static Vector<Object> contacts; // TODO: typer
	private static Vector<Object> groups; 	// TODO: typer
	private static String pseudo;
	private static String perso_msg;
	
	public Session()
	{
		status = 0;
		pseudo = "";
		contacts = new Vector<Object>();
		groups = new Vector<Object>();
		perso_msg = "";
	}
	
	public void run()
	{
		Update();
	}
	
	public static void Update()
	{
		while(true)
		{
			
		}
	}
}
