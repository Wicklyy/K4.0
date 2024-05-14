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
			
			if(!nivGraph.peut_cliquer_pyramide()){
				return;
			}
			int taille_cube = nivGraph.tailleCubePyramide();
			Point pts[][] = nivGraph.pointsPyr();
			int l, c;
			Point p;
			for(int i=0; i<6; i++){
				for(int j=0; j<=i; j++){
					if(e.getX() >= pts[i][j].getX() && e.getX() <= pts[i][j].getX() + taille_cube){
						if(e.getY() >= pts[i][j].getY() && e.getY() <= pts[i][j].getY() + taille_cube){
							l = 5-i;
							c = i-j;
							p = new Point(l, c);
							nivGraph.setPoint(p);
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
			int taille_cube = nivGraph.tailleCubePioche();
			Point pts[][] = nivGraph.pointsPioche();
			int l, c;
			int couleurs[] = nivGraph.couleurs();
			Point p;
			// for (int i=0; i<7; i++){
			// 	System.out.println(couleurs[i]);
			// }
			for(int i=0; i<7; i++){
				for(int j=0; j<couleurs[i]; j++){
					// System.out.println(i + " "+ j);
					if(e.getX() >= pts[i][j].getX() && e.getX() <= pts[i][j].getX() + taille_cube){
						// System.out.println("if");
						if(e.getY() >= pts[i][j].getY() && e.getY() <= pts[i][j].getY() + taille_cube){
							l = i;
							// nivGraph.modifierLignePioche(l);
							c = couleurs[i] - j - 1;
							p = new Point(l, couleurs[i] - j - 1);
							nivGraph.modifierLignePioche(p);
							controle.clicSourisPioche(l,  c);
						}
					}
				}
			}
			// controle.clicSourisPioche(e.getX(),  e.getY());
		}
		// controle.clicSouris(e.getY() / nivGraph.Hauteur_case(),  e.getX() / nivGraph.Largeur_case());
		
	}
}