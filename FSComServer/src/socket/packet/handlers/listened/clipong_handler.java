package socket.packet.handlers.listened;

import java.net.Socket;

import socket.packet.handlers.Listen_handler;


import misc.Log;

public class CliPong_handler extends Listen_handler {

	public CliPong_handler(Socket sock)
	{
		Log.outTimed("Pong received from client " + sock.getInetAddress() + "!");
	}
}
