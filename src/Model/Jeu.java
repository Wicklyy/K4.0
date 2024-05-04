package Model;

import java.util.Random;
import java.util.ArrayList;
import java.awt.Point;

public class Jeu {
    Player[] players;
    public int nbJoueur;
    public Pyramid principale;
    PawnsBag bag;
    public int current_player, size;

    public Jeu(int nb){             /*tout fonctionn bien */
        nbJoueur = nb;              
        players = new Player[nb];
        bag = new PawnsBag(nb);
        principale = new Pyramid(9);
        int y = 0;
        for( Cube cube : bag.init_center()){        /*petit soucis ici de temps en temps bug bizarre qui fait une index out of bound 9 pour aucune raison */
            principale.set(0, y, cube);
            y++;
        }
        for(int i = 0;i < nb; i++ ){
            size = 8-nb;
            players[i] = new Player(size);
            if(nb!=4){
                for(int j = 0; j < 4/nb; j++){
                    players[i].addBag(Cube.Blanc);
                    players[i].addBag(Cube.Neutre);
                    
                }
                if(nb == 3){players[i].addBag(Cube.Neutre);}
            }
            else{
                players[i].addSide(Cube.Blanc);
                players[i].addBag(Cube.Neutre);
            }
        }
        Random r = new Random();
        current_player = r.nextInt(nb);
    }

    //CALLED ONLY AFTER/IN VALIDITY CHECK !!!           /* fonctionne */
    public boolean check_penality(Cube cube, int x, int y) {
        return x != y && principale.get(x-1, y) == principale.get(x-1, y+1);
    }

    //MOVE VALIDITY :
    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int move_validity(Cube cube, int x, int y){          /* bonne validitee renvoyee */
        if ( sameColor(principale.get(x, y), Cube.Vide) && (sameColor(principale.get(x-1, y),cube) || ( y != (size - x) && sameColor(principale.get(x-1, y+1),cube)))){
            if (check_penality(cube, x, y)){
                return 2;
            }
            return 1;
            }
        else {return 0;}
    }

    public boolean sameColor(Cube c1,Cube c2){
        return (c1 == c2) || (c1 == Cube.Neutre) || (c2 == Cube.Neutre);
    }

    public boolean accessible(int x, int y){                /* bon resultat */
        Pyramid pyramid = players[current_player].getPyramid();
        return accessible(pyramid , x, y);
    }

    public boolean accessible(Pyramid pyramid , int x, int y){
        return (pyramid.get(x, y) != Cube.Vide) && ( x == size-1 && y == 0  ) || (pyramid.get(x+1, y) == Cube.Vide && (y==0 || pyramid.get(x+1, y-1)== Cube.Vide));
    }
    
    public ArrayList<Point> AccessibleCubesPlayer(){
        ArrayList<Point> list = new ArrayList<Point>();
        for (int i=0; i<players[current_player].getSize(); i++){
            for (int j=0; j<players[current_player].getSize(); j++){
                if (accessible(i,j)){
                    Point p = new Point(i, j);
                    list.add(p);
                }
            }
        }
        return list;
    }
    
    //Next player out of those still in the game
    public int next_player(){           
        return next_player(current_player);
    }

    public int next_player(int current_player){
        int next_player = (current_player+1)%nbJoueur;
        while ( players[next_player].lost() == true ){
            next_player = (next_player+1)%nbJoueur;
        }
        return next_player;
    }

    public void avance(){           /* le bon joueur est envoyer */
        current_player = next_player();    
    }

    //Determine the previous player out of those still in the game
    public int previous_player(){
        return previous_player(current_player);
    }

    public int previous_player(int current_player){
        int previous_player = (current_player + nbJoueur - 1) % nbJoueur;
        while(players[previous_player].lost() == true){
            previous_player = (previous_player + nbJoueur - 1) % nbJoueur;
        }
        return previous_player;
    }
    //Penality token from current player's pyramid
    public void takePenaltyCubeFromPyramid(int x,int y) {
        players[previous_player()].addSide(players[current_player].get(x,y));
        players[current_player].set(x,y,Cube.Vide);
    }

    //Penality token from current player's side
    public void takePenaltyCubeFromSide(int x) {
        players[previous_player()].addSide(players[current_player].getSide(x));
        players[current_player].removeSide(x);
    }
    
    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int add_central_pyramid(int x_central, int y_central, int x_player, int y_player){
        if(accessible(x_player, y_player)){
        Cube cube = players[current_player].get(x_player, y_player);
        int valid = move_validity(cube, x_central, y_central);
        if(valid != 0){
            players[current_player].set(x_player, y_player, Cube.Vide);
            principale.set(x_central, y_central, cube);
        }
        return valid;}
        return 0;
    }


    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int add_central_side(int x_central, int y_central, int x_player){
        Cube cube = players[current_player].getSide(x_player);
        int valid = move_validity(cube, x_central, y_central);
        if(valid != 0){
            players[current_player].removeSide(x_player);
            principale.set(x_central, y_central, cube);
        }
        return valid;
    }
    

    public boolean End_Game(){
        int count_survivors = 0;
        for(int i = 0;i < nbJoueur; i++ ){
            if (players[i].lost() == false){
                count_survivors = count_survivors + 1;
            }
        }
        return (count_survivors==1);
    }



    /* NOUVELLE FONCTION AJOUTEEEEEE */
    public Player getPlayer(int i){
        return players[i];
    }

    public Player getPlayer(){
        return getPlayer(current_player);
    }

    public void setPlayer(int x, int y, Cube cube){
        players[current_player].set(x, y, cube);
        avance();
    }

    public boolean draw(){
        if(bag.getSize() > 2){
            for(Cube c : bag.draw()){
                players[current_player].addBag(c);
            }
            avance();
            return true;
        }
        return false;
    }

    public void ajoute(int x, int y, int emplacement){
        players[current_player].ajoute(x, y, emplacement);
    }
    
    public String bag(){
        return bag.toString();
    }

}