package windows.actions.click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

//import windows.window.form_conversation_avec;
public class contact_onclick implements MouseListener {

	private JTree arbre;
	private DefaultMutableTreeNode noeud;
	public contact_onclick(JTree tree) {
		this.arbre = tree;
	}

	public void mouseClicked(MouseEvent e) {
		noeud = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
	
		if(e.getClickCount() == 2 && noeud.isLeaf()){
			/*new form_conversation_avec(noeud)*/;
			//System.out.println("double cliqué sur " + noeud);
		}		   
	}


	public void mouseReleased(MouseEvent e) {
		
		TreePath index = arbre.getPathForLocation(e.getX(), e.getY());
        arbre.setSelectionPath(index);
        noeud = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
        
		if (e.getButton() == 3 && noeud.isLeaf()){
			JPopupMenu menu = new JPopupMenu();
		      
			JMenuItem envoi = new JMenuItem("Envoyer un message");
			envoi.addActionListener(new contact_onclick_sendmsg(noeud));
			JMenuItem supprimer = new JMenuItem("Supprimer");
			supprimer.addActionListener(new contact_delete(noeud));
			JMenuItem bloquer = new JMenuItem("Bloquer");
			bloquer.addActionListener(new contact_onclick_block(noeud));
			JMenuItem details = new JMenuItem("Voir les détails");
			details.addActionListener(new contact_onclick_details(noeud));
			menu.add(envoi);
			menu.add(supprimer);
			menu.add(bloquer);
			menu.add(details);
			
	        menu.show (e.getComponent(),e.getX(),e.getY());

	        //System.out.println("clique droit sur " + noeud);
		}else if(e.getButton() == 3){
			//renommer grp
			//supprimer grp
			//le tout dans un beau PopUp Menu
			System.out.println("click droit sur grp");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
