package session.defines;

public enum clientstatus {
	DISCONNECTED(0),
	CONNECTED(1),
	BUSY(2),
	AFK(3),
	APPEAROFFLINE(4),
	MAX_STATUS(5);
	private final int value;
	
	private clientstatus(int val){
		this.value = val;
	}
	
	public int getvalue(){
		return this.value;
	}
}
