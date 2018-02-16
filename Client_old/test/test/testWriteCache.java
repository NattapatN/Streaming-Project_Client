/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Client_Main.Client;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class testWriteCache {
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter File Name : ");
        String fileName = scan.nextLine();
        File file = new File("media/" + fileName);
        
        //Connect NIC 1 & 2

        //send Steaming
        FileOutputStream filePart;
        try {
            InputStream in = new FileInputStream(file);
            byte[] buffer = new byte[1024 * 1024 * 1]; //1MB
            int bytesRead = in.read(buffer);
            while (bytesRead != -1) {
                filePart = new FileOutputStream(new File("media/test.mp4.cache"));
                filePart.write(buffer);
                filePart.flush();
                filePart.close();
                
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
