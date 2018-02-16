/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ConnectServerFile {
    
    String server;
    int port;
    int link;
    String filename;
    
    public ConnectServerFile(String server,int port,int link,String filename){
        this.server = server;
        this.port = port;
        this.link=link;
        this.filename = filename;
    }
    
    public int getNewPort(){
        int newPort = 0;
        try {
            Socket socket= new Socket(server,port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            newPort=in.readInt();
            out.writeInt(link);
            File file = new File (filename);
            byte [] data = file.getName().getBytes("UTF-8");
            out.writeInt(data.length);
            out.write(data);
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectServerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newPort;
    }
    
}
