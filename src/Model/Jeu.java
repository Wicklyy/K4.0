package Model;

import java.util.Random;

public class Jeu {
    Player[] players;
    int nbJoueur;
    Pyramid principale;
    PawnsBag bag;
    int current_player;

    Jeu(int nb){
        nbJoueur = nb;
        players = new Player[nb];
        bag = new PawnsBag();
        int y = 0;
        for( Cube cube : bag.init_center()){
            principale.set(0, y, cube);
            y++;
        }
        for(int i = 0;i < nb; i++ ){
            players[i] = new Player(8-nb);
            if(nb == 4){
                players[i].addSide(Cube.Blanc);
            }
        }
        Random r = new Random();
        current_player = r.nextInt(nb);
    }

    //CALLED ONLY AFTER/IN VALIDITY CHECK !!!
    public boolean check_penality(Cube cube, int x, int y) {
        return principale.get(x-1, y)==cube && principale.get(x-1, y+1)==cube;
    }

    //MOVE VALIDITY :
    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int move_validity(Cube cube, int x, int y){
        if (principale.get(x, y) == Cube.Vide){
            if (cube==Cube.Neutre || principale.get(x-1, y)==Cube.Neutre || principale.get(x-1, y+1)==Cube.Neutre || principale.get(x-1, y)==cube || principale.get(x-1, y+1)==cube){
                if (check_penality(cube, x, y)){
                    return 2;
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean accessible(int x, int y){
        Pyramid pyramid = players[current_player].getPyramid();
        return accessible(pyramid , x, y);
    }

    public boolean accessible(Pyramid pyramid , int x, int y){
        return pyramid.get(x+1, y)== Cube.Vide && (y==0 || pyramid.get(x+1, y-1)== Cube.Vide);
    }
    
    //Next player out of those still in the game
    public int next_player(int current_player){
        int next_player = (current_player+1)%nbJoueur;
        while ( players[next_player].lost() == true ){
            next_player = (next_player+1)%nbJoueur;
        }
        return next_player;
    }

    public void avance(){
        current_player = next_player(current_player);    
    }

    //Determine the previous player out of those still in the game
    public int previous_player(int current_player){
        int previous_player = (current_player + nbJoueur - 1) % nbJoueur;
        while(players[previous_player].lost() == true){
            previous_player = (previous_player + nbJoueur - 1) % nbJoueur;
        }
        return previous_player;
    }
    //Penality token from current player's pyramid
    public void takePenaltyCubeFromPyramid(int x,int y) {
        players[previous_player(current_player)].addSide(players[current_player].get(x,y));
        players[current_player].set(x,y,Cube.Vide);
    }

    //Penality token from current player's side
    public void takePenaltyCubeFromSide(int x) {
        players[previous_player(current_player)].addSide(players[current_player].getSide(x));
        players[current_player].removeSide(x);
    }
    
    // 0 -> NOT VALID
    // 1 -> VALID
    // 2 -> VALID WITH PENALITY
    public int add_central_pyramid(int x_central, int y_central, int x_player, int y_player){
        Cube cube = players[current_player].get(x_player, y_player);
        int valid = move_validity(cube, x_central, y_central);
        if(valid != 0){
            players[current_player].set(x_player, y_player, Cube.Vide);
            principale.set(x_central, y_central, cube);
        }
        return valid;
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
}