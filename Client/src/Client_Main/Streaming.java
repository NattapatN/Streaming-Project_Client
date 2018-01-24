/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Streaming {
    String fileName;
    int bufferSize;
    ArrayList<NetworkInterface> nic;
    public Streaming (String fileName,int bufferSize,ArrayList<NetworkInterface> nic){
        this.nic=nic;
        this.fileName =fileName;
        this.bufferSize = bufferSize;
    }
    
    public void start(){
        
        File file = new File("media/" + fileName);
        
        //send Steaming
        FileOutputStream filePart;
        try {
            InputStream in = new FileInputStream(file);
            byte[] buffer = new byte[1024 * 1024 * bufferSize]; //1MB
            int bytesRead = in.read(buffer);
            while (bytesRead != -1) {
                filePart = new FileOutputStream(new File("media/test.mp4.cache"));
                filePart.write(buffer);
                filePart.flush();
                filePart.close();
                System.out.println("write cache : "+buffer.length );
                
                bytesRead = in.read(buffer);
            }
            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
