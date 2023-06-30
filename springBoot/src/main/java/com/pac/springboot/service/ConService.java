package com.pac.springboot.service;

import com.pac.springboot.pojo.sysPara;
import com.pac.springboot.serial.Frame.CompressorConFrame;
import com.pac.springboot.serial.Frame.DrawerConFrame;
import com.pac.springboot.serial.Frame.SetSysParaFrame;
import com.pac.springboot.serial.Frame.SetTempFrame;
import com.pac.springboot.serial.SerialWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConService {
    public void sendCompressor(boolean b){
        log.info("绷不住了");
        log.error("i dont know");
        CompressorConFrame compressorConFrame = new CompressorConFrame(b);
        SerialWriter.frame = compressorConFrame.encapsulate();
/*        System.out.println("------------------------------------------------------------------");
        System.out.println(SerialWriter.frame);
        System.out.println("------------------------------------------------------------------");
*/
        SerialWriter.flag = true;
    }
    public void sendDrawer(boolean[] b){
        DrawerConFrame drawerConFrame = new DrawerConFrame(b);
        SerialWriter.frame = drawerConFrame.encapsulate();
        SerialWriter.flag = true;
    }
    public void sendPara(sysPara sp){
        SetSysParaFrame sysParaFrame = new SetSysParaFrame(sp);
        SerialWriter.frame = sysParaFrame.encapsulate();
        SerialWriter.flag = true;
    }
    public void sendTemp(String s){
        float a = Float.valueOf(s);
        log.error("给我看看"+a);
        SetTempFrame tempFrame = new SetTempFrame(a);
        SerialWriter.frame = tempFrame.encapsulate();
        SerialWriter.flag = true;
    }
}
