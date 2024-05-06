package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.IOException;

import Global.FileLoader;

import java.awt.*;

public class Bouton
{
    public static JButton creerButton(String text) {
		BoutonArrondi bouton = new BoutonArrondi(text, 20);
		Font police = new Font("Arial", Font.BOLD, 14);
		bouton.setFont(police);
		bouton.setBorder(new LineBorder(Color.BLACK, 2));
		bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bouton.setFocusable(false);
		return bouton;
	}

	public static JButton BoutonMute()
	{
        ImageIcon iconMute = new ImageIcon("res/son.png");
        Image resizedImageMute = iconMute.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        iconMute = new ImageIcon(resizedImageMute);
        return new JButton(iconMute);
	}

	public static JButton BoutonUnMute()
	{
		ImageIcon iconUnMute = new ImageIcon("res/mute.png");
		Image resizedImageUnMute = iconUnMute.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
		iconUnMute = new ImageIcon(resizedImageUnMute);
		return new JButton(iconUnMute);
	}

	public static JButton BoutonRetour()
	{
		// // Charger l'image d'icône
		// try
		// {
		// 	ImageIcon iconRetour = new ImageIcon(FileLoader.getImage("res/retour.png").getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH));
		// 	return new JButton(iconRetour);
		// }
		// catch(IOException e)
		// {
		// 	System.exit(1);
		// }

		ImageIcon inconRetour = new ImageIcon("res/retour.png");
		Image resizedImageRetour = inconRetour.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH);
		inconRetour = new ImageIcon(resizedImageRetour);
		return new JButton(inconRetour);
	}
}
