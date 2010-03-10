package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.Session;
import socket.packet.handlers.sends.group_handlers.AddGroup_handler;

/*
 * Action sur ajout groupe du menu
 */
public class menubar_addgroup implements ActionListener {

	private String reponse;

	public void actionPerformed(ActionEvent e) {
	//popup pour récupérer le nom du groupe
		reponse = JOptionPane.showInputDialog(null,"Entrez le nom du groupe � ajouter :",
				"Nouveau groupe",JOptionPane.QUESTION_MESSAGE);
		if (reponse != null && !reponse.equalsIgnoreCase(""))
		{//et si le nom est valide
			if(reponse.length()<20)
			{
				AddGroup_handler pck = new AddGroup_handler(Session.getMaxGid()+1,reponse);//on créait le packet
				pck.Send();		//et on l'envoi		
			}
			else
				JOptionPane.showMessageDialog(null,"Le nom du groupe est trop long !" );
			//on affiche une erreur si le nom est invalide
			
		}	
	}

}
