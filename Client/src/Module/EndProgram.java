/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class EndProgram {

    public EndProgram() {
        try {
            //end program
            System.out.print("Press \"c\" to Exit program. : ");
            while (System.in.read() != (int) 'c') {
                Thread.sleep(100); //Let the CPU do more worthwhile things for 0.1 seconds.
            }
        } catch (IOException ex) {
            Logger.getLogger(EndProgram.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EndProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
