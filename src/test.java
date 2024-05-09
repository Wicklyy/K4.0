//import java.net.*;
import Reseau.*;

public class test {
    public static void main(String[] args){
        try{
            Server serv = new Server(4);
            serv.initCon();
            serv.showPort();
            serv.close();
        }catch(Exception e){}
    }
}
