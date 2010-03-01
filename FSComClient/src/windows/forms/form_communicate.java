package windows.forms;

import java.awt.Dimension;

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
		frame.setSize(new Dimension(200,200));
		frame.setTitle("Cookie Messenger - Conversation"); 
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	    frame.addWindowListener(new CloseAllConversTabs_button(frame,TabPan));
	}
	
	public void AddTab(contact noeud)
	{
		boolean UserFound = false;
		Tab onglet;
		int nbonglet = TabPan.getTabCount();
    	for(int i=0;i<nbonglet;i++)
    	{
    		onglet = TabPan.getTabAt(i);
    		contact ct = ((onglet_communicate) onglet.getContentComponent()).GetContact();
    		if(ct.getCid().equals(noeud.getCid()))
    			UserFound = true;
    	}
    	
		if(!UserFound)
		{
			TitledTab tab = new TitledTab(noeud.getPseudo(), null, new onglet_communicate(noeud), null );
			tab.setTitleComponent( new CloseButton_button(tab,frame ) );
			TabPan.addTab(tab) ;
			TabPan.setSelectedTab(tab);
		 	frame.add(TabPan);
		 	frame.setVisible(true);
		}
	}
	
	public onglet_communicate GetContactConvers(Integer _uid)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))
    			return (onglet_communicate)TabPan.getTabAt(i).getContentComponent();
		}
		return null;
	}
	
	public void ChangeConversStatusForContact(Integer _uid)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))
    		{
    			((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).ChangeBorderStatus();
    			return;
    		}
		}
	}
	
	public void ChangeConversTabTitle(Integer _uid, String name)
	{
		int nbonglet = TabPan.getTabCount();
		for(int i=0;i<nbonglet;i++)
		{
			contact ct = ((onglet_communicate) TabPan.getTabAt(i).getContentComponent()).GetContact();
    		if(ct.getCid().equals(_uid))
    		{
    			((TitledTab)TabPan.getTabAt(i)).setText(name);
    			return;
    		}
		}
	}
}
