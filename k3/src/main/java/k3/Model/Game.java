package k3.Model;

import java.util.Random;
import java.util.Vector;
import java.util.random.*;


/**
 * 
 * Instance of Game which Players interract with
 * @see Player
 */
public class Game {
    Pyramid mainPyramid;
    RandomGenerator r= new Random();
    private int MAX_PLAYER_SIZE = 4;
    Vector<Player> playerList= new Vector<>();
    int currentPlayer=0; // ranges between 0 and playerCount
    gamePhase currentPhase=gamePhase.INIT;

    Vector<Cube> poolCubes;
    /**
     * initialize the pool as specified in the rules
     */
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

    /**
     * Creates and initialize an instance of the K3 game model
     */
    public Game(){
        init_pool();
    }

    /**
     * Creates and initialize an instance of the K3 game model with the given seed
     * @param seed : seed of any randomness occuring within the scope of the K3 Game
     */
    public Game(int seed){
        r=new Random(seed);
        init_pool();
    }
    /**
     * Enumeration of the phases of the game
     */
    enum gamePhase{// read the diagram
        INIT,BUILD,MAIN,PENALTY,CHANGE
    }
    /**
     * join() method permits player to add themselves to this game instance if there is still room for them
     * 
     * @param p the Player to add
     * @see Player
     */
    public void join(Player p){
        assert currentPhase == gamePhase.INIT: "Can't join a game currently playing";
        assert playerList.size()<=MAX_PLAYER_SIZE  : "Room is full sorry :/";
        playerList.add(p);
    }
    /**
     * start() method permits players to signal the game they are ready to start the game. 
     * The game can't start if every player didn't say they are ready or if the player amount is incorrect.
     * When all conditions are met, game will then enter build phase
     * 
     */
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

    /**validBase method permit to check if the given base is a valid pyramid base
     * 
     * @param base : the candidate base
     * @return false : if the base isn't of the correct length, 9, or if there isn't at least 4 different colors in it. Else : true
     */
    public boolean validBase(Cube[] base){

        if (base.length!=9){return false;}

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
            if (zero>=2){//at least 2 color not in the given base, return false
                return false;
            }
        }
        return true;

    }

    /**drawCubes method draws randomly an amount of cubes in the pool of cubes from this game's instance 
     * 
     * @param amount : the amount of cubes that should be retreived from the pool
     * @return the list of cubes that have been drawn as a Cube[]
     * 
     * @see Cube
     * 
     */
    public Cube[] drawCubes(int amount){
        Cube[] draw= new Cube[amount];
        for (int i=0; i<amount; i++){
                int randomDraw=r.nextInt(0, poolCubes.size()); //draw cube from pool chosen randomly
                draw[i]=poolCubes.remove(randomDraw);
            }
        return draw;
    }
    /**cubes() method creates a Cube list containing only any amount of cubes of the asked color
     * 
     * @param amount : the amount of cubes that should be generated
     * @param c : the color of these cubes
     * @return a list of amount c cubes list as a Cube[]
     */
    public Cube[] cubes(int amount,Color c){
        Cube[] out=new Cube[amount];
        for(int i=0;i<amount;i++){
            out[i]=new Cube(c);
        }

        return out;
    }
    /**flushCubes method take a list of Cube and add each element of it in the pool
     * 
     * @param list : a list of Cube 
     */
    public void flushCubes(Cube[] list){
        for(int i=0;i<list.length;i++){
            poolCubes.add(list[i]);
        }
    }

    /**
     * build() method builds the mainPyramid and gives to the player the cubes so that they can build their hand
     */
    public void build(){
        mainPyramidBuild();

        playersHandBuild();
        //now await for players to finish building their pyramind
    }
    /**
     * mainPyramidBuild() method tries to build the main pyramid base according to the specification given by the rules until having one valid
     */
    public void mainPyramidBuild(){
        boolean cond;
        Cube[] base;
        do{
            base = drawCubes(9);
            cond=!validBase(base);
            if (cond){//invalid base, reinitialize bag
                flushCubes(base);
            }
        }while(cond);//restart base build if invalid base
        //TODO: Create MainPyramid class
    }
    /**
     * playersHandBuild() method gives cubes to every players of this instance accordingly to the rules
     */
    public void playersHandBuild(){
        int colored=0;
        int white=0;
        int neutral=0;
        int playerCount=playerList.size();

        assert playerCount>1 && playerCount<=MAX_PLAYER_SIZE :"Player amount incoherent";
        switch (playerCount) {
            case 2:
                colored=17;
                neutral=2;
                white=2;
                break;
            case 3:
                colored=12;
                neutral=2;
                white=1;
                break;
            case 4:
                colored=9;
                neutral=1;
                white=1;
                break;
        }
        int drawnAmount=0;
        int toDraw;
        while (drawnAmount<colored){
            toDraw= (drawnAmount+3 < colored) ? 3 : (colored-drawnAmount) ;
            for (Player p : playerList){
                p.give(drawCubes(toDraw));//draw 3 by 3 like irl, not optimised but it's fun 
            }
            drawnAmount+=toDraw;
        }

        for(Player p: playerList){
            p.give(cubes(white,Color.WHITE));
            p.give(cubes(neutral,Color.NEUTRAL));
            p.ready=false;
        }
    }
}
