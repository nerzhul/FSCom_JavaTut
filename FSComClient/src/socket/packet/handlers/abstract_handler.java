package socket.packet.handlers;

public abstract class abstract_handler {

	protected Integer opcode;
	protected Object data;
	abstract void PrintError();
	public void Destroy(){}
	public abstract boolean HasValidData();
}
