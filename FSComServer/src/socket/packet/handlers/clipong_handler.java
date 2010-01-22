package socket.packet.handlers;

import java.net.Socket;

import misc.Log;

public class clipong_handler extends listen_handler {

	public clipong_handler(Socket sock)
	{
		Log.outTimed("Pong received from client " + sock.getInetAddress() + "!");
	}
}
