/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import Module.ConnectServer;
import Module.ReadNIC;
import Module.SendFile;
import java.io.FileInputStream;
import java.io.IOException;
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
        ConnectServer con = new ConnectServer(server, port, nic.size());
        int newPort = con.getNewPort(bufferSize);
        System.out.println("Connected");
        System.out.println();
        System.out.println("You have " + nic.size() + " network connection.");

        //loop write
        int count=0;
        try {
            FileInputStream fis = new FileInputStream("media/" + filename);
            byte[] buffer = new byte[bufferSize];
            while (fis.read(buffer) > 0) {
                System.out.println(count);
                SendFile sendFile = new SendFile(nic.get(count), buffer, server, newPort);
                sendFile.start();
                count = (count+1)%nic.size();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client_2.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}
