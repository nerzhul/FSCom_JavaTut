package windows.forms;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import windows.SwingExtendLib.SwingEL;
import windows.actions.click.chang_avatar;
import windows.actions.click.chang_msgperso;
import windows.actions.menus.menubar_a_propos;
import windows.actions.menus.menubar_addcontact;
import windows.actions.menus.menubar_addgroup;
import windows.actions.menus.menubar_changecolorframe;
import windows.actions.menus.menubar_changepseudo;
import windows.actions.menus.menubar_disconnect;
import windows.actions.menus.menubar_inscr;
import windows.actions.menus.menubar_quit;

public class form_master extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JFrame fram;
	private JPanel pan_connect;
	private JPanel pan_contact;
	
	public form_master()
	{
		BuildWindow();
		BuildPanel(1);
	}
	
	private void BuildWindow()
	{
		fram = new JFrame();
		pan_connect = new JPanel();
		fram.setTitle("Cookie Messenger"); 
		fram.setSize(320,550); 
		fram.setLocationRelativeTo(null);
		fram.setResizable(false);
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icone = Toolkit.getDefaultToolkit().getImage("icone.png");
		fram.setIconImage(icone);
		pan_connect = pan_contact = null;
	}
	
	public void BuildPanel(int i) 
	{
		switch(i)
		{
			case 1:
				Menubar_Connect();
				if(pan_contact != null)
					fram.remove(pan_contact);
				
				pan_connect = new panel_connect(this);
				fram.add(pan_connect);
				fram.setVisible(true);
				break;
			case 2:	
				Menubar_contact();
				if(pan_connect != null)
					fram.remove(pan_connect);

				pan_contact = new panel_contact();
				fram.add(pan_contact);
				fram.setVisible(true);
				break;
		}
	}

	private void Menubar_Connect() 
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Menu");
		JMenu menu2 = new JMenu("?");
		
		SwingEL.AddItemToMenuBar(menu1,"Quitter",new menubar_quit());
		SwingEL.AddItemToMenuBar(menu2,"A propos",new menubar_a_propos());
		SwingEL.AddItemToMenuBar(menu1,"Inscription",new menubar_inscr());
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		fram.setJMenuBar(menuBar);
	}
	
	private void Menubar_contact() 
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Menu");
		JMenu menu2 = new JMenu("?");
		JMenu menu3 = new JMenu("Option");
		
		SwingEL.AddItemToMenuBar(menu1,"Quitter",new menubar_quit());
		SwingEL.AddItemToMenuBar(menu3,"Ajouter un contact",new menubar_addcontact());
		SwingEL.AddItemToMenuBar(menu3,"Ajouter un groupe",new menubar_addgroup());
		SwingEL.AddItemToMenuBar(menu3,"Changer de pseudo",new menubar_changepseudo());
		SwingEL.AddItemToMenuBar(menu3,"Changer de message perso",new chang_msgperso());
		SwingEL.AddItemToMenuBar(menu3,"Changer d'avatar",new chang_avatar());
		SwingEL.AddItemToMenuBar(menu1,"Se d�connecter",new menubar_disconnect(this));
		SwingEL.AddItemToMenuBar(menu2,"A propos...",new menubar_a_propos());
		SwingEL.AddItemToMenuBar(menu3, "Changer la couleur de la fen�tre", new menubar_changecolorframe());
		
		menuBar.add(menu1);
		menuBar.add(menu3);
		menuBar.add(menu2);
		fram.setJMenuBar(menuBar);
	}
	
	public panel_contact getPanContact() { return (panel_contact) pan_contact; }
	public panel_connect getPanConnect() { return (panel_connect) pan_connect; }
}
