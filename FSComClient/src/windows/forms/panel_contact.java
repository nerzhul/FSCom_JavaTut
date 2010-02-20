package windows.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import misc.Log;

import session.Session;
import session.group;
import windows.actions.buttons.changestatus_button;
import windows.actions.click.contact_onclick;

public class panel_contact extends JPanel implements DropTargetListener, DragGestureListener, DragSourceListener{

	private static final long serialVersionUID = 1L;
	private JLabel Titre;
	private JLabel Soustitre;
	private JTree  tree;
	private JComboBox changstatus;
	private form_communicate comm;

	private DragSource dragSource = null;
	private DefaultMutableTreeNode selecContact = null;
	private DefaultMutableTreeNode dropContact = null;

	
	private int status2;
	private JTextField msgperso;

	public panel_contact()
	{
		BuildPanel();
	}

	private void BuildPanel()
	{
		
		setLayout(new FlowLayout());
		setBackground(new Color(128,128,255));
		setLayout(new FlowLayout(FlowLayout.CENTER,400,10));
		
		Titre = new JLabel(Session.getPseudo());
		
		changstatus= new JComboBox();
		
		changstatus.addItem("Online");
		changstatus.addItem("Busy");
		changstatus.addItem("AFK");
		changstatus.addItem("Offline");
		changstatus.setSelectedIndex(status2);
		changstatus.addActionListener(new changestatus_button(changstatus));
		msgperso = new JTextField(20);
		msgperso.setText(Session.getPerso_msg());
		
		Soustitre = new JLabel("Liste de vos contacts : ");
		
		add(Titre);
		add(changstatus);
		add(msgperso);
		add(Soustitre);
		
		
		setlistcontact();	
		
		comm = null;
	}
	
	private void setlistcontact()
	{
		Vector<group> groups = Session.getGroups();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Contactlist");
		 
		// Adding nodes
		for(int i=0;i<groups.size();i++)
		{
			DefaultMutableTreeNode tmp_grp = new DefaultMutableTreeNode(groups.get(i).getGname());
			root.add(tmp_grp);
			for(int j=0;j<groups.get(i).getContacts().size();j++)
			{
				DefaultMutableTreeNode tmp_contact = new DefaultMutableTreeNode(groups.get(i).getContacts().get(j));
				tmp_grp.add(tmp_contact);	
			}
		}
		     
		// Construction du mod�le de l'arbre.
		DefaultTreeModel myModel = new DefaultTreeModel(root);
		 
		// Construction de l'arbre.
		tree = new JTree(myModel);
		
		/*
		// Construction d'un afficheur par d�faut.
		DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();
		 
		// Changement de l'ic�ne pour les feuilles de l'arbre.
		myRenderer.setLeafIcon(new ImageIcon("pageIcon.png"));
		// Changement de l'ic�ne pour les noeuds ferm�s.
		myRenderer.setClosedIcon(new ImageIcon("closedBookIcon.png"));
		// Changement de l'ic�ne pour les noeuds ouverts.
		myRenderer.setOpenIcon(new ImageIcon("openBookIcon.png"));
		 
		// Application de l'afficheur � l'arbre.
		myTree.setCellRenderer(myRenderer);
		*/

		new DropTarget(tree, this);
	    dragSource = new DragSource();
	    dragSource.createDefaultDragGestureRecognizer(tree, DnDConstants.ACTION_MOVE, this);
	 
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addMouseListener(new contact_onclick(tree,this));
		tree.setRootVisible(false);
		this.add(tree);
		JScrollPane Scrollbar = new JScrollPane(tree);
		Scrollbar.setPreferredSize(new Dimension(150, 300));
		this.add(Scrollbar);
	}

	public void setComm(form_communicate comm) { this.comm = comm; }
	public form_communicate getComm() { return comm; }
	
	public void ChPseudo(String n_pseudo)
	{
		Titre.setText("Pseudo : " + n_pseudo);
	}

	public void dragEnter(DropTargetDragEvent e) {	e.acceptDrag(DnDConstants.ACTION_MOVE);	}

	public void dragGestureRecognized(DragGestureEvent e) 
	{
		selecContact = null;
	    dropContact = null;
	 
	    Object selected = tree.getSelectionPath();
	 
	    if (selected != null) 
	    {
	      TreePath treepath = (TreePath) selected;
	      selecContact = (DefaultMutableTreeNode) treepath.getLastPathComponent();
	      dragSource.startDrag(e, DragSource.DefaultMoveDrop, new StringSelection(selected.toString()), this);
	    }
	}

	public void drop(DropTargetDropEvent e) 
	{
		 Transferable transferable = e.getTransferable();
		 
		 if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		     e.acceptDrop(DnDConstants.ACTION_MOVE);
		     Point dropPoint = e.getLocation();
		     TreePath dropPath = tree.getClosestPathForLocation(dropPoint.x,dropPoint.y);
		     dropContact = (DefaultMutableTreeNode) dropPath.getLastPathComponent();
		     e.getDropTargetContext().dropComplete(true);
		 }
		 else {
		    e.rejectDrop();
		}
	}
	
	public void dragDropEnd(DragSourceDropEvent e) {
		if (e.getDropSuccess()) {
			if (dropContact == null)
		        Log.outError("Deplacement impossible car en dehors de l'arbre !");
		    else
		    if (selecContact.getLevel()==1)
		    	Log.outError("Deplacement impossible car le noeud source est un groupe !");
		    else
		    if(dropContact.getLevel()==2){
		    	Log.outError("Deplacement impossible car le groupe de destination est un contact !");   
		    }else{
		      ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(selecContact);
			  //changement du groupe --> envoi au serveur
			  ((DefaultTreeModel) tree.getModel()).insertNodeInto(selecContact, dropContact, dropContact.getChildCount());
		    }
		}
	}
	
	public void dragExit(DropTargetEvent arg0) {}
	public void dragOver(DropTargetDragEvent arg0) {}
	public void dropActionChanged(DropTargetDragEvent arg0) {}
	public void dragEnter(DragSourceDragEvent dsde) {}
	public void dragExit(DragSourceEvent dse) {}
	public void dragOver(DragSourceDragEvent dsde) {}
	public void dropActionChanged(DragSourceDragEvent dsde) {}
}