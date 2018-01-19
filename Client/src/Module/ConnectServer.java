/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ConnectServer {
    String address;
    int port;
    
    public ConnectServer(String add,int por){
            address = add;
            port = por;
    }
    
    public int getNewPort(){
        int newport=0;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(address,port));
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            newport =in.readInt();
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newport;
    }
    
}
