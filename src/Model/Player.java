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
    public void addSide(Cube c){
        side.add(c);
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
        switch (cube) {
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
        pyramid.set(x,y,c);
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

    public void removeSide(int x){
        side.remove(x);
    }

    public Pyramid getPyramid(){
        return pyramid;
    }
    
    @Override
    public String toString(){
        String chaine = "";
        chaine = pyramid + "\n";
        for( Cube cube : side ){
            switch (cube) {
                case Noir:
                    chaine += "Noir ";
                    break;
                case Bleu:
                    chaine += "Bleu ";
                    break;
                case Blanc:
                    chaine += "Blan ";
                    break;
                case Rouge:
                    chaine += "Roug ";
                    break;
                case Jaune:
                    chaine += "Jaun ";
                    break;
                case Vert:
                    chaine += "Vert ";
                    break;
                case Neutre:
                    chaine += "Neut ";
                    break;
                case Vide:
                    chaine += "Vide ";
                    break;
                default:
                    break;
            }
        }

        return chaine;
    }

}