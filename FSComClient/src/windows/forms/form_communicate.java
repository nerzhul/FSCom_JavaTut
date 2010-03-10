package windows.forms;

/*
 * Fenetre pouvant contenir des panels onglets
 */
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.infonode.tabbedpanel.Tab;
import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import session.contact;
import windows.actions.buttons.CloseAllConversTabs_button;
import windows.actions.buttons.CloseButton_button;

public class form_communicate extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	public TabbedPanel TabPan;
	
	public form_communicate() 
	{
		TabPan = new TabbedPanel();

		frame = new JFrame();
		frame.setTitle("Cookie Messenger - Conversation"); //titre de la fenetre
		frame.setSize(800,530);//taille de la fenetre
		frame.setLocationRelativeTo(null);//position de la fenetre (au milieu)
		frame.setResizable(false);//n'est pas redimmensionnable
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //aucune action lorsque on click sur la croix
	    frame.addWindowListener(new CloseAllConversTabs_button(frame,TabPan));//mais un listener qui fermera les onglets avant de fermer la fenetre
	}
	
	//fonction d'ajout d'onglet a la fenetre
	public void AddTab(contact noeud)
	{
		boolean UserFound = false;
		Tab onglet;
		int nbonglet = TabPan.getTabCount();
		//on cherche dans tt les onglets pour savoir si le contact n'a pas déja un onglet
    	for(int i=0;i<nbonglet;i++)
    	{
    		onglet = TabPan.getTabAt(i);
    		contact ct = ((onglet_communicate) onglet.getContentComponent()).GetContact();
    		if(ct.getCid().equals(noeud.getCid()))
    			UserFound = true;
    	}
    	
    	//si il n'y a pas d'onglet on en créait un
		if(!UserFound)
		{
			//avec dans le nom, le pseudo du contact
			TitledTab tab = new TitledTab(noeud.getPseudo(), null, new onglet_communicate(noeud), null );
			tab.setTitleComponent( new CloseButton_button(tab,frame ) );//un petit icone de fermeture
			TabPan.addTab(tab) ;
			TabPan.setSelectedTab(tab);
			((onglet_communicate)TabPan.getTabAt(TabPan.getTabCount()-1).getContentComponent()).RequestContactAvatar();//on recupère l'avatar du contact
		 	frame.add(TabPan);//on ajoute les onglets a la fenetre
		 	frame.setVisible(true);
		}
	}
	
	//fonction pour connaitre l'onglet attribué a un contact
	public onglet_communicate GetContactConvers(Integer _uid)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//on parcours tous les onglets
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))//quand on trouve le contact on recupere les composants de l'onglet
    			return (onglet_communicate)TabPan.getTabAt(i).getContentComponent();
		}
		return null;
	}
	
	//fonction pour changer le statut d'un contact dans l'onglet
	public void ChangeConversStatusForContact(Integer _uid)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//on parcourt tt les onglets
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))//et quand on trouve le contact on change son cadre de statut
    		{
    			((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).ChangeBorderStatus();
    			return;
    		}
		}
	}
	
	//fonction permettant le changement du "titre" de l'onglet (qui correspond au pseudo)
	public void ChangeConversTabTitle(Integer _uid, String name)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//parcourt des onglets
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))//et quand on trouve le contact
    		{
    			((TitledTab)TabPan.getTabAt(i)).setText(name);//on change le pseudo
    			return;
    		}
		}
	}
	
	//fonction pour le changement de la couleur de la fenetre (a partir du menu principal)
	public void ChangeTabbedpanColor(Color bg)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//on parcourt et on change la couleur dans chaque onglet
			((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).setBackground(bg);
	}
	
	//fonction pour changer le cadre représentant le statut de l'utilisateur dans chaque onglet
	public void ChangeAllMyStatusBorder()
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//on parcourt les onglets et on chnage son cadre dans chaque onglet
			((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).ChangeMyBorderStatus();
	}

	//fonction pour changer son avatar dans chaque onglet
	public void ChangeAllMyAvatarsInTab(String path)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//on parcourt tt les onglets et on change son avatar dans chaque onglet
			((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).ChangeMyAvatar();
	}
	
	//fonction pour changer l'avatar de son contact dans l'onglet
	public void ChangeContactAvatar(Integer _uid, ImageIcon img) 
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)//on parcourt les onglets
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))//quand on trouve le contact
    		{
    			((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).ChangeContactAvatar(img);
    			//on change son avatar
    			return;
    		}
		}
	}
	
	//fonction pour fermer la fenetre
	public void closepan(){
		frame.dispose();
	}
}
