/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Sync;

import Module.ConnectServer;
import java.util.ArrayList;

/**
 *
 * @author NattapatN
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Client Start");
        int bufferSize =1024*1024;
        
        ReadNIC rnic = new ReadNIC();
        ArrayList<String> nic = rnic.getNIC();
        
        System.out.println("Connecting...");
        ConnectServer con = new ConnectServer("192.168.2.1",9000,nic.size());
        int newPort = con.getNewPort(bufferSize);
        System.out.println("Connected new port : "+newPort);
        
    }
}
