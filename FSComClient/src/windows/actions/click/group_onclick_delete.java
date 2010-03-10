package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import session.group;
import socket.packet.handlers.sends.group_handlers.DelGroup_handler;
/*
 * Action de suppression d'un groupe (par le popupmenu)
 */
public class group_onclick_delete implements ActionListener 
{
	private Integer _gid;
	public group_onclick_delete(group gr) 
	{
		_gid = gr.getGid();
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		//popup de confirmation
		if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce groupe ?",
				"Confirmation de suppression",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			DelGroup_handler dhl = new DelGroup_handler(_gid);//création du packet
			dhl.Send();//envoi du packet
		}
	}

}
