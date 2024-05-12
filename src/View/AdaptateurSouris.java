package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;


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
		if((e.getX() <= nivGraph.Largeur_Fenetre()*3/4) && e.getY() <= nivGraph.Hauteur_Fenetre()*7/10){
			int taille_cube = nivGraph.tailleCubePyramide();
			Point pts[][] = nivGraph.pointsPyr();
			int l, c;
			for(int i=0; i<6; i++){
				for(int j=0; j<=i; j++){
					if(e.getX() >= pts[i][j].getX() && e.getX() <= pts[i][j].getX() + taille_cube){
						if(e.getY() >= pts[i][j].getY() && e.getY() <= pts[i][j].getY() + taille_cube){
							l = 5-i;
							c = i-j;
							controle.clicSourisPyr(l, c);
							// System.out.println("("+(5-i)+","+(i-j)+")");
						}
						
						// System.out.println(j);
					}
				}
			}
			//controle.clicSourisPyr(e.getX(),  e.getY());
		}
		else if(e.getX() >= nivGraph.Largeur_Fenetre()*3/4){
			controle.clicSourisPioche(e.getX(),  e.getY());
		}
		// controle.clicSouris(e.getY() / nivGraph.Hauteur_case(),  e.getX() / nivGraph.Largeur_case());
		
	}
}