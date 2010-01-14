package misc.CommandLine;

import java.io.IOException;

import misc.Log;

import socket.sender;


public class MasterCommandLine{

	private static String command[];
	public MasterCommandLine()
	{
		command[0] = "";
		command[1] = "";
	}
	
	public static void DoCommand(String cmd) throws IOException
	{
		if(cmd == "")
		{
			Log.outError("No command specified");
			return;
		}
		
		String tmpcmd[] = cmd.trim().split(" ");
		command[0] = tmpcmd[0];
		for(int i=1;i<tmpcmd.length;i++)
		{
			command[1] += tmpcmd[i];
		}
		
		if(command[0] == "testpacket")
		{
			
		}
	}
}
