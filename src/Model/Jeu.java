package Model;

import java.util.Random;
import Patterns.Observable;

public class Jeu extends Observable{
    Boolean action,victoire;
    int joueur,ligne,colonne;

    public Jeu(int i, int j){           /*Constructeur */
        ligne = i;
        colonne = j;
        joueur = new Random().nextInt(2);
        action = false;
        victoire = false;
        metAJour();
    }

    public int ligne()
    {
        return ligne;
    }

    public int colonne()
    {
        return colonne;
    }


    public void renitialiser(){         /*Renitiialisation */
        joueur = new Random().nextInt(2);
        action = false;
        victoire = false;
        metAJour();
    }
    public void renitialiser(int x, int y){         /*Renitiialisation */
        joueur = new Random().nextInt(2);
        action = false;
        victoire = false;
        ligne = x;
        colonne = y;
        metAJour();
    }
    public void ajoute_colonne(){
        renitialiser(ligne,colonne+1);
    }

    public void ajoute_ligne(){
        renitialiser(ligne+1,colonne);
    }
    public void supprime_ligne(){
        if(ligne > 2){
            renitialiser(ligne-1, colonne);
        }
    }
    public void supprime_colonne(){
        if(colonne > 2){
            renitialiser(ligne, colonne-1);
        }
    }


    public void joue(int i,int j){               

    }

}