package com.pac.springboot.dao;

import com.pac.springboot.pojo.data;
import com.pac.springboot.pojo.result;
import com.pac.springboot.serial.Frame.StatusFrame;
import com.pac.springboot.serial.SerialReader;
import com.pac.springboot.serial.TwoWaySerialComm;
import com.pac.springboot.serial.mySerial;
import gnu.io.SerialPort;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Stack;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
@Slf4j
@Repository
public class PerDao {
    data d ;

    public result test(){
        float temp = (float)36.5;
        boolean[] drawer ={true,true,true,true,true,true,true,true,true,true,true};
        float[] temp_a = {temp,temp,(float) 37.5,temp,temp};
        float[] temp_b = {temp,temp,(float) 37.5,temp,temp};
        d.setTemp(temp);
        d.setCompressor("01");
        //d.setSetting_array(temp_a);
        //d.setTemp_array(temp_b);
        d.setSetting_temp(temp);
        d.setDrawer(drawer);
        result r = new result();
        return r;
    }

    public String getFrame(){
        try
        {
            log.info("成功没说一声");
            return SerialReader.read_Frame;
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "d";
    }

    public StatusFrame getStatusFrame(){
        try
        {

            log.info("成功没说一声");

            StatusFrame statusFrame = new StatusFrame();
            statusFrame.decapsulate(SerialReader.read_Frame);
            return statusFrame;
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new StatusFrame();
    }

}
