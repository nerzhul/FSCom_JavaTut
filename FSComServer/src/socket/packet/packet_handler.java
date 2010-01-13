package socket.packet;

import java.io.IOException;
import java.net.Socket;

import misc.Log;

public class packet_handler 
{
	final static int MAX_OPCODE = 10;
	Integer opcode_id;
	Object packet;
	Socket mysock;
	
	static Object[][] opcodetable;
	
	public packet_handler(Object stream) throws IOException
	{
		try
		{
			opcode_id = Integer.decode((stream.toString()).substring(0,3));
			String tmp = (stream.toString());
			int lng = tmp.length();
			packet = (stream.toString()).substring(3,lng);
			ActionOnPacket();
		}
		catch(Exception e)
		{
			Log.outError("Packet Handler: client " + mysock.getInetAddress() + " send invalid packet !");
		}
		
	}
	
	public void ShowPacket() throws IOException
	{
		Log.outString(opcode_id + " / " + packet);
	}
	
	public void ActionOnPacket() throws IOException
	{
		if(this.opcode_id > MAX_OPCODE)
		{
			Log.outError("Unrecognized opcode received from " + mysock.getInetAddress());
			return;
		}
	}
}
