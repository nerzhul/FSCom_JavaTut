package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import socket.packet.handlers.sends.client_handlers.AccCreate_handler;
import thread.threading;
/*
 * Action sur le bouton validant l'inscription
 */
public class valid_inscription_button implements ActionListener 
{

	private JFormattedTextField username;
	private JPasswordField pass,pass2;
	public valid_inscription_button(JFormattedTextField idtxt,
			JPasswordField passtxt, JPasswordField passtxt2) { 
		//on stocke les variables
		username = idtxt;
		pass=passtxt;
		pass2=passtxt2;
	}

	public void actionPerformed(ActionEvent arg0) {
		//on récupère les pass et l'identifiant
		String id = username.getText().trim();
		String passtxt = new String(pass.getPassword());
		String passtxt2 = new String(pass2.getPassword());
		//on vérifie si tout es OK
		if (id != null && !id.equalsIgnoreCase("") &&
				passtxt != null && passtxt2 != null && passtxt.equals(passtxt2) && passtxt.length()>4 
				&& !passtxt.equals(id))
		{
			threading.LaunchSender(true);//on lance le sender pour permettre l'envoi d'infos au serveur
			AccCreate_handler pck = new AccCreate_handler(id,passtxt2);//créait le packet
			pck.Send();//et on l'envoi
		}
		else//affichage d'un message d'erreur si les informations données sont incorrectes.
			JOptionPane.showMessageDialog(null,"Informations incorrectes !","Erreur !", JOptionPane.ERROR_MESSAGE);


	}

}
