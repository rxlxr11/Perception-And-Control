package com.pac.springboot.serial;

import com.pac.springboot.serial.Frame.Tool;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** */
public class SerialReader implements Runnable
{
    private InputStream in;
    //public String portName = "COM1";
    public static String read_Frame;
    public SerialReader(InputStream in )
    {
        this.in = in;
        this.read_Frame = "FFFF2C010101FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000FFFFFFFF";
    }

    public void run ()
    {

        //try {
            //CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(this.portName);
           // if (portIdentifier.isCurrentlyOwned()) {
            //    System.out.println("Error: Port is currently in use");
            //} else {
               // CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
              //  if (commPort instanceof SerialPort) {
               //     SerialPort serialPort = (SerialPort) commPort;
                //    serialPort.setSerialPortParams(38400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //} else {
                 //   System.out.println("Error: Only serial ports are handled by this example.");
                //}
            //}
        //}catch (Exception e){
           // e.printStackTrace();
        //}
        System.out.println("start1");
        byte[] buffer = new byte[44];
        int len = buffer.length;
        int readBytes=0;

        try
        {
            while ( readBytes < len )
            {

                int read = in.read(buffer, readBytes, len - readBytes);

                //判断是不是读到了数据流的末尾 ，防止出现死循环。

                //if (read == -1) {

                  //  break;

                //}

                readBytes += read;
                String s = Tool.bytesToHexString(buffer,readBytes);
                StringBuilder sb=new StringBuilder(s);
                System.out.println(read_Frame);
                if (s.length() >= 4) {
                    if (readBytes == 44 || sb.substring(s.length() - 4, s.length()).toString().equals("FFF7")) {
                        if (readBytes == 44){
                            read_Frame = Tool.bytesToHexString(buffer, len);}
                        readBytes = 0;
                    }
                }

            }

            //System.out.println("睁大你的眼睛看看"+this.read_Frame);
            readBytes = 0;
            //notifyAll();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
