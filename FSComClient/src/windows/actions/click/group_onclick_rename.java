package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.group;
import socket.packet.handlers.sends.group_handlers.RenameGroup_handler;

public class group_onclick_rename implements ActionListener {

	private Integer _gid;
	
	public group_onclick_rename(group gr) 
	{
		_gid = gr.getGid();
	}

	public void actionPerformed(ActionEvent e) 
	{
		String nName = JOptionPane.showInputDialog(null,"Nouveau nom :",
				"Renommer le groupe",JOptionPane.QUESTION_MESSAGE);
		if (nName != null && !nName.equalsIgnoreCase("") && nName.length()<20)
		{
			RenameGroup_handler rhl = new RenameGroup_handler(_gid,nName);
			rhl.Send();
		}
		else
			JOptionPane.showMessageDialog(null,"Le nom du groupe est invalide !" );

	}
}
