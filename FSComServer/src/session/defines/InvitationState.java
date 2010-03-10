package session.defines;

/*
 * invitations states, used.
 */
public enum InvitationState {
	ACCEPT(0),
	NOT_ACCEPT(1),
	LATER(2);
	
	private final int value;
	
	private InvitationState(int val){
		this.value = val;
	}
	
	public int getvalue(){
		return this.value;
	}
}
