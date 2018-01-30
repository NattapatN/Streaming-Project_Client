/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Sync2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int bufferSize = 1024 * 1024; //1MB
        ArrayList<String> nic;
        Test_Sync.ReadNIC rn = new Test_Sync.ReadNIC();
        nic = rn.getNIC();
        /*------------------------------------------------------*/
        System.out.print("Enter Server Address : 127.0.0.1");
//        String server = scan.next();
        String server = "127.0.0.1";
        System.out.print("Enter Server Port : 9000");
//        int port = scan.nextInt();
        int port = 9000;
        /*------------------------------------------------------*/
        System.out.println();
        System.out.println("Connecting...");
        Module.ConnectServer con = new Module.ConnectServer(server, port, 1);
        int newPort = con.getNewPort(bufferSize);
        System.out.println("Connected");
        System.out.println();
        /*------------------------------------------------------*/
        SendFile[] sf = new SendFile[1];
        for (int i = 0; i < 1; i++) {
            sf[i] = new SendFile();
        }
        
        int count=0;
        try {
            FileInputStream fis = new FileInputStream("media/test.mp4");
            byte[] buffer = new byte[bufferSize];
            while (fis.read(buffer) > 0) {
                System.out.println(count);
                
                sf[0].send("",server,port,buffer);
                
                count = (count+1)%1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test_Sync.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test_Sync.Main.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

}
