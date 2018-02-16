/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ConnectServer {
    
    String server;
    int port;
    int link;
    
    public ConnectServer(String server,int port,int link){
        this.server = server ;
        this.port=port;
        this.link =link;
    }
    
    public int getNewPort(int bufferSize){
        int newPort =0;
        try {
            Socket socket= new Socket(server,port);
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            newPort=in.readInt();
            out.writeInt(link);
            out.writeInt(bufferSize);
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newPort;
    }
    
}
