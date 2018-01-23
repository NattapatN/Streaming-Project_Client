/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import Module.Connect;
import Module.ConnectServer;
import Module.EndProgram;
import Module.ReadNIC;
import Module.SendData;
import java.io.File;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author NattapatN
*
 */
public class Client {

    public static void main(String[] args) {
        
        ReadNIC read = new ReadNIC();
        ArrayList<NetworkInterface> nic = read.getNIC();
        
        Scanner scan = new Scanner(System.in);
        System.out.println("--[ Client ]--");
        System.out.print("Enter Server Address : ");
        String server = scan.nextLine();
        System.out.print("Port : ");
        int iport = scan.nextInt();
        
        System.out.println();
        System.out.println("Connecting...");
        System.out.println();
        
        ConnectServer a = new ConnectServer(nic.get(0),server, iport);
        int newPort = a.getNewPort();
        
        System.out.println();
        System.out.println("Connected");
        System.out.println();
        
        Connect con = new Connect();
        //Connect socket 1
        Socket socket1 = con.con(nic.get(0), server, newPort);

        //Connect socket 2
        Socket socket2 = con.con(nic.get(1), server, newPort);
        
        Socket[] soc = new Socket[2];
        soc[0]=socket1;
        soc[1]=socket2;
        
        SendData sdata = new SendData(soc);
        sdata.send(new File("media/test.mp4"));
        
        //End program
        new EndProgram();
    }
}
