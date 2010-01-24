package socket.packet;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import session.session;
import socket.sender;
import socket.packet.handlers.*;


import misc.Log;

public class packet_handler 
{
	final static int MAX_OPCODE = 0xFF;
	Integer opcode_id;
	Object packet;
	Socket mysock;
	sender send;
	private session m_sess;
	
	public packet_handler(Object stream,Socket sock,session sess)
	{
		mysock = sock;
		m_sess = sess;
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
			Log.outError("Packet Handler: client " + mysock.getInetAddress() + " send invalid packet !");
			e.printStackTrace();
		}
		
	}
	
	public void ShowPacket() throws IOException
	{
		Log.outString(opcode_id + " / " + packet);
	}
	
	public void ActionOnPacket() throws IOException, SQLException
	{
		if(this.opcode_id > MAX_OPCODE)
		{
			Log.outError("Unrecognized opcode received from " + mysock.getInetAddress());
			return;
		}
		else
		{
			Log.outString("Packet received from " + mysock.getInetAddress() + " (opcode :" + this.opcode_id + ")");
			abstract_handler pkthandle = null;
			switch(opcode_id)
			{
				case 0x00:
					pkthandle = new pong_handler();
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x03:
					pkthandle = new clipong_handler(mysock);
					break;
				case 0x04:
					pkthandle = new connect_handler(packet,m_sess);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x05:
					// disconnect
					break;
				case 0x06:
					// request disconnect socket
					break;
				case 0x09:
					pkthandle = new status_handler(m_sess,packet);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x0B:
					pkthandle = new contactlist_handler(m_sess);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x0E:
					pkthandle = new grouplist_handler(m_sess);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x11:
					pkthandle = new blockcontact_handler(m_sess,packet);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x01:
				case 0x02:
				case 0x07:
				case 0x08:
				case 0x0A:
				case 0x0C:
				case 0x0D:
				case 0x0F:
				case 0x10:
				case 0x12:
					pkthandle = new clientside_handler(this.opcode_id);
					break;
				default:
					pkthandle = new null_handler(this.opcode_id);
					break;
			}
		}
	}

	public void Destroy() {}
}
