package windows.forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.MaskFormatter;

import windows.actions.buttons.cancel_inscription_button;
import windows.actions.buttons.valid_inscription_button;

public class form_inscription extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	public form_inscription()
	{
		frame = new JFrame();
		frame.setTitle("Cookie Messenger - Inscription"); 
		frame.setSize(300,250);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		panel();
	}
	
	public void panel()
	{
		GridBagConstraints gridBagConstraints;
		JPanel pane = new JPanel();
        JLabel Titre = new JLabel();
        JButton valider = new JButton();
        JButton annuler = new JButton();
        JPasswordField passtxt = new JPasswordField();
        JPasswordField passtxt2 = new JPasswordField();
        JLabel pass = new JLabel();
        JLabel pass2 = new JLabel();
        MaskFormatter Mask10 = null;
		try {
			Mask10 = new MaskFormatter("*****************");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        JLabel identifiant = new JLabel();
        JFormattedTextField idtxt = new JFormattedTextField(Mask10);

        pane.setLayout(new GridBagLayout());

        Titre.setText("Bienvenue sur la page d'inscription !");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new Insets(16, 4, 16, 4);
        pane.add(Titre, gridBagConstraints);

        valider.setText("Valider");
        valider.addActionListener(new valid_inscription_button(idtxt,passtxt,passtxt2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        pane.add(valider, gridBagConstraints);

        annuler.setText("Annuler");
        annuler.addActionListener(new cancel_inscription_button(frame));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        pane.add(annuler, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        passtxt.setToolTipText("Doit être différent de l'identifiant et comporter au minimum 4 caractères !");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        pane.add(passtxt, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        passtxt2.setToolTipText("Doit être égal au mot de passe entré précédemment !");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        pane.add(passtxt2, gridBagConstraints);

        pass.setText("Mot de passe :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        pane.add(pass, gridBagConstraints);

        pass2.setText("Confirmation :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        pane.add(pass2, gridBagConstraints);

        identifiant.setText("Identifiant :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        pane.add(identifiant, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        idtxt.setToolTipText("Maximum 15 caractères !");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 152;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pane.add(idtxt, gridBagConstraints);
        
        frame.add(pane);
        pane.setVisible(true);
	}
}
