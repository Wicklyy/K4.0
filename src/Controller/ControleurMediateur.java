package src.Controller;

import src.View.*;
import src.Model.*;

public class ControleurMediateur implements CollecteurEvenements 
{
	Jeu jeu;
	InterfaceGraphique vue;
	boolean toggleIA=false;

	public ControleurMediateur(Jeu j) {
		jeu = j;
    }
	
	public void ImporterVue(InterfaceGraphique vue)
	{
		this.vue = vue;
	}
    
    @Override
	public void clicSouris(int x, int y)
    {
        
	}

	@Override
	public boolean commande(String c)
	{
		switch (c)
		{
			case "Quit":
				System.exit(0);
				break;
			
			case "fullscreen":
				vue.basculePleinEcran();
				break;

			case "NouvellePartie":
				jeu.renitialiser(); 
				break;

            case "Lan":
				break;
                
            case "Charger":
				break;
            
            case "FR":
                break;

            case "EN":
                break;
			
			case "Regles":
                break;

			default:
				return false;
		}
		return true;
	}
}