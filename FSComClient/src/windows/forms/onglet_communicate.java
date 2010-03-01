package windows.forms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import session.contact;
import windows.actions.buttons.SendMsg_button;
import windows.actions.keylisteners.follow_keyboard;

public class onglet_communicate extends JPanel{

	private static final long serialVersionUID = 1L;
	private contact ct;
	private JTextArea MainText;
	private MatteBorder borderafk,borderbusy,borderonline,borderoffline;
	private JLabel image;
	
	public onglet_communicate(contact noeud)
	{
		super();
		ct = noeud;
		CreateTab();
	}
	
	public void CreateTab()
	{
		setBackground(new Color(128,128,255));
		
		JLabel TitleText = new JLabel ("Entrez ici le texte a envoyer : ");
	    MainText = new JTextArea(15,50);
	    MainText.setEditable(false);
	    MainText.setLineWrap(true); 
	    JTextArea txt = new JTextArea(3,50);
	    txt.setText("\n");
	    txt.addKeyListener(new follow_keyboard(ct,txt,MainText));
	    txt.setLineWrap(true); 
	    JButton envoi = new JButton("Envoyer !");
	    envoi.addActionListener(new SendMsg_button(ct,txt,MainText));
	    
	    ChangeContactAvatar("avatar.jpg");
	    
	    CreateBorders();
	    ChangeBorderStatus();
	    
        add(image);
	    add(MainText);
	    JScrollPane MainScroll = new JScrollPane(MainText);
	    MainScroll.setAutoscrolls(true);
	    add(MainScroll);
	    add(TitleText);
	    add(txt);
	    JScrollPane SendScroll = new JScrollPane(txt);        
	    add(SendScroll);
	    add(envoi);
	}

	public void ChangeContactAvatar(String path)
	{
		ImageIcon a = new ImageIcon (path);
	    Image avatar = scale(a.getImage(),80,80);
	    image = new JLabel( new ImageIcon(avatar));
	}
	
	private void CreateBorders()
	{
		borderafk = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.orange);
	    borderbusy = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red);
	    borderonline = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green);
	    borderoffline = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black);
	}
	
	public void ChangeBorderStatus()
	{
		if (ct.getStatus().equals(0) || ct.getStatus().equals(4))
	    	image.setBorder(borderoffline);
	    else if (ct.getStatus().equals(1))
	    	image.setBorder(borderonline);
	    else if (ct.getStatus().equals(2))
	    	image.setBorder(borderbusy);
	    else if (ct.getStatus().equals(3))
	    	image.setBorder(borderafk);
	}
	
	public void WriteMsg(String msg)
	{
		MainText.setText(MainText.getText() + ct.getPseudo() +" a ecrit : "+ msg + "\n");
	}
	
	public contact GetContact() { return ct; }
	
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
}


