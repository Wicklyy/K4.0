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

    public Cube get(int x, int y){
        return pyramid[x][y];
    }

    public void set(int x, int y, Cube c){
        pyramid[x][y] = c;
    }
    
    @Override
    public String toString(){
        String chaine = "";
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){}
        }
        return chaine;
    }

    /*switch (cube) {
                case Noir:
                case Bleu:
                case Vert:
                case Vide:
                    chaine+=" ";
                case Blanc:
                case Rouge:
                case Jaune:
                    chaine+=" ";
                default:
                    chaine+= cube +" ";
                    break;
            } */
}
