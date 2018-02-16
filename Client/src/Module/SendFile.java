/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class SendFile extends Thread{
    String nic;
    String server;
    int port;
    String filename;
    long start;
    
    public SendFile(String nic,String server,int port,String filename,long start){
        this.nic=nic;
        this.server = server;
        this.port=port;
        this.filename=filename;
        this.start = start;
    }
    
    public void run(){
        try {
            File file = new File(filename);
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(nic, 0));
            socket.connect(new InetSocketAddress(server, port));
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(filename);
            byte [] data = file.getName().getBytes("UTF-8");
            dos.writeInt(data.length);
            dos.write(data);
            dos.writeLong(file.length());
            
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close();
            dos.close();
            long end = System.currentTimeMillis()-start;
            System.out.println("Send File Complete!! : "+end+" ms.");
        } catch (IOException ex) {
            Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
