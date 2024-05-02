package Model;

import java.util.ArrayList;
import java.util.Collections;

public class PawnsBag {
    private ArrayList<Cube> PawnsBag;

    public PawnsBag() {
        PawnsBag = new ArrayList<Cube>();
        for (int i=0; i<9; i++){
            PawnsBag.add(Cube.Rouge);
            PawnsBag.add(Cube.Bleu);
            PawnsBag.add(Cube.Vert);
            PawnsBag.add(Cube.Jaune);
            PawnsBag.add(Cube.Noir);
        }
    }

    public boolean count_colors (ArrayList<Cube> cubes){
        boolean Rouge=false , Vert=false, Bleu=false, Noir=false, Jaune=false;
        for (Cube cube : cubes){
            if (!(Rouge && Vert && Bleu && Noir && Jaune)){
                switch (cube){
                    case Rouge :
                        Rouge = true;
                        break;
                    case Vert :
                        Vert=true;
                        break;
                    case Bleu :
                        Bleu=true;
                        break;
                    case Noir :
                        Noir=true;
                        break;
                    default:
                        Jaune=true;
                        break;
                }
            } else {
                return true;
            }
        }
        int count = 0;
        if (Rouge) count++;
        if (Vert) count++;
        if (Bleu) count++;
        if (Noir) count++;
        if (Jaune) count++;

        return count >= 4;
    }

    public ArrayList<Cube> init_center() {
        ArrayList<Cube> cubes = new ArrayList<>();
        while (! count_colors (cubes)){
            for (int i = 0; i < cubes.size(); i++) {
                PawnsBag.add(cubes.remove(0)); // Remettre les pion in the PawnsBag to re shuflle
            }

            Collections.shuffle(PawnsBag); // Mélanger le PawnsBag pour assurer l'aléatoire
            for (int i = 0; i < 9; i++) {
                cubes.add(PawnsBag.remove(0)); // Piocher un pion et le retirer du PawnsBag
            }
        }
        return cubes;
    }

}
