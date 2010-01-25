package session;

public enum AddContactState {
	EXIST(0),
	NOT_EXIST(1),
	ALREADY_ACCEPTED(2),
	NOT_ACCEPT(3),
	GROUP_NOT_EXIST(4),
	PACKET_ERROR(5);
	
	private final int value;
	
	private AddContactState(int val){
		this.value = val;
	}
	
	public int getvalue(){
		return this.value;
	}
}
