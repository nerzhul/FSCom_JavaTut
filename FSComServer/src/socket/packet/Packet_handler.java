package socket.packet;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import session.SessionHandler;
import session.session;
import socket.Sender;
import socket.packet.handlers.*;
import socket.packet.handlers.listened.*;
import socket.packet.handlers.senders.AccCreate_handler;
import socket.packet.handlers.senders.AddContact_handler;
import socket.packet.handlers.senders.BlockContact_handler;
import socket.packet.handlers.senders.Connect2_handler;
import socket.packet.handlers.senders.Connect_handler;
import socket.packet.handlers.senders.Invitation_Answer_handler;
import socket.packet.handlers.senders.Pong_handler;
import socket.packet.handlers.senders.Status_handler;


import misc.Log;

public class Packet_handler 
{
	final static int MAX_OPCODE = 0xFF;
	Integer opcode_id;
	Object data;
	Socket mysock;
	Sender send;
	private session m_sess;
	
	public Packet_handler(Packet stream,Socket sock,session sess)
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
			Abstract_handler pkthandle = null;
			switch(opcode_id)
			{
				case 0x00:
					pkthandle = new Pong_handler();
					((Send_handler) pkthandle).Send(mysock);
					break;
				case 0x03:
					pkthandle = new CliPong_handler(mysock);
					break;
				case 0x04:
					pkthandle = new Connect_handler(data,m_sess);
					((Send_handler) pkthandle).Send(mysock);
					break;
				case 0x05:
					// disconnect
					break;
				case 0x06:
					// request disconnect socket
					break;
				case 0x09:
					pkthandle = new Status_handler(m_sess,data);
					break;
				case 0x11:
					pkthandle = new BlockContact_handler(m_sess,data);
					((Send_handler) pkthandle).Send(mysock);
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
					((Send_handler) pkthandle).Send(mysock);
					break;
				case 0x1C:
					pkthandle = new Req_DelContact_handler(m_sess,data);
					break;
				case 0x1F:
					pkthandle = new Invitation_Answer_handler(m_sess,data);
					break;
				case 0x20:
					pkthandle = new Req_pseudo_handler(m_sess,data);
					break;
				case 0x21:
					pkthandle = new Connect2_handler(m_sess,data);
					((Send_handler) pkthandle).Send(mysock);
					break;
				case 0x24:
					m_sess.EventContactGroupChange(data);
					break;
				case 0x25:
					m_sess.EventGroupAdd(data);
					break;
				case 0x27:
					m_sess.EventGroupDel(data);
					break;
				case 0x29:
					m_sess.EventGroupRen(data);
					break;
				case 0x2D:
					pkthandle = new AccCreate_handler(SessionHandler.AccountCreate(data));
					((Send_handler) pkthandle).Send(mysock);
					break;
				case 0x2F:
					m_sess.SearchIp(data);
					break;
				case 0x0B:
				case 0x0E:
					new Depreciated_handler(opcode_id);
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
				case 0x26:
				case 0x28:
				case 0x2A:
				case 0x2B:
				case 0x2C:
				case 0x2E:
				case 0x30:
					pkthandle = new ClientSide_handler(this.opcode_id);
					break;
				default:
					pkthandle = new Null_handler(this.opcode_id);
					break;
			}
		}
	}

	public void Destroy() {}
}
