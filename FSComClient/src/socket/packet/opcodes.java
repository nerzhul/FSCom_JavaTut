package socket.packet;

public enum opcodes {

	CMSG_REQUEST_PING(0x00),
	SMSG_RESPONSE_PONG(0x01),
	SMSG_REQUEST_PING(0x02),
	CMSG_RESPONSE_PONG(0x03),
	CMSG_REQUEST_CONNECT(0x04),
	CMSG_REQUEST_DISCONNECT(0x05),
	CMSG_REQUEST_CLOSE_SOCKET(0x06),
	SMSG_REQUEST_CLOSE_SOCKET(0x07),
	SMSG_RESPONSE_CONNECT(0x08),
	CMSG_FINAL_REQUEST_CONNECT(0x09),
	SMSG_FINAL_RESPONSE_CONNECT(0x0A),
	
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
