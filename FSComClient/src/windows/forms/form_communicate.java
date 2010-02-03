package windows.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import session.objects.contact;

import windows.actions.buttons.sendmsg_button;
import windows.actions.keylisteners.follow_keyboard;

public class form_communicate extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	public JTabbedPane TabPan;
	private JPanel pane;
	private JPanel panel;
	
	public form_communicate() {
			
		TabPan = new JTabbedPane();
		
		frame = new JFrame();
		frame.setSize(new Dimension(200,200));
		frame.setTitle("Cookie Messenger - Conversation"); 
		frame.setSize(600,450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		}
	
	public void AddTab(contact noeud)
	{
		panel = onglet(noeud);
		TabPan.add(noeud.toString(),panel);
	 	frame.add(TabPan);
	 	frame.setVisible(true);
	}
	
	public JPanel onglet(contact noeud){
		pane = new JPanel();
		pane.setLayout(new FlowLayout());
		pane.setBackground(new Color(128,128,255));
		pane.setLayout(new FlowLayout(FlowLayout.CENTER,200,10));

		JLabel Titretxt = new JLabel ("Entrez ici le texte a envoyer : ");
	    JTextArea textprincipal = new JTextArea(15,50);
	    textprincipal.setEditable(false);
	    textprincipal.setLineWrap(true); 
	    JTextArea txt = new JTextArea(3,50);
	    txt.addKeyListener(new follow_keyboard(noeud,txt,textprincipal));
	    txt.setLineWrap(true); 
	    JButton envoi = new JButton("Envoyer !");
	    envoi.addActionListener(new sendmsg_button(noeud,txt,textprincipal));

	    pane.add(textprincipal);
	    JScrollPane scrollprincipal = new JScrollPane(textprincipal);
	    pane.add(scrollprincipal);
	    pane.add(Titretxt);
	    pane.add(txt);
	    JScrollPane scrollenvoi = new JScrollPane(txt);        
	    pane.add(scrollenvoi);
	    pane.add(envoi);
		return pane;    
	}
}
