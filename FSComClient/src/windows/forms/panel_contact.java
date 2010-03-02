package windows.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.MatteBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import session.Session;
import session.contact;
import session.group;
import socket.packet.handlers.sends.MoveGroup_handler;
import windows.SwingExtendLib.Tree_Renderer;
import windows.actions.buttons.ChangeStatus_button;
import windows.actions.click.chang_avatar;
import windows.actions.click.chang_msgperso;
import windows.actions.click.contact_onclick;
import windows.actions.menus.menubar_changepseudo;

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
	
	private JLabel msgperso;
	private MatteBorder borderafk,borderbusy,borderonline,borderoffline;
	private JLabel image;

	public panel_contact()
	{
		BuildPanel();
	}

	private void BuildPanel()
	{
		setBackground(new Color(128,128,255));
		
		GridBagConstraints gridBagConstraints;

        Titre = new JLabel();
        image = new JLabel();
        changstatus = new JComboBox();
        msgperso = new JLabel();
        Soustitre = new JLabel();
        JScrollPane scrolltree = new JScrollPane();

        setLayout(new GridBagLayout());

        Titre.setText(Session.getPseudo());
        Titre.addMouseListener(new menubar_changepseudo());
        Titre.setToolTipText("Cliquez ici pour changer de pseudo !");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        add(Titre, gridBagConstraints);

        ChangeMyAvatar("avatar.jpg");
        ChangeBorderStatus();
        image.addMouseListener(new chang_avatar());
        image.setToolTipText("Cliquez ici pour changer d'Avatar !");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(image, gridBagConstraints);

        changstatus.setModel(new DefaultComboBoxModel(new String[] { "Online", "Busy", "AFK", "Offline" }));
		changstatus.setSelectedIndex(Session.getStatus()-1);
		changstatus.addActionListener(new ChangeStatus_button(changstatus));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(changstatus, gridBagConstraints);

        msgperso.setText(Session.getPerso_msg());
        msgperso.addMouseListener(new chang_msgperso());
        msgperso.setToolTipText("Cliquez ici pour changer de phrase personnelle !");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(msgperso, gridBagConstraints);

        Soustitre.setText("Liste de vos contacts :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(Soustitre, gridBagConstraints);
		
        SetListContact();
        
		scrolltree.setViewportView(tree);
		scrolltree.setPreferredSize(new Dimension(150,300));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        add(scrolltree, gridBagConstraints);
        
		comm = null;
	}
	
	private void SetListContact()
	{
		GenerateNodes();
		
		new DropTarget(tree, this);
	    dragSource = new DragSource();
	    dragSource.createDefaultDragGestureRecognizer(tree, DnDConstants.ACTION_MOVE, this);
	 
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addMouseListener(new contact_onclick(tree,this));
		tree.setRootVisible(false);
		this.add(tree);
	}
	
	private void GenerateNodes()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Contactlist");
		
		for(group g : Session.getGroups())
		{
			DefaultMutableTreeNode tmp_grp = new DefaultMutableTreeNode(g,true);

			root.add(tmp_grp);
			for(contact ct : g.getContacts())
			{
				DefaultMutableTreeNode tmp_contact = new DefaultMutableTreeNode(ct,false);
				tmp_grp.add(tmp_contact);	
			}
		}
		
		// Construction du modèle de l'arbre.
		DefaultTreeModel myModel = new DefaultTreeModel(root);
		myModel.setAsksAllowsChildren(true);

		// Construction de l'arbre.
		tree = new JTree(myModel);
		tree.setCellRenderer(new Tree_Renderer());
		OpenContactList();
	}
	
	private void OpenContactList()
	{
		for(int i=0;i<Session.getGroups().size();i++)
			tree.expandRow(i);
	}
	
	public void RefreshContactList()
	{
		((DefaultTreeModel) tree.getModel()).reload();
		OpenContactList();
	}

	public void ChangeBorderStatus()
	{
		if (Session.getStatus().equals(0) || Session.getStatus().equals(4))
	    	image.setBorder(borderoffline);
	    else if (Session.getStatus().equals(1))
	    	image.setBorder(borderonline);
	    else if (Session.getStatus().equals(2))
	    	image.setBorder(borderbusy);
	    else if (Session.getStatus().equals(3))
	    	image.setBorder(borderafk);
	}
	
	public void ChangeMyAvatar(String path)
	{
		ImageIcon a = new ImageIcon (path);
	    Image avatar = scale(a.getImage(),80,80);
	    image.setIcon( new ImageIcon(avatar));
	}
	
	public void setComm(form_communicate comm) { this.comm = comm; }
	public form_communicate getComm() { return comm; }
	
	public void ChPseudo(String n_pseudo) {	Titre.setText(n_pseudo); }

	/*
	 * Drag and Drop
	 */
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
	      if(selecContact.getLevel() == 1)
	    	  return;
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
		     if(dropContact.getLevel() == 2)
		    	 return;
		     e.getDropTargetContext().dropComplete(true);
		 }
		 else
		    e.rejectDrop();
	}
	
	public void dragDropEnd(DragSourceDropEvent e) {
		if (e.getDropSuccess()) {
			if (dropContact == null)
		        return;
		    else
		    {
		      ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(selecContact);
			  ((DefaultTreeModel) tree.getModel()).insertNodeInto(selecContact, dropContact, dropContact.getChildCount());
			  MoveGroup_handler pck = new MoveGroup_handler(((contact)selecContact.getUserObject()).getCid(),
					  ((group)dropContact.getUserObject()).getGid());
			  pck.Send();

		    }
		}
	}

	public static Image scale(Image source, int width, int height) {
	    /* On crée une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    /* On dessine sur le Graphics de l'image bufferisée. */
	    Graphics2D g = buf.createGraphics();
	    g.drawImage(source, 10, 10, width, height, null);
	    g.dispose();

	    /* On retourne l'image bufferisée, qui est une image. */
	    return buf;
	}
	
	public void dragExit(DropTargetEvent arg0) {}
	public void dragOver(DropTargetDragEvent arg0) {}
	public void dropActionChanged(DropTargetDragEvent arg0) {}
	public void dragEnter(DragSourceDragEvent dsde) {}
	public void dragExit(DragSourceEvent dse) {}
	public void dragOver(DragSourceDragEvent dsde) {}
	public void dropActionChanged(DragSourceDragEvent dsde) {}
}
