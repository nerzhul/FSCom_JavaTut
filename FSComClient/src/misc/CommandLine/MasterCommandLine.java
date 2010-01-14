package misc.CommandLine;

import java.io.IOException;

import misc.Log;
import misc.CommandLine.Commands.*;

import socket.sender;


public class MasterCommandLine{

	private static String command[];
	public MasterCommandLine()
	{
		command = new String[2];
		command[0] = new String();
		command[1] = new String();
	}
	
	public static void DoCommand(String cmd) throws IOException
	{
		if(cmd == null || cmd == "")
		{
			Log.outError("No command specified");
			return;
		}
		
		String tmpcmd[] = cmd.trim().split(" ");
		command[0] = tmpcmd[0];
		for(int i=1;i<tmpcmd.length;i++)
		{
			command[1] = tmpcmd[i];
		}
		
		if(command[0].equals("testpacket"))
		{
			cmd_testpacket pck = new cmd_testpacket(command[1]);
		}
		else if(command[0].equals("ping"))
		{
			cmd_ping pck = new cmd_ping();
		}
	}
}
