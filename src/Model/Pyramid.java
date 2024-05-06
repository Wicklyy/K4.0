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

    public int getSize(){
        return size;
    }
    public String tmp(int i){
        String chaine = "";
        for(int j = 0; j < i; j++){
            chaine += "   ";
        }
        return chaine;
    }
    public String milieu(Cube c){
        String chaine = "";
        switch (c) {
            case Noir:
            case Bleu:
            case Vert:
            case Vide:
                chaine = " " + c + " ";
                break;
            case Blanc:
            case Rouge:
            case Jaune:
                chaine = " " + c;
                break;
            default:
                chaine = "" + c;
                break;
        }
        return chaine;
    }
    
    @Override
    public String toString(){
        String chaine = "";
        for(int i = size-1; i >= 0; i--){
            chaine += tmp(i);
            for (int j = 0; j <= size - 1 - i; j++){
                chaine += milieu(pyramid[i][j]) + " ";
            }
            chaine+="\n";
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
