package windows.forms;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import windows.actions.buttons.Connect_button;

public class panel_connect extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel Titre;
	private JLabel labeluser;
	private JLabel labelpass;
	private JTextField mail;
	private JPasswordField password;
	private JButton connect;
	private JComboBox status;
	private JCheckBox save;
	private String autouser,autopass;
	private form_inscription frmInsc;
	private String couleur;
	
	public panel_connect(form_master formFram)
	{
		ReadSavedVariables();
		BuildFenetre();
	}

	private void ReadSavedVariables()
	{
		autouser = "";
		autopass = "";
		couleur = "128,128,255";
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
				autouser=tab[0];
				autopass=tab[1];
				if(!tab[2].equalsIgnoreCase("null"))
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
	
	private void BuildFenetre(){

		setLayout(new FlowLayout());
		setBackground(new Color(128,128,255));
		setLayout(new FlowLayout(FlowLayout.CENTER,200,10));

		JLabel Image = new JLabel(new ImageIcon("icone.png"),JLabel.CENTER);
		Titre=new JLabel("Bienvenue dans Cookie Messenger",JLabel.CENTER);
		labeluser = new JLabel("Identifiant");
		mail = new JTextField(autouser,15);
		labelpass = new JLabel("Mot de passe");
		password = new JPasswordField(autopass,15);
		status= new JComboBox();
		
		status.addItem("Online");
		status.addItem("Busy");
		status.addItem("Idle");
		status.addItem("Offline");
		save = new JCheckBox("Enregistrer les infos");
		if(autouser!="" && autopass !="")
			save.setSelected(true);
		connect=new JButton("Connexion");
		connect.addActionListener(new Connect_button(status,mail,password,save,couleur));

		add(Titre);
		add(Image);
		add(labeluser);
		add(mail);
		add(labelpass);
		add(password);
		add(status);
		add(save);		
		add(connect);
	}
	
	public void setFmInsc(form_inscription insc) { frmInsc = insc; }
	public form_inscription getFmInsc() { return frmInsc; }
}
