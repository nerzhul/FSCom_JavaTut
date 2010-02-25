package windows.forms;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import session.contact;

public class MyRenderer extends DefaultTreeCellRenderer {
 
 
	private static final long serialVersionUID = -3991603773928897980L;
	private ImageIcon afkIcon;
	private ImageIcon busyIcon;
	private ImageIcon offlineIcon;
	private ImageIcon onlineIcon;

 
	public MyRenderer(String afk, String busy, String offline, String online) {
		afkIcon = new ImageIcon(afk);
		busyIcon = new ImageIcon(busy);
		offlineIcon = new ImageIcon(offline);
		onlineIcon = new ImageIcon(online);
	}
 
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	    
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	    if(node.getLevel() == 2){
		    contact info = (contact)node.getUserObject();
		    Integer status = info.getStatus();
		    if (status.equals(0)){
		    	setIcon(offlineIcon);
		    } else if (status.equals(1)) {
		    	setIcon(onlineIcon);
		    } else if (status.equals(2)) {
		    	setIcon(busyIcon);
		    } else if (status.equals(3)) {
		    	setIcon(afkIcon);
		    }
	   }

	    setOpenIcon(new ImageIcon("ouvert.png"));
	    setClosedIcon(new ImageIcon("fermer.png"));
	    return this;
	}
}