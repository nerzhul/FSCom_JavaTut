package socket.packet.handlers.listened;

import java.net.Socket;

import socket.packet.handlers.listen_handler;


import misc.Log;

public class clipong_handler extends listen_handler {

	public clipong_handler(Socket sock)
	{
		Log.outTimed("Pong received from client " + sock.getInetAddress() + "!");
	}
}
