package windows.actions.click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import session.contact;
import session.group;
import windows.SwingExtendLib.SwingEL;
import windows.forms.form_communicate;
import windows.forms.panel_contact;

//import windows.window.form_conversation_avec;
public class contact_onclick implements MouseListener 
{

	private JTree arbre;
	private panel_contact pn;
	public contact_onclick(JTree tree, panel_contact pan) 
	{
		this.arbre = tree;
		this.pn = pan;
	}

	public void mouseClicked(MouseEvent e) {
		DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
	
		if(e.getClickCount() == 2 && noeud != null && noeud.getLevel() == 2){
			if(pn.getComm() == null)
				pn.setComm(new form_communicate());
			
			pn.getComm().AddTab((contact) noeud.getUserObject());
		}		   
	}


	public void mouseReleased(MouseEvent e) {
		
		TreePath index = arbre.getPathForLocation(e.getX(), e.getY());
        arbre.setSelectionPath(index);
        DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
        
        if(noeud == null)
        {
        	// TODO : basic actions (add group, add contact...)
        }
        else if (e.getButton() == 3 && noeud != null && noeud.getLevel() == 2)
		{
			JPopupMenu menu = new JPopupMenu();
			
			SwingEL.AddItemToMenu(menu,"Envoyer un message",new contact_onclick_sendmsg((contact) noeud.getUserObject(),pn));
			SwingEL.AddItemToMenu(menu,"Supprimer",new contact_delete((contact) noeud.getUserObject()));
			SwingEL.AddItemToMenu(menu,"Bloquer",new contact_onclick_block(noeud));
			SwingEL.AddItemToMenu(menu,"Voir les détails",new contact_onclick_details((contact)noeud.getUserObject()));
			
	        menu.show (e.getComponent(),e.getX(),e.getY());
		}
		else if(e.getButton() == 3 && noeud != null && noeud.getLevel() == 1)
		{
			if(((group)noeud.getUserObject()).getGid().equals(0))
				return;
			
			JPopupMenu menu = new JPopupMenu();
			SwingEL.AddItemToMenu(menu,"Renommer le groupe", new group_onclick_rename((group) noeud.getUserObject()));
			SwingEL.AddItemToMenu(menu,"Supprimer le groupe", new group_onclick_delete((group) noeud.getUserObject()));
			menu.show (e.getComponent(),e.getX(),e.getY());
		}
	}

	
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}
