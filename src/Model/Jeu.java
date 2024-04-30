package Model;

public class Jeu {
    Player[] players;
    int nbJoueur;
    Pyramid principale;

    Jeu(int nb){
        players = new Player[4];
        for(int i = 0;i < nb; i++ ){
            players[i] = new Player(8-nb);
            if(nb == 4){
                players[i].addSide(Cube.Blanc);
            }
        }
    }

}
