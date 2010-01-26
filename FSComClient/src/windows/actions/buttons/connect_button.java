package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import session.Session;
import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.connect_handler;
import thread.threading;

//import windows.forms.form_contact;

public class connect_button implements ActionListener {
	private JComboBox status;
	private JTextField username;
	private JPasswordField passwd;
	public connect_button(JComboBox statusco, JTextField mail, JPasswordField password) {
		this.status=statusco;
		this.username=mail;
		this.passwd=password;
	}
	
	public synchronized void actionPerformed(ActionEvent e) 
	{
			// Store status in session
			Session.setStatus(status.getSelectedIndex() + 1);
			
			// get User and Pass
			String user = username.getText();
			String pass = new String(passwd.getPassword());
			
			// Launcher socket with server
			threading.LaunchSender(true);
			
			send_handler pck = new connect_handler(user + " " + pass);
			if(pck != null)
				pck.Send();	
	}
	
}
		