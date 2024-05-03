package Controller;

import View.*;
import Model.*;

public class ControleurMediateur implements CollecteurEvenements 
{
	Jeu jeu;
	InterfaceGraphique vue;
	boolean toggleIA=false;
	MusicPlayer musique;

	public ControleurMediateur(Jeu j, MusicPlayer musique)
	{
		jeu = j;
		this.musique = musique;
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

			case "Son":
				System.out.println("Case son de ControleurMediateur");
				musique.jouerMusique();
				break;

			default:
				return false;
		}
		return true;
	}
}