package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import session.Session;

public class menubar_addgroup implements ActionListener {

	private JFrame fenetre;
	private String reponse;

	public void actionPerformed(ActionEvent e) {
	
		reponse = JOptionPane.showInputDialog(fenetre,"Entrez le nom du groupe à ajouter :",
				"Nouveau groupe",JOptionPane.QUESTION_MESSAGE);
		if (reponse != null && !reponse.equalsIgnoreCase(""))
		{
			if(reponse.length()<20)
			{
				Session.getMaxGid();
				//envoi au serveur du nouveau groupe
			}
			else
				JOptionPane.showMessageDialog(fenetre,"Le nom du groupe est trop long !" );
			
		}	
	}

}
