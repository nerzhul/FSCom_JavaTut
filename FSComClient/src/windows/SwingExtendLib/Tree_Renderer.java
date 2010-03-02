package windows.SwingExtendLib;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import session.contact;

public class Tree_Renderer extends DefaultTreeCellRenderer {
 
 
	private static final long serialVersionUID = -3991603773928897980L;
 
	public Tree_Renderer() {}
 
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	    
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	    if(node.getLevel() == 2){
		    contact info = (contact)node.getUserObject();
		    Integer status = info.getStatus();
		    boolean block = info.isBlocked();
		    if (status.equals(0) || status.equals(4))
		    {
		    	if(block)
		    		setIcon(new ImageIcon("offlineblock.png"));
		    	else
		    		setIcon(new ImageIcon("offline.png"));
		    	
		    }
		    else if (status.equals(1))
		    {
		    	if(block)
		    		setIcon(new ImageIcon("onlineblock.png"));
		    	else
		    		setIcon(new ImageIcon("online.png"));
		    }
		    else if (status.equals(2))
		    {
		    	if(block)
		    		setIcon(new ImageIcon("busyblock.png"));
		    	else
		    		setIcon(new ImageIcon("busy.png"));
		    }
		    else if (status.equals(3))
		    {
		    	if(block)
		    		setIcon(new ImageIcon("afkblock.png"));
		    	else
		    		setIcon(new ImageIcon("afk.png"));
		    }
	   }

	    setOpenIcon(new ImageIcon("minus.png"));
	    setClosedIcon(new ImageIcon("plus.png"));
	    return this;
	}
}
