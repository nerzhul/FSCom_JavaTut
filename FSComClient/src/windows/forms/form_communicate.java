package windows.forms;

import java.awt.Dimension;

import javax.swing.JFrame;

import net.infonode.tabbedpanel.Tab;
import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import session.contact;
import windows.actions.buttons.CloseAllConversTabs;
import windows.actions.buttons.CloseButton;

public class form_communicate extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	public TabbedPanel TabPan;
	
	public form_communicate() {
			
		TabPan = new TabbedPanel();

		frame = new JFrame();
		frame.setSize(new Dimension(200,200));
		frame.setTitle("Cookie Messenger - Conversation"); 
		frame.setSize(600,450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	    frame.addWindowListener(new CloseAllConversTabs(frame,TabPan));

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
    		if(ct.getCid() == noeud.getCid())
    			UserFound = true;
    	}
    	
		if(!UserFound){
			TitledTab tab = new TitledTab(noeud.getPseudo(), null, new onglet_communicate(noeud), null );
			tab.setTitleComponent( new CloseButton(tab,frame ) );
			
			TabPan.addTab(tab) ;
			TabPan.setSelectedTab(tab);
		 	frame.add(TabPan);
		 	frame.setVisible(true);
		}
    	
	}
	public JFrame getfram(){
		return this;
	}

}