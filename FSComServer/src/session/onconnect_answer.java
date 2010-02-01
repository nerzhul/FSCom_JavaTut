package session;

public enum onconnect_answer 
{
	CONN_NONE(0),
	CONN_PACKET_MALFORMED(1),
	CONN_SUCCESS(2),
	CONN_LOGIN_FAILED(3);
	private int value;
	private onconnect_answer(int val)
	{
		this.value = val;
	}
	
	public Integer getValue(){ return this.value; }
}
