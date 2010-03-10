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
import socket.packet.handlers.senders.Pong_handler;
import socket.packet.handlers.senders.connect_handlers.Connect2_handler;
import socket.packet.handlers.senders.connect_handlers.Connect_handler;
import socket.packet.handlers.senders.contact_handlers.AddContact_handler;
import socket.packet.handlers.senders.contact_handlers.Status_handler;


import misc.Log;

/*
 * manage the packet recv
 */
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
		// register socket & session
		mysock = sock;
		m_sess = sess;
		try
		{
			// register id
			opcode_id = stream.getOpcode();
			Log.outString("Packet received from " + mysock.getInetAddress() + " (opcode :" + this.opcode_id + ")");
			
			// register data
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
			// packet is not in list
			Log.outError("Unrecognized opcode received from " + mysock.getInetAddress());
			return;
		}
		else
		{
			Abstract_handler pkthandle = null;
			// switch by opcode and do actions
			switch(opcode_id)
			{
				case 0x00:
					Pong_handler pkt = new Pong_handler();
					pkt.Send(mysock);
					break;
				case 0x03:
					pkthandle = new CliPong_handler(mysock);
					break;
				case 0x04:
					Connect_handler pk = new Connect_handler(data,m_sess);
					pk.Send(mysock);
					break;
				case 0x05:
					m_sess.disconnect_client();
					break;
				case 0x06:
					// request disconnect socket
					break;
				case 0x09:
					new Status_handler(m_sess,data);
					break;
				case 0x11:
					m_sess.block_contact(data);
					break;
				case 0x13:
					m_sess.TransmitMsgTo(data);
					break;
				case 0x16:
					m_sess.ChangePseudo(data);
					break;
				case 0x18:
					m_sess.ChangeMsgPerso(data);
					break;
				case 0x1A:
					pkthandle = new AddContact_handler(m_sess,data);
					((Send_handler) pkthandle).Send(mysock);
					break;
				case 0x1C:
					m_sess.DelContact(data);
					break;
				case 0x1F:
					new Invitation_Answer_handler(m_sess,data);
					break;
				case 0x20:
					new Req_pseudo_handler(m_sess,data);
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
				case 0x31:
					m_sess.EventReqAvatar(data);
					break;
				case 0x33:
					m_sess.EventStoreAvatar(data);
					break;
				case 0x2F:
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
				case 0x32:
					new ClientSide_handler(this.opcode_id);
					break;
				default:
					new Null_handler(this.opcode_id);
					break;
			}
		}
	}

	public void Destroy() {}
}
