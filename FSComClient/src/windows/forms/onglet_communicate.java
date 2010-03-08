package windows.forms;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
import socket.packet.handlers.sends.contact_handlers.ReqContactAvatar_handler;
import thread.windowthread;
import windows.SwingExtendLib.SwingEL;
import windows.actions.buttons.Retablir_button;
import windows.actions.buttons.SendMsg_button;
import windows.actions.click.chang_avatar;
import windows.actions.keylisteners.follow_keyboard;

public class onglet_communicate extends JPanel{

	private static final long serialVersionUID = 1L;
	private contact ct;
	private JTextArea MainText;
	private MatteBorder borderafk,borderbusy,borderonline,borderoffline;
	private JLabel image,myimage;
	private String couleur;
	
	public onglet_communicate(contact noeud)
	{
		super();
		ct = noeud;
		ReadSavedVariables();
		CreateTab();
	}
	
	private void ReadSavedVariables()
	{
		couleur = "127,127,255";
		String file = "savedvariables";
		String chaine = "";
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+"\n";
			}
			br.close();
			String tab[]=chaine.split("\n");
			couleur=tab[2];
		}		
		catch (Exception e)
		{
			try 
			{
				FileWriter fw = new FileWriter(file);
				fw.close();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	
	public void CreateTab()
	{
		StringTokenizer st = new StringTokenizer(couleur, ",");
		int fr 	= Integer.parseInt(st.nextToken());
		int fg 	= Integer.parseInt(st.nextToken());
		int fb 	= Integer.parseInt(st.nextToken());
		Color c  	= new Color(fr, fg, fb);

		setBackground(c);
		
		CreateBorders();
		
		GridBagConstraints gridBagConstraints;

        image = new JLabel();
        myimage = new JLabel();
        myimage.addMouseListener(new chang_avatar());
        JButton envoi = new JButton();
        JScrollPane MainScroll = new JScrollPane();
        MainText = new JTextArea();
        JScrollPane SendScroll = new JScrollPane();
        JTextArea txt = new JTextArea();
        JLabel TitleText = new JLabel();
        JButton retablir = new JButton();

        setLayout(new GridBagLayout());
        
	    
        ChangeContactAvatar("avatar.png");
        ChangeBorderStatus();
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
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

        ChangeMyAvatar();
        ChangeMyBorderStatus();
		myimage.setToolTipText("Cliquez ici pour changer d'Avatar !");
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
		ReqContactAvatar_handler pck = new ReqContactAvatar_handler(ct.getCid());
		pck.Send();
	}

	public void ChangeMyAvatar()
	{
		myimage.setIcon(windowthread.getFmConn().getPanContact().getMyImage().getIcon());
	}

	public void ChangeContactAvatar(String path)
	{
		ImageIcon a = new ImageIcon (path);
	    Image avatar = SwingEL.scale(a.getImage());
	    image.setIcon( new ImageIcon(avatar));
	}
	
	public void ChangeContactAvatar(ImageIcon img)
	{
		image.setIcon(img);
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
	
	
}


