package Client_Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class concatenate extends Thread {
    
    String fileName;
    public concatenate(String fileName){this.fileName = fileName;}
    
    public void run()  {
        try {
            String[] cmd = {"C:\\ffmpeg\\bin\\ffmpeg", "-i", "C:\\Users\\NattapatN\\Documents\\GitHub\\Streaming-Project_Client\\Client\\media\\cacheVideo.mp4", "-i", "C:\\Users\\NattapatN\\Documents\\GitHub\\Streaming-Project_Client\\Client\\media\\cacheSound.aac", "-acodec", "copy", "-vcodec", "copy", "C:\\Users\\NattapatN\\Documents\\GitHub\\Streaming-Project_Client\\Client\\media\\"+fileName};
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(concatenate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
