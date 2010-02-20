package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import session.contact;
import socket.packet.handlers.sends.DelContact_handler;

public class contact_delete implements ActionListener 
{
	private contact contact;
	private JFrame fenetre;
	public contact_delete(contact contactasupprimer) {
		this.contact = contactasupprimer;
	}

	public void actionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment supprimer "
				+ contact +" ?","Important !!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			DelContact_handler dch = new DelContact_handler(contact.getCid());
			if(dch != null)
				dch.Send();
		}
	}
}
