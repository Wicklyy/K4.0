package View;

import Model.Jeu;
import Patterns.Observateur;
import javax.swing.*;
import java.awt.*;

public class NiveauGraphique extends JComponent implements Observateur{
    int width_fenetre, height_fenetre, nb_ligne, nb_colonne, largeur_case, hauteur_case;
    JFrame frame;
    Jeu jeu;

    NiveauGraphique(Jeu jeu)
    {
        this.jeu = jeu;
        nb_colonne = jeu.colonne();
        nb_ligne = jeu.ligne();
        
    }

    @Override
	public void miseAJour()
    {
        nb_colonne = jeu.colonne();
        nb_ligne = jeu.ligne();
		repaint();
	}

    public int Largeur_Fenetre()
    {
        return width_fenetre;
    }

    public int Hauteur_Fenetre()
    {
        return height_fenetre;
    }

    public int Largeur_case()
    {
        return largeur_case;
    }

    public int Hauteur_case()
    {
        return hauteur_case;
    }
    

    public void Dessiner_plateau()
    {
  
    }


    public void paintComponent(Graphics g)
    {
	
    }
}
