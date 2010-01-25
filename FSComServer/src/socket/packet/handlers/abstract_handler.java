package socket.packet.handlers;

import java.io.IOException;

public abstract class abstract_handler {

	protected Integer opcode;
	protected String data;
	abstract void PrintError() throws IOException;
}
