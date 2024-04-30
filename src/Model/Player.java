package Model;

import java.util.ArrayList;

public class Player{
    Pyramid pyramid;
    ArrayList<Cube> side;
    int noir, bleu, blanc, rouge, jaune, vert, neutre;

    Player(int i){
        pyramid = new Pyramid(i);
        side = new ArrayList<>();
    }
    public void addSide(Cube c){
        side.add(c);
    }

}