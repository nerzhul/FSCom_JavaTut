package socket.packet;

import java.net.Socket;

import session.Session;
import session.events;
import socket.packet.handlers.*;
import socket.packet.handlers.listens.null_handler;
import socket.packet.handlers.listens.pong_handler;
import socket.packet.handlers.listens.serverside_handler;
import socket.packet.handlers.listens.srvconnect_handler;
import socket.packet.handlers.sends.AskAllDatas_handler;
import socket.packet.handlers.sends.AskContacts_handler;
import socket.packet.handlers.sends.AskGroups_handler;
import socket.packet.handlers.sends.srvpong_handler;
import misc.Log;

public class packet_handler 
{
	final static int MAX_OPCODE = 0xFF;
	Integer opcode_id;
	Object data;
	Socket mysock;
	
	public packet_handler(packet stream,Socket sock)
	{
		mysock = sock;
		try
		{
			opcode_id = stream.getOpcode();
			data = stream.getData();
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
		Log.outString(opcode_id + " / " + data);
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
					if((new srvconnect_handler(data.toString())).HasValidData())
					{
						pcktrecv = new AskAllDatas_handler(Session.getStatus());
						((send_handler) pcktrecv).Send();
					}
					break;
				case 0x0A:
					pcktrecv = new AskGroups_handler();
					((send_handler) pcktrecv).Send();
					break;
				case 0x0C:
					//events.StoreContacts(data);
					events.ShowConnectedFrame();
					break;
				case 0x0D:
					events.ContactDisconnected(data);
					break;
				case 0x0F:
					///events.StoreGroups(data);
					pcktrecv = new AskContacts_handler();
					((send_handler) pcktrecv).Send();
					break;
				case 0x10:
					events.ContactConnected(data);
					break;
				case 0x12:
					events.BlockContact(data);
					break;
				case 0x14:
					events.RecvMsg(data);
					break;
				case 0x15:
					events.ContactModifyStatus(data);
					break;
				case 0x17:
					events.ContactModifyPseudo(data);
					break;
				case 0x19:
					events.ContactModifyPmsg(data);
					break;
				case 0x1B:
					events.ContactAdded(data);
					break;
				case 0x1D:
					events.ContactDeleted(data);
					break;
				case 0x1E:
					events.RecvInvitation(data);
					break;
				case 0x22:
					events.StoreAllDatas(data);
					events.ShowConnectedFrame();
					// TODO : handle correctly
					/*pcktrecv = new AskGroups_handler();
					((send_handler) pcktrecv).Send();*/
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
				case 0x21:
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
