/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Module.*;
import java.util.ArrayList;

/**
 *
 * @author NattapatN
 */
public class MainProgram {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("config.txt");
            BufferedReader br = new BufferedReader(fr);

            String server = br.readLine();
            int port = Integer.parseInt(br.readLine());
            ReadNIC read = new ReadNIC();
            ArrayList<String> nic = read.getNIC();

            SendStream[] sStream = new SendStream[nic.size()];
            for (int i = 0; i < nic.size(); i++) {
                sStream[i] = new SendStream();
            }

            //------------------<Print out>---------------------//
            System.out.println("Server : " + server);
            System.out.println("Port : " + port);
            //------------------<Print out>---------------------//
            
            long startProgram = System.currentTimeMillis();
            int count=0;
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainProgram.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
