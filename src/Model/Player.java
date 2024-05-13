package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Cloneable{
    Pyramid pyramid;

    ArrayList<Cube> side, personalBag;
    int[] totalCube;
    int noir, bleu, blanc, rouge, jaune, vert, neutre;
    int size;
    boolean loss;

    Player(int i){
        pyramid = new Pyramid(i);
        size = i;
        totalCube = new int[7];
        side = new ArrayList<>();
        personalBag = new ArrayList<>();
    }

    public Player clone() throws CloneNotSupportedException {
        Player clone = (Player) super.clone();  // Clone the basic object structure

        clone.pyramid = pyramid.clone();
        clone.side = new ArrayList<>(side.size());
        for (Cube cube : side) {
          clone.side.add(cube);  // Add existing cube references
        }
        clone.personalBag = new ArrayList<>(personalBag.size());
        for (Cube cube : personalBag) {
          clone.personalBag.add(cube);  // Add existing cube references
        }
        return clone;
    }

    public int totalCube (){
        return ColourAmmount(Cube.Vide);
    }

    //CUBE VIDE => Total of all colours
    public int ColourAmmount (Cube cube){
        int total = 0;
        switch(cube){
            case Noir:
                return totalCube[0];
            case Neutre:
                return totalCube[1];
            case Blanc:
                return totalCube[2];
            case Vert:
                return totalCube[3];
            case Jaune:
                return totalCube[4];
            case Rouge:
                return totalCube[5];
            case Bleu:
                return totalCube[6];
            default:
                for(int i = 0; i < 7; i++){
                    total+=totalCube[i];
                }
                return total;
        }
    }

    public int getSize(){
        return size;
    }

    public void increment(Cube c){
        switch (c) {
            case Noir:
                totalCube[0]++;
                break;
            case Neutre:
                totalCube[1]++;
                break;
            case Blanc:
                totalCube[2]++;
                break;
            case Vert:
                totalCube[3]++;
                break;
            case Jaune:
                totalCube[4]++;
                break;
            case Rouge:
                totalCube[5]++;
                break;
            case Bleu:
                totalCube[6]++;
                break;
            default:
                break;
        }
    }

    public void decrement(Cube c){
        switch (c) {
            case Noir:
                totalCube[0]--;
                break;
            case Neutre:
                totalCube[1]--;
                break;
            case Blanc:
                totalCube[2]--;
                break;
            case Vert:
                totalCube[3]--;
                break;
            case Jaune:
                totalCube[4]--;
                break;
            case Rouge:
                totalCube[5]--;
                break;
            case Bleu:
                totalCube[6]--;
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
        increment(cube);
        personalBag.add(cube);
    }

    public boolean bagEmpty(){
        return personalBag.isEmpty();
    }

    public int getBagSize(){
        return personalBag.size();
    }

    public int[] compte_personnal_bag(){
        return totalCube;
    }

    public void melange(){
        Collections.shuffle(personalBag);
    }

    public void playerLost(){
        loss = true;
    }

    public void construction(int x, int y,Cube cube){
        if(!(get(x, y) == Cube.Vide)){
            personalBag.add(get(x, y));
        }
        //System.out.println(personalBag.remove(emplacement));
        personalBag.remove(cube);
        pyramid.set(x,y,cube);
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
