/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import Module.ConnectServer;
import Module.ReadNIC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1; //1MB
        Scanner scan = new Scanner(System.in);

        ReadNIC read = new ReadNIC();
        ArrayList<NetworkInterface> nic = read.getNIC();

        System.out.println("[ Client ]");
        System.out.print("Enter Server Address : ");
        String server = scan.nextLine();
        System.out.print("Enter Server Port : ");
        int port = scan.nextInt();
        
        System.out.println();
        System.out.println("Connecting...");

        //Connect Server
        ConnectServer con = new ConnectServer(server, port, nic.size());
        int newPort = con.getNewPort();

        System.out.println("Connected");
        System.out.println();
        
        //Streaming (input streaming or file)
        System.out.print("Enter File Name : ");
        String fileName = scan.next();
        
        //streaming1 
        Streaming stream =new Streaming(fileName,bufferSize,nic);
        stream.start();
    }

}
