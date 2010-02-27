package socket.packet.handlers.listens;

import socket.packet.handlers.Listen_handler;
import misc.Log;

public class Pong_handler extends Listen_handler {
	
	public static void PongReceived()
	{
		Log.outTimed("Pong received from server.");
	}
	
}
