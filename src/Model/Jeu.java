package Model;

import java.util.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class Jeu implements Cloneable{
    Player[] players;
    public int nbJoueur;
    public Pyramid principale;
    PawnsBag bag;
    public int current_player, size;
    boolean End;



    public Jeu(int nb){             /*tout fonctionn bien */
        nbJoueur = nb;
        End = false;              
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

    public Jeu clone() throws CloneNotSupportedException {
        Jeu clone = (Jeu) super.clone();  // Clone the basic object structure
        
        clone.players = new Player[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            clone.players[i] = players[i].clone();
        }
        clone.principale = principale.clone();
        clone.bag = bag.clone();

        return clone;
    }

    //CALLED ONLY AFTER/IN VALIDITY CHECK !!!           /* fonctionne */
    public boolean check_penality(Cube cube, int x, int y) {
        return principale.get(x-1, y) == principale.get(x-1, y+1);
    }

    //MOVE VALIDITY :
    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int move_validity(Cube cube, int x, int y){          /* bonne validitee renvoyee */
        if ( sameColor(principale.get(x, y), Cube.Vide) && check_under(x,y) && (sameColor(principale.get(x-1, y),cube) || ( sameColor(principale.get(x-1, y+1),cube))) ){
            if (check_penality(cube, x, y)){
                return 2;
            }
            return 1;
        }
        else {return 0;}
    }

    public boolean check_under(int x, int y){
        return !sameColor(principale.get(x-1, y),Cube.Vide) && !sameColor(principale.get(x-1, y+1),Cube.Vide);
    }

    public boolean sameColor(Cube c1,Cube c2){
        return (c1 == c2) || (c1 == Cube.Neutre) || (c2 == Cube.Neutre);
    }

    public boolean accessible(int x, int y){                /* bon resultat */
        Pyramid pyramid = players[current_player].getPyramid();
        return accessible(pyramid , x, y);
    }

    public boolean accessible(Pyramid pyramid , int x, int y){
        return (pyramid.get(x, y) != Cube.Vide) && (( x == size-1 && y == 0  ) || (( y == size-x-1 || pyramid.get(x+1, y) == Cube.Vide) && (y == 0 || pyramid.get(x+1, y-1)== Cube.Vide)));
    }
    
    public ArrayList<Point> AccessibleCubesPlayer(){            /* Fonctionne mais crash?(tres rarement)*/
        ArrayList<Point> list = new ArrayList<Point>();
        for (int i=players[current_player].getSize()-1; i>=0; i--){
            for (int j=0; j<players[current_player].getSize()-i; j++){
                if (accessible(i,j)){
                    Point p = new Point(i, j);
                    list.add(p);
                }
            }
        }
        return list;
    }

    //COORD POSITION POSSIBLES POUR UN CUBE DONNEE
    public ArrayList<Point> CubeAccessibleDestinations(Cube cube){ 
        ArrayList<Point> list = new ArrayList<Point>;
        for (i=0;i<principale.getsize();i++){
            for(j=0;j<principale.getsize();j++){
                if (move_validity(cube,i,j)!=0){
                    Point p = new Point(i,j);
                    list.add(p);
                }
            }
        }
        return list;
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
    public int next_player(){               /* Fonctionne */
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
    public void takePenaltyCubeFromPyramid(int x,int y) {               /*Fonctionne */
        players[previous_player()].addSide(players[current_player].get(x,y));
        players[current_player].set(x,y,Cube.Vide);
    }

    //Penality token from current player's side
    public void takePenaltyCubeFromSide(int x) {            /* Fonctionne */
        players[previous_player()].addSide(players[current_player].getSide(x));
        players[current_player].removeSide(x);
    }

    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int add_central_pyramid(int x_central, int y_central, int x_player, int y_player){   /*Fonctionne */
        if(accessible(x_player, y_player)){
            Cube cube = players[current_player].get(x_player, y_player);
            int valid = move_validity(cube, x_central, y_central);
            if(valid != 0){
                players[current_player].set(x_player, y_player, Cube.Vide);
                principale.set(x_central, y_central, cube);
            }
            return valid;
        }
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
        return End;
    }



    /* NOUVELLE FONCTION AJOUTEEEEEE */
    public boolean check_loss(){
        if(noPlay() || getPlayer().totalCube() == 0){
            getPlayer().playerLost();
            int next = next_player();
            if(next == next_player(next)){End = true;}
            return true;
        }
        return false;
    }

    public boolean noPlay(){
        return Accessible_Playable().size==0;
    }

    public ArrayList<Point> Accessible_Playable(){
        HashMap<Cube,Boolean> list = accessibleColors();
        ArrayList<Point> Aksel = new ArrayList<Point>;

        for(Point e : AccessibleCubesPlayer()){
            Cube cube = getPlayer().get(e.x, e.y);
            if(cube == Cube.Blanc || cube == Cube.Neutre || list.get(cube) != null){
                Aksel.add(e);
            }
        }
        int x = 0;
        for(Cube c : getPlayer().getSide()){
            if(c == Cube.Blanc || c == Cube.Neutre || list.get(c) != null){
                Point p = new Point(x, -1);
                Aksel.add(p);
            }
            x++;
        }
        return Aksel;
    }

    public HashMap<Cube,Boolean> accessibleColors(){
        HashMap<Cube,Boolean> list = new HashMap<>();
        for(int i = principale.getSize()-1; i >= 0; i--){
            for(int j = 0; j < principale.getSize()-i; j++){
                if(possible(i, j)){
                    list.put(principale.get(i, j), true);
                }
            }
        }
        return list;
    }

    public boolean possible(int x, int y){
        if( principale.get(x, y) != Cube.Vide && ((principale.get(x+1, y) == Cube.Vide) || ((y != principale.getSize() - 1 - x) && principale.get(x+1, y) == Cube.Vide)) ){return true;}
        return false;
    }

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