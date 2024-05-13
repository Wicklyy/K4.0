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

    public int MinMaxIA(Jeu j,int depth, int player_max, int alpha, int beta, int IA){
        int value;
        boolean bon_joueur = player_max == j.getCurrent();
        if(j.End_Game()){ //Condition de défaite de tous les autres joueurs en même temps à implémenter
                                             //Besoin d'une fonctione auxiliaire qui permet lors de tests de si un joueur a perdu de l'exclure du calcul
                                             //Et appeler l'IA avec les nouveaux paramètres.
            if(j.check_loss()){
                return -1000;
            }else{
                return 1000;
            }
        }
        if (depth == 0){
            if(IA == 0){current_player, current_
                if(bon_joueur){
                    return j.getPlayer().TotCubesHand() - j.getPlayer(j.next_player()).TotCubesHand();
                }
                else{
                    return j.getPlayer(j.next_player()).TotCubesHand() - j.getPlayer().TotCubesHand();
                }
            }
        }
        ArrayList<Point> cubes_access = j.Accessible_Playable();
        if(bon_joueur){
            value = -1000;
            for(Point depart : cubes_access){
                ArrayList<Point> coups_jouables = j.CubeAccessibleDestinations(j.getPlayer().get((int) depart.getX(),(int) depart.getY()));
                for(Point arrivee : coups_jouables){
                    j.add_central_pyramid((int) arrivee.getX(), (int) arrivee.getY(), (int) depart.getX(),(int) depart.getY());
                    value = Math.max(value,MinMaxIA(j,depth-1,player_max, alpha, beta, IA));
                    if(alpha >= value){
                        return value;
                    }
                    beta = Math.min(beta,value);
                }
            }
        }
        else{
            value = 1000;
            for(Point depart : cubes_access){
                ArrayList<Point> coups_jouables = j.CubeAccessibleDestinations(j.getPlayer().get((int) depart.getX(),(int) depart.getY()));
                for(Point arrivee : coups_jouables){
                    j.add_central_pyramid((int) arrivee.getX(), (int) arrivee.getY(), (int) depart.getX(),(int) depart.getY());
                    value = Math.min(value,MinMaxIA(j,depth-1,player_max, alpha, beta, IA));
                    if(beta <= value){
                        return value;
                    }
                    alpha = Math.max(alpha,value);
                }
            }
        }
        return value;
    }

    void add_central_pyramid() {
        throw new UnsupportedOperationException();
    }
}
