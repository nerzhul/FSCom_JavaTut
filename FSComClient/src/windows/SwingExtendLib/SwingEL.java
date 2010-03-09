package windows.SwingExtendLib;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import misc.Log;

public class SwingEL 
{
	public SwingEL() {}
	
	public static void AddItemToMenu(JPopupMenu pop, String txt, ActionListener lst)
	{
		JMenuItem ji = new JMenuItem(txt);
		ji.addActionListener(lst);
		pop.add(ji);
	}
	
	public static void AddItemToMenuBar(JMenu pop, String txt, ActionListener lst)
	{
		JMenuItem ji = new JMenuItem(txt);
		ji.addActionListener(lst);
		pop.add(ji);
	}
	
	public static Image scale(Image source) {
	    /* On crée une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);

	    /* On dessine sur le Graphics de l'image bufferisée. */
	    Graphics2D g = buf.createGraphics();
	    g.drawImage(source, 0, 0, 80, 80,null);
	    g.dispose();

	    /* On retourne l'image bufferisée, qui est une image. */
	    SaveImage(buf);
	    return buf;
	}
	
	public static Image scaleWithoutSave(Image source) {
	    /* On crée une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);

	    /* On dessine sur le Graphics de l'image bufferisée. */
	    Graphics2D g = buf.createGraphics();
	    g.drawImage(source, 0, 0, 80, 80,null);
	    g.dispose();

	    /* On retourne l'image bufferisée, qui est une image. */
	    return buf;
	}
	
	public static void SaveImage(BufferedImage bufI)
	{
		File file = new File("avatar.png");
	    try 
	    {
			ImageIO.write(bufI, "png", file);
		} catch (IOException e) {
			Log.outError("Failed to register avatar");
		}

	}
}
