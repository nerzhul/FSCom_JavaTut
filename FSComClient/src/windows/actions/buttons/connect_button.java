package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import session.Session;
import socket.packet.handlers.send_handler;
import socket.packet.handlers.sends.connect_handler;
import java.io.*;
import thread.threading;
import windows.forms.form_master;

//import windows.forms.form_contact;

public class connect_button implements ActionListener {
	private JComboBox status;
	private JTextField username;
	private JPasswordField passwd;
	private JCheckBox need_save;
	private form_master mainframe;
	public connect_button(JComboBox statusco, JTextField mail, JPasswordField password, JCheckBox save, form_master fram) 
	{
		this.status=statusco;
		this.username=mail;
		this.passwd=password;
		need_save = save;
		mainframe = fram;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(need_save.isSelected())
			SaveFile();
		// Store status in session
		Session.setStatus(status.getSelectedIndex() + 1);

	
		// Launcher socket with server
		threading.LaunchSender(true);
		
		send_handler pck = new connect_handler(username.getText() + " " + new String(passwd.getPassword()));
		if(pck != null)
			pck.Send();	
	}
	
	public void SaveFile(){
		String file = "savedvariables";
		try {
			FileWriter fw = new FileWriter (file);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter wfile = new PrintWriter (bw); 
			wfile.println (username.getText()); 
			wfile.println (passwd.getPassword()); 
			wfile.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}	
	}
	
}
		