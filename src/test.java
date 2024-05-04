

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

    static void build(Player player){
        player.melange();
        for(int i = player.getSize()-1; i >= 0; i--){
            for(int j = 0; j < player.getSize()-i; j++){
                //System.out.println( i + " " + j + " " + "0" );
                player.ajoute(i, j, 0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Jeu jeu = new Jeu(s.nextInt());
        boolean not = true;

        Player player;
        int x,y,z,w;

        String[] entree;
        System.out.println("help pour afficher le menue");
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
                    else{
                        player = jeu.getPlayer(Integer.parseInt(entree[2]) - 1);
                        System.out.println(player);
                    }
                }
                if(entree[1].equals("milieu")){
                    System.out.println(jeu.principale);
                }
                
            }
            if(entree[0].equals("accessible")){
                x = Integer.parseInt(entree[1]);
                y = Integer.parseInt(entree[2]);
                if(!jeu.accessible(x, y)){System.out.print("Not ");}
                System.out.println("Accessible");
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
                        jeu.avance();
                        break;
                    case 2:
                        System.out.println("coup valide mais penalitee appliquee\nvoici le jeu du joueur penaliser:");
                        System.out.println(jeu.getPlayer());
                        System.out.println("Joueur " + (jeu.previous_player()) +" selectionnez 'p' si vous voulez prendre de sa pyramide ou 's' de son sac" );
                        entree = s.nextLine().split("\\s+");
                        if(entree[0].equals("p")){
                            while(not || !jeu.accessible(jeu.getPlayer().getPyramid(),x, y)){
                                not = false;
                                System.out.println("Selectionnez une case accessible a voler");
                                entree = s.nextLine().split("\\s+");
                                x = Integer.parseInt(entree[0]);
                                y = Integer.parseInt(entree[1]);
                            }
                        }
                        if(entree[0].equals("s")){
                            while(not || x < jeu.getPlayer().getBagSize()){
                                System.out.println("Selectionnez l'indice du cube a voler");
                                entree = s.nextLine().split("\\s+");
                                x = Integer.parseInt(entree[0]);
                            }
                        }
                        jeu.avance();

                    default:
                        break;
                }
                
            }
            if(entree[0].equals("rempli")){
                rempli(jeu);
            }
            if(entree[0].equals("auto")){
                drawAll(jeu);
                for(int i = 0; i < jeu.nbJoueur; i++){
                    build(jeu.getPlayer());
                    jeu.avance();
                }
            }
            if(entree[0].equals("build")){
                if(entree.length == 1){build(jeu.getPlayer());}
                else{build(jeu.getPlayer(Integer.parseInt(entree[1])-1));}
            }
            if(entree[0].equals("show")){System.out.println(jeu.bag());}
            if(entree[0].equals("next")){jeu.avance();}
            if(entree[0].equals("quit")){break;}
            if(entree[0].equals("help")){
                System.out.println("-print <joueur/milieu> <n du joueur (optionelle)> #affiche la pyramide");
                System.out.println("-accessible <x> <y> ##affiche si la case du joueur est accessible");
                System.out.println("-draw <all (optionnelle)> ##ajoute au joueur courant les cube piochee, si all ajouter tout les cubes sont distribuer");
                System.out.println("-ajoute <x> <y> <z> #ajoute a la pyramide du joueur au coordonnee x y le cube dans son sac a l'emplacement z");
                System.out.println("-joue <x> <y> <z> <w> #joue le cube de coordonnee (z,y) a l'emplacement (x,y)");
                System.out.println("-rempli #rempli la premiere ligne de la pyramide du joueur aleatoirement");
                System.out.println("-auto #effectue la fonction draw all puis remplie toute les pyramide de tout les joueur de maniere aleatoire");
                System.out.println("-build <n joueur (optionnelle)> #rempli toute la pyramide du joueur courant ou le nombre indiquee");
                System.out.println("-show #affiche le contenue de la pioche");
                System.out.println("-next #donne la main au prochain joueur");
                System.out.println("-quit #quite le jeu");
            }
            System.out.println();
            System.out.println();
        }
        s.close();
    }
    
}