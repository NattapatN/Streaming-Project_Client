/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class SplitFile {
    
    public static void main(String [] args){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("media/test.mp4");
            int size = 1024;
            byte buffer[] = new byte[size];
            int count = 0;
            while (true) {
                int i = fis.read(buffer, 0, size);
                if (i == -1)
                    break;
                
                String filename = "media/testx" + count;
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(buffer, 0, i);
                fos.flush();
                fos.close();
                
                ++count;
            }   } catch (FileNotFoundException ex) {
            Logger.getLogger(SplitFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SplitFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(SplitFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
