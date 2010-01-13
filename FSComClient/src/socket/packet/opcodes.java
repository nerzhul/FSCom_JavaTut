package socket.packet;

public enum opcodes {

	CMSG_REQUEST_CONNECT(0);
	private final int value;
	
	private opcodes(int op)
	{
		this.value = op;
	}
	
	public int getValue() {
		return this.value;
	}

}
