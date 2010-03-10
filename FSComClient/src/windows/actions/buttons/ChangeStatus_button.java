package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import socket.packet.handlers.Send_handler;
import socket.packet.handlers.sends.client_handlers.StatusSender_handler;
/*
 * Action sur la combobox de changement de statut
 */
public class ChangeStatus_button implements ActionListener {

	private JComboBox newstatus;
	public ChangeStatus_button(JComboBox changstatus) {
		this.newstatus = changstatus;
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		Integer st = newstatus.getSelectedIndex();//on selectionne le nouveau statut dans la liste
		Send_handler pck = new StatusSender_handler(st+1);//on créait le packet
		pck.Send();	//et on l'envoi			
	}
}
