/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class SendFile extends Thread {

    Socket socket;
    String filename;

    public SendFile(Socket socket, String filename) {
        this.socket = socket;
        this.filename = filename;
    }

    public void run() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(filename);
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dos.close();
            } catch (IOException ex) {
                Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
