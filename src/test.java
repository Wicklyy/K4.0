

import Model.*;
import java.util.Scanner;;

public class test{
    static void rempli(Jeu jeu){
        for (int i = 0; i < 6; i++){
            jeu.ajoute(0, i, 0);
        }
        System.out.println(jeu.getPlayer());
    }
    static void drawAll(Jeu jeu){
        while(jeu.draw()){}
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu(2);
        Scanner s = new Scanner(System.in);
        Player player;
        int x,y,z,w;

        
        String[] entree;
        while(true){
            //System.out.println("pyramide du milieu:\n" + jeu.principale);
            System.out.println("tour du joueur:" + (jeu.current_player+1));
            entree = s.nextLine().split("\\s+");
            if(entree[0].equals("print")){
                if(entree[1].equals("joueur")){
                    if(entree.length == 2){
                        player = jeu.getPlayer();
                        System.out.println(player);
                    }
                    else if(entree[2].equals("1")){
                        player = jeu.getPlayer(0);
                        System.out.println(player);
                    }
                    else if(entree[2].equals("2")){
                        player = jeu.getPlayer(1);
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
            if(entree[0].equals("draw")){
                if(entree.length == 1){
                    if(!jeu.draw()){
                        System.out.println("plus rien dans le sac");
                    }
                }
                else{
                    drawAll(jeu);
                }
            }
            if(entree[0].equals("ajoute")){
                x = Integer.parseInt(entree[1]);
                y = Integer.parseInt(entree[2]);
                z = Integer.parseInt(entree[3]);
                jeu.ajoute(x, y, z);
                System.out.println(jeu.getPlayer());

            }
            if(entree[0].equals("joue")){
                x = Integer.parseInt(entree[1]);
                y = Integer.parseInt(entree[2]);
                z = Integer.parseInt(entree[3]);
                w = Integer.parseInt(entree[4]);
                switch(jeu.add_central_pyramid(x,y,z,w)){
                    case 0:
                        System.err.println("coup non valide");
                        break;
                    case 1:
                        System.out.println("coup valide");
                        break;
                    case 2:
                        System.out.println("coup valide mais penalitee appliquee");
                    default:
                        break;
                }
            }
            if(entree[0].equals("rempli")){
                rempli(jeu);
            }
            if(entree[0].equals("auto")){
                drawAll(jeu);
                rempli(jeu);
                jeu.avance();
                rempli(jeu);
            }
            if(entree[0].equals("show")){System.out.println(jeu.bag());}
            if(entree[0].equals("next")){jeu.avance();}
            if(entree[0].equals("quit")){break;}
            System.out.println();
            System.out.println();
        }
        s.close();
    }
    
}