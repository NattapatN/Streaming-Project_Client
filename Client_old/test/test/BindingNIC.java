/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author NattapatN
 */
public class BindingNIC {
    
    public static void main(String [] args) throws IOException{
        
        System.out.println();
        System.out.println("connecting...");
        System.out.println();
        
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress("192.168.2.129", 0));
        socket.connect(new InetSocketAddress("192.168.2.1", 9000));
        
        Socket socket1 = new Socket();
        socket1.bind(new InetSocketAddress("192.168.2.130", 0));
        socket1.connect(new InetSocketAddress("192.168.2.1", 9001));
        
        
    }
    
}
