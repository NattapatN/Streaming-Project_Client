/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.File;
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
public class Split {

    File file;

    public Split(File file) {
        this.file = file;
    }

    public void get() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getPath());
            long size = file.length(); //Byte
            int piece = 0;
            if (size % 2 != 0) {
                piece = 1;
            }
            byte buffer[] = new byte[(int) (((size / 2) + piece) * 8)];
            int count = 0;
            while (true) {
                int i = fis.read(buffer, 0, (int) (size / 2) + piece);
                if (i == -1) {
                    break;
                }
                String filename = "media/testx" + count;
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(buffer, 0, i);
                fos.flush();
                fos.close();
                ++count;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Split.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Split.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
