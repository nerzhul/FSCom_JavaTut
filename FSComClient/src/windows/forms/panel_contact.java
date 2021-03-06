package windows.forms;

import java.awt.Color;
import java.awt.Dimension;
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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
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
import session.events;
import session.group;
import socket.packet.handlers.sends.client_handlers.AvatarSender_handler;
import socket.packet.handlers.sends.group_handlers.MoveGroup_handler;
import windows.SwingExtendLib.SwingEL;
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
	private JScrollPane scrolltree;
	private form_communicate comm;

	private DragSource dragSource = null;
	private DefaultMutableTreeNode selecContact = null;
	private DefaultMutableTreeNode dropContact = null;
	
	private JLabel msgperso;
	private MatteBorder borderafk,borderbusy,borderonline,borderoffline;
	private JLabel image;
	private String couleur;
	
	private GridBagConstraints gridBagConstraints;

	public panel_contact()
	{
		ReadSavedVariables();
		BuildPanel();
	}

	private void ReadSavedVariables()
	{
		couleur = "127,127,255";
		String file = "savedvariables";
		String chaine = "";
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+"\n";
			}
			br.close();
			String tab[]=chaine.split("\n");
			couleur=tab[2];
		}		
		catch (Exception e)
		{
			try 
			{
				FileWriter fw = new FileWriter(file);
				fw.close();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
	private void BuildPanel()
	{

		StringTokenizer st = new StringTokenizer(couleur, ",");
		int fr 	= Integer.parseInt(st.nextToken());
		int fg 	= Integer.parseInt(st.nextToken());
		int fb 	= Integer.parseInt(st.nextToken());
		Color c  	= new Color(fr, fg, fb);

		setBackground(c);
	
        Titre = new JLabel();
        image = new JLabel();
        changstatus = new JComboBox();
        msgperso = new JLabel();
        Soustitre = new JLabel();
        scrolltree = new JScrollPane();
        CreateBorders();
        
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

        ChangeMyAvatar("avatar.png",false);
        ChangeBorderStatus();
        image.addMouseListener(new chang_avatar());
        image.setToolTipText("Cliquez ici pour changer d'Avatar !");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(image, gridBagConstraints);

        changstatus.setModel(new DefaultComboBoxModel(new String[] { "Online", "Busy", "Idle", "Offline" }));
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
		
		scrolltree.setViewportView(tree);
		scrolltree.setPreferredSize(new Dimension(200,300));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        add(scrolltree, gridBagConstraints);
        OpenContactList();
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
		
		// Construction du mod�le de l'arbre.
		DefaultTreeModel myModel = new DefaultTreeModel(root);
		myModel.setAsksAllowsChildren(true);

		// Construction de l'arbre.
		tree = new JTree(myModel);
		tree.setCellRenderer(new Tree_Renderer());
		
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

	public void HardRefreshContactList()
	{
		DefaultTreeModel model = ((DefaultTreeModel) tree.getModel());
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		root.removeAllChildren();
		model.reload();
		SetListContact();
		tree.updateUI();
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
	
	private void CreateBorders()
	{
		borderafk = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.orange);
	    borderbusy = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red);
	    borderonline = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green);
	    borderoffline = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black);
	}
	
	public void ChangeMyAvatar(String path, boolean save)
	{
		ImageIcon a = new ImageIcon (path);
	    Image avatar;
	    if(save)
	    	avatar = SwingEL.scale(a.getImage());
	    else
	    	avatar = SwingEL.scaleWithoutSave(a.getImage());
	    image.setIcon( new ImageIcon(avatar));
	    
	    AvatarSender_handler pck = new AvatarSender_handler(new ImageIcon(avatar));
	    pck.Send();
	}
	
	public void setComm(form_communicate comm) { this.comm = comm; }
	public form_communicate getComm() { return comm; }
	public JLabel getMyImage() { return image; }
	
	public void ChPseudo(String n_pseudo) {	Titre.setText(n_pseudo); }
	public void ChPPers(String n_perso) { msgperso.setText(n_perso); }

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
			  events.ContactChangeGroup(((contact)selecContact.getUserObject()),((group)dropContact.getUserObject()).getGid());

		    }
		}
	}
	
	public JTree recup(){
		return tree;
	}
	
	public void dragExit(DropTargetEvent arg0) {}
	public void dragOver(DropTargetDragEvent arg0) {}
	public void dropActionChanged(DropTargetDragEvent arg0) {}
	public void dragEnter(DragSourceDragEvent dsde) {}
	public void dragExit(DragSourceEvent dse) {}
	public void dragOver(DragSourceDragEvent dsde) {}
	public void dropActionChanged(DragSourceDragEvent dsde) {}
}
