package socket.packet.handlers;

import misc.Log;

public class pong_handler extends listen_handler {
	
	public static void PongReceived()
	{
		Log.outTimed("Pong received from server.");
	}
	
}
