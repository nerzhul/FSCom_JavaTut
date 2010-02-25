package windows.actions.buttons;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.infonode.tabbedpanel.titledtab.TitledTab;

	public class CloseButton_button extends JLabel
	{
		private static final long serialVersionUID = 184842104821L;
		
		public CloseButton_button(final TitledTab tab, JFrame frame)
		{
			super(new ImageIcon("./close.png" ));
			final JFrame convers = frame;
			addMouseListener( new MouseAdapter()
			{
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
					if(tab.getTabbedPanel().getTabCount()>1)
						tab.getTabbedPanel().removeTab( tab );
					else
					{
						tab.getTabbedPanel().removeTab( tab );
						convers.dispose();
					}
				}
				
			} );
		}
	}
