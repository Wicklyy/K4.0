package k3.Model;


import org.junit.Test;
import static org.junit.Assert.*;


public class TestGame {
    
    @Test
    public void testInitialization(){
        Game g = new Game();
        assertEquals(Game.gamePhase.INIT,g.currentPhase);
        assertEquals(0,g.playerList.size());
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


}
