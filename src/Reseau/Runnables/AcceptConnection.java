package Reseau.Runnables;
import java.net.*;

public class AcceptConnection implements Runnable{
    ServerSocket ssocket;
    Socket socket;

    public AcceptConnection(ServerSocket ssocket, Socket socket){
        this.ssocket = ssocket;
        this.socket = socket;
    }
    @Override
    public void run(){
        try{socket = ssocket.accept();}
        catch(Exception e){e.getMessage();}
    }
}
