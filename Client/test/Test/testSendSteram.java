/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class testSendSteram {

    public static void main(String[] args) {
        SendThread send = new SendThread();
        send.start();
    }

}

class SendThread extends Thread {

    public SendThread() {
    }

    public void run() {
        ReadNIC readNic = new ReadNIC();
        ArrayList<String> nic = readNic.getNIC();
        SendStream[] sStream = new SendStream[nic.size()];
        for (int i = 0; i < 6; i++) {
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
            }
//            while(sStream[i % nic.size()].isAlive()){
//                try {
//                    sleep(100);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
            sStream[i % nic.size()] = new SendStream("192.168.2.1", 9000, nic.get(i % nic.size()), "media/output" + i + ".mp4");
            sStream[i % nic.size()].start();
            
        
        }
    }
}

class SendStream extends Thread {

    String server;
    int port;
    String nic;
    String filename;

    public SendStream(String server, int port, String nic, String filename) {
        this.server = server;
        this.port = port;
        this.nic = nic;
        this.filename = filename;
    }

    public void run() {
        try {
            File file = new File(filename);
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(nic, 0));
            socket.connect(new InetSocketAddress(server, port));

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(filename);
            dos.writeLong(file.length());

            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(SendStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
