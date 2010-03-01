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

import session.contact;
import windows.actions.buttons.Retablir_button;
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
		
		/*JLabel TitleText = new JLabel ("Entrez ici le texte a envoyer : ");
	    MainText = new JTextArea(15,50);
	    MainText.setEditable(false);
	    MainText.setLineWrap(true); 
	    JTextArea txt = new JTextArea(3,50);
	    txt.setText("\n");
	    txt.addKeyListener(new follow_keyboard(ct,txt,MainText));
	    txt.setLineWrap(true); 
	    JButton envoi = new JButton("Envoyer !");
	    envoi.addActionListener(new SendMsg_button(ct,txt,MainText));
	    
	    ImageIcon a = new ImageIcon ("avatar.jpg"); //modif par l'image
	    Image avatar = scale(a.getImage(),80,80);
	    image = new JLabel( new ImageIcon(avatar));
	    image.setText(ct.getPseudo());
	    
	    
	    
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
	    
	    /***********************************************************************/
	    GridBagConstraints gridBagConstraints;

        image = new JLabel();
        JButton envoi = new JButton();
        JScrollPane MainScroll = new JScrollPane();
        MainText = new JTextArea();
        JScrollPane SendScroll = new JScrollPane();
        JTextArea txt = new JTextArea();
        JLabel TitleText = new JLabel();
        JButton retablir = new JButton();
        JLabel imagemoi = new JLabel();

        setLayout(new GridBagLayout());
        CreateBorders();
	    ChangeBorderStatus();
        ImageIcon a = new ImageIcon ("avatar.jpg"); //modif par l'image
	    Image avatar = scale(a.getImage(),80,80);
	    image = new JLabel( new ImageIcon(avatar));
	    
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

        ImageIcon a2 = new ImageIcon ("avatar.jpg"); //modif par l'image
	    Image avatar2 = scale(a2.getImage(),80,80);
	    imagemoi = new JLabel( new ImageIcon(avatar2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(12, 5, 0, 5);
        add(imagemoi, gridBagConstraints);
        
        
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
	    /* On cr�e une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    /* On dessine sur le Graphics de l'image bufferis�e. */
	    Graphics2D g = buf.createGraphics();
	    g.drawImage(source, 10, 10, width, height, null);
	    g.dispose();

	    /* On retourne l'image bufferis�e, qui est une image. */
	    return buf;
	}
}


