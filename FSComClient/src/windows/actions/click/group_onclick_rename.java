package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.group;
import socket.packet.handlers.sends.group_handlers.RenameGroup_handler;

/*
 * Action de renommage d'un groupe (par le popupmenu)
 */
public class group_onclick_rename implements ActionListener {

	private Integer _gid;
	
	public group_onclick_rename(group gr) 
	{
		_gid = gr.getGid();
	}

	public void actionPerformed(ActionEvent e) 
	{
		//recupération du nouveau nom
		String nName = JOptionPane.showInputDialog(null,"Nouveau nom :",
				"Renommer le groupe",JOptionPane.QUESTION_MESSAGE);
		//divers tests sur ce nouveau nom
		if (nName != null && !nName.equalsIgnoreCase("") && nName.length()<20)
		{
			RenameGroup_handler rhl = new RenameGroup_handler(_gid,nName);//creation du packet
			rhl.Send();//envoi du packet
		}
		else
			JOptionPane.showMessageDialog(null,"Le nom du groupe est invalide !" );
		//message d'erreur si le nouveau nom n'est pas valide

	}
}
