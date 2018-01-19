/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

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
public class SendMeta {
    int bufferSize=1;
    Socket socket;
    public SendMeta(Socket socket){
        this.socket =socket;
    }
    
    public void send(String filename){
        
        try {
            File file =new File("media/"+filename);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(bufferSize);
            out.writeLong(file.length());
            byte[]data=file.getName().getBytes("UTF-8");
            out.writeInt(data.length);
            out.write(data);
            
            Upload up = new Upload(socket);
            up.up("test.mp4",bufferSize);
        } catch (IOException ex) {
            Logger.getLogger(SendMeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
