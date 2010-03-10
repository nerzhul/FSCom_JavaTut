package socket.packet.handlers;

import java.io.IOException;

/*
 * basic handler for packets
 */
public abstract class Abstract_handler {

	protected Integer opcode;
	protected Object data;
	abstract void PrintError() throws IOException;
}
