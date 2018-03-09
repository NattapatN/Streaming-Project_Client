/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import com.github.sarxos.webcam.Webcam;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

/**
 *
 * @author NattapatN
 */
public class Record extends Thread {

    Dimension size;
    Webcam wCam;
    String[] time;
    public boolean isStop = false;
    int count = 0;
    JButton liveButton;

    public Record(Dimension size, Webcam wCam, String[] time, JButton liveButton) {
        this.size = size;
        this.wCam = wCam;
        this.time = time;
        this.liveButton = liveButton;
    }

    public void run() {
        System.out.println("[File No.]\t[File write]\t[Time Stamp]\t[Total Time]");
        long startStreaming = System.currentTimeMillis();
        while (System.currentTimeMillis() - startStreaming < 30000) {
            IMediaWriter writer = ToolFactory.makeWriter("media/output" + count + ".mp4");
            writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);
            long start = System.currentTimeMillis();
            for (int i = 0; System.currentTimeMillis() - start <= Integer.parseInt(time[count % time.length]) * 10; i++) {
                BufferedImage image = ConverterFactory.convertToType(wCam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
                IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
                IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
                frame.setKeyFrame(i == 0);
                frame.setQuality(0);
                writer.encodeVideo(0, frame);
            }
            Thread threadWrite = new Thread() {
                public void run() {
                    int c = count - 1;
                    long startWirte = System.currentTimeMillis();
                    writer.close();
                    double writetime = (double) (System.currentTimeMillis() - startWirte) / 1000;
                    double thisTime = (double) (System.currentTimeMillis() - startStreaming) / 60000;
                    System.out.println(c + "\t\t" + String.format("%.3f", writetime) + " s.\t" + startWirte + "\ttime : " + String.format("%.3f", thisTime)+" m.");
                }
            };
            threadWrite.start();
            count++;
        }
        liveButton.setText("End");
    }

}
