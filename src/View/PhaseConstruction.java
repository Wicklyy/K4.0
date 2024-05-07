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
import java.util.ArrayList;

public class PhaseConstruction
{
    JFrame frame;
    JButton Aide;
    CollecteurEvenements controle;
    Graphics2D drawable;
    Image neutre, bleu, vert, jaune, noir, blanc, rouge, vide;
    Jeu jeu;

    public PhaseConstruction(JFrame frame, CollecteurEvenements controle, Jeu jeu){
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
            in = new FileInputStream("res/carre_vide.png");
			vide = ImageIO.read(in);

		} catch (FileNotFoundException e) {
			System.err.println("ERREUR : impossible de trouver le fichier du pousseur");
			System.exit(2);
		} catch (IOException e) {
			System.err.println("ERREUR : impossible de charger l'image");
			System.exit(3);
		}

        while(jeu.draw())
        {
        }
    }

    public void fonction_globale(Jeu jeu, Graphics g, int width_fenetre, int height_fenetre){
        Pyramid pyr = jeu.getPlayer(0).getPyramid();
        dessiner_cubes_pioches(g, jeu, width_fenetre, height_fenetre);
        dessiner_pyramide_centrale(g, jeu, width_fenetre, height_fenetre);
        dessiner_pyramide_joueur(g, jeu, width_fenetre, height_fenetre);
        //dessiner_pyramide_joueur(g, pyr, 0, 0, width_fenetre, height_fenetre);
    }

    public int max_nb(int[] tab){
        int max = tab[0];
        for (int i = 0; i<7; i++){
            if (tab[i] > max){
                max = tab[i];
            }
        }
        return max;
    }

    public void dessiner_cubes_pioches(Graphics g, Jeu jeu, int width_fenetre, int height_fenetre){
        drawable = (Graphics2D) g;
        int debut_zone_haut = height_fenetre / 10;

        int fin_zone_gauche = (int)(0.95 * width_fenetre);
        
        int nb_couleurs[] = jeu.compte_personnal_bag();
        // Cube c;
        int nb;
        int max = max_nb(nb_couleurs);

        int taille_cube = 9*width_fenetre / (4*max*10);
        // int taille_cube = Math.min(9*width_fenetre / (4*max*10), 9*height_fenetre / (7*10)); //(4*3)
        // int taille_cube = Math.min(9*width_fenetre / (4*max*10), height_fenetre*8/10);
        int y=0;
        for (int i=0; i<7; i++){
            nb = nb_couleurs[i];
            if (nb > 0){
                y++;
            }
            for(int x=0; x<nb; x++){
                switch(i){
                    case 0:
                        drawable.drawImage(noir, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);
                        break;
                    case 1:
                        drawable.drawImage(neutre, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);                    
                        break;
                    case 2:
                        drawable.drawImage(blanc, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);   
                        break;
                    case 3:
                        drawable.drawImage(vert, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);                      
                        break;
                    case 4:
                        drawable.drawImage(jaune, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);                     
                        break;
                    case 5:
                        drawable.drawImage(rouge, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);                   
                        break;
                    case 6:
                        //drawable.drawImage(bleu, fin_zone_gauche - x*(taille_cube+taille_cube/10), y*(taille_cube+taille_cube/10) + debut_zone_haut, taille_cube, taille_cube, null);                    
                        drawable.drawImage(bleu, fin_zone_gauche - x*(taille_cube), y*(taille_cube) + debut_zone_haut, taille_cube*95/100, (int)taille_cube*95/100, null);                    
                        break;
                }
                
            }   
        }
        
    }

    public void dessiner_pyramide_centrale(Graphics g, Jeu jeu, int width_fenetre, int height_fenetre){
        drawable = (Graphics2D) g;
        int taille_cube = height_fenetre/12;
        int debut_zone_haut = height_fenetre * 85/100;
        int taille_pyramide = (taille_cube+taille_cube/10)*9;
        int taille_zone = width_fenetre*3/4;
        int debut_zone_gauche = (taille_zone - taille_pyramide) / 2;

        for (int i=0; i<9; i++){
            switch(jeu.principale.get(0, i)){
                case Noir:
                // System.out.println("noir");
                    drawable.drawImage(noir, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                case Bleu:
                // System.out.println("bleu");
                    drawable.drawImage(bleu, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                case Blanc:
                // System.out.println("blanc");
                    drawable.drawImage(blanc, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                case Rouge:
                // System.out.println("rouge");
                    drawable.drawImage(rouge, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                case Jaune:
                // System.out.println("jaune");
                    drawable.drawImage(jaune, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                case Vert:
                // System.out.println("vert");
                    drawable.drawImage(vert, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                case Neutre:
                // System.out.println("neutre");
                    drawable.drawImage(neutre, debut_zone_gauche+(taille_cube+taille_cube/10)*i, debut_zone_haut, taille_cube, taille_cube, null);
                    break;
                default:
                    break;
            }
        }
    }

    public void dessiner_pyramide_joueur(Graphics g, Jeu jeu, int width_fenetre, int height_fenetre){
        drawable = (Graphics2D) g;
        int taille_cube = height_fenetre/12;
        int debut_zone_bas = height_fenetre*7/10;
        int debut_zone_haut = height_fenetre/10;
        int taille_pyramide = (taille_cube+taille_cube/10)*6;
        int taille_zone = width_fenetre*3/4;
        int debut_zone_gauche = (taille_zone - taille_pyramide) / 4;
        int x_haut, y_haut;
        // int y=0;
        for (int x=0; x<6; x++){
            for (int y = 0; y<=x; y++){
                // System.out.print(y + " ");
                x_haut = debut_zone_haut + x*(taille_cube + taille_cube/10);
                y_haut = debut_zone_gauche + x*(taille_cube + taille_cube/10)/2 + (taille_cube + taille_cube/10)*(6-y);
                System.out.print(y_haut + " ");
                drawable.drawImage(vide, y_haut, x_haut, taille_cube, taille_cube, null);
            }
            System.out.println("");
        }
    }
    /*
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
    */


}