package windows.actions.click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;

import thread.windowthread;
import windows.SwingExtendLib.Image_filter;

/*
 * Action sur le click sur l'avatar (ou en passant par le menu) pour changer d'avatar
 */
public class chang_avatar implements MouseListener, ActionListener {

	private JFileChooser fc;
	
	public void mouseClicked(MouseEvent arg0) 
	{
		changavatar();
	}
	
	public void actionPerformed(ActionEvent e) {
		changavatar();
	}
	
	public void changavatar(){
		//on créait et ouvre un explorateur de fichier
		if (fc == null) 
		{
            fc = new JFileChooser();
            fc.addChoosableFileFilter(new Image_filter());//avec un filtre pour ne choisir que les images
            fc.setAcceptAllFileFilterUsed(false);
        }
        int returnVal = fc.showDialog(null,"Choisir");
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {//si on valide une image on change notre avatar
            File avatar = fc.getSelectedFile();
            windowthread.getFmConn().getPanContact().ChangeMyAvatar(avatar.toString(),true);//dans la page principale
            if(windowthread.getFmConn().getPanContact().getComm() != null)
            	windowthread.getFmConn().getPanContact().getComm().ChangeAllMyAvatarsInTab(avatar.toString());//et dans les conversations
        }
        fc.setSelectedFile(null);
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
