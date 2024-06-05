// javac -cp src -d out src/*.java src/*/*.java
// jar cfe $HOME/K3.jar K3 *.class */*.class */*/*.class -C ../src/Global/ res -C ../src/Global/ saves
// java -cp out K3

import Model.*;
import View.*;
import Controller.*;

class K3 {
    public static void main(String[] args) {
        Jeu jeu = new Jeu(2);
        //MusicPlayer musique = new MusicPlayer();
        StructurePainter.init();
        ControleurMediateur contMEd = new ControleurMediateur(jeu,null);
        InterfaceGraphique.demarrer(jeu, contMEd);
        
    }
}