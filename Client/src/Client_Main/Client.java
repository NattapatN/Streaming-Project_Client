/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import Module.ConnectServer;
import Module.ReadNIC;
import Module.Upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class Client {

    public static void main(String[] args) throws InterruptedException {
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
        ConnectServer con = new ConnectServer(server, port,nic.size());
        int newPort = con.getNewPort(file, bufferSize);
        System.out.println("Connected");
        System.out.println();

        System.out.println("You have " + nic.size() + " network connection.");
//
//        byte[] buffer;
//        int fileSize = (int) file.length();
//        int readd = 0, use = 0;
//        FileOutputStream filePart;
//        try {
//            //write cache
//            FileInputStream inputStream = new FileInputStream(file);
//            while (fileSize > 0) {
//                if (fileSize <= bufferSize) {
//                    bufferSize = fileSize;
//                }
//                buffer = new byte[bufferSize];
//                readd = inputStream.read(buffer, 0, bufferSize);
//                fileSize -= readd;;
//                filePart = new FileOutputStream(new File("Cache"+file.getName() + ".cache"+use));
//                filePart.write(buffer);
//                filePart.flush();
//                filePart.close();
//                buffer = null;
//                filePart = null;
//
////                conect
//                Socket socket1 =new Socket();
//                socket1.bind(new InetSocketAddress(nic.get(use%nic.size()), 0));
////                while(socket1.isConnected()){sleep(100);}
//                socket1.connect(new InetSocketAddress(server, newPort));
//                System.out.println("Connect : "+use);
////                Upload upload = new Upload(socket1, new File("Cache"+file.getName() + ".cache"+use), bufferSize);
////                upload.run();
//                use ++;
//
//            }
//       192.168.2.1
    }

}
