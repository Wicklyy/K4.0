package Model;

import java.util.*;
import java.awt.Point;

public abstract class IA {
    Jeu jeu;

    public static IA nouvelle(Jeu j) {
        IA resultat = null;
        String type = "Aleatoire";

        switch (type) {
            case "Aleatoire":
                resultat = new IAAleatoire();
                break;
            case "Facile":
                resultat = new IAFacile();
                break;
            case "Medium":
                resultat = new IAMedium();
                break;
            case "Difficile":
                resultat = new IADifficile();
                break;
        }
        if (resultat != null) {
            resultat.jeu = j;
        }
        return resultat;
    }

    public int MinMaxIA(Jeu j,int depth, boolean player1, int alpha, int beta){
        int value;
        if(j.End_Game()){ //Condition de défaite de tous les autres joueurs en même temps à implémenter
                                             //Besoin d'une fonctione auxiliaire qui permet lors de tests de si un joueur a perdu de l'exclure du calcul
                                             //Et appeler l'IA avec les nouveaux paramètres.
            if(player1){
                return -1000;
            }else{
                return 1000;
            }
        }
        if (depth == 0){
            return 0;
        }
        ArrayList<Point> cubes_access = j.AccessibleCubesPlayer();
        if(player1){
            value = -1000;
            for(Point cube : cubes_access){
                value = Math.max(value,MinMaxIA(j,depth-1,!player1, alpha, beta));
                if(alpha >= value){
                    return value;
                }
                beta = Math.min(beta,value);
            }
        }
        else{
            value = 1000;
            for(Point cube : cubes_access){
                value = Math.min(value,MinMaxIA(j,depth-1,!player1,alpha,beta));
                if(beta <= value){
                    return value;
                }
                alpha = Math.max(alpha,value);
            }
        }
        return value;
    }

    void joue() {
        throw new UnsupportedOperationException();
    }
}
