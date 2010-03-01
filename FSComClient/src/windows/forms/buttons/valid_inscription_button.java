package windows.forms.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import misc.Log;

public class valid_inscription_button implements ActionListener {

	private JFrame frame2;
	private JFormattedTextField idtxt2;
	private JPasswordField pass,pass2;
	public valid_inscription_button(JFrame frame, JFormattedTextField idtxt,
			JPasswordField passtxt, JPasswordField passtxt2) { 
		frame2=frame;
		idtxt2 = idtxt;
		pass=passtxt;
		pass2=passtxt2;
	}

	public void actionPerformed(ActionEvent arg0) {
		String id = idtxt2.getText().trim();
		String passtxt = new String(pass.getPassword());
		String passtxt2 = new String(pass2.getPassword());
		if (id != null && !id.equalsIgnoreCase("")){
			//TO DO : identifiant déja dans la base !
			Log.outError("identifiant OK");
		}else{
			JOptionPane.showMessageDialog(null,"Identifiant incorrect");
		}
			
		if(passtxt != null && passtxt2!=null && passtxt.equals(passtxt2) && passtxt.length()>4 && !passtxt.equals(id))
		{
			Log.outError("password OK");
		}else{
			JOptionPane.showMessageDialog(null,"Password incorrect");
		}
		/*TO DO :
		* 	ajout dans la base + popup qui dit que tout est OK + femrmeture de la fenetre
		*/

	}

}
