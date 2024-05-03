

import Model.*;
import java.util.Scanner;;

public class test{
    public static void main(String[] args) {
        Jeu jeu = new Jeu(2);
        Scanner s = new Scanner(System.in);
        Player player;
        int x,y;

        
        String[] entree;
        while(true){
            entree = s.nextLine().split("\\s+");
            System.out.println("pyramide du milieu:\n" + jeu.principale);
            System.out.println("tour du joueur:" + (jeu.current_player+1));
            if(entree[0].equals("print")){
                if(entree[1].equals("joueur")){
                    if(entree[2].equals("1")){
                        player = jeu.getPlayer(0);
                        System.out.println(player);
                    }
                    if(entree[2].equals("2")){
                        player = jeu.getPlayer(1);
                        System.out.println(player);
                    }
                    else{
                        player = jeu.getPlayer();
                        System.out.println(player);
                    }
                }
                if(entree[1].equals("milieu")){
                    System.out.println(jeu.principale);
                }
                else if(entree[1].equals("plateau")){
                    System.out.println(jeu.principale);
                }
            }
            if(entree[0].equals("ajoute")){
                x = Integer.parseInt(entree[1]);
                y = Integer.parseInt(entree[2]);
                jeu.setPlayer(x, y,Cube.valueOf(entree[3]));

            }
            if(entree[0].equals("joue")){
                x = Integer.parseInt(entree[1]);
                y = Integer.parseInt(entree[2]);
            }
            if(entree[0].equals("quit")){break;}
        }
        s.close();

        //s.close();
    }
    
}