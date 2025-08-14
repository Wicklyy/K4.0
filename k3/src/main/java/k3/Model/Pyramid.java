package k3.Model;
/**
 * Pyramid class permits to represent a pyramid as a Cube structure
 */
public class Pyramid {
    Cube[][] pyramid;
    int size; 
    boolean extended;
    /**
     * Creates a Pyramid 
     * @param size : the size for the base of the pyramid
     */
    public Pyramid(int size){
        this.size = size;
        pyramid = new Cube[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < x; y++){
                pyramid[x][y] = new Cube(Color.VOID);
            }
        }
        extended = false;
    }


    /* setter and getter */

    /**set method sets given cube at the given coordonate
     * 
     * @param x : floor
     * @param y : rank
     * @param c : cube to set
     */
    public void set(int x, int y, Cube c){
        pyramid[x][y] = c;
    }

    /**get method 
     * 
     * @param x : floor
     * @param y : rank
     * @return the Cube at the given coordonate
     */

    public Cube get(int x, int y){
        return pyramid[x][y];
    }

    /**remove method removes the cube at x,y coordinates
     * 
     * @param x : floor
     * @param y : rank
     * 
     * @see pop()
     */
    public void remove(int x, int y){
        pyramid[x][y] = new Cube(Color.VOID);
    }

    /**pop method removes the cube at x,y coordinates and return its value
     * 
     * @param x : floor
     * @param y : rank
     * 
     * @return the value of the cube removed
     */
    public Cube pop(int x, int y){
        Cube out= pyramid[x][y];
        remove(x, y);
        return out;
    }

    
    /* If pyramid full and first time calling methode, game continues */
    public boolean late_game(){
        if (!extended) return false;
        Cube cop_pyramid[][] = new Cube[size+2][size+2];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                cop_pyramid[i][j+1] = pyramid[i][j];
            }
        }
        pyramid = cop_pyramid;
        size += 2;
        return true;
    }


}
