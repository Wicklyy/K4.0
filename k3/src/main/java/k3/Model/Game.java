package k3.Model;

import java.util.Vector;



public class Game {
    Pyramid mainPyramid;

    int MAX_PLAYER_SIZE = 4;
    Vector<Player> playerList;
    int currentPlayer=0; // ranges between 0 and playerCount
    gamePhase currentPhase=gamePhase.INIT;
    
    enum gamePhase{// read the diagram
        INIT,BUILD,MAIN,PENALTY,CHANGE
    }

    public void join(Player p){
        assert currentPhase == gamePhase.INIT: "Can't join a game currently playing";
        assert playerList.size()<=4  : "Room is full sorry :/";
        playerList.add(p);
    }
    
    public void start(){
        assert currentPhase == gamePhase.INIT : "Who sent start ??? we're not in init phase";
        for (Player player : playerList) {
            if (!player.ready){
                return;
            }
        }
        //all players are ready
        currentPhase = gamePhase.BUILD;
    }
}
