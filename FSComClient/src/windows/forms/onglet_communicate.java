package windows.forms;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import session.contact;

import windows.actions.buttons.sendmsg_button;
import windows.actions.keylisteners.follow_keyboard;

public class onglet_communicate extends JPanel{

	private static final long serialVersionUID = 1L;
	private contact ct;
	public onglet_communicate(contact noeud){
		super();
		ct=noeud;
		CreateTab();
	}
	
	public void CreateTab(){
		setLayout(new FlowLayout());
		setBackground(new Color(128,128,255));
		setLayout(new FlowLayout(FlowLayout.CENTER,200,10));

		JLabel TitleText = new JLabel ("Entrez ici le texte a envoyer : ");
	    JTextArea MainText = new JTextArea(15,50);
	    MainText.setEditable(false);
	    MainText.setLineWrap(true); 
	    JTextArea txt = new JTextArea(3,50);
	    txt.setText("\n");
	    txt.addKeyListener(new follow_keyboard(ct,txt,MainText));
	    txt.setLineWrap(true); 
	    JButton envoi = new JButton("Envoyer !");
	    envoi.addActionListener(new sendmsg_button(ct,txt,MainText));

	    add(MainText);
	    JScrollPane MainScroll = new JScrollPane(MainText);
	    add(MainScroll);
	    add(TitleText);
	    add(txt);
	    JScrollPane SendScroll = new JScrollPane(txt);        
	    add(SendScroll);
	    add(envoi);
	}

	public contact GetContact(){
		return ct;
	}
}