package windows.actions.menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JColorChooser;

import thread.windowthread;
import windows.forms.form_communicate;

public class menubar_changecolorframe implements ActionListener {
	
	private String autouser,autopass;

	public void actionPerformed(ActionEvent e) {
		Color couleur = JColorChooser.showDialog(null,"Choix de la nouvelle couleur",new Color(128,128,255));
		if(couleur != null)
		{
			ReadSavedVariables();
			windowthread.getFmConn().getPanContact().setBackground(couleur);
			form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
			if(fmCom != null)
				fmCom.ChangeTabbedpanColor(couleur);
			
			String file = "savedvariables";
			try {
				FileWriter fw = new FileWriter (file);
				BufferedWriter bw = new BufferedWriter (fw);
				PrintWriter wfile = new PrintWriter (bw); 
				wfile.println(autouser);
				wfile.println(autopass);
				wfile.println (couleur.getRed()+","+couleur.getGreen()+","+couleur.getBlue());
				wfile.close();
			}
			catch (Exception e1){
				System.out.println(e1.toString());
			}	
		}
	}
	
	private void ReadSavedVariables()
	{
		autouser = "";
		autopass = "";
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
}

