package windows.forms;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import session.Session;
import session.contact;
import windows.actions.buttons.Retablir_button;
import windows.actions.buttons.SendMsg_button;
import windows.actions.keylisteners.follow_keyboard;

public class onglet_communicate extends JPanel{

	private static final long serialVersionUID = 1L;
	private contact ct;
	private JTextArea MainText;
	private MatteBorder borderafk,borderbusy,borderonline,borderoffline;
	private JLabel image,myimage;
	
	
	public onglet_communicate(contact noeud)
	{
		super();
		ct = noeud;
		CreateTab();
	}
	
	public void CreateTab()
	{
		setBackground(new Color(128,128,255));
		CreateBorders();
		
		GridBagConstraints gridBagConstraints;

        image = new JLabel();
        JButton envoi = new JButton();
        JScrollPane MainScroll = new JScrollPane();
        MainText = new JTextArea();
        JScrollPane SendScroll = new JScrollPane();
        JTextArea txt = new JTextArea();
        JLabel TitleText = new JLabel();
        JButton retablir = new JButton();

        setLayout(new GridBagLayout());
        
	    
        ChangeContactAvatar("avatar.jpg");
        ChangeBorderStatus();
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(22, 5, 0, 5);
        add(image, gridBagConstraints);

        envoi.setText("Envoyer !");
        envoi.addActionListener(new SendMsg_button(ct,txt,MainText));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 6, 10, 6);
        add(envoi, gridBagConstraints);

        MainText.setColumns(20);
        MainText.setEditable(false);
        MainText.setLineWrap(true);
        MainText.setRows(5);
        MainText.setAutoscrolls(true);
        MainScroll.setViewportView(MainText);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 200;
        gridBagConstraints.insets = new Insets(20, 6, 10, 6);
        add(MainScroll, gridBagConstraints);

        txt.setColumns(20);
        txt.setLineWrap(true);
        txt.setRows(5);
        txt.setText("\n");
        txt.addKeyListener(new follow_keyboard(ct,txt,MainText));
        SendScroll.setViewportView(txt);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = -35;
        gridBagConstraints.insets = new Insets(10, 0, 20, 0);
        add(SendScroll, gridBagConstraints);

        TitleText.setText("Entrer ici le message à envoyer :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(TitleText, gridBagConstraints);

        retablir.setText("Retablir");
        retablir.addActionListener(new Retablir_button(txt));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new Insets(0, 6, 10, 6);
        add(retablir,gridBagConstraints);

        ChangeMyAvatar("avatar.jpg");
        ChangeMyBorderStatus();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(12, 5, 0, 5);
        add(myimage, gridBagConstraints);
        
        RequestContactAvatar();
	}

	private void RequestContactAvatar() 
	{
		
		
	}

	private void ChangeMyAvatar(String path)
	{
		ImageIcon a2 = new ImageIcon (path);
	    Image avatar2 = scale(a2.getImage(),80,80);
	    myimage = new JLabel( new ImageIcon(avatar2));
	}

	public void ChangeContactAvatar(String path)
	{
		ImageIcon a = new ImageIcon (path);
	    Image avatar = scale(a.getImage(),80,80);
	    image = new JLabel( new ImageIcon(avatar));
	}
	
	public void ChangeContactAvatar(Image img)
	{
		image = new JLabel( new ImageIcon(img));
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
	
	public void ChangeMyBorderStatus()
	{
		if (Session.getStatus().equals(0) || Session.getStatus().equals(4))
			myimage.setBorder(borderoffline);
	    else if (Session.getStatus().equals(1))
	    	myimage.setBorder(borderonline);
	    else if (Session.getStatus().equals(2))
	    	myimage.setBorder(borderbusy);
	    else if (Session.getStatus().equals(3))
	    	myimage.setBorder(borderafk);
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


