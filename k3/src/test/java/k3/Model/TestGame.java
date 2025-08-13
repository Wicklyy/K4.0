package k3.Model;


import org.junit.Test;
import static org.junit.Assert.*;
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
        Cube[] drawn=g.drawCubes(5);
        Cube[] expected=new Cube[5];
        expected[0]=new Cube(Color.RED);
        expected[1]=new Cube(Color.RED);
        expected[2]=new Cube(Color.BLUE);
        expected[3]=new Cube(Color.GREEN);
        expected[4]=new Cube(Color.GREEN); //value obtained with seed 42 on first draw
        assertArrayEquals(expected, drawn);
        assertEquals(40,g.poolCubes.size());//size diminishes by 5
    }

}
