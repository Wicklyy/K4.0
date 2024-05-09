//import java.net.*;
import Reseau.*;

public class test {
    public static void main(String[] args){
        try{
            int nbClient = 4;
            Server serv = new Server(nbClient);
            int port = serv.initSockets();
            serv.initConnection();
            for(int i = 0; i < nbClient; i++){
                Client client = new Client("localhost:"+ port);
                client.port();
            }
            serv.wait();
            serv.close();
        }catch(Exception e){}
    }
}
