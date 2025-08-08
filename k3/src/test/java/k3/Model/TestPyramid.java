package k3.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPyramid {

    @Test
    public void testInitialization() {
        Pyramid pyramid = new Pyramid(3);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < x; y++) {
                assertEquals(Color.VOID, pyramid.get(x, y).getColor());
            }
        }
    }

    @Test
    public void testSetAndGet() {
        Pyramid pyramid = new Pyramid(3);
        Cube cube = new Cube(Color.RED);
        pyramid.set(1, 0, cube);
        assertEquals(cube, pyramid.get(1, 0));
    }

    @Test
    public void testRemoveSetsToVoid() {
        Pyramid pyramid = new Pyramid(3);
        pyramid.set(2, 1, new Cube(Color.BLUE));
        pyramid.remove(2, 1);
        assertEquals(Color.VOID, pyramid.get(2, 1).getColor());
    }
}
