package socket;

import java.net.*;
import java.io.*;

import misc.Log;
import misc.CommandLine.MasterCommandLine;
import socket.packet.*;

public class sender extends Thread
{
	final static int port = 5677;
	final static String IP = "172.20.9.36";

	static Socket socket;

	public sender(){}
	
	public void run()
	{
		try
		{
			// create a socket on mine IP.
		    socket = new Socket(IP,port);
		    
		    Log.outString("Initialising Master Command Line...");
		    MasterCommandLine cmdline = new MasterCommandLine();
		    Log.outString("Master Command Line Initialized");
		    
		    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    String line;
		    MasterCommandLine.DoCommand("ping");
		    while (true) 
		    {
		    	Log.outPrompt();
		    	line = reader.readLine();
		    	if(line.equals("close"))
		    		break;
		    	// todo : stuff on cmd
		    	MasterCommandLine.DoCommand(line);
		    }
		    // don't forget to close the socket or client get an internal error !
		    
		    Log.outString("Close connection with server !");
		    socket.close();

		} catch (Exception e) 
		{
	      e.printStackTrace();
	    }
	}
	
	public static void SendPacket(Integer opcode,Object packt) throws IOException
	{
		// creating buffers for packets to send
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		// send packet
    	out.println("0x" + Integer.toHexString(opcode) + packt);
	}

}
