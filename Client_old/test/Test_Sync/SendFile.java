/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Sync;

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
public class SendFile {
    
    public SendFile (){
    }
    
    public void send(String soc,String server,int port,byte[]buffer){
        DataOutputStream dos = null;
        try {
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(soc, 0));
            socket.connect(new InetSocketAddress(server, port));
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(buffer);
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(Test_Sync.SendFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dos.close();
            } catch (IOException ex) {
                Logger.getLogger(Test_Sync.SendFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(soc+" "+server+" "+port);
    }
    
    
}
