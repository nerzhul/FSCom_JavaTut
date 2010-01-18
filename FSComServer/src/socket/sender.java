package socket;

import java.net.*;
import java.io.*;

import socket.packet.opcodes;

public class sender
{

	private Socket socket;
	private Integer opcode;
	private Object packt;
	public sender(Socket sock,Integer op,Object packet)
	{
		socket = sock;
		opcode = op;
		packt = packet;
	}
	
	public void CloseConnection() throws IOException
	{
		socket.close();
	}
	
	public void SendPacket() throws IOException
	{
		// creating buffers for packets to send
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		// send packet
    	out.println("0x" + Integer.toHexString(opcode) + packt);
	}

}
