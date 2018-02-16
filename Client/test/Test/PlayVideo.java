/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author NattapatN
 */
public class PlayVideo {

    public static void main(String[] args) {
        
        ted t = new ted();
        t.start();

//        emp.prepareMedia(file);
//
//        emp.play();
    }
}

class ted extends Thread {

    public ted() {
    }

    public void run() {
        JFrame f = new JFrame();
        f.setLocation(100, 50);
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Canvas c = new Canvas();
        c.setBackground(Color.black);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        p.add(c);
        f.add(p);

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        MediaPlayerFactory mpf = new MediaPlayerFactory();

        EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));

        emp.setVideoSurface(mpf.newVideoSurface(c));

        MediaListPlayer player = mpf.newMediaListPlayer();
        player.setMediaPlayer(emp);
//        //full Screen
//        emp.toggleFullScreen();
        //hide cursor
        emp.setEnableMouseInputHandling(false);
        //disablc keyboard
        emp.setEnableKeyInputHandling(false);

//        String file = "media/test.mp4";
        //prepare media file
        MediaList playlist = mpf.newMediaList();
        playlist.addMedia("media/output1.mp4");
        
        player.setMediaList(playlist);
        player.setMode(MediaListPlayerMode.DEFAULT);
        player.play();
        
        try {
            sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ted.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        playlist.addMedia("media/output2.mp4");
        player.playItem(1);
        playlist.addMedia("media/output3.mp4");
        playlist.addMedia("media/output4.mp4");
        
    }
}
