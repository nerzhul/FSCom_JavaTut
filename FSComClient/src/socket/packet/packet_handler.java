package socket.packet;

import java.net.Socket;

import session.Session;
import session.events;
import socket.packet.handlers.*;
import socket.packet.handlers.listens.null_handler;
import socket.packet.handlers.listens.pong_handler;
import socket.packet.handlers.listens.serverside_handler;
import socket.packet.handlers.listens.srvconnect_handler;
import socket.packet.handlers.sends.AskContacts_handler;
import socket.packet.handlers.sends.AskGroups_handler;
import socket.packet.handlers.sends.srvpong_handler;
import socket.packet.handlers.sends.statussender_handler;

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
					// TODO: send correct status
					if((new srvconnect_handler(packet.toString())).HasValidData())
					{
						pcktrecv = new statussender_handler(Session.getStatus(),true);
						((send_handler) pcktrecv).Send();
					}
					else
					{
						// TODO: event fenêtre connexion failed
					}
					break;
				case 0x0A:
					pcktrecv = new AskGroups_handler();
					((send_handler) pcktrecv).Send();
					break;
				case 0x0C:
					events.StoreContacts(packet);
					break;
				case 0x0D:
					events.ContactDisconnected(packet);
					break;
				case 0x0F:
					events.StoreGroups(packet);
					pcktrecv = new AskContacts_handler();
					((send_handler) pcktrecv).Send();
					break;
				case 0x10:
					events.ContactConnected(packet);
					break;
				case 0x12:
					events.BlockContact(packet);
					break;
				case 0x14:
					events.RecvMsg(packet);
					break;
				case 0x15:
					events.ContactModifyStatus(packet);
					break;
				case 0x17:
					events.ContactModifyPseudo(packet);
					break;
				case 0x19:
					events.ContactModifyPmsg(packet);
					break;
				case 0x1B:
					events.ContactAdded(packet);
					break;
				case 0x1D:
					events.ContactDeleted(packet);
					break;
				case 0x1E:
					events.RecvInvitation(packet);
					break;
				case 0x00:
				case 0x03:
				case 0x04:
				case 0x05:
				case 0x06:
				case 0x09:
				case 0x0B:
				case 0x0E:
				case 0x11:
				case 0x13:
				case 0x16:
				case 0x18:
				case 0x1A:
				case 0x1C:
				case 0x1F:
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
