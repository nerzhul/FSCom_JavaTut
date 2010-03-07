package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.contact;
import socket.packet.handlers.sends.contact_handlers.DelContact_handler;

public class contact_delete implements ActionListener 
{
	private contact contact;
	public contact_delete(contact toDel) {
		this.contact = toDel;
	}

	public void actionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer "
				+ contact +" ?","Important !!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			DelContact_handler dch = new DelContact_handler(contact.getCid());
			if(dch != null)
				dch.Send();
		}
	}
}
