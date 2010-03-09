package session.defines;

public enum InvitationState {
	LATER(0),
	NOT_ACCEPT(1),
	ACCEPT(2);
	
	private final int value;
	
	private InvitationState(int val){
		this.value = val;
	}
	
	public int getvalue(){
		return this.value;
	}
}
