package windows.actions.click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;

import misc.Log;

public class chang_avatar implements MouseListener {

	private JFileChooser fc;
	public void mouseClicked(MouseEvent arg0) 
	{
		if (fc == null) 
		{
            fc = new JFileChooser();
            fc.addChoosableFileFilter(new Filtre_image());
            fc.setAcceptAllFileFilterUsed(false);
        }
        int returnVal = fc.showDialog(null,"Choisir");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File avatar = fc.getSelectedFile();
            Log.outError(avatar.toString());
            /* TO DO : 
             * envoi au serveur ou je ne sais pas quoi de "avatar" !!
            */
        }
        fc.setSelectedFile(null);
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
