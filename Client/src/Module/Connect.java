/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Connect {
    
    public Connect(){}
    
    public Socket con(NetworkInterface nic ,String server,int port){
        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress(nic.getName(), 0));
            socket.connect(new InetSocketAddress(server, port));
            
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socket;
    }
}
