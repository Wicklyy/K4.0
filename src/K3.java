package src;

import src.Model.*;
import src.View.*;
import src.Controller.*;

class K3
{   
    public static void main(String [] args) {
        Jeu jeu = new Jeu(6, 7);
        ControleurMediateur contMEd = new ControleurMediateur(jeu);
        InterfaceGraphique.demarrer(jeu, contMEd);
    }
}