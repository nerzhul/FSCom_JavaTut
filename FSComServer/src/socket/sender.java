package socket;

import java.net.*;
import java.io.*;

import misc.Log;

public class sender
{
	private Socket socket;
	private Integer opcode;
	private Object packt;
	public sender(Socket sock,Integer op,Object packet)
	{
		socket = sock;
		opcode = op;
		packt = packet;
	}
	
	public void CloseConnection()
	{
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendPacket()
	{
		try
		{
			// creating buffers for packets to send
			PrintWriter out;
			out = new PrintWriter(socket.getOutputStream(), true);
			// send packet
			String str = "0x";
			if(opcode < 16)
				str += "0";
			str += Integer.toHexString(opcode);		
			str += packt;
	    	out.println(str);
	    	Log.outTimed("Send packet : " + str + " to client : " + socket.getInetAddress());
		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
