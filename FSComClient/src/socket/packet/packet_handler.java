package socket.packet;

import java.net.Socket;

import socket.packet.handlers.*;

import misc.Log;

public class packet_handler 
{
	final static int MAX_OPCODE = 0xFF;
	Integer opcode_id;
	Object packet;
	Socket mysock;
	
	public packet_handler(Object stream,Socket sock)
	{
		mysock = sock;
		try
		{
			String tmp = (stream.toString());
			opcode_id = Integer.decode(tmp.substring(0,4));
			
			int lng = tmp.length();
			packet = tmp.substring(4,lng);
			ActionOnPacket();
		}
		catch(Exception e)
		{
			Log.outError("Packet Handler: server send invalid packet !");
			e.printStackTrace();
		}
		
	}
	
	public void ShowPacket()
	{
		Log.outString(opcode_id + " / " + packet);
	}
	
	public void ActionOnPacket()
	{
		if(this.opcode_id > MAX_OPCODE)
		{
			Log.outError("Unrecognized opcode received from server");
			return;
		}
		else
		{
			Log.outString("Packet received from server (opcode :" + this.opcode_id + ")");
			abstract_handler pcktrecv = null;
			switch(opcode_id)
			{
				case 0x01:
					pong_handler.PongReceived();
					break;
				case 0x02:
					pcktrecv = new srvpong_handler();
					((send_handler) pcktrecv).Send();
					break;
				case 0x07:
					// server kill client socket
					break;
				case 0x08:
					// todo send correct status
					if((new srvconnect_handler(packet.toString())).HasValidData())
					{
						pcktrecv = new statussender_handler(1);
						((send_handler) pcktrecv).Send();
					}
					else
					{
						// TODO: event fenêtre connexion failed
					}
					break;
				case 0x0A:
					// final response
					break;
				case 0x00:
				case 0x03:
				case 0x04:
				case 0x05:
				case 0x06:
				case 0x09:
					pcktrecv = new serverside_handler(this.opcode_id);
					break;
				default:
					pcktrecv = new null_handler(this.opcode_id);
					break;
			}
			if(pcktrecv != null)
				pcktrecv.Destroy();
		}
	}

	public void Destroy() {}
}
