package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class menubar_addgroup implements ActionListener {

	private JFrame fenetre;
	private String reponse;

	public void actionPerformed(ActionEvent e) {
	
		reponse = JOptionPane.showInputDialog(fenetre,"Entrez le nom du groupe à ajouter :",
				"Nouveau groupe",JOptionPane.QUESTION_MESSAGE);
		if (reponse !=null && !reponse.equalsIgnoreCase(""))
		{
			if(reponse.length()<20)
			{
				//envoi au serveur du nouveau groupe
				JOptionPane.showMessageDialog(fenetre,"Le groupe "+reponse+" a bien été créé !" );
			}else
				JOptionPane.showMessageDialog(fenetre,"Nouveau groupe trop long !" );
			
		}	

	}

}
