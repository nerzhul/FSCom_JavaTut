package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import socket.packet.handlers.sends.AccCreate_handler;
import thread.threading;

public class valid_inscription_button implements ActionListener 
{

	private JFormattedTextField username;
	private JPasswordField pass,pass2;
	public valid_inscription_button(JFormattedTextField idtxt,
			JPasswordField passtxt, JPasswordField passtxt2) { 
		username = idtxt;
		pass=passtxt;
		pass2=passtxt2;
	}

	public void actionPerformed(ActionEvent arg0) {
		String id = username.getText().trim();
		String passtxt = new String(pass.getPassword());
		String passtxt2 = new String(pass2.getPassword());
		if (id != null && !id.equalsIgnoreCase("") &&
				passtxt != null && passtxt2 != null && passtxt.equals(passtxt2) && passtxt.length()>4 
				&& !passtxt.equals(id))
		{
			threading.LaunchSender(true);
			AccCreate_handler pck = new AccCreate_handler(id,passtxt2);
			pck.Send();
		}
		else
			JOptionPane.showMessageDialog(null,"Informations incorrectes !");


	}

}
