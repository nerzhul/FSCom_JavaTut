package windows.actions.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import session.Session;
import socket.packet.handlers.sends.group_handlers.AddGroup_handler;
import thread.windowthread;

public class menubar_addgroup implements ActionListener {

	private JFrame fenetre;
	private String reponse;

	public void actionPerformed(ActionEvent e) {
	
		reponse = JOptionPane.showInputDialog(fenetre,"Entrez le nom du groupe � ajouter :",
				"Nouveau groupe",JOptionPane.QUESTION_MESSAGE);
		if (reponse != null && !reponse.equalsIgnoreCase(""))
		{
			if(reponse.length()<20)
			{
				AddGroup_handler pck = new AddGroup_handler(Session.getMaxGid()+1,reponse);
				pck.Send();			
				JTree arbre = windowthread.getFmConn().getPanContact().recup();
				/*arbre.ad
				 DefaultMutableTreeNode parentNode = null;
				    TreePath parentPath = tree.getSelectionPath();

				    if (parentPath == null) {
				        //There is no selection. Default to the root node.
				        parentNode = rootNode;
				    } else {
				        parentNode = (DefaultMutableTreeNode)
				                     (parentPath.getLastPathComponent());
				    }*/
			}
			else
				JOptionPane.showMessageDialog(fenetre,"Le nom du groupe est trop long !" );
			
			
		}	
	}

}
