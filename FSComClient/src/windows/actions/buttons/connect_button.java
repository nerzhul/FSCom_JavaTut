package windows.actions.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import windows.forms.form_contact;

public class connect_button implements ActionListener {
	//contenuUser() et contenuPass();
	private JFrame fenetre;
	private JComboBox status;
	private JTextField username;
	private JPasswordField passwd;
	public connect_button(JFrame fram, JComboBox statusco, JTextField mail, JPasswordField password) {
		fenetre=fram;
		this.status=statusco;
		this.username=mail;
		this.passwd=password;
	}
	
	public void actionPerformed(ActionEvent e) {

		boolean connectok=true;
		if(connectok == true)
		{
			String stat = status.getSelectedItem().toString();
			String user = username.getText();
			String pass = new String(passwd.getPassword());
			
			//verif login-->server avec user qui est le nom d'utilisateur et pass qui est le password et on récupère le pseudo dans pseudo
			String pseudo="kikoo";		
			fenetre.dispose();
			//new form_contact(stat,pseudo);
		}
		else{
			JOptionPane.showMessageDialog(fenetre, "Mauvais mot de passe ou mail");
		}
	}
	
}
		