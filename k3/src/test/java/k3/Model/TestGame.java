package k3.Model;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;


public class TestGame {
    
    @Test
    public void testInitialization(){
        Game g = new Game();
        assertEquals(Game.gamePhase.INIT,g.currentPhase);
        assertEquals(0,g.playerList.size());
        assertEquals(45,g.poolCubes.size());
    }

    @Test 
    public void testValidBase(){
        Game g=new Game();
        Cube[] base=new Cube[9];
        for (int i=0; i<9; i++){
            base[i]=new Cube(Color.BLACK);
        }
        assertEquals(false, g.validBase(base));//all black base => false
        base[0]=new Cube(Color.RED);
        assertEquals(false, g.validBase(base));//r,n,n,n,n,n,n,n,n base 2 different colors => false
        base[1]=new Cube(Color.GREEN);
        assertEquals(false, g.validBase(base));//r,g,n,n,n,n,n,n,n base 3 different colors=> false
        base[2]=new Cube(Color.BLUE);
        assertEquals(true, g.validBase(base));//r,g,b,n,n,n,n,n,n base  4 different colors => true
        base[3]=new Cube(Color.YELLOW);
        assertEquals(true, g.validBase(base));//r,g,b,y,n,n,n,n,n base  5 different colors=> true
    }

    @Test
    public void testDrawCubes(){
        Game g=new Game();
        g.r=new Random(42);
        int[] poolInitProp={ 9, 9, 9, 9, 9 };
        assertArrayEquals(poolInitProp, bagProportions(g.poolCubes));
        Cube[] drawn=g.drawCubes(5);
        Cube[] expected={   new Cube(Color.RED),
                            new Cube(Color.RED),
                            new Cube(Color.BLUE),
                            new Cube(Color.GREEN),
                            new Cube(Color.GREEN)
                        }; //value obtained with seed 42 on first draw
        assertArrayEquals(expected, drawn);
        assertEquals(40,g.poolCubes.size());//size diminishes by 5
        int[]poolProp = bagProportions(g.poolCubes);
        int[]drawnProp = bagProportions(Arrays.asList(drawn));
        for (int i=0;i<poolProp.length;i++){//drawn cubes are actually drawn
            poolProp[i]+=drawnProp[i];
        }
        assertArrayEquals(poolInitProp, poolProp);
    }

    private int[] bagProportions(Iterable<Cube> list){
        int[] cpt=new int[5];
        for(int i=0;i<cpt.length;i++){
            cpt[i]=0;
        }//init r,g,b,y,b
        for (Cube c : list) {
            switch (c.cube_color) {
                case RED:cpt[0]++;break;
                case GREEN:cpt[1]++;break;
                case BLUE:cpt[2]++;break;
                case YELLOW:cpt[3]++;break;
                case BLACK:cpt[4]++;break;
                default:break;
            }
        }
        return cpt;
    }

    @Test
    public void testCubes(){
        Game g=new Game();
        Cube[] list=g.cubes(5, Color.WHITE);// creates [w,w,w,w,w] with cubes
        Cube[] expected={   new Cube(Color.WHITE),
                            new Cube(Color.WHITE),
                            new Cube(Color.WHITE),
                            new Cube(Color.WHITE),
                            new Cube(Color.WHITE) 
                        };//and manually
        assertArrayEquals(expected, list);//should be equal
        expected= new Cube[0];
        assertArrayEquals( expected, g.cubes(0, Color.RED));//creates empty list if amount=0, independently on the color given
    }

    @Test
    public void testFlushCubes(){
        Game g= new Game();
        int poolSize=g.poolCubes.size();
        int addedAmount=10;
        g.flushCubes(g.cubes(addedAmount, Color.WHITE));//add addedAmount of WHITE cubes to pool
        assertEquals(poolSize+addedAmount, g.poolCubes.size());//check quantity
        for (int i=poolSize; i<g.poolCubes.size();i++) {
            assertEquals(new Cube(Color.WHITE),g.poolCubes.get(i));//check value
        }
    }
}
