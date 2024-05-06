package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Player{
    Pyramid pyramid;

    ArrayList<Cube> side, personalBag;

    int noir, bleu, blanc, rouge, jaune, vert, neutre;
    int size;
    boolean loss;

    Player(int i){
        pyramid = new Pyramid(i);
        size = i;
        side = new ArrayList<>();
        personalBag = new ArrayList<>();
    }
    
    public int getSize(){
        return size;
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
    public ArrayList<Cube> getSide(){
        return side;
    }

    public int getSideSize(){
        return side.size();

    }

    public void set(int x, int y, Cube c){
        Cube cube = get(x, y);
        decrement(cube);
        pyramid.set(x,y,c);
        increment(c);
    }

    public void removeSide(int x){
        decrement(side.remove(x));
    }

    public Pyramid getPyramid(){
        return pyramid;
    }


    /* FONCTION AJOUTER */
    public void addBag(Cube cube){
        personalBag.add(cube);
    }

    public boolean bagEmpty(){
        return personalBag.isEmpty();
    }
    
    public int getBagSize(){
        return personalBag.size();
    }

    public void melange(){
        Collections.shuffle(personalBag);
    }

    public int totalCube(){
        return noir + bleu + blanc + rouge + jaune + vert + neutre;
    }
    public void playerLost(){
        loss = true;
    }

    public void ajoute(int x, int y, int emplacement){
        if(!(get(x, y) == Cube.Vide)){
            personalBag.add(get(x, y));
        }
        //System.out.println(personalBag.remove(emplacement));
        set(x, y, personalBag.remove(emplacement));
    }
    @Override
    public String toString(){
        String chaine = "Noir: "+ noir + "     Bleu: " + bleu + "     Blanc: "+ blanc + "     Rouge: " + rouge +"\nJaune: " + jaune +"     Vert: " + vert + "      Neutre: " + neutre + "\n";
        chaine +="Bag: ";
        int nb = 0;
        for(Cube cube : personalBag){
            chaine+= nb + ":" + cube + " ";
            nb++;
        }
        chaine +="\nSide: ";
        nb = 0;
        for( Cube cube : side ){
            chaine+= nb + ":" + cube + " ";
            nb++;
        }
        chaine+="\nPyramide:\n" + pyramid;

        return chaine;
    }


    



}
