package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class contact_onclick_block implements ActionListener {

	private Object contact;
	private JFrame fenetre;
	public contact_onclick_block(Object contactabloquer) {
		this.contact=contactabloquer;
	}

	public void actionPerformed(ActionEvent e) 
	{
		//envoi au serveur pour bloqué "contact"
		//puis maj list
		JOptionPane.showMessageDialog(fenetre,"Le contact "+ contact +" a été bloqué avec succès !");
	}

}
