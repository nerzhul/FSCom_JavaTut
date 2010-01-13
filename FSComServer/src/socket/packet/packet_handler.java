package socket.packet;

import java.io.IOException;
import java.net.Socket;

import misc.Log;

public class packet_handler 
{
	final static int MAX_OPCODE = 250;
	Integer opcode_id;
	Object packet;
	Socket mysock;
	
	public packet_handler(Object stream,Socket sock) throws IOException
	{
		mysock = sock;
		try
		{
			opcode_id = Integer.decode((stream.toString()).substring(0,4));
			String tmp = (stream.toString());
			int lng = tmp.length();
			packet = (stream.toString()).substring(4,lng);
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
		else
		{
			Log.outString("Packet received from " + mysock.getInetAddress());
			ShowPacket();
		}
	}
}
