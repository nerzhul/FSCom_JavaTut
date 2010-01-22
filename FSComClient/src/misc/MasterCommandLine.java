package misc;

import misc.CommandLine.Commands.*;

public class MasterCommandLine{

	private static String command[];
	public MasterCommandLine()
	{
		command = new String[2];
		command[0] = new String();
		command[1] = new String();
	}
	
	public static void DoCommand(String cmd)
	{
		if(cmd == null || cmd == "")
		{
			Log.outError("No command specified");
			return;
		}
		
		String tmpcmd[] = cmd.trim().split(" ");
		command[1] = "";
		command[0] = tmpcmd[0];
		for(int i=1;i<tmpcmd.length;i++)
		{
			command[1] += tmpcmd[i];
			if(i < tmpcmd.length - 1)
				command[1] += " ";
		}
		cmd_abstract pck = null;
		if(command[0].equals("testpacket"))
		{
			pck = new cmd_testpacket(command[1]);
		}
		else if(command[0].equals("ping"))
		{
			pck = new cmd_ping();
		}
		else if(command[0].equals("connect"))
		{
			pck = new cmd_connect(command[1]);
		}
		
		if(pck != null && pck.HasValidData())
		{
			pck.Send();
		}
	}

	public void Destroy() {}
}
