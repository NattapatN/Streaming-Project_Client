/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class SendFile extends Thread {

    String soc;
    String filename;
    String server;
    int port;
    byte[] buffer;

    public SendFile(String soc, byte[] buffer, String server, int port) {
        this.soc = soc;
        this.buffer = buffer;
        this.server = server;
        this.port = port;
    }

    public void run() {
        DataOutputStream dos = null;
        try {
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(soc, 0));
            socket.connect(new InetSocketAddress(server, port));
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(buffer);
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
