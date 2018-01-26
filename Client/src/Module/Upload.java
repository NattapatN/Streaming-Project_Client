/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataOutputStream;
import java.io.File;
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
public class Upload extends Thread{

    Socket socket;
    File file;
    int bufferSize;

    public Upload(Socket socket, File file,int bufferSize) {
        this.bufferSize = bufferSize;
        this.socket = socket;
        this.file = file;
    }
    
    public void run(){
        System.out.println("Upload cache from "+socket.getInetAddress());
        FileInputStream fis = null;
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            fis = new FileInputStream(file.getName());
            byte[] buffer = new byte[bufferSize];
            while (0 <= fis.read(buffer)){
                dos.write(buffer);
            }
            fis.close();
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
