package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
}
