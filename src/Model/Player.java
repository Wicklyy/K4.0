package Model;

import java.util.ArrayList;

public class Player{
    Pyramid pyramid;
    ArrayList<Cube> side;
    int noir, bleu, blanc, rouge, jaune, vert, neutre;
    boolean loss;

    Player(int i){
        pyramid = new Pyramid(i);
        side = new ArrayList<>();
    }

    public void increment(Cube c){
        switch (c) {
            case Noir:
                noir++;
                break;
            case Bleu:
                bleu++;
                break;
            case Blanc:
                blanc++;
                break;
            case Rouge:
                rouge++;
                break;
            case Jaune:
                jaune++;
                break;
            case Vert:
                vert++;
                break;
            case Neutre:
                neutre++;
                break;
            default:
                break;
        }
    }

    public void decrement(Cube c){
        switch (c) {
            case Noir:
                noir--;
                break;
            case Bleu:
                bleu--;
                break;
            case Blanc:
                blanc--;
                break;
            case Rouge:
                rouge--;
                break;
            case Jaune:
                jaune--;
                break;
            case Vert:
                vert--;
                break;
            case Neutre:
                neutre--;
                break;
            default:
                break;
        }
    }

    public void addSide(Cube c){
        side.add(c);
        increment(c);
    }

    public boolean lost(){
        return loss;
    }
    public Cube get(int x, int y){
        return pyramid.get(x, y);
    }

    public Cube getSide(int x){
        return side.get(x);
    } 

    public void set(int x, int y, Cube c){
        Cube cube = get(x, y);
        decrement(cube);
        pyramid.set(x,y,c);
        increment(c);
    }

    public void removeSide(int x){
        side.remove(x);
        decrement(side.get(x));
    }

    public Pyramid getPyramid(){
        return pyramid;
    }

    @Override
    public String toString(){
        String chaine = "Noir: "+noir+"\tBleu: "+ bleu + "\tBlanc: "+ blanc + "\nRouge: " + rouge +"\tJaune: " + jaune +"\tVert: " + vert + "\tNeutre: " + neutre + "\n";
        chaine += pyramid + "\nSide: ";
        for( Cube cube : side ){
            chaine+=cube + " ";
        }
        chaine+="\n" + pyramid;

        return chaine;
    }



}