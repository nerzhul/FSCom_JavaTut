package socket.packet.handlers;

public abstract class Abstract_handler {

	protected Integer opcode;
	protected Object data;
	abstract void PrintError();
	public void Destroy(){}
}
