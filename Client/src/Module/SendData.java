/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.File;
import java.net.Socket;

/**
 *
 * @author NattapatN
 */
public class SendData {
    
    Socket [] socket; 
    
    public SendData(Socket [] soc){socket = soc;}
    
    public void send(File file){
        //Send Meta
        SendMeta smeta = new SendMeta(socket[0],1); //buffersie = 1
        smeta.send(file);
        
        //Send Data
        
        
    }
    
    
    
}
