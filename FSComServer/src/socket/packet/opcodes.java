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
	CMSG_SEND_STATUS(0x09),
	SMSG_CONNECT_STATUS_RECEIVED(0x0A),
	CMSG_REQUEST_CONTACTLIST(0x0B),
	SMSG_SEND_CONTACTLIST(0x0C),
	SMSG_CONTACT_DISONNECTED(0x0D),
	CMSG_REQUEST_GROUPLIST(0x0E),
	SMSG_SEND_GROUPLIST(0x0F),
	SMSG_CONTACT_CONNECTED(0x10),
	CMSG_BLOCK_CONTACT(0x11),
	SMSG_CONFIRM_BLOCK_CONTACT(0x12),
	CMSG_SEND_MSG_TO(0x13),
	SMSG_TRANSMIT_MSG_TO(0x14),
	SMSG_TRANSMIT_STATUS_TO(0x15),
	CMSG_CHANGE_PSEUDO(0x16),
	SMSG_TRANSMIT_PSEUDO_TO(0x17),
	CMSG_CHANGE_MSGPERSO(0x18),
	SMSG_TRANSMIT_MSGPERSO_TO(0x19),
	
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
