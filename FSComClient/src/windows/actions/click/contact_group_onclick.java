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

/*
 * Actions sur les divers clicks sur les contacts ET les groupes (click droit/gauche)
 */
public class contact_group_onclick implements MouseListener 
{

	private JTree arbre;
	private panel_contact pn;
	public contact_group_onclick(JTree tree, panel_contact pan) 
	{
		//stockage des données
		this.arbre = tree;
		this.pn = pan;
	}

	public void mouseClicked(MouseEvent e) {
		DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
	
		//action sur le double click sur le contact
		if(e.getClickCount() == 2 && noeud != null && noeud.getLevel() == 2){
			//on créait une fenetre de conversation si il n'y en a pas
			if(pn.getComm() == null)
				pn.setComm(new form_communicate());
			//on ajoute un onglet avec le contact
			pn.getComm().AddTab((contact) noeud.getUserObject());
			pn.getComm().ChangeAllMyStatusBorder();
		}		   
	}


	public void mouseReleased(MouseEvent e) {
		
		TreePath index = arbre.getPathForLocation(e.getX(), e.getY());
        arbre.setSelectionPath(index);
        DefaultMutableTreeNode noeud = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
        
        //affichage d'un menu lorsque l'on click droit sur un contact
        if (e.getButton() == 3 && noeud != null && noeud.getLevel() == 2)
		{
			JPopupMenu menu = new JPopupMenu();
			contact user = (contact) noeud.getUserObject();
			//avec les divers actions possible sur le contact
			SwingEL.AddItemToMenu(menu,"Envoyer un message",new contact_onclick_sendmsg(user,pn));
			SwingEL.AddItemToMenu(menu,"Supprimer",new contact_delete(user));
			SwingEL.AddItemToMenu(menu,(user.isBlocked()) ? "Autoriser" : "Ignorer",new contact_onclick_block(user));
			SwingEL.AddItemToMenu(menu,"Voir les d�tails",new contact_onclick_details(user));
			
	        menu.show (e.getComponent(),e.getX(),e.getY());//définition de la position du menu
		}//affichage d'un autre menu lors du click droit sur un groupe
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
