package Frame;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.example.TwoWaySerialComm2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TwoWaySerialComm
{
    public TwoWaySerialComm()
    {
        super();
    }

    void connect ( String portName ) throws Exception
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
                (new Thread(new SerialReader(in))).start();
                OutputStream out = serialPort.getOutputStream();
                (new Thread(new SerialWriter(out))).start();
                SerialWriter sw = new SerialWriter(out);
                sw.setFrame("ff");
                Thread outPut =new Thread(sw);
                outPut.start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    /** */
    public static class SerialReader implements Runnable
    {
        InputStream in;

        public SerialReader ( InputStream in )
        {
            this.in = in;
        }

        public void run ()
        {
            System.out.println("start");
            byte[] buffer = new byte[44];
            int len = buffer.length;
            int readBytes=0;
            try
            {
                while ( readBytes < len )
                {

                    int read = in.read(buffer, readBytes, len - readBytes);

                    //判断是不是读到了数据流的末尾 ，防止出现死循环。

                    if (read == -1) {

                        break;

                    }

                    readBytes += read;
                    String s = Tool.bytesToHexString(buffer,len);
                    System.out.println(readBytes+s);

                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    /** */
    public static class SerialWriter implements Runnable
    {
        OutputStream out;

        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }

        private String frame;

        public void setFrame(String frame) {
            this.frame = frame;
        }

        public void run ()
        {
            try
            {
                byte[] data = Tool.hexStringToBytes(this.frame);
//                int c = 0;
//
//                while ( ( c = System.in.read()) > -1 )
//                {
                    this.out.write(data);
//                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
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
