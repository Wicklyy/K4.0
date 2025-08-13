package k3.Model;

import java.util.Random;
import java.util.Vector;
import java.util.random.*;;



public class Game {
    Pyramid mainPyramid;

    private int MAX_PLAYER_SIZE = 4;
    Vector<Player> playerList= new Vector<>();
    int currentPlayer=0; // ranges between 0 and playerCount
    gamePhase currentPhase=gamePhase.INIT;

    Vector<Cube> poolCubes;
    
    enum gamePhase{// read the diagram
        INIT,BUILD,MAIN,PENALTY,CHANGE
    }

    public void join(Player p){
        assert currentPhase == gamePhase.INIT: "Can't join a game currently playing";
        assert playerList.size()<=MAX_PLAYER_SIZE  : "Room is full sorry :/";
        playerList.add(p);
    }
    
    public void start(){
        assert currentPhase == gamePhase.INIT : "Who sent start ??? we're not in init phase";
        int playerCount=playerList.size();
        assert playerCount>1 && playerCount<=MAX_PLAYER_SIZE : "Player amount incorrect";
        for (Player player : playerList) {
            if (!player.ready){
                return;
            }
        }
        //all players are ready
        currentPhase = gamePhase.BUILD;
        build();
    }
    private void init_pool(){
        poolCubes=new Vector<>();
        for (int i=0;i<9;i++){
            poolCubes.add(new Cube(Color.RED));
            poolCubes.add(new Cube(Color.GREEN));
            poolCubes.add(new Cube(Color.BLUE));
            poolCubes.add(new Cube(Color.YELLOW));
            poolCubes.add(new Cube(Color.BLACK));
        }
    }

    public boolean validBase(Cube[] base){
        int[] cpt = new int[5]; //r,g,b,y,n

        for(Cube c : base){
            switch (c.getColor()) {
                case RED:cpt[0]++;break;
                case GREEN:cpt[1]++;break;
                case BLUE:cpt[2]++;break;
                case YELLOW:cpt[3]++;break;
                case BLACK:cpt[4]++;break;
                default:break;
            }
        }
        int zero=0;// count amount of color equal to 0
        for (int i=0; i<5; i++){
            zero += (cpt[i]==0) ? 1:0;
            if (zero==2){//at least 2 color not in the given base, return false
                return false;
            }
        }
        return true;

    }

    private void build(){
        init_pool();

        Cube[] base = new Cube[9];
        RandomGenerator r= new Random();
        boolean cond=false;
        do{
            for (int i=0; i<9; i++){
                int randomDraw=r.nextInt(0, poolCubes.size()); //draw cube from pool chosen randomly
                base[i]=poolCubes.remove(randomDraw);
            }
            cond=!validBase(base);
            if (cond){//invalid base, reinitialize bag
                for(Cube c: base){
                    poolCubes.add(c);
                }
            }
        }while(cond);//restart base build if invalid base

    }
}
