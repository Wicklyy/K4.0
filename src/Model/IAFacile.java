package Model;
import java.util.*;
import java.io.*;

public class IAFacile extends IA {

    

    public ArrayList<int[]> coupIA(Plateau p, boolean joueur1){
        ArrayList<int[]> resultat_ok = new ArrayList<>();
        ArrayList<int[]> resultat_ko = new ArrayList<>();
        
        for(int i=0; i<p.hauteur; i++){
            for(int j=0; j<p.longueur; j++){
                if(p.exist(i,j)){
                    Plateau clone = p.clone();
                    int[] pos = new int[2];
                    pos[0] = i;
                    pos[1] = j;
                    int value= MinMaxIA(clone, 2, joueur1);
                    if(value==0){
                        resultat_ok.add(pos);
                    }
                    else if(value==1){
                        resultat_ok.clear();
                        resultat_ok.add(pos);
                        return resultat_ok;
                    }
                    else{
                        resultat_ko.add(pos);
                    }
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
