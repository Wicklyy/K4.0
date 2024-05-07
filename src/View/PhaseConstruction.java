package View;

import Model.Jeu;
import Model.Pyramid;
import Model.Cube;
import Patterns.Observateur;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Math;
import java.net.Proxy;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileNotFoundException;

public class PhaseConstruction
{
    JFrame frame;
    JButton Aide;
    CollecteurEvenements controle;
    Graphics2D drawable;
    Image neutre, bleu, vert, jaune, noir, blanc, rouge;

    public PhaseConstruction(JFrame frame, CollecteurEvenements controle){
        this.frame = frame;
        this.controle = controle;
        try {
			InputStream in = new FileInputStream("res/carre_bois.png");
			neutre = ImageIO.read(in);
            in = new FileInputStream("res/carre_bleu.png");
			bleu = ImageIO.read(in);
            in = new FileInputStream("res/carre_vert.png");
			vert = ImageIO.read(in);
            in = new FileInputStream("res/carre_jaune.png");
			jaune = ImageIO.read(in);
            in = new FileInputStream("res/carre_noir.png");
			noir = ImageIO.read(in);
            in = new FileInputStream("res/carre_blanc.png");
			blanc = ImageIO.read(in);
            in = new FileInputStream("res/carre_rouge.png");
			rouge = ImageIO.read(in);

		} catch (FileNotFoundException e) {
			System.err.println("ERREUR : impossible de trouver le fichier du pousseur");
			System.exit(2);
		} catch (IOException e) {
			System.err.println("ERREUR : impossible de charger l'image");
			System.exit(3);
		}
    }

    public void fonction_globale(Jeu jeu, Graphics g, int width_fenetre, int height_fenetre){
        Pyramid pyr = jeu.getPlayer(0).getPyramid();
        System.out.println("fonction_globale");
        //drawable.drawImage(bleu, 0, 0, 50, 50, null);
        //dessiner_pyramide_joueur(g, pyr, 0, 0, width_fenetre, height_fenetre);
    }

    public void 

    public void dessiner_pyramide_joueur(Graphics g, Pyramid pyr, int x_gauche, int y_haut, int x_droite, int y_bas){
        Graphics2D drawable = (Graphics2D) g;
        int hauteur = y_bas - y_haut;
        int largeur = x_droite - x_gauche;
        int taille_cube = Math.min(largeur / 6, hauteur / 6); //base de 6 pour 2 joueurs, 5 pour 3 et 4 pour 4
        Cube cube;
        
        //System.out.println("premier print");
        for(int ligne = 0; ligne < 6; ligne++){
            for(int col = 0; col < 6; col++){
                // drawable.drawImage(bleu, col*50, ligne*50, 50, 50, null);



                //System.out.println("premier print");
                // System.out.println(x_gauche + col + (int)0.5*ligne);
                // System.out.println(ligne);
                // System.out.println(taille_cube);
                // System.out.println()

                // drawable.drawLine(0, 0, 200, 200);
                // drawable.drawImage(bleu, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                // System.out.println("deuxieme print");

                cube = pyr.get(ligne, col);
                
                
                switch (cube){
                    case Noir:
                    System.out.println("noir");
                        drawable.drawImage(noir, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    case Bleu:
                    System.out.println("bleu");
                        drawable.drawImage(bleu, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    case Blanc:
                    System.out.println("blanc");
                        drawable.drawImage(blanc, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    case Rouge:
                    System.out.println("rouge");
                        drawable.drawImage(rouge, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    case Jaune:
                    System.out.println("jaune");
                        drawable.drawImage(jaune, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    case Vert:
                    System.out.println("vert");
                        drawable.drawImage(vert, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    case Neutre:
                    System.out.println("neutre");
                        drawable.drawImage(neutre, x_gauche + col + (int)0.5*ligne, ligne, taille_cube, taille_cube, null);
                        break;
                    default:
                        break;
                
                }
                
            }
            System.out.println("construction !!!!!!!!");
        }
    }


}