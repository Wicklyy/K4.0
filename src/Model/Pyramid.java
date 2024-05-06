package Model;

public class Pyramid {
    Cube[][] pyramid;
    int size;

    Pyramid(int i){
        pyramid = new Cube[i][i];
        size = i;
        for (int x=0; x<i; x++ ){
            for (int y=0; y<i; y++ ){
                pyramid[x][y] = Cube.Vide;
            }
        }
    }

    public int getsize(){
        return size;
    }

    public Cube get(int x, int y){
        return pyramid[x][y];
    }

    public void set(int x, int y, Cube c){
        pyramid[x][y] = c;
    }
}