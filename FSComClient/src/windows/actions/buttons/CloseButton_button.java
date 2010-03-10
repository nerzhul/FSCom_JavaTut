package windows.actions.buttons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.infonode.tabbedpanel.titledtab.TitledTab;

/*
 * Action sur le click de l'icone de fermeture d'un onglet
 */
	public class CloseButton_button extends JLabel
	{
		private static final long serialVersionUID = 184842104821L;
		
		public CloseButton_button(final TitledTab tab, JFrame frame)
		{
			super(new ImageIcon("./close.png" ));//définition de l'icone
			final JFrame convers = frame;
			addMouseListener( new MouseAdapter()
			{
				//on met un petit effet quand on passe la souris sur l'icone
				public void mouseExited(MouseEvent e)
				{
					setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 0) );
				}
				public void mouseEntered(MouseEvent e)
				{
					setBorder( BorderFactory.createEtchedBorder() );
				}

				public void mouseClicked(MouseEvent e)
				{
					if(tab.getTabbedPanel().getTabCount()>1)//puis on fermer l'onglet courant
						tab.getTabbedPanel().removeTab( tab );
					else
					{
						tab.getTabbedPanel().removeTab( tab );//et si c'etait le dernier onglet on ferme la fenetre
						convers.dispose();
					}
				}
				
			} );
		}
	}
