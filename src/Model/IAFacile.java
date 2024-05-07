package Model;
import java.util.*;
import java.io.*;
import java.awt.Point;

public class IAFacile extends IA {

    

    public ArrayList<ArrayList<Point>> coupIA(Jeu j, boolean joueur1){
        ArrayList<ArrayList<Point>> resultat_ok = new ArrayList<>();
        ArrayList<ArrayList<Point>> resultat_ko = new ArrayList<>();
        int value_max= -100000;
        ArrayList<Point> cubes_access = j.Accessible_Playable();
        for(Point depart : cubes_access){
            ArrayList<Point> coups_jouables = j.CubeAccessibleDestinations(j.getPlayer().get((int) depart.getX(),(int) depart.getY()));
            for(Point arrivee : coups_jouables){
                ArrayList<Point> pos = new ArrayList<>();
                pos.add(depart);
                pos.add(arrivee);
                Jeu clone = new Jeu(2);
                clone = j.clone();
                int value= MinMaxIA(clone, 2, 0, -10000, +10000, 0);
                value_max = Math.max(value,value_max);
                if(value==value_max){
                    resultat_ok.add(pos);
                }
                else if(value==1000){
                    resultat_ok.clear();
                    resultat_ok.add(pos);
                    return resultat_ok;
                }
                else{
                    resultat_ko.add(pos);
                }
            }
        }
        if(resultat_ok.size()==0){
            return resultat_ko;
        }
        return resultat_ok;
    }

    @Override
    public void joue(){
        ArrayList<int[]> coups_possibles = coupIA(plateau, true);
        Random random = new Random();
        int[] coup_a_jouer = coups_possibles.get(random.nextInt(coups_possibles.size()));
        plateau.joue(coup_a_jouer[0],coup_a_jouer[1]);
    }
}
