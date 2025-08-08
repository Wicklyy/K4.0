package k3.Model;

public class Pyramid {
    Cube[][] pyramid;
    int size; 
    boolean extended;

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
    public void set(int x, int y, Cube c){
        pyramid[x][y] = c;
    }
    public Cube get(int x, int y){
        return pyramid[x][y];
    }
    public void remove(int x, int y){
        pyramid[x][y] = new Cube(Color.VOID);
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
