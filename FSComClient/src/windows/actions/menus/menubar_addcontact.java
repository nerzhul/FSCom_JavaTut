package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import socket.packet.handlers.sends.AddContact_handler;

public class menubar_addcontact implements ActionListener {

	private JFrame fenetre;
	private String reponse;

	public void actionPerformed(ActionEvent e) {
	
		reponse = JOptionPane.showInputDialog(fenetre,"Entrez le nom du contact à ajouter :",
				"Nouveau contact",JOptionPane.QUESTION_MESSAGE);
		if (reponse != null && !reponse.equalsIgnoreCase(""))
		{
			AddContact_handler pck = new AddContact_handler(reponse);
			pck.Send();
		}	

	}

}
