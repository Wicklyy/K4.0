package src.View;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.Model.Jeu;
import src.Patterns.Observateur;

public class InterfaceGraphique implements Runnable, Observateur
{
    JFrame frame;
    JLabel joueur;
    CollecteurEvenements controle;
    Jeu jeu;
    NiveauGraphique niv;
    boolean maximized;
	Timer t;
	JToggleButton IA;
	JButton NewGame, Charger, Lan, Quit, Regles, FR, EN;

    InterfaceGraphique(Jeu jeu, CollecteurEvenements c)
    {
        this.jeu = jeu;
        controle = c;
    }

    public static void demarrer(Jeu j, CollecteurEvenements c)
    {
        InterfaceGraphique vue = new InterfaceGraphique(j, c);
        c.ImporterVue(vue);
		SwingUtilities.invokeLater(vue);
	}

	public void stopTimer(){
		t.stop();
	}
	public void startTimer(){
		t.start();
	}
    public void basculePleinEcran() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}
	}

	private JButton creerButton(String text) {
		BoutonArrondi bouton = new BoutonArrondi(text, 20);
		Font police = new Font("Arial", Font.BOLD, 14);
		bouton.setFont(police);
		bouton.setBorder(new LineBorder(Color.BLACK, 2));
		bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
		bouton.setFocusable(false);
		return bouton;
	}


	@Override
	public void miseAJour()
	{
	}

	public void Page_NewGame(Container container){
		//Supprimer tous les composants de la fenêtre
        for (Component component : container.getComponents()) {
            container.remove(component);
        }
        // Rafraîchir la fenêtre pour refléter les changements
        container.revalidate();
        container.repaint();
    }


    public void run()
    {
		frame = new BackgroundPanel();
		frame.setTitle("K3");
		frame.setSize(500, 300);
       	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        niv = new NiveauGraphique(jeu);

		// Charger l'image d'icône
        ImageIcon icon = new ImageIcon("res/IconeV2.png");
        frame.setIconImage(icon.getImage());

		// Panneau central avec les boutons
		JPanel centrePanel = new JPanel();
		centrePanel.setOpaque(false);
		centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS)); // Utiliser BoxLayout pour l'alignement vertical
		centrePanel.add(Box.createVerticalGlue());
		centrePanel.add(Box.createVerticalStrut(40));

		NewGame = creerButton("Nouvelle partie");
		NewGame.addActionListener(new AdaptateurNewGame(controle));
		centrePanel.add(NewGame);
		centrePanel.add(Box.createVerticalStrut(10));

		Charger = creerButton("Charger partie");
		Charger.addActionListener(new AdaptateurCharger(controle));
		centrePanel.add(Charger);
		centrePanel.add(Box.createVerticalStrut(10));

		Lan = creerButton("LAN");
		Lan.addActionListener(new AdaptateurLan(controle));
		centrePanel.add(Lan);
		centrePanel.add(Box.createVerticalStrut(10));

		Quit = creerButton("Quitter");
		Quit.addActionListener(new AdaptateurQuit(controle));
		centrePanel.add(Quit);
		centrePanel.add(Box.createVerticalStrut(10));
		centrePanel.add(Box.createVerticalGlue());

		frame.add(centrePanel, BorderLayout.CENTER);

		// Panneau en bas à gauche pour les drapeaux
        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// Charger les icônes d'images
		bottomLeftPanel.setOpaque(false);
		ImageIcon iconFR = new ImageIcon("res/Francev2.jpg");
		ImageIcon iconEN = new ImageIcon("res/anglais.png");
		
		// Redimensionner les images à 20x20 pixels
		Image resizedImageFR = iconFR.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
		Image resizedImageEN = iconEN.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);

		// Créer les icônes redimensionnées
		iconFR = new ImageIcon(resizedImageFR);
		iconEN = new ImageIcon(resizedImageEN);

		// Créer les boutons avec les icônes d'images
		FR = new JButton(iconFR);
		EN = new JButton(iconEN);
		
		// Ajouter des écouteurs d'actions aux boutons
		FR.addActionListener(new AdaptateurLangues(controle));
		EN.addActionListener(new AdaptateurLangues(controle)); 

		// Ajouter les boutons au panneau
		bottomLeftPanel.add(FR);
		bottomLeftPanel.add(EN);

		// Panneau en bas à droite pour le bouton "Règles"
        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  
		bottomRightPanel.setOpaque(false);      
		Regles = creerButton("Règles");
		Regles.addActionListener(new AdaptateurRegles(controle));
		bottomRightPanel.add(Regles);
    

		// Combiner les panneaux en bas à gauche et à droite dans un seul panneau en bas
        JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false); 
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);

		SourisAdapte sourisNewGame = new SourisAdapte(NewGame, "res/clic.wav");
		SourisAdapte sourisCharger = new SourisAdapte(Charger, "res/clic.wav");
		SourisAdapte sourisLan = new SourisAdapte(Lan, "res/clic.wav");
		SourisAdapte sourisQuit = new SourisAdapte(Quit, "res/clic.wav");
		SourisAdapte sourisRegles = new SourisAdapte(Regles, "res/clic.wav");
		SourisAdapte sourisFr = new SourisAdapte(FR, "res/clic.wav");
		SourisAdapte sourisEn = new SourisAdapte(EN, "res/clic.wav");
		
		NewGame.addMouseListener(sourisNewGame);
		Charger.addMouseListener(sourisCharger);
		Lan.addMouseListener(sourisLan);
		Quit.addMouseListener(sourisQuit);
		Regles.addMouseListener(sourisRegles);
		FR.addMouseListener(sourisFr);
		FR.setBorder(BorderFactory.createEmptyBorder());
		FR.setContentAreaFilled(false);
		EN.addMouseListener(sourisEn);
		EN.setBorder(BorderFactory.createEmptyBorder());
		EN.setContentAreaFilled(false);

		NewGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				// Les trois lignes du dessous permettent d'ouvrir une nouvelle fenêtre
				// frame.dispose();
				// // Action exécutée lors du clic sur le bouton
				// openNewPage();

				// Les lignes du dessous permettent de "clear" la fenetre actuelle
				Page_NewGame(centrePanel); // Boutons du milieu
				Page_NewGame(bottomPanel);	// Boutons des langues
				// Page_NewGame(bottomRightPanel);
				// Page_NewGame(bottomLeftPanel);
				JRadioButton joueur = new JRadioButton("Joueur 1");
			}
		});

		frame.setVisible(true);
    }
}
