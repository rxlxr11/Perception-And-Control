package com.pac.springboot.serial;

import com.pac.springboot.serial.Frame.Tool;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */
public class SerialWriter implements Runnable {
    OutputStream out;
    public static boolean flag = false;

    public SerialWriter(OutputStream out) {
        this.out = out;
    }

    public static String frame ;

    //public static void setFrame(String frame) {
        //this.frame = frame;
    //}

    public void run() {
        try {
            while (true)
            {
                if (flag){
                    byte[] data = Tool.hexStringToBytes(frame);
                    this.out.write(data);
                    flag = false;
                }

            }
//                int c = 0;
//
//                while ( ( c = System.in.read()) > -1 )
//                {

//                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
