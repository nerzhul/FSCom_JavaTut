package socket.packet;

import java.net.Socket;

import session.Session;
import session.events;
import socket.ServerList;
import socket.packet.handlers.*;
import socket.packet.handlers.listens.Depreciated_handler;
import socket.packet.handlers.listens.Null_handler;
import socket.packet.handlers.listens.Pong_handler;
import socket.packet.handlers.listens.ServerSide_handler;
import socket.packet.handlers.listens.SrvConnect_handler;
import socket.packet.handlers.sends.AskAllDatas_handler;
import socket.packet.handlers.sends.SrvPong_handler;
import misc.Log;

public class Packet_handler 
{
	final static int MAX_OPCODE = 0xFF;
	Integer opcode_id;
	Object data;
	Socket mysock;
	
	public Packet_handler(Packet stream,Socket sock)
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
			
			Abstract_handler pcktrecv = null;
			switch(opcode_id)
			{
				case 0x01:
					Pong_handler.PongReceived();
					break;
				case 0x02:
					pcktrecv = new SrvPong_handler();
					((Send_handler) pcktrecv).Send();
					break;
				case 0x07:
					// server kill client socket
					break;
				case 0x08:
					if((new SrvConnect_handler(data.toString())).HasValidData())
					{
						pcktrecv = new AskAllDatas_handler(Session.getStatus());
						((Send_handler) pcktrecv).Send();
					}
					break;
				case 0x0D:
					events.ContactDisconnected(data);
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
					ServerList.ClosePopup();
					events.ShowConnectedFrame();
					break;
				case 0x26:
					events.GroupAdded(data);
					break;
				case 0x28:
					events.GroupDeleted(data);
					break;
				case 0x2A:
					events.GroupRenamed(data);
					break;
				case 0x2E:
					events.CreateAccountAnswer(data);
					break;
				case 0x32:
					events.ChangeContactAvatar(data);
					break;
				case 0x30:
				case 0x2C:
				case 0x0C:
				case 0x0F:
				case 0x0A:
				case 0x23:
					new Depreciated_handler(opcode_id);
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
				case 0x24:
				case 0x25:
				case 0x27:
				case 0x29:
				case 0x2B:
				case 0x2D:
				case 0x2F:
				case 0x31:
				case 0x33:
					pcktrecv = new ServerSide_handler(this.opcode_id);
					break;
				default:
					pcktrecv = new Null_handler(this.opcode_id);
					break;
			}
			if(pcktrecv != null)
				pcktrecv.Destroy();
		}
	}

	public void Destroy() {}
}
