/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Sync2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
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
        System.out.println("Enter Server Address : 192.168.2.1");
//        String server = scan.next();
        String server = "192.168.2.1";
        System.out.println("Enter Server Port : 9000");
//        int port = scan.nextInt();
        int port = 9000;
        /*------------------------------------------------------*/
        System.out.println();
        System.out.println("Connecting...");
        Module.ConnectServer con = new Module.ConnectServer(server, port, nic.size());
        int newPort = con.getNewPort(bufferSize);
        System.out.println("Connected");
        System.out.println();
        /*------------------------------------------------------*/
        SendFile[] sf = new SendFile[nic.size()];
        for (int i = 0; i < nic.size(); i++) {
            sf[i] = new SendFile();
        }

        int count = 0;
        String fileOut;
        try {
            FileInputStream fis = new FileInputStream("media/test.mp4");
            FileOutputStream out;
            byte[] buffer = new byte[bufferSize];
            while (fis.read(buffer) > 0) {
                fileOut = "media/cache" + count + ".mp4";
                out = new FileOutputStream(fileOut);
                out.write(buffer);
                out.flush();
                out.close();
                synchronized (sf[1]) {
                    ClientThread cThread = new ClientThread(nic.get(count % nic.size()), server, newPort, fileOut, sf[count % nic.size()]);
                    cThread.start();
                }
                sleep(1000);
                count++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test_Sync.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test_Sync.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

}
