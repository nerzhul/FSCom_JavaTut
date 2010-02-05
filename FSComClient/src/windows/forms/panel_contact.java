package windows.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import session.Session;
import session.objects.group;
import windows.actions.buttons.changestatus_button;
import windows.actions.click.contact_onclick;

public class panel_contact extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel Titre;
	private JLabel Soustitre;
	private JTree  tree;
	private JComboBox changstatus;
	private form_communicate comm;

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
		     
		// Construction du modèle de l'arbre.
		DefaultTreeModel myModel = new DefaultTreeModel(root);
		 
		// Construction de l'arbre.
		tree = new JTree(myModel);
		
		/*
		// Construction d'un afficheur par défaut.
		DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();
		 
		// Changement de l'icône pour les feuilles de l'arbre.
		myRenderer.setLeafIcon(new ImageIcon("pageIcon.png"));
		// Changement de l'icône pour les noeuds fermés.
		myRenderer.setClosedIcon(new ImageIcon("closedBookIcon.png"));
		// Changement de l'icône pour les noeuds ouverts.
		myRenderer.setOpenIcon(new ImageIcon("openBookIcon.png"));
		 
		// Application de l'afficheur à l'arbre.
		myTree.setCellRenderer(myRenderer);
		*/

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
}
