/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Sync;

/**
 *
 * @author NattapatN
 */
public class ThreadSocket extends Thread{
    String soc;
    SendFile sf;
    String server;
    int port;
    byte[] buffer;
    
    public ThreadSocket(String soc,SendFile sf,String server,int port,byte[] buffer){
        this.soc = soc;
        this.sf=sf;
        this.server = server;
        this.port = port;
        this.buffer =buffer;
    }
    
    public void run(){
        synchronized(sf) {
            sf.send(soc,server,port,buffer);
      }
    }
    
}
