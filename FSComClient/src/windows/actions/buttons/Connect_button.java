package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import session.Session;
import socket.packet.handlers.Send_handler;
import socket.packet.handlers.sends.Connect_handler;
import java.io.*;
import thread.threading;
import thread.windowthread;

/*
 * Action sur le click du bouton de connection
 */
public class Connect_button implements ActionListener {
	private JComboBox status;
	private JTextField username;
	private JPasswordField passwd;
	private JCheckBox need_save;
	private String color;
	public Connect_button(JComboBox statusco, JTextField mail, JPasswordField password, JCheckBox save, String couleur) 
	{
		//on stocke les variables
		this.status=statusco;
		this.username=mail;
		this.passwd=password;
		color = couleur;
		need_save = save;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//Et on les sauvegardes dans un fichier si besoin
		if(need_save.isSelected())
			SaveFile();
		
		Session.setStatus(status.getSelectedIndex()+1);//on selectionne le statut dans la fenetre principale

		//si il y a une fenetre d'inscription on la ferme
		if(windowthread.getFmConn().getPanConnect().getFmInsc() != null)
		{
			windowthread.getFmConn().getPanConnect().getFmInsc().dispose();
			threading.StopSender();
		}
		//On lance le socket client
		threading.LaunchSender(true);
		
		//on envoi le packet de connection avec le pass + identifiant
		Send_handler pck = new Connect_handler(username.getText() + " " + new String(passwd.getPassword()));
		if(pck != null)
			pck.Send();	
	}
	
	//fonction pour la sauvegarde des variables
	public void SaveFile(){
		String file = "savedvariables";
		try {
			FileWriter fw = new FileWriter (file);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter wfile = new PrintWriter (bw); 
			wfile.println (username.getText()); 
			wfile.println (passwd.getPassword());
			wfile.println (color); 
			wfile.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}	
	}
	
}
		