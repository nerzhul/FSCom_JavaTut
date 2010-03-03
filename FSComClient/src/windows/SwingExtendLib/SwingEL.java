package windows.SwingExtendLib;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
	    return buf;
	}
}
