package session.defines;

/*
 * Enum contenant les statuts possible d'un utilisateur
 */
public enum clientstatus {
	DISCONNECTED(0),
	CONNECTED(1),
	BUSY(2),
	AFK(3),
	APPEAROFFLINE(4),
	MAX_STATUS(5);
	private final int value;
	
	//fonction retournant le statut d'un contact
	private clientstatus(int val){
		this.value = val;
	}
	
	public int getvalue(){
		return this.value;
	}
}
