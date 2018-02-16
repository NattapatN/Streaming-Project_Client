/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;


/**
 *
 * @author NattapatN
 */
public class ClientThread extends Thread{
    String soc;
    String server;
    int port;
    String filename;
    SendFile sf;
    
    public ClientThread(String soc,String server,int port,String filename,SendFile sf){
        this.soc=soc;
        this.server=server;
        this.port= port;
        this.filename=filename;
        this.sf=sf;
    }
    
    public void run(){
        System.out.println("thread : "+filename+" start!!");
        sf.send(soc,server,port,filename);
    }
    
}
