package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.statussender_handler;

public class changestatus_button implements ActionListener {

	private Integer newstatus;
	public changestatus_button(int i) {
		this.newstatus= i;
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		send_handler pck = new statussender_handler(newstatus,false);
		pck.Send();				
	}

}
