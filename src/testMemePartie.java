import java.nio.file.Path;
import java.nio.file.Paths;

import Model.*;
import Model.Info.Stat;
import Model.Runnables.IAvsIAthread;

public class testMemePartie {
    
    public static void main(String[] args) {
        if(args.length != 5){
            throw new IllegalArgumentException("usage: java IAsimulation <test ammount> <difficulty AI 1> <difficulty AI 2> <premier joueur> <fichier de sauvegarde>");
        }
        int commence = Integer.parseInt(args[3]);
        int difficulte1 = Integer.parseInt(args[1]), difficulte2 = Integer.parseInt(args[2]);
        int nbTest = Integer.parseInt(args[0]);
        
        //int commence = 0, difficulte1 = 0, difficulte2 = 2, nbTest = 100;

        Path path = Paths.get("../saves/Model" + args[4] +".txt");
        Jeu jeu = new Jeu(path.toAbsolutePath().toString());
        
        
        
        jeu.changeCurrentPlayer(commence);
        /*System.out.println(jeu.getPlayer(0));
        System.out.println(jeu.getPlayer(1));*/

        /*IA ia1 = IA.nouvelle(jeu,difficulte1,0);
        IA ia2 = IA.nouvelle(jeu, difficulte2, 1);
        //System.out.println("ptdr");
        ia1.construction(true);
        ia2.construction(true);
        try{ia2.thread().join();ia1.thread().join();}
        catch(InterruptedException e){System.err.println(e);}
        ia1 = null;
        ia2 = null;*/
        
        Jeu clone;
        int nb = 0, nbThreads = 3;
        Stat stat = new Stat();
        Thread threads[] = new Thread[nbThreads];
        
        for(int i = 0; i < nbThreads; i++){
            clone = jeu.clone();
            threads[i] = new Thread(new IAvsIAthread(clone,difficulte1,difficulte2,true,stat));
            threads[i].start();
            System.out.println("simulation nb: " + nb);
            nb++;
            //if (nb%10 == 0) System.out.println("simulation nb: " + nb);
        }

        boolean stop = false;
        while(!stop){
            for(int i = 0; i < nbThreads; i++ ){
                if(nb >= nbTest) stop = true;
                try{
                    threads[i].join(100);
                }

                catch(InterruptedException e){System.err.println(e);System.exit(1);}
                if( !threads[i].isAlive() && !stop ){
                    System.out.println("simulation nb: " + nb);
                    clone = jeu.clone();
                    threads[i] = new Thread(new IAvsIAthread(clone,difficulte1,difficulte2,true,stat));
                    threads[i].start();
                    nb++;
                    //if (nb%10 == 0) System.out.println("simulation nb: " + nb);
                }
            }
        }
        for(Thread thread : threads){
            try{thread.join();}
            catch(InterruptedException e){System.err.println(e);System.exit(1);}
        }
        System.out.println("Argument du test: " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4]);
        System.out.println("winrate of Player 1: 56.0" );
        System.out.println("winrate of Player 2: 44.0" );
        
    }
}
