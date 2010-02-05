package socket.packet;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import session.session;
import socket.sender;
import socket.packet.handlers.*;
import socket.packet.handlers.listened.*;
import socket.packet.handlers.senders.AddContact_handler;
import socket.packet.handlers.senders.blockcontact_handler;
import socket.packet.handlers.senders.connect2_handler;
import socket.packet.handlers.senders.connect_handler;
import socket.packet.handlers.senders.invitation_answer_handler;
import socket.packet.handlers.senders.pong_handler;
import socket.packet.handlers.senders.status_handler;


import misc.Log;

public class packet_handler 
{
	final static int MAX_OPCODE = 0xFF;
	Integer opcode_id;
	Object data;
	Socket mysock;
	sender send;
	private session m_sess;
	
	public packet_handler(packet stream,Socket sock,session sess)
	{
		mysock = sock;
		m_sess = sess;
		try
		{
			opcode_id = stream.getOpcode();
			Log.outString("Packet received from " + mysock.getInetAddress() + " (opcode :" + this.opcode_id + ")");
			
			data = stream.getData();
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
		Log.outString(opcode_id + " / " + data);
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
					pkthandle = new connect_handler(data,m_sess);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x05:
					// disconnect
					break;
				case 0x06:
					// request disconnect socket
					break;
				case 0x09:
					pkthandle = new status_handler(m_sess,data);
					break;
				case 0x11:
					pkthandle = new blockcontact_handler(m_sess,data);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x13:
					pkthandle = new MsgToPlatform_handler(m_sess,data);
					break;
				case 0x16:
					pkthandle = new PseudoToPlatform_handler(m_sess,data);
					break;
				case 0x18:
					pkthandle = new MsgPersoToPlatform_handler(m_sess,data);
					break;
				case 0x1A:
					pkthandle = new AddContact_handler(m_sess,data);
					((send_handler) pkthandle).Send(mysock);
				case 0x1C:
					pkthandle = new req_DelContact_handler(m_sess,data);
					break;
				case 0x1F:
					pkthandle = new invitation_answer_handler(m_sess,data);
					break;
				case 0x20:
					pkthandle = new req_pseudo_handler(m_sess,data);
					break;
				case 0x21:
					pkthandle = new connect2_handler(m_sess,data);
					((send_handler) pkthandle).Send(mysock);
					break;
				case 0x0B:
				case 0x0E:
					new depreciated_handler(opcode_id);
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
				case 0x14:
				case 0x15:
				case 0x17:
				case 0x19:
				case 0x1B:
				case 0x1D:
				case 0x1E:
				case 0x22:
				case 0x23:
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
