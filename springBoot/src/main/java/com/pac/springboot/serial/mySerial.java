package com.pac.springboot.serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class mySerial
{
    public mySerial()
    {
        super();
    }

    public void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(38400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                SerialReader sr = new SerialReader(in);
                Thread read = new Thread(sr);
                OutputStream out = serialPort.getOutputStream();
                SerialWriter sw = new SerialWriter(out);
                Thread writer = new Thread(sw);
                System.out.println("start");
                read.start();
                writer.start();
                //read.join();
                //System.out.println("fu"+sr.read_Frame);
                //serialPort.close();
                //return sr.read_Frame;
                //(new Thread(new SerialReader(in))).start();
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
                //return "error";
            }
        }
        //return "error";
    }

    public static void main ( String[] args )
    {
        try
        {
            (new mySerial()).connect("COM1");
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

