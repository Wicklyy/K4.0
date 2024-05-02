package src.View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JFrame {
   private static final String IMAGE_PATH = "res/Back.png";
   private static final String MUSIC_PATH = "res/jazz.wav";

   // Constructeur
   public BackgroundPanel() {
       // Configurer la fenêtre
       
       setLocationRelativeTo(null); // Centrer la fenêtre

       
       ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel();
       backgroundPanel.setLayout(new BorderLayout()); // Utiliser BorderLayout pour le panneau
       backgroundPanel.setOpaque(false);
       // Utiliser le panneau personnalisé comme contenu de la JFrame
       setContentPane(backgroundPanel);
       jouerMusique();
   }

   // Classe personnalisée pour le panneau avec image en arrière-plan
   class ImageBackgroundPanel extends JPanel {
       private Image backgroundImage;

       // Constructeur
       public ImageBackgroundPanel() {
           // Charger l'image en arrière-plan
           backgroundImage = new ImageIcon(IMAGE_PATH).getImage();
       }

       // Redéfinir paintComponent pour dessiner l'image en arrière-plan
       @Override
       protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           if (backgroundImage != null) {
               // Dessiner l'image en arrière-plan
               g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
           }
       }
   }

   private void jouerMusique() {
        try {
            // Charger le fichier audio
            File audioFile = new File(MUSIC_PATH);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            // Jouer la musique
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}