package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.statussender_handler;

public class ChangeStatus_button implements ActionListener {

	private JComboBox newstatus;
	public ChangeStatus_button(JComboBox changstatus) {
		this.newstatus = changstatus;
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		Integer st = newstatus.getSelectedIndex();
		send_handler pck = new statussender_handler(st);
		pck.Send();				
	}
}
