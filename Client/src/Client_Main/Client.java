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
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
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
        File file = new File("media/" + fileName);

        Socket[] socket = new Socket[nic.size()];
        //connect server
        for (int i = 0; i < nic.size(); i++) {
            try {
                socket[i].bind(new InetSocketAddress(nic.get(i).getName(), 0));
                socket[i].connect(new InetSocketAddress(server, newPort + i));
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //send Steaming
        FileOutputStream filePart;
        try {
            InputStream in = new FileInputStream(file);
            byte[] buffer = new byte[bufferSize]; //1MB
            int bytesRead = in.read(buffer);
            int count = 0;
            while (bytesRead != -1) {
                filePart = new FileOutputStream(new File("media/test.mp4.cache"));
                filePart.write(buffer);
                filePart.flush();
                filePart.close();

                //Send File
                file = new File("media/test.mp4.cache");
                Upload up=new Upload(socket[count%nic.size()],file,bufferSize,count);
                up.start();
                
                count++;

                bytesRead = in.read(buffer);
            }
            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
