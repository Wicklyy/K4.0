package Reseau;
import java.net.*;
import java.io.*;

public class Client {
    Socket socket;
    
    
    public Client(String Connection){
        try{
            String[] split = Connection.split(":");
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            socket = new Socket(host,port);
            System.out.println("connected to server Transfer");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            port = Integer.parseInt(in.readLine());
            socket.close();
            socket = new Socket(host,port);
            System.out.println("connected to Game server");
        }
        catch(Exception e){e.getMessage();}
    }

    public void port(){
        System.out.println(socket.getPort());
    }
}
