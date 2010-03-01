package windows.actions.click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;

import thread.windowthread;
import windows.SwingExtendLib.Image_filter;

public class chang_avatar implements MouseListener {

	private JFileChooser fc;
	
	public chang_avatar(){}
	public void mouseClicked(MouseEvent arg0) 
	{
		if (fc == null) 
		{
            fc = new JFileChooser();
            fc.addChoosableFileFilter(new Image_filter());
            fc.setAcceptAllFileFilterUsed(false);
        }
        int returnVal = fc.showDialog(null,"Choisir");
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File avatar = fc.getSelectedFile();
            windowthread.getFmConn().getPanContact().ChangeMyAvatar(avatar.toString());
            if(windowthread.getFmConn().getPanContact().getComm() != null)
            	windowthread.getFmConn().getPanContact().getComm().ChangeAllMyAvatarsInTab(avatar.toString());
        }
        fc.setSelectedFile(null);
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
