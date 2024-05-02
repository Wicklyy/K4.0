package src.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SourisAdapte extends MouseAdapter {
    private Color originalBackgroundColor;
    private Color originalForegroundColor;
    private JButton bouton;
    private Clip audioClip;

    public SourisAdapte(JButton bouton, String audioFilePath) {
        this.bouton = bouton;
        // Sauvegarder l'état d'origine du bouton
        this.originalBackgroundColor = bouton.getBackground();
        this.originalForegroundColor = bouton.getForeground();

        // Charger le fichier audio
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Changer la couleur du bouton
        bouton.setBackground(Color.YELLOW);
        bouton.setForeground(Color.BLACK);
        
        // Jouer le son dans un thread séparé
        Thread t=new Thread() {
            public void run(){
                if (audioClip != null && !audioClip.isRunning()) {
                    audioClip.setFramePosition(0); // Repositionner le clip au début
                    audioClip.start(); // Jouer le son
                }
            }

        };
        t.start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Restaurer l'état d'origine du bouton
        bouton.setBackground(originalBackgroundColor);
        bouton.setForeground(originalForegroundColor);
    }
}
