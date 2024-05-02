package src.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdaptateurSouris extends MouseAdapter 
{
	CollecteurEvenements controle;
	NiveauGraphique nivGraph;

	AdaptateurSouris(CollecteurEvenements c, NiveauGraphique nivGraph)
	{
		controle = c;
		this.nivGraph = nivGraph;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		controle.clicSouris(e.getY() / nivGraph.Hauteur_case(),  e.getX() / nivGraph.Largeur_case());
	}
}