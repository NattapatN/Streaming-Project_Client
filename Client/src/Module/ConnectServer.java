/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.DataInputStream;
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
public class ConnectServer {

    String address;
    int port;
    NetworkInterface nic;

    public ConnectServer(NetworkInterface nic, String add, int por) {
        this.nic = nic;
        address = add;
        port = por;
    }

    public int getNewPort() {
        int newport = 0;
        try {
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(nic.getName(), 0));
            socket.connect(new InetSocketAddress(address, port));

            DataInputStream in = new DataInputStream(socket.getInputStream());
            newport = in.readInt();

        } catch (IOException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newport;
    }

}
