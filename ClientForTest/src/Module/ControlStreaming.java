/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class ControlStreaming extends Thread {

    int delay = 0;
    String[] speed;
    String server;
    int port;
    ArrayList<String> nic;

    public ControlStreaming(String[] speed, String server, int port, ArrayList<String> nic) {
        this.speed = speed;
        this.server = server;
        this.port = port;
        this.nic = nic;
        for (int i = 0; i < speed.length; i++) {
            delay += Integer.parseInt(speed[i]);
        }
        delay*=10;
    }

    public void run() {
        try {
            sleep(delay);
            SendStream[] sStream = new SendStream[nic.size()];
            for (int i = 0; i < nic.size(); i++) {
                sStream[i] = new SendStream();
            }
            long start = System.currentTimeMillis();
            int count = 0;
            while (System.currentTimeMillis() - start < delay) {
                if (new File("media/output" + count + ".mp4").exists()) {
                    synchronized (sStream[count%nic.size()]) {
                        sStream[count%nic.size()].send(server, port, nic.get(count % nic.size()), "media/output" + count + ".mp4");
                        sleep(Integer.parseInt(speed[count % nic.size()]));
                    }
//                    System.out.println("Readfile : "+count);
                    sleep(Integer.parseInt(speed[count % nic.size()])*10);
                    count++;
                    start=System.currentTimeMillis();
                } else {
                    sleep(100);
                }
            }
            System.out.println("end Send Streaming");
        } catch (InterruptedException ex) {
            Logger.getLogger(ControlStreaming.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
