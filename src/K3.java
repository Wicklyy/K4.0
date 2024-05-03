// javac -cp src -d out src/*.java src/*/*.java
// java -cp out K3


import Model.*;
import View.*;
import Controller.*;

class K3
{   
    public static void main(String [] args) {
        Jeu jeu = new Jeu(6, 7);
        ControleurMediateur contMEd = new ControleurMediateur(jeu);
        InterfaceGraphique.demarrer(jeu, contMEd);
    }
}