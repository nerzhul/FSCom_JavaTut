package socket.packet;

public enum opcodes {

	CMSG_REQUEST_CONNECT(0x00),
	CMSG_REQUEST_DISCONNECT(0x01),
	
	CMSG_REQUEST_TESTPACKET(0xFF);
	
	private final int value;
	
	private opcodes(int op)
	{
		this.value = op;
	}
	
	public int getValue() {
		return this.value;
	}

}
