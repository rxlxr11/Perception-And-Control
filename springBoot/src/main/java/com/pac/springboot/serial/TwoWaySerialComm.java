package com.pac.springboot.serial;

import com.pac.springboot.serial.Frame.CompressorConFrame;
import com.pac.springboot.serial.Frame.DrawerConFrame;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class TwoWaySerialComm
{
    public TwoWaySerialComm()
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

                //InputStream in = serialPort.getInputStream();
                //(new Thread(new SerialReader(in))).start();
                OutputStream out = serialPort.getOutputStream();
 //               (new Thread(new SerialWriter(out))).start();
                SerialWriter sw = new SerialWriter(out);
                //CompressorConFrame compressorConFrame = new CompressorConFrame(true);
                //SerialWriter.frame = compressorConFrame.encapsulate();
                boolean [] booleans ={true,true,true,true,true,true,true,true,true,true};
                DrawerConFrame drawerConFrame = new DrawerConFrame(booleans);
                SerialWriter.frame = drawerConFrame.encapsulate();
                SerialWriter.flag = true;
                System.out.println("lalal");
                Thread outPut =new Thread(sw);
                outPut.start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public static void main ( String[] args )
    {
        try
        {
            (new TwoWaySerialComm()).connect("COM1");
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

