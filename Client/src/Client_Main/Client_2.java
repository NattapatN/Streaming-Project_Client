/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import Module.ConnectServer;
import Module.ReadNIC;
import Module.SendFile;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Client_2 {

    public static void main(String[] args) {
        int bufferSize = 1 * (1024 * 1024); // 1 MB
        Scanner scan = new Scanner(System.in);

        ReadNIC read = new ReadNIC();
        ArrayList<String> nic = read.getNIC();

        //Binding Socket
        Socket[] socket = new Socket[nic.size()];

        //get Sever address and port
        System.out.println("[ Client ]");
        System.out.print("Enter Server Address : ");
        String server = scan.next();
        System.out.print("Enter Server Port : ");
        int port = scan.nextInt();
        System.out.print("Enter file name : ");
        String filename = scan.next();

        System.out.println();
        System.out.println("Connecting...");
        File file = new File("media/" + filename);
        ConnectServer con = new ConnectServer(server, port, nic.size());
        int newPort = con.getNewPort(file, bufferSize);
        System.out.println("Connected");
        System.out.println();
        System.out.println("You have " + nic.size() + " network connection.");

        for (int i = 0; i < nic.size(); i++) {
            try {
                socket[i] = new Socket();
                socket[i].bind(new InetSocketAddress(nic.get(i), 0));
                socket[i].connect(new InetSocketAddress(server, newPort));
            } catch (IOException ex) {
                Logger.getLogger(Client_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        SendFile sendFile = new SendFile(socket[0],"media/"+filename);
        sendFile.start();
    }

}
