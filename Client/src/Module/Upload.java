/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Upload {
    Socket socket;
    public Upload(Socket socket){
        this.socket =socket;
    }
    
    public void up(String file,int buffersize){
        FileInputStream fis = null;
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            fis = new FileInputStream("media/"+file);
            byte[] buffer = new byte[1024*1024*buffersize];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close();
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
