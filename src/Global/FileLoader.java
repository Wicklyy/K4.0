package Global;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;


public class FileLoader {
    static HashMap<String,Clip> openedSoundMap=new HashMap<>();
    static HashMap<String,Image> openedImageMap=new HashMap<>();

    public static Clip getSound(String path)throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (openedSoundMap.containsKey(path)){
            return openedSoundMap.get(path);
        }else{
            return null;
            /*try {
                //AudioInputStream audioStream = AudioSystem.getAudioInputStream(FileLoader.class.getClassLoader().getResource(path));
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(FileLoader.class.getResourceAsStream(path));
                Clip audioClip = AudioSystem.getClip();
                audioClip = null;
                audioClip.open(audioStream);
                openedSoundMap.put(path, audioClip);
                return audioClip;
                
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                throw e;
            }*/
        }
    }

    public static InputStream getSave(String path){
        File textfile=new File(path);
        try{
        return new FileInputStream(textfile);
        }catch(Exception e){
            System.err.println( "File " + path + " not found");
            return null;
        }


    }
    public static Image getImage(String path) {
        if (openedImageMap.containsKey(path)) {
            return openedImageMap.get(path);
        } else {
            // String chaine = path;
            try (InputStream is = FileLoader.class.getResourceAsStream(path)) { // Try-with-resources
                if (is != null) {
                    Image out = ImageIO.read(is);
                    openedImageMap.put(path, out);
                    return out;
                } else {
                    System.err.println("Ressource introuvable : " + path);
                    // Retournez une image par défaut ou signalez l'erreur
                }
            } catch (IOException e) {
                System.err.println("Erreur lors du chargement de l'image : " + path);
                e.printStackTrace();
            }
        }
        return null; // Ou une image par défaut
    }
    
}
