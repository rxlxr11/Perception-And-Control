package com.pac.springboot;

import com.pac.springboot.serial.SerialReader;
import com.pac.springboot.serial.SerialWriter;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;
import java.io.OutputStream;

@SpringBootApplication
@EnableScheduling
public class MyApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 在项目启动后执行的操作
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
        CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
        if ( commPort instanceof SerialPort)
        {
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(38400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

            InputStream in = serialPort.getInputStream();
            OutputStream out = serialPort.getOutputStream();
            SerialReader sr = new SerialReader(in);
            Thread read = new Thread(sr);
            SerialWriter sw = new SerialWriter(out);
            Thread writer = new Thread(sw);
            System.out.println("start");
            read.start();
            writer.start();
        }
        System.out.println("项目启动后执行的操作");
    }
}
